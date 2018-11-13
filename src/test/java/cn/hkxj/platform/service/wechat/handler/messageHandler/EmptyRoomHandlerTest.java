package cn.hkxj.platform.service.wechat.handler.messageHandler;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

/**
 * @author junrong.chen
 * @date 2018/11/6
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class EmptyRoomHandlerTest {
	@Autowired
	private EmptyRoomHandler emptyRoomHandler;

	@Test
	public void parseContent() {
		String content = emptyRoomHandler.parseContent("空教室 教室 科S208");
		System.out.println(content);
	}
}