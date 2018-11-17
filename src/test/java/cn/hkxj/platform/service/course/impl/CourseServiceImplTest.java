package cn.hkxj.platform.service.course.impl;

import cn.hkxj.platform.PlatformApplication;
import cn.hkxj.platform.pojo.CourseGroupMsg;
import cn.hkxj.platform.service.impl.CourseServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.List;

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

    @Test
    public void test(){
        System.out.println(courseService.getCoursesCurrentDay(2016025910));
        System.out.println(courseService.toText(courseService.getCoursesCurrentDay(2016024170)));
    }

    @Test
    public void getCoursesForCurrentDay()throws Exception{
        List<CourseGroupMsg> msgList = courseService.getCoursesSubscribeForCurrentDay();
        msgList.forEach(msg -> {
            msg.getOpenIds().stream().filter(openid -> openid != null).forEach(openid -> {
                System.out.println(openid);
                System.err.println(msg.getCourseTimeTables());
            });
        });
    }
}