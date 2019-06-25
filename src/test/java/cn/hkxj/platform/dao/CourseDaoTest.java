package cn.hkxj.platform.dao;

import cn.hkxj.platform.PlatformApplication;
import cn.hkxj.platform.pojo.Course;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.util.Lists;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * @author junrong.chen
 * @date 2019/6/25
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = PlatformApplication.class)
@Slf4j
public class CourseDaoTest {
    @Resource
    private CourseDao courseDao;

    @Test
    public void selectCourseById() {
        ArrayList<String> list = Lists.newArrayList("1606002", "1606026", "TS17028");
        List<Course> courses = courseDao.selectCourseByUid(list);
        log.info(courses.toString());
    }
}