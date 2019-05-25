package cn.hkxj.platform.task;

import cn.hkxj.platform.builder.TemplateBuilder;
import cn.hkxj.platform.config.wechat.WechatMpConfiguration;
import cn.hkxj.platform.config.wechat.WechatMpPlusProperties;
import cn.hkxj.platform.pojo.ScheduleTask;
import cn.hkxj.platform.pojo.wechat.CourseGroupMsg;
import cn.hkxj.platform.pojo.wechat.OneOffSubscription;
import cn.hkxj.platform.service.CourseSubscribeService;
import cn.hkxj.platform.service.ScheduleTaskService;
import cn.hkxj.platform.utils.OneOffSubcriptionUtil;
import cn.hkxj.platform.utils.SchoolTimeUtil;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.template.WxMpTemplateData;
import me.chanjar.weixin.mp.bean.template.WxMpTemplateMessage;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

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
    @Resource
    private TemplateBuilder templateBuilder;

    private static final String MSG_TITLE = "今日课表";

    @Scheduled(cron = "0 0 8 ? * MON-FRI")      //这个cron表达式的意思是星期一到星期五的早上8点执行一次
//    @Scheduled(cron = "0/30 * * * * ?")
    public void sendCourseRemindMsg() {
        log.info("Course Push Task is start....");
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
                msg.getScheduleTasks().stream().filter(cgm -> !Objects.isNull(cgm)).parallel().peek(this::messagePeek).forEach(task -> {
                    if(Objects.equals(appid, wechatMpPlusProperties.getAppId())){
                        plusMpProcess(task, msg, wxMpService);
                    } else {
                        proMpProcess(task, msg, wxMpService);
                    }
                });
            }
        });
        log.info("course push task is end");
    }

    private void plusMpProcess(ScheduleTask task, CourseGroupMsg msg, WxMpService wxMpService){
        List<WxMpTemplateData> templateData = assemblyTemplateContent(msg);
        String url = "http://platform.hackerda.com/platform/course/timetable";
        WxMpTemplateMessage templateMessage = templateBuilder.buildCourseMessage(task.getOpenid(), templateData, url);
        try {
            wxMpService.getTemplateMsgService().sendTemplateMsg(templateMessage);
            scheduleTaskService.updateSendStatus(task, ScheduleTaskService.SEND_SUCCESS);
            log.info("send Message to appid:{} openid:{} success", wxMpService.getWxMpConfigStorage().getAppId(), task.getOpenid());
        } catch (WxErrorException e) {
            e.printStackTrace();
            log.info("send kefu Message to appid:{} openid:{} failed", wxMpService.getWxMpConfigStorage().getAppId(), task.getOpenid());
        }
    }

    private void proMpProcess(ScheduleTask task, CourseGroupMsg msg, WxMpService wxMpService){
        String scene = String.valueOf(task.getScene());
        OneOffSubscription oneOffSubscription =
                new OneOffSubscription.Builder(task.getOpenid(), scene, MSG_TITLE, wxMpService.getWxMpConfigStorage().getTemplateId())
                        .data(msg.getCourseContent()).url(OneOffSubcriptionUtil.getHyperlinks(null, scene, wxMpService)).build();
        try {
            OneOffSubcriptionUtil.sendTemplateMessageToUser(oneOffSubscription, wxMpService);
            scheduleTaskService.updateSendStatus(task, ScheduleTaskService.SEND_SUCCESS);
            scheduleTaskService.updateSubscribeStatus(task, ScheduleTaskService.FUNCTION_DISABLE);
            log.info("send course push to user:{} appid:{} success", task.getOpenid(), wxMpService.getWxMpConfigStorage().getAppId());
        } catch (WxErrorException e) {
            log.info("send course push to user:{} appid:{} fail message:{}",
                    task.getOpenid(), wxMpService.getWxMpConfigStorage().getAppId(), e.getMessage());
            e.printStackTrace();
        }
    }

    private WxMpService getWxMpService(String appid) {
        return WechatMpConfiguration.getMpServices().get(appid);
    }

    private List<WxMpTemplateData> assemblyTemplateContent(CourseGroupMsg msg) {
        List<WxMpTemplateData> templateDatas = new ArrayList<>();
        WxMpTemplateData first = new WxMpTemplateData();
        first.setName("first");
        first.setValue("当日课表\n");
        WxMpTemplateData course = new WxMpTemplateData();
        course.setName("keyword1");
        course.setValue("\n" + msg.getCourseContent() + "\n");
        WxMpTemplateData date = new WxMpTemplateData();
        date.setName("keyword2");
        date.setValue("第" + SchoolTimeUtil.getSchoolWeek() + "周   " + SchoolTimeUtil.getDayOfWeekChinese());
        WxMpTemplateData remark = new WxMpTemplateData();
        remark.setName("remark");
        remark.setValue("查询仅供参考，以学校下发的课表为准，如有疑问微信添加吴彦祖【hkdhd666】");

        templateDatas.add(first);
        templateDatas.add(course);
        templateDatas.add(date);
        templateDatas.add(remark);

        return templateDatas;
    }


    /**
     * 给并行流添加一个监视
     */
    private void messagePeek(ScheduleTask scheduleTask) {
        log.info("thread name {} send course push to user：{} appid:{}",
                Thread.currentThread().getName(), scheduleTask.getOpenid(), scheduleTask.getAppid());
    }
}
