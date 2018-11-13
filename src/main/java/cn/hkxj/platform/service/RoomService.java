package cn.hkxj.platform.service;

import cn.hkxj.platform.mapper.RoomMapper;
import cn.hkxj.platform.pojo.Building;
import cn.hkxj.platform.pojo.CourseTimeTable;
import cn.hkxj.platform.pojo.Room;
import cn.hkxj.platform.pojo.RoomExample;
import cn.hkxj.platform.pojo.RoomTimeTable;
import com.google.common.collect.HashMultimap;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Comparator;
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

	public Room getRoomByName(String name){
		RoomExample roomExample = new RoomExample();
		roomExample.createCriteria()
				.andNameEqualTo(name);
		List<Room> roomList = roomMapper.selectByExample(roomExample);
		if(roomList.size() != 1){
			log.error("getRoomByName exception name{} list{}", name, roomList );
			throw new RuntimeException("room name is invalid");
		}
		return roomList.get(0);
	}

}
