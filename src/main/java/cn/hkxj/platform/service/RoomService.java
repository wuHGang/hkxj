package cn.hkxj.platform.service;

import cn.hkxj.platform.mapper.RoomMapper;
import cn.hkxj.platform.pojo.constant.Building;
import cn.hkxj.platform.pojo.Room;
import cn.hkxj.platform.pojo.example.RoomExample;
import lombok.extern.slf4j.Slf4j;
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
}
