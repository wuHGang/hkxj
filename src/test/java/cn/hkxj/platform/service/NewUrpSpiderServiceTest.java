package cn.hkxj.platform.service;

import cn.hkxj.platform.pojo.Student;
import cn.hkxj.platform.spider.NewUrpSpider;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;


@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class NewUrpSpiderServiceTest {
    @Resource
    private NewUrpSpiderService newUrpSpiderService;

    @Test
    public void getVerifyCode() {
        NewUrpSpider urpSpider = new NewUrpSpider("xxx", "xxx");
        String code = urpSpider.getCode("e76ebd7e-2ab1-4d17-85f5-9e17a45c5448");
        System.out.println(code);
    }

    @Test
    public void login() {
        Student student = newUrpSpiderService.getStudentInfo("2014025838", "1");
        log.info(student.toString());

    }



}