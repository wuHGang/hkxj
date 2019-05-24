package cn.hkxj.platform.task;

import cn.hkxj.platform.config.wechat.WechatMpConfiguration;
import cn.hkxj.platform.config.wechat.WechatMpPlusProperties;
import cn.hkxj.platform.config.wechat.WechatMpProProperties;
import cn.hkxj.platform.mapper.ScheduleTaskMapper;
import cn.hkxj.platform.pojo.ScheduleTask;
import cn.hkxj.platform.pojo.wechat.CourseGroupMsg;
import cn.hkxj.platform.pojo.wechat.OneOffSubscription;
import cn.hkxj.platform.service.CourseSubscribeService;
import cn.hkxj.platform.service.ScheduleTaskService;
import cn.hkxj.platform.service.SubscribeService;
import cn.hkxj.platform.utils.OneOffSubcriptionUtil;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.kefu.WxMpKefuMessage;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

/**
 * @author Yuki
 * @date 2018/11/6 20:36
 */
@Slf4j
@Service
public class CourseSubscriptionTask {

    @Resource
    private CourseSubscribeService courseSubscribeService;
    @Resource
    private ScheduleTaskService scheduleTaskService;
    @Resource
    private WechatMpPlusProperties wechatMpPlusProperties;

    private static final Byte SEND_SUCCESS = 1;
    private static final Byte SEND_FAILED = 0;
    private static final String MSG_TITLE = "今日课表";

    //    @Scheduled(cron = "0 0 8 ? * MON-FRI")      //这个cron表达式的意思是星期一到星期五的早上8点执行一次
    @Scheduled(cron = "0/30 * * * * ?")
    public void sendCourseRemindMsg() {
        log.info("Course Push Task is start....");
        Map<String, Set<CourseGroupMsg>> courseGroupMsgMap = courseSubscribeService.getCoursesSubscribeForCurrentDay();
        courseGroupMsgMap.forEach((appid, courseGroupMsgSet) -> {
            if (courseGroupMsgSet == null) {
                log.error("course daily push get list of CourseGroupMsg size is null");
                return;
            }
            if (courseGroupMsgSet.size() == 0) {
                log.error("course daily push get list of CourseGroupMsg size is 0");
                return;
            }
            WxMpService wxMpService = getWxMpService(appid);
            log.info("appid:{} data size:{}", appid, courseGroupMsgMap.size());
            for (CourseGroupMsg msg : courseGroupMsgSet) {
                //获取一个并行流，添加监视messagePeek,设置过滤条件，然后每一个都进行消息发送
                msg.getScheduleTasks().stream().filter(cgm -> !Objects.isNull(cgm)).parallel().peek(this::messagePeek).forEach(task -> {
                    if(Objects.equals(appid, wechatMpPlusProperties.getAppId())){
                        plusMpProcess(task, msg, wxMpService);
                    } else {
                        proMpProcess(task, msg, wxMpService);
                    }
                });
            }
        });
        log.info("Course Push Task is end....");
    }

    private void plusMpProcess(ScheduleTask task, CourseGroupMsg msg, WxMpService wxMpService){
        WxMpKefuMessage wxMpKefuMessage = getKufuMessage(task.getOpenid(), msg.getCourseContent());
        try {
            wxMpService.getKefuService().sendKefuMessage(wxMpKefuMessage);
            log.info("send Message to appid:{} openid:{} success", wxMpService.getWxMpConfigStorage().getAppId(), task.getOpenid());
        } catch (WxErrorException e) {
            e.printStackTrace();
            log.info("send kefu Message to appid:{} openid:{} failed", wxMpService.getWxMpConfigStorage().getAppId(), task.getOpenid());
        }
    }

    private void proMpProcess(ScheduleTask task, CourseGroupMsg msg, WxMpService wxMpService){
        //TODO 将ip添加白名单后进行测试.
        String scene = String.valueOf(task.getScene());
        OneOffSubscription oneOffSubscription =
                new OneOffSubscription.Builder(task.getOpenid(), scene, MSG_TITLE, wxMpService.getWxMpConfigStorage().getTemplateId())
                        .data(msg.getCourseContent()).url(OneOffSubcriptionUtil.getHyperlinks(null, scene, wxMpService)).build();
        try {
            OneOffSubcriptionUtil.sendTemplateMessageToUser(oneOffSubscription, wxMpService);
            log.info("send course push to user:{} appid:{} success", task.getOpenid(), wxMpService.getWxMpConfigStorage().getAppId());
            scheduleTaskService.updateSendStatus(task, SEND_SUCCESS);
        } catch (WxErrorException e) {
            log.info("send course push to user:{} appid:{} fail message:{}",
                    task.getOpenid(), wxMpService.getWxMpConfigStorage().getAppId(), e.getMessage());
            e.printStackTrace();
        }
    }

    private WxMpService getWxMpService(String appid) {
        return WechatMpConfiguration.getMpServices().get(appid);
    }

    private WxMpKefuMessage getKufuMessage(String openid, String msgContent) {
        WxMpKefuMessage wxMpKefuMessage = new WxMpKefuMessage();
        wxMpKefuMessage.setContent("今日课表\n" + msgContent);
        wxMpKefuMessage.setToUser(openid);
        wxMpKefuMessage.setMsgType("text");
        return wxMpKefuMessage;
    }


    /**
     * 给并行流添加一个监视
     */
    private void messagePeek(ScheduleTask scheduleTask) {
        log.info("thread name {} send course push to user：{} appid:{}",
                Thread.currentThread().getName(), scheduleTask.getOpenid(), scheduleTask.getAppid());
    }
}
