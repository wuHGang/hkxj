package cn.hkxj.platform.service;

import cn.hkxj.platform.exceptions.RoomParseException;
import cn.hkxj.platform.mapper.RoomMapper;
import cn.hkxj.platform.pojo.Room;
import cn.hkxj.platform.pojo.constant.Building;
import cn.hkxj.platform.pojo.constant.Direction;
import cn.hkxj.platform.pojo.example.RoomExample;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.CharUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author junrong.chen
 * @date 2018/10/30
 */
@Service
@Slf4j
public class RoomService {

	@Resource
	private RoomMapper roomMapper;

	private final static byte NOT_ALLOW = 0;
	private final static byte ALLOW = 1;

	private static Map<String, Room> roomMap = new HashMap<>();


	public List<Room> getRoomByBuilding(Building building) {
		RoomExample roomExample = new RoomExample();
		roomExample.createCriteria().andAreaEqualTo(building);
		return roomMapper.selectByExample(roomExample);
	}

	public List<Room> getRoomByBuildingAndFloor(Building building, int floor) {
		RoomExample roomExample = new RoomExample();
		roomExample.createCriteria()
				.andAreaEqualTo(building)
				.andFloorEqualTo(floor);
		return roomMapper.selectByExample(roomExample);
	}

	private Room getRoomByNameFromDB (String name){
		RoomExample roomExample = new RoomExample();
		roomExample.createCriteria()
				.andNameEqualTo(name);
		List<Room> roomList = roomMapper.selectByExample(roomExample);
		if(roomList.size() != 1){
            log.error("getRoomByName exception name{}  list{}", name, roomList);
            Room room = new Room();
            room.setName(name);
            return room;
//			throw new IllegalArgumentException("illegal room name: "+ name);
		}
		return roomList.get(0);
	}


	/**
	 * 通过教室名取到教室实体，如果缓存有从缓存读取
	 * 缓存没有从数据库读取后再存入缓存
	 *
	 * @param position 教室名
	 */
	public Room getRoomByName(String position){
		Room room;
		if (!roomMap.containsKey(position) ){
			room = getRoomByNameFromDB(position);
			roomMap.put(position, room);
		}
		else {
			room = roomMap.get(position);
		}

		return room;
	}

	public Room parseToRoomForSpider(String classroomName, String buildingName) throws RoomParseException {
		try {
			if("科大".equals(buildingName)){
                return parseForScience(classroomName);
            }
			//因为爬虫返回的主楼数据都是如主楼（西楼），所以使用startsWith
			if(buildingName.startsWith(Building.MAIN.getChinese())){
                return parseForMainBuilding(classroomName);
            }
			if(buildingName.equals(Building.LIBRARY.getChinese())){
                return parseForLibrary(classroomName);
            }
			if(buildingName.equals(Building.PLAYGROUND.getChinese())){
                return parseForPlayGround(classroomName);
            }
			if(buildingName.startsWith(Building.LABORATORY.getChinese())){
                return parseForLaboratory(classroomName);
            }
		} catch (Exception e) {
		    log.error("room parse fail classroomName:{} buildingName:{} message:{}", classroomName, buildingName, e.getMessage());
			throw new RoomParseException("room parse fail classroomName  " + classroomName + "   buildingName   " + buildingName, e);
		}
		throw new RoomParseException("room parse fail classroomName  " + classroomName + "   buildingName   " + buildingName);
	}

	private Room parseForScience(String classroomName){
		char second = classroomName.charAt(1);
		if(CharUtils.isAsciiAlpha(second)){
			return parseForScienceBuilding(classroomName, second);
		} else {
			return parseForScienceHigh(classroomName);
		}
	}

