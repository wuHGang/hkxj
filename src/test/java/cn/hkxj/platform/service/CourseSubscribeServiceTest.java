package cn.hkxj.platform.service;

import cn.hkxj.platform.PlatformApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import javax.annotation.Resource;

import static org.junit.Assert.*;

/**
 * @author Yuki
 * @date 2019/5/9 22:35
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = PlatformApplication.class)
@WebAppConfiguration
@TestPropertySource(locations = "classpath:application-local.properties")
public class CourseSubscribeServiceTest {

    @Resource
    private CourseSubscribeService courseSubscribeService;

    @Test
    public void getCoursesSubscribeForCurrentDay() {
    }

    @Test
    public void getCourseTimeTables() {
    }
}