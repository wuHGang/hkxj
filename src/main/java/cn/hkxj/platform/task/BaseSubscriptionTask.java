package cn.hkxj.platform.task;

import cn.hkxj.platform.pojo.ScheduleTask;
import cn.hkxj.platform.service.ScheduleTaskService;
import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.kefu.WxMpKefuMessage;
import me.chanjar.weixin.mp.bean.template.WxMpTemplateMessage;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author Yuki
 * @date 2019/10/16 10:20
 *
 * 希望通过这个类来为定时任务提供消息发送的函数
 */
@Slf4j
@Service
public class BaseSubscriptionTask {

    @Resource
    private ScheduleTaskService scheduleTaskService;

    /**
     * 为定时任务提供发送模板消息的模板函数
     * @param wxMpService wxMpService
     * @param templateMessage 模板消息
     * @param task 定时任务实体
     * @return 消息是否发送成功
     */
    protected boolean sendTemplateMessage(WxMpService wxMpService, WxMpTemplateMessage templateMessage,
                                          ScheduleTask task, String logTitle){
        try {
            //发送成功的同时更新发送状态
            wxMpService.getTemplateMsgService().sendTemplateMsg(templateMessage);
            scheduleTaskService.updateSendStatus(task, ScheduleTaskService.SEND_SUCCESS);
            log.info("send {} Message to appid:{} openid:{} message:{} success", logTitle,
                    wxMpService.getWxMpConfigStorage().getAppId(), task.getOpenid(), templateMessage.getData());
            return true;
        } catch (WxErrorException e) {
            scheduleTaskService.updateSendStatus(task, ScheduleTaskService.SEND_FAIL);
            log.error("send {} Message to appid:{} openid:{} failed", logTitle,
                    wxMpService.getWxMpConfigStorage().getAppId(), task.getOpenid(), e);
            return false;
        }
    }

}
