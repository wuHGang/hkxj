package cn.hkxj.platform.service.wechat;

import cn.hkxj.platform.pojo.Student;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class StudentBindServiceTest {
    @Autowired
    StudentBindService studentBindService;

    @Test
    public void studentBind() {
        Student student = studentBindService.studentLogin("2016023344", "1");
        assert student != null;
    }

}