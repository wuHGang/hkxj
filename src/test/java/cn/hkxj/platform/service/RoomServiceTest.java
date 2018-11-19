package cn.hkxj.platform.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

/**
 * @author junrong.chen
 * @date 2018/11/3
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class RoomServiceTest {
	@Resource
	private RoomService roomService;

	@Test
	public void getTodayRoomTimeTable() {
//		for (RoomTimeTable table : roomService.getTodayRoomTimeTable(Building.SCIENCE, 1)) {
//			System.out.println(table.toText());
//		}

	}
}