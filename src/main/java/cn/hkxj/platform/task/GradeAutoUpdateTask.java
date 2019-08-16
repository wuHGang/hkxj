package cn.hkxj.platform.task;

import cn.hkxj.platform.builder.TemplateBuilder;
import cn.hkxj.platform.config.wechat.WechatMpConfiguration;
import cn.hkxj.platform.config.wechat.WechatMpPlusProperties;
import cn.hkxj.platform.config.wechat.WechatTemplateProperties;
import cn.hkxj.platform.pojo.*;
import cn.hkxj.platform.pojo.constant.MiniProgram;
import cn.hkxj.platform.pojo.constant.SubscribeScene;
import cn.hkxj.platform.pojo.wechat.Openid;
import cn.hkxj.platform.service.GradeSearchService;
import cn.hkxj.platform.service.NewGradeSearchService;
import cn.hkxj.platform.service.OpenIdService;
import cn.hkxj.platform.service.ScheduleTaskService;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.kefu.WxMpKefuMessage;
import me.chanjar.weixin.mp.bean.template.WxMpTemplateData;
import me.chanjar.weixin.mp.bean.template.WxMpTemplateMessage;
import org.apache.commons.lang3.BooleanUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.*;
import java.util.concurrent.*;

/**
 * @author Yuki
 * @date 2019/6/6 10:55
 */
@Slf4j
@Service
public class GradeAutoUpdateTask {
    //这里设置拒绝策略为调用者运行，这样可以降低产生任务的速率
    private static ExecutorService gradeAutoUpdatePool = new ThreadPoolExecutor(10, 10,
            0L, TimeUnit.MILLISECONDS, new LinkedBlockingDeque<>(20), new ThreadPoolExecutor.CallerRunsPolicy());

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

    @Value("scheduled.gradeUpdate")
    private String updateSwitch;

//    @Async
//    @Scheduled(cron = "0 0 0/2 * * ?") //每两个小时执行一次
//    @Scheduled(cron = "0/60 * * * * ?") //每两个小时执行一次
    void autoUpdateGrade() {
        //执行前，检查定时任务的可用性
        if (isTaskEnable()) {
            return;
        }
        //内部的结果是appid和所有与该appid相关的定时任务
        Map<String, List<ScheduleTask>> appMappingScheduleTask =
                scheduleTaskService.getSubscribeData(Integer.parseInt(SubscribeScene.GRADE_AUTO_UPDATE.getScene()));
        appMappingScheduleTask.forEach((appid, tasks) -> {
            WxMpService wxMpService = WechatMpConfiguration.getMpServices().get(appid);
            tasks.forEach(task -> processScheduleTask(task, wxMpService, appid));
        });
    }

    /**
     * 处理每一个定时任务
     * @param task 定时任务
     * @param wxMpService wxMpService
     * @param appid 公众号的appid
     */
    private void processScheduleTask(ScheduleTask task, WxMpService wxMpService, String appid){
        Student student = openIdService.getStudentByOpenId(task.getOpenid(), task.getAppid());
        //通过appid和openid如果没找到相应的学生信息，跳出循环并记录日志
        if (Objects.isNull(student)) {
            log.info("appid:{} openid:{} grade update failed. no opposite student info", task.getAppid(), task.getOpenid());
            return;
        }
        //如果学生对应的密码不正确
        if(!isUrpPasswordCorrect(student)){
            processUrpPasswordNotCorrect(task, wxMpService, appid);
            return;
        }
//        CompletableFuture.supplyAsync(() -> {
//            //获取更新的结果，并将结果转换成一个字符串
//            List<GradeAndCourse> crawlingResult = gradeSearchService.getCurrentGradeFromSpider(student);
//            //saveGradeAndCourse会返回新更新的成绩
//            return gradeSearchService.saveGradeAndCourse(student, crawlingResult);
//        }, gradeAutoUpdatePool).whenComplete((result, exception) -> missionComplete(result, task, wxMpService, appid));
    }

