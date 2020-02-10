package cn.hkxj.platform.dao;

import cn.hkxj.platform.PlatformApplication;
import cn.hkxj.platform.pojo.Course;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author JR Chan
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = PlatformApplication.class)
public class CourseDaoTest {
    @Resource
    private CourseDao courseDao;

    @Test
    public void getAllCourse() {
    }

    @Test
    public void selectCourseByPojo() {
        Course course = new Course().setNum("1102009").setCourseOrder("01");

        List<Course> courses = courseDao.selectCourseByPojo(course);
        System.out.println(courses);
    }

    @Test
    public void selectByNumAndOrder() {
    }

    @Test
    public void insertSelective() {
    }
}