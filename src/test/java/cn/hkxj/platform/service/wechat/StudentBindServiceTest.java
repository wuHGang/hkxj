package cn.hkxj.platform.service.wechat;

import cn.hkxj.platform.exceptions.OpenidExistException;
import cn.hkxj.platform.exceptions.PasswordUncorrectException;
import cn.hkxj.platform.exceptions.ReadTimeoutException;
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
        try {
            studentBindService.studentBind("2","2014025846","3664");
        } catch (PasswordUncorrectException | ReadTimeoutException | OpenidExistException e) {
            log.error(e.toString());
        }
    }
}