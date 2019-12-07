package cn.hkxj.platform.service;

import cn.hkxj.platform.PlatformApplication;
import cn.hkxj.platform.pojo.ScheduleTask;
import cn.hkxj.platform.pojo.constant.SubscribeScene;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import javax.annotation.Resource;

import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = PlatformApplication.class)
@WebAppConfiguration
public class ScheduleTaskServiceTest {
    @Resource
    private ScheduleTaskService scheduleTaskService;

    @Test
    public void getSubscribeData() {
        Map<String, List<ScheduleTask>> data = scheduleTaskService.getSubscribeData(Integer.parseInt(SubscribeScene.GRADE_AUTO_UPDATE.getScene()));

        System.out.println(data);
    }
}