	private Room parseForScienceBuilding(String classroomName, Character second){
		Room room = new Room();
		int[] floorAndNumber = getFloorAndNumber(classroomName.substring(2, classroomName.length()), 1);
		room.setName(classroomName);
		room.setArea(Building.SCIENCE);
		room.setDirection(Direction.getDirectionObjectByDirection(second.toString()));
		room.setIsAllow(ALLOW);
		room.setFloor(floorAndNumber[0]);
		room.setNumber(floorAndNumber[1]);
		return room;
	}

	private Room parseForScienceHigh(String classroomName){
		Room room = new Room();
		int index = 0;
		//从一开始 科0401
		for(int i = 1, length = classroomName.length(); i < length; i++){
			if(!CharUtils.isAsciiNumeric(classroomName.charAt(i))){
				break;
			}
			index++;
		}
		index = index + 1;
		int[] floorAndNumber = getFloorAndNumber(classroomName.substring(1, index), 1);
		room.setName(classroomName);
		room.setArea(Building.SCIENCE_HIGH);
		room.setDirection(Direction.CORRECT);
		room.setIsAllow(NOT_ALLOW);
		room.setFloor(floorAndNumber[0]);
		room.setNumber(floorAndNumber[1]);
		return room;
	}

	private Room parseForMainBuilding(String classroomName){
		Room room = new Room();
		if(classroomName.startsWith("画室")){
			room.setIsAllow(NOT_ALLOW);
			room.setName(classroomName);
			room.setArea(Building.MAIN);
			room.setDirection(Direction.CORRECT);
			room.setFloor(0);
			room.setNumber(0);
			return room;
		}
		String direction = CharUtils.isAsciiAlpha(classroomName.charAt(1)) ? classroomName.substring(0, 2) : classroomName.substring(0, 1);
		int index = 0;
		//从二开始的原因是，不过是E0401或者是EN0401，从二开始都是数字开头
		for(int i = 2, length = classroomName.length(); i < length; i++){
			if(!CharUtils.isAsciiNumeric(classroomName.charAt(i))){
				break;
			}
			index++;
		}
		//切割字符串时要把前两个字符算进去
		index += 2;
		int[] floorAndNumber = getFloorAndNumber(classroomName.substring(2, index), 1);
		room.setIsAllow(ALLOW);
		room.setName(classroomName.substring(0, index));
		room.setArea(Building.MAIN);
		room.setDirection(Direction.getDirectionObjectByDirection(direction));
		room.setFloor(floorAndNumber[0]);
		room.setNumber(floorAndNumber[1]);
		return room;
	}

	private Room parseForLibrary(String classroomName){
		Room room = new Room();
		String direction = classroomName.substring(3, 4);
		int[] floorAndNumber = getFloorAndNumber(classroomName.substring(4, classroomName.length()), 10);
		room.setIsAllow(NOT_ALLOW);
		room.setName(classroomName);
		room.setArea(Building.LIBRARY);
		room.setDirection(Direction.getDirectionObjectByDirection(direction));
		room.setFloor(floorAndNumber[0]);
		room.setNumber(floorAndNumber[1]);
		return room;
	}

	private Room parseForPlayGround(String classroomName){
		Room room = new Room();
		room.setIsAllow(NOT_ALLOW);
		room.setName(classroomName);
		room.setArea(Building.PLAYGROUND);
		room.setDirection(Direction.CORRECT);
		room.setFloor(0);
		room.setNumber(0);
		return room;
	}

	private Room parseForLaboratory(String classroomName){
		Room room = new Room();
		room.setIsAllow(NOT_ALLOW);
		room.setName(classroomName);
		room.setArea(Building.LABORATORY);
		room.setDirection(Direction.CORRECT);
		room.setFloor(0);
		room.setNumber(0);
		return room;
	}

	private int[] getFloorAndNumber(String numberSeq, int multiple){
		int floorAndNumber = Integer.parseInt(numberSeq);
		int param = 100 * multiple;
		int floor = floorAndNumber / param;
		int number = floorAndNumber % param;
		return new int[]{ floor, number};
	}
}
