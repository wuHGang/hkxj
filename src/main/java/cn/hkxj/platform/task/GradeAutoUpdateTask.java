package cn.hkxj.platform.task;

import cn.hkxj.platform.MDCThreadPool;
import cn.hkxj.platform.builder.TemplateBuilder;
import cn.hkxj.platform.config.wechat.WechatTemplateProperties;
import cn.hkxj.platform.exceptions.UrpEvaluationException;
import cn.hkxj.platform.exceptions.UrpException;
import cn.hkxj.platform.pojo.Student;
import cn.hkxj.platform.pojo.constant.MiniProgram;
import cn.hkxj.platform.pojo.vo.GradeVo;
import cn.hkxj.platform.pojo.wechat.miniprogram.SubscribeGradeData;
import cn.hkxj.platform.pojo.wechat.miniprogram.SubscribeMessage;
import cn.hkxj.platform.pojo.wechat.miniprogram.SubscribeValue;
import cn.hkxj.platform.service.NewGradeSearchService;
import cn.hkxj.platform.service.SubscribeService;
import cn.hkxj.platform.service.wechat.SendMessageService;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.mp.bean.template.WxMpTemplateData;
import me.chanjar.weixin.mp.bean.template.WxMpTemplateMessage;
import org.apache.commons.lang3.BooleanUtils;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.*;
import java.util.concurrent.*;
import java.util.stream.Collectors;

/**
 * @author Yuki
 * @date 2019/6/6 10:55
 */
@Slf4j
@Service
public class GradeAutoUpdateTask extends BaseSubscriptionTask {
    //这里设置拒绝策略为调用者运行，这样可以降低产生任务的速率
    private static ExecutorService gradeAutoUpdatePool = new MDCThreadPool(8, 8,
            0L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<>(), r -> new Thread(r, "gradeUpdate"));

    private static final String ERROR_CONTENT = "请更新你的账号\n" +
            "你的账号密码可能有误，请点击这里的链接进行更新。否则部分功能将无法使用\n";
    private static final String BIND_URL = "https://platform.hackerda.com/platform/bind";


    @Resource
    private NewGradeSearchService newGradeSearchService;
    @Resource
    private TemplateBuilder templateBuilder;
    @Resource
    private WechatTemplateProperties wechatTemplateProperties;
    @Resource
    private SubscribeService subscribeService;
    @Resource
    private SendMessageService sendMessageService;

    private static final BlockingQueue<UrpFetchTask> queue = new LinkedBlockingQueue<>();

    @Value("${scheduled.gradeUpdate}")
    private String updateSwitch;

    //    @Async
    @Scheduled(cron = "0 0/20 * * * ? ")
    //每20分钟执行一次
    public void autoUpdateGrade() {
        if (!isSwitchOn()) {
            return;
        }
        log.info("grade update task run");
        while (true) {
            try {

                Set<UrpFetchTask> fetchTasks = subscribeService.getGradeUpdateSubscribeStudent()
                        .stream().map(UrpFetchTask::new)
                        .collect(Collectors.toSet());

                queue.addAll(fetchTasks);

                log.info("grade update task run, queue size {}", queue.size());

                for (int x = 0; x < 8; x++) {
                    CompletableFuture.runAsync(() -> {
                        UrpFetchTask task;
                        try {
                            while ((task = queue.take()) != null) {
                                UUID uuid = UUID.randomUUID();
                                MDC.put("traceId", "gradeUpdateTask-" + uuid.toString());
                                try {
                                    processScheduleTask(task);
                                } catch (UrpException e) {
                                    // TODO  这个可以根据异常类来优化
                                    task.timeoutCount++;
                                    if (task.timeoutCount < 3) {
                                        queue.add(task);
                                    } else {
                                        log.error("grade update task {} urp exception {}", task, e.getMessage());
                                    }
                                } catch (UrpEvaluationException e) {
                                    log.debug("{} 评估未完成", task);
                                } catch (Exception e) {
                                    log.error("grade update task {} error ", task, e);
                                } finally {
                                    MDC.clear();
                                }
                            }
                        } catch (InterruptedException e) {
                            log.error("get grade update task error", e);
                        }

                    }, gradeAutoUpdatePool);
                }
            } catch (Exception e) {
                continue;
            }
            break;
        }


    }

    void processScheduleTask(Student student) {
        processScheduleTask(new UrpFetchTask(student));
    }

    /**
     * 处理每一个定时任务
     */
    private void processScheduleTask(UrpFetchTask urpFetchTask) {

        Student student = urpFetchTask.student;
        if (student == null) {
            return;
        }
        List<GradeVo> updateList = getUpdateList(student);

        if (!CollectionUtils.isEmpty(updateList)) {
            for (GradeVo gradeVo : updateList) {
                sendGradeToAccount(student, gradeVo);
            }

        }
    }

    public void sendMessageToApp(Student student, GradeVo gradeVo){
        SubscribeMessage<SubscribeGradeData> appMessage = new SubscribeMessage<>();
        appMessage.setTemplateId("dmE0nyulM8OVcUs-KojDxCYECrKTmzOGDkEUUm2T5UE")
                .setPage(MiniProgram.GRADE_PATH.getValue())
                .setData(new SubscribeGradeData()
                        .setCourseName(new SubscribeValue(gradeVo.getCourse().getName()))
                        .setName(new SubscribeValue(student.getName()))
                        .setScore(new SubscribeValue(gradeVo.getScore().toString()))
                        .setRemark(new SubscribeValue("如需获取新提醒，请重新点击订阅~")));
        sendMessageService.sendAppTemplateMessage(appMessage, student.getAccount());

    }

    public void sendMessageToPlus(Student student, GradeVo gradeVo){
        List<WxMpTemplateData> templateData = templateBuilder.gradeToTemplateData(student, gradeVo);
        WxMpTemplateMessage.MiniProgram miniProgram = new WxMpTemplateMessage.MiniProgram();
        miniProgram.setAppid(MiniProgram.APP_ID);
        miniProgram.setPagePath(MiniProgram.GRADE_PATH.getValue());
        WxMpTemplateMessage templateMessage =
                templateBuilder.build("", templateData, wechatTemplateProperties.getPlusGradeUpdateTemplateId(),
                        miniProgram);

        sendMessageService.sendPlusTemplateMessageByAccount(templateMessage, student.getAccount());


    }

    public void sendGradeToAccount(Student student, GradeVo gradeVo) {

        sendMessageToApp(student, gradeVo);
        sendMessageToPlus(student, gradeVo);

    }


    List<GradeVo> getUpdateList(Student student) {

        List<GradeVo> termGrade = newGradeSearchService.getCurrentTermGradeVoSync(student);
        return termGrade.stream()
                .filter(GradeVo::isUpdate)
                .filter(x -> !x.getScore().equals(-1.0))
                .filter(x-> !x.getCoursePropertyCode().equals("003"))
                .collect(Collectors.toList());
    }

    /**
     * 根据配置文件中的属性，判断该定时任务是否可用
     *
     * @return 可用结果
     */
    boolean isSwitchOn() {
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        int hour = cal.get(Calendar.HOUR_OF_DAY);
        log.info("switch  {} hour {}", updateSwitch, hour);
        return BooleanUtils.toBoolean(updateSwitch) && (hour > 7 || hour < 1);
    }

    @Data
    private static class UrpFetchTask {
        private int timeoutCount;
        private Student student;

        UrpFetchTask(Student student) {
            this.student = student;
        }
    }


}