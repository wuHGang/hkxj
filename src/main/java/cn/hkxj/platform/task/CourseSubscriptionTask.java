package cn.hkxj.platform.task;

import cn.hkxj.platform.builder.TemplateBuilder;
import cn.hkxj.platform.config.wechat.WechatMpConfiguration;
import cn.hkxj.platform.config.wechat.WechatTemplateProperties;
import cn.hkxj.platform.pojo.ScheduleTask;
import cn.hkxj.platform.pojo.constant.MiniProgram;
import cn.hkxj.platform.pojo.wechat.CourseGroupMsg;
import cn.hkxj.platform.service.CourseSubscribeService;
import cn.hkxj.platform.service.ScheduleTaskService;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.template.WxMpTemplateData;
import me.chanjar.weixin.mp.bean.template.WxMpTemplateMessage;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author Yuki
 * @date 2018/11/6 20:36
 */
@Slf4j
@Service
public class CourseSubscriptionTask {

    private static ExecutorService courseSubscriptionSendPool = new ThreadPoolExecutor(5, 5,
            0L, TimeUnit.MILLISECONDS, new LinkedBlockingDeque<>(10), new ThreadPoolExecutor.CallerRunsPolicy());


    @Value("${domain}")
    private String domain;
    @Resource
    private CourseSubscribeService courseSubscribeService;
    @Resource
    private ScheduleTaskService scheduleTaskService;
    @Resource
    private TemplateBuilder templateBuilder;
    @Resource
    private WechatTemplateProperties wechatTemplateProperties;

    @Async
    @Scheduled(cron = "0 0 8 ? * MON-FRI")      //这个cron表达式的意思是星期一到星期五的早上8点执行一次
    void sendCourseRemindMsg() {
        Map<String, Set<CourseGroupMsg>> courseGroupMsgMap = courseSubscribeService.getCoursesSubscribeForCurrentDay();
        courseGroupMsgMap.forEach((appid, courseGroupMsgSet) -> {
            //如果courseGroupMsgSet为空时，说明没有可用的订阅，直接跳过当前循环
            if(courseGroupMsgSet == null) {
                return;
            }
            WxMpService wxMpService = getWxMpService(appid);
            log.info("appid:{} data size:{}", appid, courseGroupMsgMap.size());
            for (CourseGroupMsg msg : courseGroupMsgSet) {
                //获取一个并行流，添加监视messagePeek,设置过滤条件，然后每一个都进行消息发送
                //parallelStream不是线程安全的
                msg.getScheduleTasks().stream().filter(cgm -> !Objects.isNull(cgm)).peek(this::messagePeek).forEach(task -> {
                    //根据appid来选择不同的处理过程
                    courseSubscriptionSendPool.execute(() -> plusMpProcess(task, msg, wxMpService));
                });
            }
        });
    }

    /**
     * plus的处理过程
     * @param task 定时任务
     * @param msg 课程推送信息
     * @param wxMpService wxMpService
     */
    private void plusMpProcess(ScheduleTask task, CourseGroupMsg msg, WxMpService wxMpService){
        //当CourseTimeTableDetailDtos的引用为null时，说明没有对应的数据，直接返回
        if(msg.getDetailDtos() == null) { return; }
        List<WxMpTemplateData> templateData = templateBuilder.assemblyTemplateContentForCourse(msg);
        WxMpTemplateMessage.MiniProgram miniProgram = new WxMpTemplateMessage.MiniProgram();
        miniProgram.setAppid(MiniProgram.APP_ID.getValue());
        miniProgram.setPagePath(MiniProgram.COURSE_PATH.getValue());
        String url = domain + "/platform/show/timetable";
        //构建一个课程推送的模板消息
        WxMpTemplateMessage templateMessage =
                templateBuilder.build(task.getOpenid(), templateData, wechatTemplateProperties.getPlusCourseTemplateId(), miniProgram, url);
        try {
            //发送成功的同时更新发送状态
            wxMpService.getTemplateMsgService().sendTemplateMsg(templateMessage);
            scheduleTaskService.updateSendStatus(task, ScheduleTaskService.SEND_SUCCESS);
            log.info("send Message to appid:{} openid:{} success", wxMpService.getWxMpConfigStorage().getAppId(), task.getOpenid());
        } catch (WxErrorException e) {
            scheduleTaskService.updateSendStatus(task, ScheduleTaskService.SEND_FAIL);
            log.error("send Message to appid:{} openid:{} failed message:{}",
                    wxMpService.getWxMpConfigStorage().getAppId(), task.getOpenid(), e.getMessage());
        }
    }

    /**
     * 根据appid获取相应的wxMpService
     * @param appid appid
     * @return wxMpService
     */
    private WxMpService getWxMpService(String appid) {
        return WechatMpConfiguration.getMpServices().get(appid);
    }

    /**
     * 给并行流添加一个监视
     */
    private void messagePeek(ScheduleTask scheduleTask) {
        log.info("send course push to user：{} appid:{}", scheduleTask.getOpenid(), scheduleTask.getAppid());
    }
}
