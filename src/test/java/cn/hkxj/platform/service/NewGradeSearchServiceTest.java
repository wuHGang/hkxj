package cn.hkxj.platform.service;

import cn.hkxj.platform.PlatformApplication;
import cn.hkxj.platform.pojo.Student;
import cn.hkxj.platform.spider.NewUrpSpider;
import cn.hkxj.platform.spider.model.VerifyCode;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.MDC;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import java.util.Scanner;

import static org.junit.Assert.*;

/**
 * @author Yuki
 * @date 2019/8/1 20:15
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = PlatformApplication.class)
@WebAppConfiguration
public class NewGradeSearchServiceTest {

    @Resource
    private NewGradeSearchService newGradeSearchService;
    @Resource
    private HttpSession httpSession;

    @Test
    public void test(){
        NewUrpSpider spider = new NewUrpSpider("xxx", "xxx");
        VerifyCode captcha = spider.getCaptcha();
        captcha.write("C:\\Users\\Yuki\\Desktop\\pic.jpg");
        Student student = new Student();
        student.setAccount(2016024170);
        student.setPassword("1");
        System.out.println(newGradeSearchService.getCurrentGrade(student));
    }
}