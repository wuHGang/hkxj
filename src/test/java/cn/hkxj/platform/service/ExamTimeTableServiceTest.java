package cn.hkxj.platform.service;

import cn.hkxj.platform.PlatformApplication;
import cn.hkxj.platform.pojo.Exam;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = PlatformApplication.class)
@WebAppConfiguration
public class ExamTimeTableServiceTest {
    @Resource
    private ExamTimeTableService examTimeTableService;


    @Test
    public void getExamtimeList() {

        for (Exam exam : examTimeTableService.getExamTimeList(2019020630)) {
            System.out.println(exam);
        }
    }
}