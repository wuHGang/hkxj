package cn.hkxj.platform.service.spider;

import cn.hkxj.platform.spider.AppSpider;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

/**
 * @author JR Chan
 * @date 2018/6/5 9:57
 */

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class AppSpiderTest {
	private AppSpider spider;

	@Before
	public void setUp() throws Exception {
		spider = new AppSpider(2015025838);
	}

	@Test
	public void getToken() throws IOException {
		System.out.println(spider.getToken());
	}

	@Test
	public void getGrade() {
		try {
			ArrayList grade = spider.getGrade();
			for (Object item: grade) {
				log.info(item.toString());
			}
			System.out.println();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void getLesson() {
		try {
			System.out.println(spider.getLesson());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void getSchedule() throws IOException {
		/*
		 *
		 */
		Map map = spider.getSchedule();
		ArrayList slist = (ArrayList) map.get("slist");
		ArrayList wlist = (ArrayList) map.get("wlist");
//        for(Object sc: slist){
//            System.out.println((Map)sc);
//        }

		for (Object sc : wlist) {
			System.out.println((Map) sc);
		}

	}

	@Test
	public void getExam() throws IOException {
		ArrayList list = spider.getExam();
		System.out.println(list);
	}

}