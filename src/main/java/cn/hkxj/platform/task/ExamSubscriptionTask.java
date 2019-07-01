package cn.hkxj.platform.task;

import cn.hkxj.platform.builder.TemplateBuilder;
import cn.hkxj.platform.builder.TextBuilder;
import cn.hkxj.platform.config.wechat.WechatMpConfiguration;
import cn.hkxj.platform.config.wechat.WechatMpPlusProperties;
import cn.hkxj.platform.config.wechat.WechatTemplateProperties;
import cn.hkxj.platform.pojo.*;
import cn.hkxj.platform.pojo.constant.MiniProgram;
import cn.hkxj.platform.pojo.timetable.ExamTimeTable;
import cn.hkxj.platform.pojo.wechat.CourseGroupMsg;
import cn.hkxj.platform.pojo.wechat.ExamGroupMsg;
import cn.hkxj.platform.pojo.wechat.OneOffSubscription;
import cn.hkxj.platform.pojo.wechat.Openid;
import cn.hkxj.platform.service.*;
import cn.hkxj.platform.utils.OneOffSubcriptionUtil;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.message.WxMpXmlMessage;
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

/**
 * 1.通过定时任务的表把对应场景值中所有订阅状态可用的用户查询出来
 * 2.按照openid和appid属性去查找对应用户的考试时间
 * 3.根据具体的逻辑去处理上面的数据。
 * 通过schedule_task(openid, appid)的这两个属性 -> 在student表中找到学生对应的class_id  -> 然后用这个class_id
 * 去class_exam_timetable中找到所有关联的本学期的timetable_id -> 前面的timetable_id对应exam_timetable的主键id。
 *
 * @author FMC
 * @date 2019/6/22 12:36
 */

@Slf4j
@Service
public class ExamSubscriptionTask {
    private static final String MSG_TITLE = "考试时间";
    @Resource
    private WechatMpPlusProperties wechatMpPlusProperties;
    @Resource
    private ExamTimeTableService examTimeTableService;
    @Resource
    private ScheduleTaskService scheduleTaskService;
    @Resource
    private TemplateBuilder templateBuilder;
    @Resource
    private WechatTemplateProperties wechatTemplateProperties;
    @Value("scheduled.gradeUpdate")
    private String updateSwitch;


    //@Scheduled(cron = "0/30 * * * * ?")
    @Async
    @Scheduled(cron = "0 0 20 ? * MON-FRI")      //这个cron表达式的意思是星期一到星期五的晚上8点执行一次
    void autoUpdateExam() {
        //执行前，检查定时任务的可用性
        if (isTaskEnable())
            return;
        // 返回classes班级信息、examTimeTables明天的考试信息、scheduleTasks定时任务信息
        Map<String, Set<ExamGroupMsg>> examGroupMsgMap = examTimeTableService.getExamSubscribeForCurrentDay();

        examGroupMsgMap.forEach((appid, examGroupMsgSet) -> {
            //如果courseGroupMsgSet为空时，说明没有可用的订阅，直接跳过当前循环
            if(examGroupMsgSet == null)
                return;
            WxMpService wxMpService = getWxMpService(appid);
            log.info("appid:{} data size:{}", appid, examGroupMsgMap.size());

            for (ExamGroupMsg examMsg : examGroupMsgSet) {
                //获取一个并行流，添加监视messagePeek,设置过滤条件，然后每一个都进行消息发送
                examMsg.getScheduleTasks().parallelStream().filter(cgm -> !Objects.isNull(cgm)).peek(this::messagePeek).forEach(task -> {
                    //根据appid来选择不同的处理过程
                    if(Objects.equals(appid, wechatMpPlusProperties.getAppId())){
                        plusMpProcess(task, examMsg, wxMpService);
                    } else {
                        proMpProcess(task, examMsg, wxMpService);
                    }
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
    private void plusMpProcess(ScheduleTask task, ExamGroupMsg msg, WxMpService wxMpService){
        List<WxMpTemplateData> templateData = templateBuilder.assemblyTemplateContentForExam(msg);

        WxMpTemplateMessage.MiniProgram miniProgram = new WxMpTemplateMessage.MiniProgram();
        miniProgram.setAppid(MiniProgram.APPID.getValue());
        miniProgram.setPagePath(MiniProgram.COURSE_PATH.getValue());
        String url = "https://platform.hackerda.com/platform/show/timetable";

        //构建一个课程推送的模板消息
        WxMpTemplateMessage templateMessage =
                templateBuilder.buildWithNoMiniProgram(task.getOpenid(), templateData, wechatTemplateProperties.getPlusExamTemplateId(), url);

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
     * pro的处理过程
     * @param task 定时任务
     * @param msg 课程推送信息
     * @param wxMpService wxMpService`
     */
    private void proMpProcess(ScheduleTask task, ExamGroupMsg msg, WxMpService wxMpService){
        String scene = String.valueOf(task.getScene());
        //先创建一个OneOffSubscription的实体
        OneOffSubscription oneOffSubscription = new OneOffSubscription.Builder()
                .touser(task.getOpenid())
                .scene(scene)
                .title(MSG_TITLE)
                .templateId(wxMpService.getWxMpConfigStorage().getTemplateId())
                .data(msg.getCourseContent())
                .build();
        try{
            //发送一次性订阅的模板消息，同时更新发送状态和订阅状态
            OneOffSubcriptionUtil.sendTemplateMessageToUser(oneOffSubscription, wxMpService);
            scheduleTaskService.updateSendStatus(task, ScheduleTaskService.SEND_SUCCESS);
            //因为一次性订阅一次只能发送一次消息，所以发送成功后将订阅状态置为不可用
            scheduleTaskService.updateSubscribeStatus(task, ScheduleTaskService.FUNCTION_DISABLE);
            log.info("send course push to user:{} appid:{} success", task.getOpenid(), wxMpService.getWxMpConfigStorage().getAppId());
        }catch (WxErrorException e){
            scheduleTaskService.updateSubscribeStatus(task, ScheduleTaskService.SEND_FAIL);
            log.error("send course push to user:{} appid:{} fail message:{}",
                    task.getOpenid(),wxMpService.getWxMpConfigStorage().getAppId(),e.getMessage());
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

    /**
     * 根据配置文件中的属性，判断该定时任务是否可用
     *
     * @return 可用结果
     */
    private boolean isTaskEnable() {
        return BooleanUtils.toBoolean(updateSwitch);
    }

}
