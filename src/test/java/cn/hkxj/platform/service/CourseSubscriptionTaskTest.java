package cn.hkxj.platform.service;

import cn.hkxj.platform.PlatformApplication;
import cn.hkxj.platform.task.CourseSubscriptionTask;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

/**
 * @author Yuki
 * @date 2018/11/6 21:16
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = PlatformApplication.class)
@WebAppConfiguration
public class CourseSubscriptionTaskTest {

    @Autowired
    private CourseSubscriptionTask courseSubscriptionTask;

    @Test
    public void sendCourseRemindMsg() {
        courseSubscriptionTask.execute(1);
    }

    @Test
    public void test1() {

    }
}