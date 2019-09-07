package cn.hkxj.platform.service;

import cn.hkxj.platform.PlatformApplication;
import cn.hkxj.platform.pojo.Classes;
import cn.hkxj.platform.pojo.SchoolTime;
import cn.hkxj.platform.pojo.Student;
import cn.hkxj.platform.spider.newmodel.coursetimetable.UrpCourseTimeTableForSpider;
import cn.hkxj.platform.utils.DateUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import javax.annotation.Resource;

/**
 * @author Yuki
 * @date 2019/9/2 16:33
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = PlatformApplication.class)
@WebAppConfiguration
public class CourseTimeTableServiceTest {

    @Resource
    private CourseTimeTableService courseTimeTableService;

    @Test
    public void test(){
        Student student = new Student();
        Classes classes = new Classes();
        classes.setId(317);
        student.setAccount(2016024186);
        student.setPassword("12345");
        student.setClasses(classes);
        SchoolTime schoolTime = DateUtils.getCurrentSchoolTime();
//        UrpCourseTimeTableForSpider spiderResult = newUrpSpiderService.getUrpCourseTimeTable(student);
        System.out.println(courseTimeTableService.getDetailsForCurrentDay(student));
    }

}