    /**
     * 当使用urp爬虫密码错误时，会将student的isCorrect属性置为0。
     * 所以当isUrpPassowrdCorrect返回false时。解绑所有的绑定
     * @param task 定时任务
     * @param wxMpService wxMpService
     * @param appid 公众号的appid
     */
    private void processUrpPasswordNotCorrect(ScheduleTask task, WxMpService wxMpService, String appid){
        Openid openid = openIdService.getOpenid(task.getOpenid(), appid).get(0);
        //将plus和pro都解绑
        openIdService.openIdUnbindAllPlatform(openid, appid);
        //将其相关联的定时任务也设为不可用
        scheduleTaskService.updateSubscribeStatus(task, ScheduleTaskService.FUNCTION_DISABLE);
        if(isPlus(appid)){
            List<WxMpTemplateData> templateData = templateBuilder.assemblyTemplateContentForTips(ERROR_CONTENT);
            WxMpTemplateMessage wxMpTemplateMessage =
                    templateBuilder.buildWithNoMiniProgram(task.getOpenid(),
                            templateData, wechatTemplateProperties.getPlusTipsTemplateId(), BIND_URL);
            try {
                wxMpService.getTemplateMsgService().sendTemplateMsg(wxMpTemplateMessage);
            } catch (WxErrorException e) {
                log.error("send template message fail openid:{} appid:{}", task.getOpenid(), appid);
            }
        } else {
            sendKefuMessage(wxMpService, task.getOpenid(), ERROR_CONTENT);
            sendKefuMessage(wxMpService, task.getOpenid(), BIND_URL);
        }
    }

    /**
     * 当异步任务完成时进行执行这一步骤
     * @param result 异步任务的计算结果
     * @param task  定时任务
     * @param wxMpService wxMpService
     * @param appid 公众号的appid
     */
    private void missionComplete(List<GradeAndCourse> result, ScheduleTask task, WxMpService wxMpService, String appid){
        //如果更新结果的集合是空，直接结束此任务
        if (CollectionUtils.isEmpty(result)) {
            return;
        }
        if (isPlus(appid)) {
            //plus处理过程
            sendTemplateMessage(task, wxMpService, result);
        } else {
            //pro处理过程
//            String content = gradeSearchService.gradeListToText(result);
//            sendKefuMessage(wxMpService, task.getOpenid(), content);
        }
    }

    /**
     * plus的处理过程
     * @param task 定时任务
     * @param wxMpService wxMpService
     * @param pendingProcess 待处理的集合
     */
    private void sendTemplateMessage(ScheduleTask task, WxMpService wxMpService, List<GradeAndCourse> pendingProcess) {
        for (GradeAndCourse gradeAndCourse : pendingProcess) {
            //因为一次模板消息只能处理一个成绩，所以循环处理
            List<WxMpTemplateData> data = templateBuilder.assemblyTemplateContentForGradeUpdate(gradeAndCourse);
            WxMpTemplateMessage.MiniProgram miniProgram = new WxMpTemplateMessage.MiniProgram();
            miniProgram.setAppid(MiniProgram.APPID.getValue());
            miniProgram.setPagePath(MiniProgram.GRADE_PATH.getValue());
            WxMpTemplateMessage templateMessage =
                    templateBuilder.buildWithNoUrl(task.getOpenid(), data,  wechatTemplateProperties.getPlusGradeUpdateTemplateId(), miniProgram);
            try {
                wxMpService.getTemplateMsgService().sendTemplateMsg(templateMessage);
                log.info("send template message success openid:{}", task.getOpenid());
            } catch (WxErrorException e) {
                log.error("send templdate message fail message:{}", e.getMessage());
            }
        }
    }

    /**
     * 发送客服消息
     *
     * @param wxMpService wxMpService
     * @param openid      openid
     * @param content     内容
     */
    private void sendKefuMessage(WxMpService wxMpService, String openid, String content) {
        WxMpKefuMessage wxMpKefuMessage = new WxMpKefuMessage();
        wxMpKefuMessage.setToUser(openid);
        wxMpKefuMessage.setMsgType("text");
        wxMpKefuMessage.setContent(content);
        try {
            wxMpService.getKefuService().sendKefuMessage(wxMpKefuMessage);
            log.info("send kefuMessage success");
        } catch (WxErrorException e) {
            log.error("send kefuMessage fail message:{}", e.getMessage());
        }

    }

    /**
     * 判断当前任务属于那个公众号
     * @param appid 公众号的appid
     * @return 是否属于
     */
    private boolean isPlus(String appid){
        return Objects.equals(wechatMpPlusProperties.getAppId(), appid);
    }

    /**
     * 判断student的isCorrect属性
     * @param student 学生实体
     * @return isCorrect的值
     */
    private boolean isUrpPasswordCorrect(Student student){
        return student.getIsCorrect();
    }

    /**
     * 根据配置文件中的属性，判断该定时任务是否可用
     *
     * @return 可用结果
     */
    private boolean isTaskEnable() {
        return BooleanUtils.toBoolean(updateSwitch);
    }

}