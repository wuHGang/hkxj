package cn.hkxj.platform.dao;

import cn.hkxj.platform.PlatformApplication;
import cn.hkxj.platform.pojo.UrpCourse;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = PlatformApplication.class)
@Slf4j
public class UrpCourseDaoTest {
    @Resource
    private UrpCourseDao urpCourseDao;

    @Test
    public void insertUrpCourse() {
    }

    @Test
    public void getUrpCourseByCourseId() {
        UrpCourse course = urpCourseDao.getUrpCourseByCourseId("1802050");
        System.out.println(course.toString());
    }

    @Test
    public void ifExistCourse() {
    }
}