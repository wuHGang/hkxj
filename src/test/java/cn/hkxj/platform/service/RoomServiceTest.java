package cn.hkxj.platform.service;

import cn.hkxj.platform.PlatformApplication;
import cn.hkxj.platform.exceptions.RoomParseException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.PropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import javax.annotation.Resource;

/**
 * @author junrong.chen
 * @date 2018/11/3
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = PlatformApplication.class)
@WebAppConfiguration
public class RoomServiceTest {
	@Resource
	private RoomService roomService;

	@Test
	public void getTodayRoomTimeTable() throws RoomParseException {
		System.out.println(roomService.parseToRoomForSpider("画室1", "主楼（西楼）"));
	}

	@Test
	public void test(){
		roomService.saveAllClassRoom();
	}
}