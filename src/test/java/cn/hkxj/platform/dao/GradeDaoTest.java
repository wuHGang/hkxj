package cn.hkxj.platform.dao;

import cn.hkxj.platform.PlatformApplication;
import cn.hkxj.platform.pojo.Grade;
import cn.hkxj.platform.pojo.Student;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

import java.util.List;

import static org.junit.Assert.*;

/**
 * @author junrong.chen
 * @date 2019/6/25
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = PlatformApplication.class)
@Slf4j
public class GradeDaoTest {

    @Resource
    private GradeDao gradeDao;

    @Test
    public void getCurrentGrade() {
        Student student = new Student();
        student.setAccount(2016024069);
        List<Grade> gradeList = gradeDao.getCurrentGrade(student);

        log.info(gradeList.toString());
    }
}