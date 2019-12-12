package cn.hkxj.platform.service.spider;

import cn.hkxj.platform.exceptions.PasswordUnCorrectException;
import cn.hkxj.platform.spider.UrpSpider;
import cn.hkxj.platform.spider.model.CurrentGrade;
import cn.hkxj.platform.spider.model.EverGrade;
import cn.hkxj.platform.spider.model.Information;
import cn.hkxj.platform.spider.model.UrpResult;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;

@Slf4j
public class UrpSpiderTest {
	private UrpSpider spider;

	@Before
	public void init() {
        spider = new UrpSpider(2016024249, "1");
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
    public void getCurrent() throws PasswordUnCorrectException {
        UrpResult<CurrentGrade> grade = spider.getCurrentGrade();
        log.info(grade.toString());
    }

	@Test
    public void getEver() throws PasswordUnCorrectException {
        UrpResult<EverGrade> everGrade = spider.getEverGrade();
        System.out.println(everGrade);
    }
}