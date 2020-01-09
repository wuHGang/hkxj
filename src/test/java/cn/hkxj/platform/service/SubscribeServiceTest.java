package cn.hkxj.platform.service;

import cn.hkxj.platform.PlatformApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import static org.junit.Assert.*;

/**
 * @author Yuki
 * @date 2018/11/21 18:45
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = PlatformApplication.class)
@WebAppConfiguration
public class SubscribeServiceTest {

    @Autowired
    private SubscribeService subscribeService;
    @Test
    public void isSubscribe() {
        System.out.println( subscribeService.isSubscribe("asdassdasdasda"));
    }

//    @Test
//    public void insertOneSubOpenid() {
//        subscribeService.insertOneSubOpenid("asd", "1000");
//    }

    @Test
    public void updateCourseSubscribeMsgState() {
        subscribeService.updateCourseSubscribeMsgState("asda", (byte)1);
    }

    @Test
    public void getGradeUpdateSubscribeStudent() {
        subscribeService.getGradeUpdateSubscribeStudent();
    }
}