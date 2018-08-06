package cn.hkxj.platform.service.spider;

import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

public class UrpSpiderTest {
	UrpSpider spider;

	@Before
	public void init() {
		spider = new UrpSpider("2014025838", "1");
	}

	@Test
	public void getInformaton() throws IOException {

		spider.getInformaton();

	}

	@Test
	public void getGrade() {
		try {
			spider.getGrade();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}