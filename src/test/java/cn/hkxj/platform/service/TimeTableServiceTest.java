package cn.hkxj.platform.service;

import cn.hkxj.platform.pojo.CourseTimeTable;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.HashSet;

/**
 * @author junrong.chen
 * @date 2018/10/31
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class TimeTableServiceTest {
	@Resource(name = "timeTableService")
	private TimeTableService timeTableService;

	@Test
	public void getTodayTimeTable() {
		HashSet<String> roomSet = new HashSet<>();
		int count = 0;
		for (CourseTimeTable table : timeTableService.getTimeTableFromDB(5)) {
			roomSet.add(table.getPosition());
			count += 1;
		}
		System.out.println(roomSet.size());
		System.out.println(count);
	}

	@Test
	public void getRoomTimeTableByBuildingAndFloor() {
//		for (RoomTimeTable table : timeTableService.getRoomTimeTableByBuildingAndFloor(Building.SCIENCE, 4)) {
//			System.out.println(table.toText()+'\n');
//		}

	}
}