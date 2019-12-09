package cn.hkxj.platform.service;

import cn.hkxj.platform.PlatformApplication;
import cn.hkxj.platform.pojo.wechat.CourseGroupMsg;
import cn.hkxj.platform.pojo.wechat.CourseSubscriptionMessage;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import javax.annotation.Resource;

import java.util.Map;
import java.util.Set;

/**
 * @author Yuki
 * @date 2019/5/9 22:35
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = PlatformApplication.class)
@WebAppConfiguration
public class CourseSubscribeServiceTest {

    @Resource
    private CourseSubscribeService courseSubscribeService;

    @Test
    public void getCoursesSubscribeForCurrentDay() {
        courseSubscribeService.getSubscriptionMessages(CourseSubscribeService.FIRST_SECTION);
    }

    @Test
    public void getCourseTimeTables() {
//        Map<String, Set<CourseGroupMsg>> map = courseSubscribeService.getCoursesSubscribeForCurrentDay();
//        map.forEach((appid, msgs) -> {
//            System.out.println("appid = " + appid);
//            System.out.println(msgs);
//        });
    }
}