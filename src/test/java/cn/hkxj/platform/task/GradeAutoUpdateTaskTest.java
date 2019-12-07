package cn.hkxj.platform.task;

import cn.hkxj.platform.dao.ScheduleTaskDao;
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
@SpringBootTest
@Slf4j
public class GradeAutoUpdateTaskTest {
    @Resource
    private GradeAutoUpdateTask gradeAutoUpdateTask;
    @Resource
    private ScheduleTaskDao scheduleTaskDao;

    @Test
    public void autoUpdateGrade() {
        // 2106147
        ScheduleTask task = scheduleTaskDao.selectByOpenid("oCxRO1G9N755dOY5dwcT5l3IlS3Y", SubscribeScene.GRADE_AUTO_UPDATE);
        gradeAutoUpdateTask.processScheduleTask(task);
    }
}