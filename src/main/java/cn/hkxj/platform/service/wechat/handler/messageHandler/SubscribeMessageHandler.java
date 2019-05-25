package cn.hkxj.platform.service.wechat.handler.messageHandler;

import cn.hkxj.platform.builder.TextBuilder;
import cn.hkxj.platform.pojo.constant.SubscribeScene;
import cn.hkxj.platform.service.ScheduleTaskService;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.common.session.WxSessionManager;
import me.chanjar.weixin.mp.api.WxMpMessageHandler;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.message.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutMessage;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Map;
import java.util.Objects;

/**
 * @author Yuki
 * @date 2019/5/25 15:08
 */
@Slf4j
@Component
public class SubscribeMessageHandler implements WxMpMessageHandler{

    @Resource
    private ScheduleTaskService scheduleTaskService;
    @Resource
    private TextBuilder textBuilder;

    @Override
    public WxMpXmlOutMessage handle(WxMpXmlMessage wxMessage, Map<String, Object> context, WxMpService wxMpService, WxSessionManager sessionManager) throws WxErrorException {
        String content = wxMessage.getContent();
        SubscribeScene subscribeScene = SubscribeScene.getSubscribeSceneByChinese(content);
        if(!Objects.isNull(subscribeScene)){
            String openid = wxMessage.getFromUser();
            String appid = wxMpService.getWxMpConfigStorage().getAppId();
            String scene = subscribeScene.getScene();
            if(scheduleTaskService.isExistSubscribeRecode(appid, openid, scene)){
                scheduleTaskService.updateSubscribeStatus(appid, openid, scene, ScheduleTaskService.FUNCTION_ENABLE);
            } else {
                scheduleTaskService.addScheduleTaskRecord(appid, openid, scene);
            }
            return textBuilder.build("订阅成功", wxMessage, wxMpService);
        }
        return textBuilder.build("你的回复超出了我们的理解范围", wxMessage, wxMpService);
    }
}
