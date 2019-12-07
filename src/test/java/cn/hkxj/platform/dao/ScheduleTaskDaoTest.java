package cn.hkxj.platform.dao;

import cn.hkxj.platform.PlatformApplication;
import cn.hkxj.platform.pojo.ScheduleTask;
import cn.hkxj.platform.pojo.constant.SubscribeScene;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = PlatformApplication.class)
@Slf4j
public class ScheduleTaskDaoTest {
    @Resource
    private ScheduleTaskDao scheduleTaskDao;

    @Test
    public void getPlusSubscribeTask() {
        for (ScheduleTask task : scheduleTaskDao.getPlusSubscribeTask(SubscribeScene.GRADE_AUTO_UPDATE)) {
            System.out.println(task);
        }


    }
}