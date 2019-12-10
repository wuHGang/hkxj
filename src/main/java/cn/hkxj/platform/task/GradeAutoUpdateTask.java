package cn.hkxj.platform.task;

import cn.hkxj.platform.MDCThreadPool;
import cn.hkxj.platform.builder.TemplateBuilder;
import cn.hkxj.platform.config.wechat.WechatMpConfiguration;
import cn.hkxj.platform.config.wechat.WechatMpPlusProperties;
import cn.hkxj.platform.config.wechat.WechatTemplateProperties;
import cn.hkxj.platform.dao.ScheduleTaskDao;
import cn.hkxj.platform.pojo.ScheduleTask;
import cn.hkxj.platform.pojo.Student;
import cn.hkxj.platform.pojo.constant.MiniProgram;
import cn.hkxj.platform.pojo.constant.SubscribeScene;
import cn.hkxj.platform.pojo.vo.GradeVo;
import cn.hkxj.platform.service.NewGradeSearchService;
import cn.hkxj.platform.service.OpenIdService;
import cn.hkxj.platform.service.ScheduleTaskService;
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
    private ScheduleTaskService scheduleTaskService;
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

    @Value("${scheduled.gradeUpdate}")
    private String updateSwitch;

    //    @Async
    @Scheduled(cron = "0 0/20 * * * ? ")
    //每20分钟执行一次
    void autoUpdateGrade() {
        if(!isSwitchOn()){
            return;
        }
        List<ScheduleTask> subscribeTask = scheduleTaskDao.getPlusSubscribeTask(SubscribeScene.GRADE_AUTO_UPDATE);
        log.info("{} grade update task to run", subscribeTask.size());


        for (ScheduleTask task : subscribeTask) {
            CompletableFuture.runAsync(() -> {
                try {
                    UUID uuid = UUID.randomUUID();
                    MDC.put("traceId", "gradeUpdateTask-"+uuid.toString());
                    processScheduleTask(task);
                } catch (Exception e) {
                    log.error("grade update task {} error ",task, e);
                } finally {
                    MDC.clear();
                }

            }, gradeAutoUpdatePool);
        }

    }

    /**
     * 处理每一个定时任务
     *
     * @param task 定时任务
     */
    void processScheduleTask(ScheduleTask task) {
        Student student = openIdService.getStudentByOpenId(task.getOpenid(), task.getAppid());
        if(!student.getIsCorrect()){
            return;
        }
        List<GradeVo> termGrade = newGradeSearchService.getCurrentTermGradeSync(student);
        List<GradeVo> updateList = termGrade.stream().filter(GradeVo::isUpdate).collect(Collectors.toList());
        WxMpService service = WechatMpConfiguration.getMpServices().get(task.getAppid());
        if (!CollectionUtils.isEmpty(updateList)) {
            for (GradeVo gradeVo : updateList.stream()
                    .filter(x -> !x.getScore().equals(-1.0))
                    .collect(Collectors.toList())) {

                List<WxMpTemplateData> templateData = templateBuilder.gradeToTemplateData(student, gradeVo);
                WxMpTemplateMessage.MiniProgram miniProgram = new WxMpTemplateMessage.MiniProgram();
                miniProgram.setAppid(MiniProgram.APP_ID);
                miniProgram.setPagePath(MiniProgram.GRADE_PATH.getValue());
                //构建一个课程推送的模板消息
                WxMpTemplateMessage templateMessage =
                        templateBuilder.build(task.getOpenid(), templateData, wechatTemplateProperties.getPlusGradeUpdateTemplateId(),
                                miniProgram);

                sendTemplateMessage(service, templateMessage, task, "gradeUpdate");

            }

        }
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

}