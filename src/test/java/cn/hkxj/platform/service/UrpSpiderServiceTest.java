package cn.hkxj.platform.service;

import cn.hkxj.platform.exceptions.PasswordUncorrectException;
import cn.hkxj.platform.pojo.Student;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

/**
 * @author JR Chan
 * @date 2018/12/15
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class UrpSpiderServiceTest {
    @Resource
    private UrpSpiderService urpSpiderService;

    private Student student;

    @Before
    public void init() {
        student = new Student();
        student.setAccount(2017025971);
        student.setPassword("1");
    }

    @Test
    public void getInformation() throws PasswordUncorrectException {
        Student information = urpSpiderService.getInformation(2017025971, "1");
        System.out.println(information.toString());
    }

    @Test
    public void getCurrentGrade() throws PasswordUncorrectException {

        urpSpiderService.getCurrentGrade(student);
    }
}