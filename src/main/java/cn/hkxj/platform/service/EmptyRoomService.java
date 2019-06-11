package cn.hkxj.platform.service;

import cn.hkxj.platform.pojo.EmptyRoom;
import cn.hkxj.platform.pojo.constant.Building;
import cn.hkxj.platform.pojo.timetable.CourseTimeTable;
import cn.hkxj.platform.pojo.Room;
import cn.hkxj.platform.pojo.timetable.RoomTimeTable;
import cn.hkxj.platform.utils.SchoolTimeUtil;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.datatype.guava.GuavaModule;
import com.google.common.collect.HashMultimap;
import lombok.extern.slf4j.Slf4j;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.IOException;
import java.io.StringWriter;
import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * @author junrong.chen
 * @date 2018/11/4
 */
@Slf4j
@Service("emptyRoomService")
public class EmptyRoomService {
    @Resource
    private RoomService roomService;
    @Resource
    private TimeTableService timeTableService;
    @Resource
    private RedisTemplate<String, String> redisTemplate;
    /**
     * 缓存当天有课教室和课程的映射
     */
    private static HashMultimap<Room, CourseTimeTable> scienceRoomTimeTable;
    private static HashMultimap<Room, CourseTimeTable> mainRoomTimeTable;
    private static int dayOfWeek;


    /**
     * 该方法为获取当天具体教学楼所有教室的课程情况
     *
     * @param building 教学楼
     * @return
     */
    public ArrayList<RoomTimeTable> getTodayEmptyRoomByBuilding(Building building) {
        ArrayList<RoomTimeTable> roomTimeTableList = new ArrayList<>();
        HashMultimap<Room, CourseTimeTable> tableMap = getTodayRoomTimeTableMap(building);
        for (Room room : tableMap.keySet()) {
            RoomTimeTable roomTimeTable = new RoomTimeTable();
            ArrayList<CourseTimeTable> courseTimeTables = new ArrayList<>(tableMap.get(room));
            courseTimeTables.sort(Comparator.comparing(CourseTimeTable::getOrder));
            roomTimeTable.setCourseTimeTable(courseTimeTables);
            roomTimeTable.setRoom(room);
            roomTimeTableList.add(roomTimeTable);
        }
        roomTimeTableList.sort((o1, o2) -> {
            if (o1.getRoom().getFloor().equals(o2.getRoom().getFloor())) {
                return o1.getRoom().getName().compareTo(o2.getRoom().getName());
            } else {
                return o1.getRoom().getFloor().compareTo(o2.getRoom().getFloor());
            }
        });

        return roomTimeTableList;
    }

    public List<RoomTimeTable> getTodayRoomTimeTable(Building building, int floor) {
        ArrayList<RoomTimeTable> roomTimeTableList = new ArrayList<>();
        HashMultimap<Room, CourseTimeTable> tableMap = getTodayRoomTimeTableMap(building);
        for (Room room : roomService.getRoomByBuildingAndFloor(building, floor)) {
            RoomTimeTable roomTimeTable = new RoomTimeTable();

            ArrayList<CourseTimeTable> courseTimeTables = new ArrayList<>(tableMap.get(room));
            //将教室对应的上课时间按顺序排序
            courseTimeTables.sort(Comparator.comparing(CourseTimeTable::getOrder));
            roomTimeTable.setRoom(room);
            roomTimeTable.setCourseTimeTable(courseTimeTables);
            roomTimeTableList.add(roomTimeTable);
        }
        //将教室按名称排序
        roomTimeTableList.sort(Comparator.comparing(o -> o.getRoom().getName()));
        return roomTimeTableList;
    }

