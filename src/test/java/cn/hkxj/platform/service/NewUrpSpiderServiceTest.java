package cn.hkxj.platform.service;

import cn.hkxj.platform.PlatformApplication;
import cn.hkxj.platform.dao.CourseDao;
import cn.hkxj.platform.dao.CourseTimeTableDao;
import cn.hkxj.platform.dao.CourseTimeTableDetailDao;
import cn.hkxj.platform.dao.StudentDao;
import cn.hkxj.platform.pojo.*;
import cn.hkxj.platform.spider.NewUrpSpider;
import cn.hkxj.platform.spider.newmodel.SearchResult;
import cn.hkxj.platform.spider.newmodel.course.UrpCourseForSpider;
import cn.hkxj.platform.spider.newmodel.coursetimetable.UrpCourseTimeTable;
import cn.hkxj.platform.spider.newmodel.coursetimetable.UrpCourseTimeTableForSpider;
import cn.hkxj.platform.spider.newmodel.examtime.UrpExamTime;
import cn.hkxj.platform.spider.newmodel.grade.CurrentGrade;
import cn.hkxj.platform.spider.newmodel.grade.general.UrpGeneralGradeForSpider;
import cn.hkxj.platform.spider.newmodel.grade.general.UrpGradeForSpider;
import cn.hkxj.platform.spider.newmodel.searchclass.ClassInfoSearchResult;
import cn.hkxj.platform.spider.newmodel.searchclassroom.SearchClassroomPost;
import cn.hkxj.platform.spider.newmodel.searchclassroom.SearchClassroomResult;
import cn.hkxj.platform.spider.newmodel.searchclassroom.SearchResultWrapper;
import cn.hkxj.platform.spider.newmodel.searchclass.CourseTimetableSearchResult;
import cn.hkxj.platform.spider.newmodel.searchcourse.SearchCoursePost;
import cn.hkxj.platform.spider.newmodel.searchcourse.SearchCourseResult;
import cn.hkxj.platform.spider.newmodel.searchteacher.SearchTeacherPost;
import cn.hkxj.platform.spider.newmodel.searchteacher.SearchTeacherResult;
import com.google.common.base.Splitter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import javax.annotation.Resource;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.*;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(classes = PlatformApplication.class)
@WebAppConfiguration
public class NewUrpSpiderServiceTest {
    @Resource
    private NewUrpSpiderService newUrpSpiderService;
    @Resource
    private NewGradeSearchService newGradeSearchService;
    @Resource
    private StudentDao studentDao;
    @Resource
    private CourseDao courseDao;
    @Resource
    private CourseTimeTableDetailDao courseTimeTableDetailDao;
    @Resource
    private CourseTimeTableDao courseTimeTableDao;



    @Test
    public void getExamTime(){
        Student student = studentDao.selectStudentByAccount(2019020856);

        List<UrpExamTime> examTime = newUrpSpiderService.getExamTime(student);

        for (UrpExamTime urpExamTime : examTime) {
            System.out.println(urpExamTime);
        }


    }

    @Test
    public void getCurrentTermGrade(){
        Student student = studentDao.selectStudentByAccount(2019020856);

        CurrentGrade grade = newUrpSpiderService.getCurrentTermGrade(student);

        for (UrpGradeForSpider gradeForSpider : grade.getList()) {
            System.out.println(gradeForSpider);
        }


    }

    @Test
    public void getCurrentGeneralGrade(){
        Student student = studentDao.selectStudentByAccount(2018024221);

        for (UrpGeneralGradeForSpider grade : newUrpSpiderService.getCurrentGeneralGrade(student)) {
            System.out.println(grade);
        }



    }

    @Test
    public void get(){
        Student student = studentDao.selectStudentByAccount(2019020856);

        UrpCourseForSpider course = newUrpSpiderService.getCourseFromSpider(student, "2106147");

        System.out.println(course);


    }
}