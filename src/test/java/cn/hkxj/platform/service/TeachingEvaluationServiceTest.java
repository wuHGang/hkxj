package cn.hkxj.platform.service;

import cn.hkxj.platform.PlatformApplication;
import cn.hkxj.platform.dao.StudentDao;
import cn.hkxj.platform.pojo.Student;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import javax.annotation.Resource;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = PlatformApplication.class)
@WebAppConfiguration
public class TeachingEvaluationServiceTest {
    @Resource
    private TeachingEvaluationService teachingEvaluationService;
    @Resource
    private StudentDao studentDao;

    @Test
    public void evaluate() {

        Student student = studentDao.selectStudentByAccount(2016024454);
        teachingEvaluationService.evaluate(student);
    }
}