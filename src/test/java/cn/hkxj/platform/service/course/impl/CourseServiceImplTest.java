package cn.hkxj.platform.service.course.impl;

import cn.hkxj.platform.PlatformApplication;
import cn.hkxj.platform.pojo.constant.MiniProgram;
import cn.hkxj.platform.pojo.timetable.CourseTimeTable;
import cn.hkxj.platform.service.CourseService;
import cn.hkxj.platform.service.CourseSubscribeService;
import cn.hkxj.platform.utils.JsonUtils;
import cn.hkxj.platform.utils.SchoolTimeUtil;
import me.chanjar.weixin.mp.bean.template.WxMpTemplateData;
import me.chanjar.weixin.mp.bean.template.WxMpTemplateMessage;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Yuki
 * @date 2018/10/11 20:18
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = PlatformApplication.class)
@WebAppConfiguration
@TestPropertySource(locations = "classpath:application-local.properties")
public class CourseServiceImplTest {
    @Autowired
    private CourseSubscribeService courseSubscribeService;
    @Autowired
    private CourseService courseService;

    @Test
    public void getCoursesByAccount() {
        List<CourseTimeTable> courseTimeTables = courseService.getCoursesByAccount(2016025067);
        System.out.println(courseService.getCoursesByAccount(2016025067));
    }

    @Test
    public void test(){
        System.out.println(courseService.getCoursesByAccount(2016024170));
        System.out.println(courseService.toText(courseService.getCoursesCurrentDay(2016024170)));
    }

    @Test
    public void getCoursesForCurrentDay(){
        List<CourseTimeTable> msgList = courseSubscribeService.getCourseTimeTables();
        System.out.println(msgList);
//        if(Objects.isNull(msgList)){
//            System.out.println("good!");
//            return;
//        }
//        msgList.forEach(msg -> {
////            .stream().filter(openid -> !Objects.equals(openid, null))
//            msg.getOpenIds().forEach(openid -> {
//                System.out.println(openid);
//                System.err.println(msg.getCourseTimeTables());
//            });
//        });
    }

    @Test
    public void builderTest(){

    }
}