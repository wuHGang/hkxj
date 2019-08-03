package cn.hkxj.platform.utils;

import cn.hkxj.platform.PlatformApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import javax.servlet.ServletConfig;

/**
 * @author Yuki
 * @date 2018/11/5 23:31
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = PlatformApplication.class)
@WebAppConfiguration
public class DateUtilsTest {

    @Test
    public void getCurrentWeek() throws  Exception{

    }

    @Test
    public void getCurrentDay() {
        System.out.println(DateUtils.getCurrentDay());
    }
}