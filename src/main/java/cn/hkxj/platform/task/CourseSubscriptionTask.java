package cn.hkxj.platform.task;

import cn.hkxj.platform.MDCThreadPool;
import cn.hkxj.platform.builder.TemplateBuilder;
import cn.hkxj.platform.config.wechat.WechatMpConfiguration;
import cn.hkxj.platform.config.wechat.WechatMpPlusProperties;
import cn.hkxj.platform.config.wechat.WechatTemplateProperties;
import cn.hkxj.platform.pojo.ScheduleTask;
import cn.hkxj.platform.pojo.constant.MiniProgram;
import cn.hkxj.platform.pojo.wechat.CourseGroupMsg;
import cn.hkxj.platform.pojo.wechat.CourseSubscriptionMessage;
import cn.hkxj.platform.service.CourseSubscribeService;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.template.WxMpTemplateData;
import me.chanjar.weixin.mp.bean.template.WxMpTemplateMessage;
import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.*;
import java.util.stream.Collectors;

/**
 * @author Yuki
 * @date 2018/11/6 20:36
 */
@Slf4j
@Service
public class CourseSubscriptionTask extends BaseSubscriptionTask {

    private static ExecutorService courseSubscriptionSendPool = new MDCThreadPool(5, 5,
            0L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<>(), r -> new Thread(r, "courseSendThread"));


    @Value("${domain}")
    private String domain;
    @Resource
    private CourseSubscribeService courseSubscribeService;
    @Resource
    private TemplateBuilder templateBuilder;
    @Resource
    private WechatTemplateProperties wechatTemplateProperties;
    @Resource
    private WechatMpPlusProperties wechatMpPlusProperties;
    @Value("${scheduled.sendCourse}")
    private String updateSwitch;

    @Scheduled(cron = "0 0 8 * * ?")//这个cron表达式的意思是星期一到星期五的早上8点执行一次
    void sendCourseRemindMsgForFirstSection() {
        execute(CourseSubscribeService.FIRST_SECTION);
    }

    @Scheduled(cron = "0 50 9 * * ?")//这个cron表达式的意思是星期一到星期五的早上9点50分执行一次
    void sendCourseRemindMsgForSecondSection() {
        execute(CourseSubscribeService.SECOND_SECTION);
    }

    @Scheduled(cron = "0 0 13 * * ?")//这个cron表达式的意思是星期一到星期五的下午13点执行一次
    void sendCourseRemindMsgForThirdSection() {
        execute(CourseSubscribeService.THIRD_SECTION);
    }

    @Scheduled(cron = "0 50 14 * * ?")//这个cron表达式的意思是星期一到星期五的下午14点50分执行一次
    void sendCourseRemindMsgForFourthSection() {
        execute(CourseSubscribeService.FOURTH_SECTION);
    }

    @Scheduled(cron = "0 0 18 * * ?")//这个cron表达式的意思是星期一到星期五的晚上6点执行一次
    void sendCourseRemindMsgForFifthSection() {
        execute(CourseSubscribeService.FIFTH_SECTION);
    }

    public void execute(int section) {
        log.info("send course message task switch {}", updateSwitch);

        if(!BooleanUtils.toBoolean(updateSwitch)){
            return;
        }

        Set<CourseSubscriptionMessage> messageSet = courseSubscribeService.getSubscriptionMessages(section);

        WxMpService wxMpService = getWxMpService(wechatMpPlusProperties.getAppId());
        List<CourseSubscriptionMessage> collect = messageSet.stream()
                .filter(subscriptionMessage -> !Objects.isNull(subscriptionMessage))
                .filter(subscriptionMessage -> !Objects.isNull(subscriptionMessage.getDetailDto()))
                .filter(x -> !StringUtils.isEmpty(x.getPushContent()))
                .collect(Collectors.toList());

        log.info("send course message size {}", collect.size());

        collect.forEach(subscriptionMessage -> courseSubscriptionSendPool.execute(() ->
                        plusMpProcess(subscriptionMessage.getTask(), subscriptionMessage, wxMpService)));
    }

    /**
     * plus的处理过程
     *
     * @param task        定时任务
     * @param msg         课程推送信息
     * @param wxMpService wxMpService
     */
    private void plusMpProcess(ScheduleTask task, CourseSubscriptionMessage msg, WxMpService wxMpService) {
        List<WxMpTemplateData> templateData = templateBuilder.assemblyTemplateContentForCourse(msg);
        //templateData为空时，说明没有对应的课程，所以直接返回不发送消息
        if (Objects.isNull(templateData)) {
            return;
        }
        WxMpTemplateMessage.MiniProgram miniProgram = new WxMpTemplateMessage.MiniProgram();
        miniProgram.setAppid(MiniProgram.APP_ID);
        miniProgram.setPagePath(MiniProgram.COURSE_PATH.getValue());
        String url = domain + "/platform/show/timetable";
        //构建一个课程推送的模板消息
        WxMpTemplateMessage templateMessage =
                templateBuilder.build(task.getOpenid(), templateData, wechatTemplateProperties.getPlusCourseTemplateId(), miniProgram, url);
        sendTemplateMessage(wxMpService, templateMessage, task, "course");
    }


    /**
     * 根据appid获取相应的wxMpService
     *
     * @param appid appid
     * @return wxMpService
     */
    private WxMpService getWxMpService(String appid) {
        return WechatMpConfiguration.getMpServices().get(appid);
    }

}
