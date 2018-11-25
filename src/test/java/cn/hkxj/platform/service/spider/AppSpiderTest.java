package cn.hkxj.platform.service.spider;

import cn.hkxj.platform.exceptions.PasswordUncorrectException;
import cn.hkxj.platform.pojo.AllGradeAndCourse;
import cn.hkxj.platform.spider.AppSpider;
import cn.hkxj.platform.utils.ApplicationUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
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
@ContextConfiguration
public class AppSpiderTest {
    @Autowired
    private ApplicationContext applicationContext;
	private AppSpider spider;
	private int account = 2017023523;

	@Before
	public void setUp() throws Exception {
	    ApplicationUtil.setApplicationContext(applicationContext);
		spider = new AppSpider(account);
		spider.getToken();
	}

	@Test
	public void getToken() throws IOException {
		try {
			System.out.println(spider.getToken());
		} catch (PasswordUncorrectException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void getGrade() throws IOException {
		AllGradeAndCourse gradeAndCourse = spider.getGradeAndCourse();
		log.info(gradeAndCourse.toString());
	}

	@Test
	public void getLesson() {

		System.out.println(spider.getLesson());

	}

	@Test
	public void getSchedule() {
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
	public void getExam() {
		ArrayList list = spider.getExam();
		System.out.println(list);
	}

	@Test
	public void yearToDate(){
		double cj = 3.5;

	}

	private static int xnToYear(String xn){
		String[] split = xn.split("-");
		return Integer.parseInt(split[0]);
	}
}