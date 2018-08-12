package cn.hkxj.platform.service.spider;

import cn.hkxj.platform.exceptions.PasswordUncorrectException;
import cn.hkxj.platform.spider.UrpSpider;
import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Slf4j
public class UrpSpiderTest {
	UrpSpider spider;
	private final static Gson gson = new Gson();

	@Before
	public void init() {
		spider = new UrpSpider("2014025838", "1");
	}

	@Test
	public void getInformaton() throws IOException, PasswordUncorrectException {

//		String result = spider.getInformaton();

//		log.info(result);
//		Double stau = (Double) 200.0;

	}

	@Test
	public void getGrade() {
		try {
			try {
				spider.getGrade();
			} catch (PasswordUncorrectException e) {
				e.printStackTrace();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}