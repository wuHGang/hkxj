package cn.hkxj.platform.service;

import cn.hkxj.platform.PlatformApplication;
import cn.hkxj.platform.exceptions.PasswordUncorrectException;
import cn.hkxj.platform.pojo.ExamTimeTable;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import javax.annotation.Resource;

import java.util.ArrayList;

import static org.junit.Assert.*;

/**
 * @author junrong.chen
 * @date 2018/11/26
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = PlatformApplication.class)
@WebAppConfiguration
public class AppSpiderServiceTest {
    @Resource
    private AppSpiderService appSpiderService;

    @Test
    public void getExamByAccount() throws PasswordUncorrectException {
        ArrayList<ExamTimeTable> examByAccount = appSpiderService.getExamByAccount(2017023523);
        System.out.println(examByAccount.toString());
    }
}