package cn.hkxj.platform.service.wechat.common.course.impl;

import cn.hkxj.platform.PlatformApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import static org.junit.Assert.*;

/**
 * @author Yuki
 * @date 2018/10/11 20:18
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = PlatformApplication.class)
@WebAppConfiguration
public class CourseServiceImplTest {
    @Autowired
    private CourseServiceImpl courseService;

    @Test
    public void getCoursesByAccount() {
        System.out.println(courseService.getCoursesByAccount(2016024170));
    }
}