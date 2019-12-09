package cn.hkxj.platform.service.wechat.handler.messageHandler;


import cn.hkxj.platform.builder.TextBuilder;
import cn.hkxj.platform.config.wechat.WechatMpPlusProperties;
import cn.hkxj.platform.service.wechat.StudentBindService;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.common.session.WxSessionManager;
import me.chanjar.weixin.mp.api.WxMpMessageHandler;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.message.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutMessage;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.annotation.Resources;
import java.util.Map;


/**
 * 用户关注事件发送引导信息
 */
@Slf4j
@Component
public class SubscribeEventHandler implements WxMpMessageHandler {
    @Resource
    private TextBuilder textBuilder;
    @Resource
    private StudentBindService studentBindService;
    @Resource
    private WechatMpPlusProperties wechatMpPlusProperties;

    @Override
    public WxMpXmlOutMessage handle(WxMpXmlMessage wxMessage, Map<String, Object> context, WxMpService wxMpService, WxSessionManager sessionManager) {
        String appId = wxMpService.getWxMpConfigStorage().getAppId();
        String fromUser = wxMessage.getFromUser();
        StringBuffer buffer = new StringBuffer();

        buffer.append("同学，你终于找到我们黑科校际了！我们在这已经等候多时了，很高兴遇见你~");
        buffer.append("\n\n");
        buffer.append("为了更好地为你服务，").append(studentBindService.getBindUrlByOpenid(appId, fromUser, "请点击我进行绑定！！！"));

        if(wechatMpPlusProperties.getAppId().equals(appId)){
            buffer.append("\n\n").append("回复 【订阅】,即可开启课程成绩提醒黑科技");
        }

        buffer.append("\n\n").append("使用过程中有问题记得在后台留言，我们会尽快解决的");

        return textBuilder.build(new String(buffer), wxMessage, wxMpService);
    }
}
