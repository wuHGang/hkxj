package cn.hkxj.platform.service;

import org.junit.Test;
import org.junit.runner.RunWith;
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

	@Test
	public void getCurrentGrade() {
		gradeSearchService.getCurrentGrade(2015025838, "1");
	}
}