    /**
     * 根据具体的时间获取具体教学楼教室的课程情况
     * 对已经查询过的日期的课表进行序列化存储到缓存中
     *
     * @param schoolWeek 教学周
     * @param dayOfWeek  星期
     * @param order      节次
     * @param building   教学楼
     * @param floor      楼层
     * @return
     */
    @SuppressWarnings(value = {"unchecked"})
    public List<RoomTimeTable> getRoomTimeTableByTime(int schoolWeek, int dayOfWeek, int order, Building building, int floor) throws java.io.IOException {
        ArrayList<RoomTimeTable> roomTimeTableList = new ArrayList<>();

        SimpleModule sm = new SimpleModule();
        //注册序列化，反序列化类。因为Jackson在序列化Map时，会把Map的Key对应的对象默认解析成字符串；但是这个字符串格式不是Json的格式
        // 通过添加自定义Map的键的序列化解析类和反序列化解析类，可以实现对Room对象Json序列化／反序列化
        sm.addKeySerializer(Room.class, new KeySerializer());
        sm.addKeyDeserializer(Room.class, new KeyDeSerializer());
        //注册到ObjectMapper中。
        ObjectMapper objectMapper = new ObjectMapper().registerModule(sm).registerModule(new GuavaModule());
        //具体时间设为redis的key
        String key = Integer.toString(schoolWeek) + Integer.toString(dayOfWeek);
        //教学楼的名字作为设为redis存储的map的hashKey
        String hashKey = building.name();

        //如果缓存中不存在查询日期的结果，就进行缓存,对于查询频繁的数据，更新他的过期时间
        if (!redisTemplate.opsForHash().hasKey(key, hashKey)) {
            redisTemplate.opsForHash().put(key, hashKey, objectMapper.writeValueAsString(getRoomTimeTableMapByTime(schoolWeek, dayOfWeek, building)));
            redisTemplate.expire(key, 30, TimeUnit.MINUTES);
        } else {
            redisTemplate.expire(key, 30, TimeUnit.MINUTES);
        }

        HashMultimap<Room, CourseTimeTable> tableMap = objectMapper.readValue((String) redisTemplate.opsForHash().get(key, hashKey), new TypeReference<HashMultimap<Room, CourseTimeTable>>() {
        });

        //想查询所有楼层时floor设为0
        for (Room room : floor == 0 ? roomService.getRoomByBuilding(building) : roomService.getRoomByBuildingAndFloor(building, floor)) {
            RoomTimeTable roomTimeTable = new RoomTimeTable();
            LinkedList<CourseTimeTable> courseTimeTables = new LinkedList<>();
            courseTimeTables.addAll(tableMap.get(room));
            //将教室对应的上课时间按顺序排序
            courseTimeTables.sort(Comparator.comparing(CourseTimeTable::getOrder));
            //在对节次有要求的查询情况下，对在该节次时有课的教室和课程进行筛选并进行移除
            boolean roomInOrder = false;
            if (order != 0) {
                for (CourseTimeTable courseTimeTable : courseTimeTables) {
                    if (courseTimeTable.getOrder() == order) {
                        roomInOrder = true;
                        break;
                    }
                }
            }
            if (!roomInOrder) {
                roomTimeTable.setRoom(room);
                roomTimeTable.setCourseTimeTable(courseTimeTables);
                roomTimeTableList.add(roomTimeTable);
            }
        }
        //将教室按名称排序
        roomTimeTableList.sort(Comparator.comparing(o -> o.getRoom().getName()));
        return roomTimeTableList;
    }

    /**
     * 根据具体的时间获取具体教学楼的教室时间表
     * 对指定了具体时间进行查询的情况下，用局部变量进行存储来确保线程安全性
     *
     * @param schoolWeek 教学周
     * @param dayOfWeek  星期
     * @param building   教学楼
     * @return
     */
    public HashMultimap<Room, CourseTimeTable> getRoomTimeTableMapByTime(int schoolWeek, int dayOfWeek, Building building) {


        try {
            HashMultimap<Room, CourseTimeTable> roomTimeTable = HashMultimap.create();
            for (CourseTimeTable timeTable : timeTableService.getTimeTableFromDB(schoolWeek, dayOfWeek)) {
                if (checkDistinct(timeTable.getDistinct())) {
                    Room room = roomService.getRoomByName(timeTable.getRoom().getName());
                    if (room.getArea() == building) {
                        roomTimeTable.put(room, timeTable);
                    }
                }
            }

            return roomTimeTable;
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("illegal building " + building.getChinese());
        }
    }

