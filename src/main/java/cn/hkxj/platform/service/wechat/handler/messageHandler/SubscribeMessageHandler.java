package cn.hkxj.platform.service.wechat.handler.messageHandler;

import cn.hkxj.platform.builder.TextBuilder;
import cn.hkxj.platform.config.wechat.WechatMpPlusProperties;
import cn.hkxj.platform.pojo.ScheduleTask;
import cn.hkxj.platform.pojo.constant.SubscribeScene;
import cn.hkxj.platform.service.ScheduleTaskService;
import com.google.common.base.Splitter;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.common.session.WxSessionManager;
import me.chanjar.weixin.mp.api.WxMpMessageHandler;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.message.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutMessage;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.StreamSupport;

/**
 * @author Yuki
 * @date 2019/5/25 15:08
 */
@Slf4j
@Component
public class SubscribeMessageHandler implements WxMpMessageHandler{
    private static final String PATTERN = "格式不正确:\n\n订阅格式:\n发送关键字 如：课表推送， 成绩推送， 考试推送";


    @Resource
    private ScheduleTaskService scheduleTaskService;
    @Resource
    private TextBuilder textBuilder;
    @Resource
    private WechatMpPlusProperties wechatMpPlusProperties;

    @Override
    public WxMpXmlOutMessage handle(WxMpXmlMessage wxMessage, Map<String, Object> context, WxMpService wxMpService, WxSessionManager sessionManager) {
        String openid = wxMessage.getFromUser();
        String appid = wxMpService.getWxMpConfigStorage().getAppId();


        if(!wechatMpPlusProperties.getAppId().equals(appid)){
            return textBuilder.build("当前订阅号不支持提醒功能"+"\n\n请关注我们的服务号【黑科校际plus】进行订阅哦！~", wxMessage, wxMpService);
        }
        if(wxMessage.getContent().equals("订阅")){
            for (SubscribeScene scene : SubscribeScene.values()) {
                ScheduleTask scheduleTask = new ScheduleTask(appid, openid, scene.getScene());
                //判断有没有相应的订阅记录，有就更新记录，没有就插入一条记录
                scheduleTaskService.checkAndSetSubscribeStatus(scheduleTask, true);
            }
            return textBuilder.build("课表,成绩,考试订阅成功"+"\n\n回复【课时排行】可以查看你学费交的有多值", wxMessage, wxMpService);
        }


        SubscribeScene subscribeScene = SubscribeScene.getSubscribeSceneByChinese(wxMessage.getContent());
        if(Objects.isNull(subscribeScene)){
            return textBuilder.build(PATTERN, wxMessage, wxMpService);
        }
        String scene = subscribeScene.getScene();
        if(Objects.nonNull(scene)){

            ScheduleTask scheduleTask = new ScheduleTask(appid, openid, scene);
            //判断有没有相应的订阅记录，有就更新记录，没有就插入一条记录
            scheduleTaskService.checkAndSetSubscribeStatus(scheduleTask, true);
            return textBuilder.build("订阅成功", wxMessage, wxMpService);
        }
        return textBuilder.build(PATTERN, wxMessage, wxMpService);
    }

}
