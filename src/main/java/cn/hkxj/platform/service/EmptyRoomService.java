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
import java.util.List;

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
	private static HashMultimap<Room, CourseTimeTable> scienceRoomTimeTable = HashMultimap.create();
	private static HashMultimap<Room, CourseTimeTable> mainRoomTimeTable = HashMultimap.create();
	private static int dayOfWeek;


	/**
	 * 该方法为获取当天具体教学楼所有教室的课程情况
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
			if(o1.getRoom().getFloor().equals( o2.getRoom().getFloor())){
				return o1.getRoom().getName().compareTo(o2.getRoom().getName());
			}
			else {
				return o1.getRoom().getFloor().compareTo( o2.getRoom().getFloor());
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

	private HashMultimap<Room, CourseTimeTable> getTodayRoomTimeTableMap(Building building) {
		int week = SchoolTimeUtil.getDayOfWeek();
		if(dayOfWeek != week){
			log.info("initialize RoomTimeTableMap! day of week{}", week);
			dayOfWeek = week;
			generateMap();
		}

		if (building == Building.SCIENCE){
			return scienceRoomTimeTable;
		}
		if (building == Building.MAIN){
			return mainRoomTimeTable;
		}

		throw new IllegalArgumentException("illegal building "+building.getChinese());
	}

	private void generateMap(){
		for (CourseTimeTable timeTable : timeTableService.getTimeTableFromDB(SchoolTimeUtil.getSchoolWeek())) {
			if (checkDistinct(timeTable.getDistinct())){
				Room room = roomService.getRoomByName(timeTable.getPosition());
				if (room.getArea() == Building.SCIENCE){
					scienceRoomTimeTable.put(room, timeTable);
				}
				if (room.getArea() == Building.MAIN){
					mainRoomTimeTable.put(room, timeTable);
				}
			}
		}
	}

	/**
	 * 根据教室名称查询当天教室的上课情况
	 * @param name 教室名
	 */
	public RoomTimeTable getTodayTimeTableByRoomName(String name){
		RoomTimeTable timeTable = new RoomTimeTable();

		try{
			Room room = roomService.getRoomByName(name);
			ArrayList<CourseTimeTable> courseTimeTableArrayList = new ArrayList<>(getTodayRoomTimeTableMap(room.getArea()).get(room));
			timeTable.setRoom(room);
			courseTimeTableArrayList.sort(Comparator.comparing(CourseTimeTable::getOrder));
			timeTable.setCourseTimeTable(courseTimeTableArrayList);
		}
		catch (Exception e){
			Room room = new Room();
			room.setName(name);
			timeTable.setRoom(room);
		}
		return timeTable;
	}

	private boolean checkDistinct(int distinct){
		return (distinct == 0) || (distinct == SchoolTimeUtil.getWeekDistinct());
	}
}
