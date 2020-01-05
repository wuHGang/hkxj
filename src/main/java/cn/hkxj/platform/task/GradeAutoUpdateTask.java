package cn.hkxj.platform.task;

import cn.hkxj.platform.MDCThreadPool;
import cn.hkxj.platform.builder.TemplateBuilder;
import cn.hkxj.platform.config.wechat.MiniProgramProperties;
import cn.hkxj.platform.config.wechat.WechatMpConfiguration;
import cn.hkxj.platform.config.wechat.WechatMpPlusProperties;
import cn.hkxj.platform.config.wechat.WechatTemplateProperties;
import cn.hkxj.platform.dao.ScheduleTaskDao;
import cn.hkxj.platform.exceptions.UrpEvaluationException;
import cn.hkxj.platform.exceptions.UrpException;
import cn.hkxj.platform.exceptions.UrpTimeoutException;
import cn.hkxj.platform.pojo.ScheduleTask;
import cn.hkxj.platform.pojo.Student;
import cn.hkxj.platform.pojo.constant.MiniProgram;
import cn.hkxj.platform.pojo.constant.SubscribeScene;
import cn.hkxj.platform.pojo.vo.GradeVo;
import cn.hkxj.platform.pojo.wechat.miniprogram.SubscribeGradeData;
import cn.hkxj.platform.pojo.wechat.miniprogram.SubscribeMessage;
import cn.hkxj.platform.pojo.wechat.miniprogram.SubscribeValue;
import cn.hkxj.platform.service.NewGradeSearchService;
import cn.hkxj.platform.service.OpenIdService;
import cn.hkxj.platform.service.ScheduleTaskService;
import cn.hkxj.platform.service.wechat.MiniProgramService;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.kefu.WxMpKefuMessage;
import me.chanjar.weixin.mp.bean.template.WxMpTemplateData;
import me.chanjar.weixin.mp.bean.template.WxMpTemplateMessage;
import org.apache.commons.lang3.BooleanUtils;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
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
    private OpenIdService openIdService;
    @Resource
    private WechatMpPlusProperties wechatMpPlusProperties;
    @Resource
    private TemplateBuilder templateBuilder;
    @Resource
    private WechatTemplateProperties wechatTemplateProperties;
    @Resource
    private ScheduleTaskDao scheduleTaskDao;
    @Resource
    private MiniProgramProperties miniProgramProperties;
    @Resource
    private MiniProgramService miniProgramService;

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

        while (true){
            try {
                List<ScheduleTask> subscribeTask = scheduleTaskDao.getPlusSubscribeTask(SubscribeScene.GRADE_AUTO_UPDATE);
                List<ScheduleTask> miniProgramSubscribeTask = scheduleTaskDao.getMiniProgramSubscribeTask(SubscribeScene.GRADE_AUTO_UPDATE);

                log.info("grade update task run, queue size {}", queue.size());
                queue.addAll(subscribeTask.stream().map(UrpFetchTask::new).collect(Collectors.toList()));
                queue.addAll(miniProgramSubscribeTask.stream().map(UrpFetchTask::new).collect(Collectors.toList()));
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
                                    }else {
                                        log.error("grade update task {} urp exception {}", task, e.getMessage());
                                    }
                                } catch (UrpEvaluationException e){
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
            }catch (Exception e){
                continue;
            }
            break;
        }



    }
    void processScheduleTask(ScheduleTask urpFetchTask){
        processScheduleTask(new UrpFetchTask(urpFetchTask));
    }

    /**
     * 处理每一个定时任务
     */
    private void processScheduleTask(UrpFetchTask urpFetchTask) {
        ScheduleTask task = urpFetchTask.scheduleTask;
        Student student = openIdService.getStudentByOpenId(task.getOpenid(), task.getAppid());
        if(student == null){
            return;
        }
        List<GradeVo> updateList = getUpdateList(student);
        WxMpService service = WechatMpConfiguration.getMpServices().get(task.getAppid());
        if (!CollectionUtils.isEmpty(updateList)) {
            for (GradeVo gradeVo : updateList.stream()
                    .filter(x -> !x.getScore().equals(-1.0))
                    .collect(Collectors.toList())) {

                if(miniProgramProperties.getAppId().equals(task.getAppid())){
                    SubscribeMessage message = new SubscribeMessage();
                    message.setToUser(task.getOpenid())
                            .setTemplateId("dmE0nyulM8OVcUs-KojDxCYECrKTmzOGDkEUUm2T5UE")
                            .setPage(MiniProgram.GRADE_PATH.getValue())
                            .setData(new SubscribeGradeData()
                                    .setCourseName(new SubscribeValue(gradeVo.getCourse().getName()))
                                    .setName(new SubscribeValue(student.getName()))
                                    .setScore(new SubscribeValue(gradeVo.getScore().toString()))
                                    .setRemark(new SubscribeValue("需要获取新提醒需要重新点击订阅~"))
                            );

                    miniProgramService.sendSubscribeMessage(message);
                }else if(wechatMpPlusProperties.getAppId().equals(task.getAppid())){
                    List<WxMpTemplateData> templateData = templateBuilder.gradeToTemplateData(student, gradeVo);
                    WxMpTemplateMessage.MiniProgram miniProgram = new WxMpTemplateMessage.MiniProgram();
                    miniProgram.setAppid(MiniProgram.APP_ID);
                    miniProgram.setPagePath(MiniProgram.GRADE_PATH.getValue());
                    //构建一个课程推送的模板消息
                    WxMpTemplateMessage templateMessage =
                            templateBuilder.build(task.getOpenid(), templateData, wechatTemplateProperties.getPlusGradeUpdateTemplateId(),
                                    miniProgram);

                    sendTemplateMessage(service, templateMessage, task, "gradeUpdate");
                }else {
                    throw new RuntimeException("unknown appId "+ task.getAppid());
                }



            }

        }
    }


    List<GradeVo> getUpdateList(Student student) {
        if (!student.getIsCorrect()) {
            return Collections.emptyList();
        }
        List<GradeVo> termGrade = newGradeSearchService.getCurrentTermGradeSync(student);
        return termGrade.stream().filter(GradeVo::isUpdate).collect(Collectors.toList());
    }


    /**
     * 判断当前任务属于那个公众号
     *
     * @param appid 公众号的appid
     * @return 是否属于
     */
    private boolean isPlus(String appid) {
        return Objects.equals(wechatMpPlusProperties.getAppId(), appid);
    }

    /**
     * 根据配置文件中的属性，判断该定时任务是否可用
     *
     * @return 可用结果
     */
    boolean isSwitchOn() {
        log.info(updateSwitch);
        return BooleanUtils.toBoolean(updateSwitch);
    }

    @Data
    private static class UrpFetchTask {
        private int timeoutCount;
        private ScheduleTask scheduleTask;

        UrpFetchTask(ScheduleTask scheduleTask) {
            this.scheduleTask = scheduleTask;
        }
    }


}