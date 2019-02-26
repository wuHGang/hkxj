package cn.hkxj.platform.service.spider;

import cn.hkxj.platform.exceptions.PasswordUncorrectException;
import cn.hkxj.platform.spider.UrpSpider;
import cn.hkxj.platform.spider.model.Information;
import cn.hkxj.platform.spider.model.UrpResult;
import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;

@Slf4j
public class UrpSpiderTest {
	private UrpSpider spider;

	@Before
	public void init() {
        spider = new UrpSpider(2018026461, "zzt20000303");
	}

	@Test
    public void getInformation() {
        UrpResult<Information> information = spider.getInformation();
        log.info(information.toString());

    }

	@Test
	public void getGrade() {
//			try {
//				Map grade = spider.getGrade();
//				System.out.println(grade.toString());
//			} catch (PasswordUncorrectException e) {
//				e.printStackTrace();
//			}
	}

	@Test
    public void getCurrent() throws PasswordUncorrectException {
//        Map grade = spider.getCurrentGrade();
//        log.info(grade.toString());
    }

	@Test
    public void getEver() throws PasswordUncorrectException {
		spider.getEverGrade();
	}
}