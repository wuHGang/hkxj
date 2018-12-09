package cn.hkxj.platform.service.spider;

import cn.hkxj.platform.exceptions.PasswordUncorrectException;
import cn.hkxj.platform.pojo.Student;
import cn.hkxj.platform.spider.UrpSpider;
import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.Map;

@Slf4j
public class UrpSpiderTest {
	private UrpSpider spider;
	private final static Gson gson = new Gson();

	@Before
	public void init() {
        spider = new UrpSpider("2017025971", "1");
	}

	@Test
    public void getInformaton() throws PasswordUncorrectException {

        Student student = spider.getInformation();

	}

	@Test
	public void getGrade() {
		try {
			try {
				Map grade = spider.getGrade();
				System.out.println(grade.toString());
			} catch (PasswordUncorrectException e) {
				e.printStackTrace();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Test
    public void getCurrent() throws PasswordUncorrectException {
        Map grade = spider.getCurrentGrade();
        log.info(grade.toString());
    }

	@Test
    public void getEver() throws PasswordUncorrectException {
		spider.getEverGrade();
	}
}