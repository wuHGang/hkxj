package cn.hkxj.platform.task;

import cn.hkxj.platform.MDCThreadPool;
import cn.hkxj.platform.dao.GradeDao;
import cn.hkxj.platform.dao.ScheduleTaskDao;
import cn.hkxj.platform.pojo.ScheduleTask;
import cn.hkxj.platform.pojo.Student;
import cn.hkxj.platform.pojo.constant.SubscribeScene;
import cn.hkxj.platform.service.NewGradeSearchService;
import cn.hkxj.platform.service.OpenIdService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.MDC;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class GradeAutoUpdateTaskTest {
    @Resource
    private GradeAutoUpdateTask gradeAutoUpdateTask;
    @Resource
    private ScheduleTaskDao scheduleTaskDao;
    @Resource
    private OpenIdService openIdService;
    @Resource
    private GradeDao gradeDao;
    @Resource
    private NewGradeSearchService newGradeSearchService;

    //这里设置拒绝策略为调用者运行，这样可以降低产生任务的速率
    private static ExecutorService gradeAutoUpdatePool = new MDCThreadPool(8, 8,
            0L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<>(), r -> new Thread(r, "gradeUpdate"));

    @Test
    public void processScheduleTask() {
        // 2106147
        ScheduleTask task = scheduleTaskDao.selectByOpenid("oCxRO1IyJi3uMaNkkS_QmDuka5w8", SubscribeScene.GRADE_AUTO_UPDATE);
        Student student = openIdService.getStudentByOpenId(task.getOpenid(), task.getAppid());

        System.out.println(student);
//        gradeAutoUpdateTask.processScheduleTask(task);
    }

    @Test
    public void autoUpdateGrade() {
        List<ScheduleTask> subscribeTask = scheduleTaskDao.getPlusSubscribeTask(SubscribeScene.GRADE_AUTO_UPDATE);


        CountDownLatch latch = new CountDownLatch(subscribeTask.size());
        for (ScheduleTask task : subscribeTask) {
            CompletableFuture.runAsync(() -> {
                try {
                    UUID uuid = UUID.randomUUID();
                    MDC.put("traceId", "gradeUpdateTask-"+uuid.toString());

                    Student student = openIdService.getStudentByOpenId(task.getOpenid(), task.getAppid());

                    gradeAutoUpdateTask.getUpdateList(student);

                } catch (Exception e) {
                    log.error("grade update task {} error ",task, e);

                } finally {
                    MDC.clear();
                    latch.countDown();
                }

            }, gradeAutoUpdatePool);
        }
        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        log.info("{} grade update task finish", subscribeTask.size());
    }

    @Test
    public void test(){
        System.out.println(gradeAutoUpdateTask.isSwitchOn());
    }
}