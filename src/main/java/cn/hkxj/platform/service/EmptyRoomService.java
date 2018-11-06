package cn.hkxj.platform.service;

import cn.hkxj.platform.pojo.Building;
import cn.hkxj.platform.pojo.CourseTimeTable;
import cn.hkxj.platform.pojo.Room;
import cn.hkxj.platform.pojo.RoomTimeTable;
import cn.hkxj.platform.utils.SchoolTimeUtil;
import com.google.common.collect.HashMultimap;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

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
	/**
	 * 缓存当天有课教室和课程的映射
	 */
	private static HashMultimap<Room, CourseTimeTable> todayRoomTimeTableMap;
	private static Map<String, Room> todayRoomMap;

	private static int dayOfWeek;

	public List<RoomTimeTable> getTodayRoomTimeTable(Building building, int floor) {
		ArrayList<RoomTimeTable> roomTimeTableList = new ArrayList<>();
		HashMultimap<Room, CourseTimeTable> tableMap = getTodayRoomTimeTableMap();
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
	 * 根据教室名称查询当天教室的上课情况
	 * @param name
	 * @return
	 */
	public RoomTimeTable getTodayTimeTableByRoomName(String name){
		RoomTimeTable timeTable = new RoomTimeTable();
		if (Objects.isNull(todayRoomMap)) {
			generateMap();
		}

		if( todayRoomMap.containsKey(name)){
			Room room = todayRoomMap.get(name);
			ArrayList<CourseTimeTable> courseTimeTableArrayList = new ArrayList<>(todayRoomTimeTableMap.get(room));
			timeTable.setRoom(room);
			courseTimeTableArrayList.sort(Comparator.comparing(CourseTimeTable::getOrder));
			timeTable.setCourseTimeTable(courseTimeTableArrayList);
		}
		else {
			Room room = new Room();
			room.setName(name);
			timeTable.setRoom(room);
		}
		return timeTable;
	}

	private HashMultimap<Room, CourseTimeTable> getTodayRoomTimeTableMap() {
		int week = SchoolTimeUtil.getDayOfWeek();
		if(dayOfWeek != week){
			log.info("initialize RoomTimeTableMap! day of week{}", week);
			dayOfWeek = week;
			generateMap();
		}
		return todayRoomTimeTableMap;
	}

	private void generateMap(){
		todayRoomTimeTableMap = HashMultimap.create();
		todayRoomMap = new HashMap<>();
		for (CourseTimeTable timeTable : timeTableService.getTimeTableFromDB(SchoolTimeUtil.getSchoolWeek())) {
			String position = timeTable.getPosition();
			if (checkDistinct(timeTable.getDistinct())){
				Room room;
				if (!todayRoomMap.containsKey(position) ){
					room = roomService.getRoomByName(position);
					todayRoomMap.put(position, room);
				}
				else {
					room = todayRoomMap.get(position);
				}

				todayRoomTimeTableMap.put(room, timeTable);
			}
		}
	}

	private boolean checkDistinct(int distinct){
		return (distinct == 0) || (distinct == SchoolTimeUtil.getWeekDistinct());
	}
}