    private HashMultimap<Room, CourseTimeTable> getTodayRoomTimeTableMap(Building building) {
        int week = SchoolTimeUtil.getDayOfWeek();
        if (dayOfWeek != week) {
            log.info("initialize RoomTimeTableMap! day of week{}", week);
            dayOfWeek = week;
            generateMap();
        }

        if (building == Building.SCIENCE) {
            return scienceRoomTimeTable;
        }
        if (building == Building.MAIN) {
            return mainRoomTimeTable;
        }

        throw new IllegalArgumentException("illegal building " + building.getChinese());
    }

    private void generateMap() {
        scienceRoomTimeTable = HashMultimap.create();
        mainRoomTimeTable = HashMultimap.create();
        for (CourseTimeTable timeTable : timeTableService.getTimeTableFromDB(SchoolTimeUtil.getSchoolWeek())) {
            if (checkDistinct(timeTable.getDistinct())) {
                Room room = roomService.getRoomByName(timeTable.getRoom().getName());
                if (room.getArea() == Building.SCIENCE) {

                    scienceRoomTimeTable.put(room, timeTable);
                }
                if (room.getArea() == Building.MAIN) {

                    mainRoomTimeTable.put(room, timeTable);
                }
            }
        }
    }

    /**
     * 根据教室名称查询当天教室的上课情况
     *
     * @param name 教室名
     */
    public RoomTimeTable getTodayTimeTableByRoomName(String name) {
        RoomTimeTable timeTable = new RoomTimeTable();

        try {
            Room room = roomService.getRoomByName(name);
            ArrayList<CourseTimeTable> courseTimeTableArrayList = new ArrayList<>(getTodayRoomTimeTableMap(room.getArea()).get(room));
            timeTable.setRoom(room);
            courseTimeTableArrayList.sort(Comparator.comparing(CourseTimeTable::getOrder));
            timeTable.setCourseTimeTable(courseTimeTableArrayList);
        } catch (Exception e) {
            Room room = new Room();
            room.setName(name);
            timeTable.setRoom(room);
        }
        return timeTable;
    }

    private boolean checkDistinct(int distinct) {
        return (distinct == 0) || (distinct == SchoolTimeUtil.getWeekDistinct());
    }

    /**
     * 获取组装后的空教室查询的结果列表
     *
     * @param roomTimeTableList
     * @return
     */
    public List<EmptyRoom> getEmptyRoomReply(List<RoomTimeTable> roomTimeTableList) {
        List<EmptyRoom> replayList = new ArrayList<>();
        for (RoomTimeTable table : roomTimeTableList) {
            replayList.add(tableToEmptyRoomPojo(table));
        }
        return replayList;
    }

    /**
     * 对空教室的结果进行组装
     *
     * @param roomTimeTable
     * @return
     */
    private EmptyRoom tableToEmptyRoomPojo(RoomTimeTable roomTimeTable) {
        EmptyRoom emptyRoom = new EmptyRoom();
        LinkedList<Integer> orderList = new LinkedList<>();

        List<CourseTimeTable> courseTimeTable = roomTimeTable.getCourseTimeTable();

        if (!(Objects.isNull(courseTimeTable) || courseTimeTable.size() == 0)) {
            for (CourseTimeTable table : courseTimeTable) {
                orderList.add(table.getOrder());
            }
        }
        emptyRoom.setName(roomTimeTable.getRoom().getName());
        emptyRoom.setOrderList(orderList);
        return emptyRoom;
    }
}

//Key的序列化类
class KeySerializer extends JsonSerializer<Room> {
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void serialize(Room historicTaskInstance, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        StringWriter writer = new StringWriter();
        objectMapper.writeValue(writer, historicTaskInstance);
        jsonGenerator.writeFieldName(writer.toString());
    }
}

//Key的反序列化类
class KeyDeSerializer extends KeyDeserializer {
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public Room deserializeKey(String key, DeserializationContext deserializationContext) throws IOException {
        return objectMapper.readValue(key, Room.class);
    }
}

