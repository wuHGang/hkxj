package cn.hkxj.platform.dao;

import cn.hkxj.platform.PlatformApplication;
import cn.hkxj.platform.pojo.Student;
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
public class StudentDaoTest {
    @Resource
    private StudentDao studentDao;

    @Test
    public void updatePassword() {
        studentDao.updatePassword("2014025838", "2");
    }

    @Test
    public void selectStudentByAccount() {
        Student student = studentDao.selectStudentByAccount(2017025299);
        System.out.println(student);
    }

    @Test
    public void selectAllStudent() {
        List<Student> studentList = studentDao.selectAllStudent();
        System.out.println(studentList.size());
    }
}