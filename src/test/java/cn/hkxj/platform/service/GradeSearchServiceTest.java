package cn.hkxj.platform.service;

import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

import static org.junit.Assert.*;

/**
 * @author junrong.chen
 * @date 2018/9/22
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class GradeSearchServiceTest {
	@Resource(name = "gradeSearchService")
	private GradeSearchService gradeSearchService;
	@Autowired
	private WxMpService wxMpService;

	@Test
	public void getCurrentGrade() {
		gradeSearchService.getCurrentGrade(2015025838, "1");
	}

	@Test
	public void getAccess_token() throws WxErrorException {
		String accessToken = wxMpService.getAccessToken();
		System.out.println(accessToken);
	}
}