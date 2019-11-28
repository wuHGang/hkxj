package cn.hkxj.platform.dao;

import cn.hkxj.platform.PlatformApplication;
import cn.hkxj.platform.pojo.StudentCourseTimeTable;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = PlatformApplication.class)
@Slf4j
public class StudentCourseTimeTableDaoTest {
    @Resource
    private StudentCourseTimeTableDao studentCourseTimeTableDao;

    @Test
    public void insertSelective() {
    }

    @Test
    public void selectByExample() {
        StudentCourseTimeTable table = new StudentCourseTimeTable().setStudentId(2018026589);
        List<StudentCourseTimeTable> list = studentCourseTimeTableDao.selectByExample(table);
        System.out.println(list.size());
    }

    @Test
    public void deleteByAccount() {
        int i = studentCourseTimeTableDao.deleteByAccount(2018026589);
        log.info("delete count {}", i);
    }
}