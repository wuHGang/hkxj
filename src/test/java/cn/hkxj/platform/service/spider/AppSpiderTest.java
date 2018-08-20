package cn.hkxj.platform.service.spider;

import cn.hkxj.platform.pojo.Course;
import cn.hkxj.platform.spider.AppSpider;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

/**
 * @author JR Chan
 * @date 2018/6/5 9:57
 */

@Slf4j
public class AppSpiderTest {
	private AppSpider spider;

	@Before
	public void setUp() throws Exception {
		spider = new AppSpider(2015025838);
		spider.getToken();
	}

	@Test
	public void getToken() throws IOException {
		System.out.println(spider.getToken());
	}

	@Test
	public void getGrade() {
//		{xh=2015025838, kcdm=1705045, xn=2016-2017, cj=76, kcxz=必修, xq=1, kcmc=物联网结构与数据分析, xf=3.0
//		{xh=2014025838, kcdm=1705045, xn=2015-2016, cj=60, kcxz=必修, xq=1, kcmc=物联网结构与数据分析, xf=3.0}
		try {
			ArrayList<Map> grade = spider.getGrade();
			for (Map item: grade) {
				log.info(item.toString());
				ArrayList<Map> items = (ArrayList)item.get("items");
				Object xn = item.get("xn");
				Object xq = item.get("xq");
				for(Map detail: items) {
					String uid = detail.get("kcdm").toString();
					String type = detail.get("kcxz").toString();
					String name = detail.get("kcmc").toString();
					String cj = detail.get("cj").toString();
					Double xf = (Double)detail.get("xf");
//					log.info(detail.toString());
//					break;
				}
//				log.info(items.toString());
			}
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