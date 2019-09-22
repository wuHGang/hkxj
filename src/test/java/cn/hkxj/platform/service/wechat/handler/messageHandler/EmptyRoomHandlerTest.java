//package cn.hkxj.platform.service.wechat.handler.messageHandler;
//
//import lombok.extern.slf4j.Slf4j;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//import org.springframework.test.context.junit4.SpringRunner;
//
///**
// * @author junrong.chen
// * @date 2018/11/6
// */
//@Slf4j
//@RunWith(SpringJUnit4ClassRunner.class)
//@SpringBootTest
//public class EmptyRoomHandlerTest {
//	@Autowired
//	private EmptyRoomHandler emptyRoomHandler;
//
//	@Test
//	public void parseContentTwoScience() {
//		String input = "空教室 科厦";
//		String content = emptyRoomHandler.parseContent(input);
//		log.info("input:{} \ncontent:{}", input, content);
//	}
//
//	@Test
//	public void parseContentTwoMain() {
//		String input = "空教室 主楼";
//		String content = emptyRoomHandler.parseContent(input);
//		log.info("input:{} \ncontent:{}", input, content);
//	}
//
//	@Test
//	public void parseContentThreeMain() {
//		String input = "空教室 主楼 3";
//		String content = emptyRoomHandler.parseContent(input);
//		log.info("input:{} \ncontent:{}", input, content);
//	}
//
//	@Test
//	public void parseContentThreeSearchClassRoom(){
//		String input = "空教室 教室 主楼W0321";
//		String content = emptyRoomHandler.parseContent(input);
//		log.info("input:{} \ncontent:{}", input, content);
//	}
//
//	@Test
//	public void errorSearchType(){
//		String input = "空教室 测试 主楼w0321";
//		String content = emptyRoomHandler.parseContent(input);
//		log.info("input:{} \ncontent:{}", input, content);
//	}
//
//	@Test
//	public void roomNameLoweCase(){
//		String input = "空教室 教室 主楼w0321";
//		String content = emptyRoomHandler.parseContent(input);
//		log.info("input:{} \ncontent:{}", input, content);
//	}
//}