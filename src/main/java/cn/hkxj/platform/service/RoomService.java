package cn.hkxj.platform.service;

import cn.hkxj.platform.dao.UrpClassRoomDao;
import cn.hkxj.platform.exceptions.RoomParseException;
import cn.hkxj.platform.mapper.RoomMapper;
import cn.hkxj.platform.mapper.UrpClassroomMapper;
import cn.hkxj.platform.pojo.Course;
import cn.hkxj.platform.pojo.Room;
import cn.hkxj.platform.pojo.UrpClassroom;
import cn.hkxj.platform.pojo.constant.Building;
import cn.hkxj.platform.pojo.constant.Direction;
import cn.hkxj.platform.pojo.example.RoomExample;
import cn.hkxj.platform.spider.newmodel.searchclassroom.SearchClassroomPost;
import cn.hkxj.platform.spider.newmodel.searchclassroom.SearchClassroomResult;
import cn.hkxj.platform.spider.newmodel.searchclassroom.SearchResultWrapper;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.CharUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

/**
 * @author junrong.chen
 * @date 2018/10/30
 */
@Service
@Slf4j
public class RoomService {


	@Resource
	private NewUrpSpiderService newUrpSpiderService;
	@Resource
	private UrpClassroomMapper urpClassroomMapper;
	@Resource
	private UrpClassRoomDao urpClassRoomDao;

	private final static byte NOT_ALLOW = 0;
	private final static byte ALLOW = 1;

	private static final Cache<String, UrpClassroom> urpRoomCache = CacheBuilder.newBuilder()
			.maximumSize(100)
			.build();

	public UrpClassroom getClassRoomByName(String name){
		try {
			return urpRoomCache.get(name, (() -> urpClassRoomDao.selectByName(name)));
		} catch (ExecutionException e) {
			log.error("get urp classroom form cache error", e);
			throw new RuntimeException(e);
		}


	}
	public void saveAllClassRoom(){
		SearchClassroomPost post = new SearchClassroomPost();
		post.setExecutiveEducationPlanNum("2019-2020-1-1");
		for (SearchResultWrapper<SearchClassroomResult> resultWrapper : newUrpSpiderService.searchClassroomInfo(post)) {
			for (SearchClassroomResult result : resultWrapper.getPageData().getRecords()) {
				UrpClassroom urpClassroom = new UrpClassroom()
						.setType(result.getClassroomTypeDirections())
						.setSeats(result.getClassNumberOfSeats())
						.setCampusName(result.getCampusName())
						.setCampusNumber(result.getId().getCampusNumber())
						.setTeachingBuildingName(result.getTeachingBuildingName())
						.setTeachingBuildingNumber(result.getId().getTeachingBuildingNumber())
						.setName(result.getClassroomName())
						.setNumber(result.getId().getClassroomNumber())
						.setDepartment(result.getDepartmentName());

				urpClassroomMapper.insertSelective(urpClassroom);
			}

		}

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
			else if(buildingName.equals(Building.LIBRARY.getChinese())){
                return parseForLibrary(classroomName);
            }
			else if(buildingName.equals(Building.PLAYGROUND.getChinese())){
                return parseForPlayGround(classroomName);
            }
			else if(buildingName.startsWith(Building.LABORATORY.getChinese())){
                return parseForLaboratory(classroomName);
            }else if(buildingName.startsWith(Building.LABORATORY_BUILDING.getChinese())){
				return parseForLaboratoryBuilding(classroomName);
			}
		} catch (Exception e) {
		    log.error("room parse fail classroomName:{} buildingName:{}", classroomName, buildingName, e);
			throw new RoomParseException("room parse fail classroomName  " + classroomName + "   buildingName   " + buildingName, e);
		}
		log.error("room parse fail classroomName:{} buildingName:{} ", classroomName, buildingName);
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
		int[] floorAndNumber = getFloorAndNumber(classroomName.substring(2), 1);
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

	private Room parseForLaboratoryBuilding(String classroomName){
		Room room = new Room();
		room.setIsAllow(NOT_ALLOW);
		room.setName(classroomName);
		room.setArea(Building.LABORATORY_BUILDING);
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
