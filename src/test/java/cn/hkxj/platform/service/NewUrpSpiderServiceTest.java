package cn.hkxj.platform.service;

import cn.hkxj.platform.pojo.Student;
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
        newUrpSpiderService.getVerifyCode().write("");
    }

    @Test
    public void login() {
        Student student = newUrpSpiderService.getStudentInfo("2014025838");
        log.info(student.toString());

    }

    @Test
    public void canUseCookie(){
        boolean canUseCookie = newUrpSpiderService.canUseCookie("2014025838");
        System.out.println(canUseCookie);
    }

}