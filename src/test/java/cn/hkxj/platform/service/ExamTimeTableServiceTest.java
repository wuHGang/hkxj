package cn.hkxj.platform.service;

import cn.hkxj.platform.PlatformApplication;
import cn.hkxj.platform.pojo.Exam;
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
public class ExamTimeTableServiceTest {
    @Resource
    private ExamTimeTableService examTimeTableService;


    @Test
    public void getExamtimeList() {

        for (Exam exam : examTimeTableService.getExamtimeList(2017023480)) {
            System.out.println(exam);
        }
        ;
    }
}