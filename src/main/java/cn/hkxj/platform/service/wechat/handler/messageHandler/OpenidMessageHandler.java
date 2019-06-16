package cn.hkxj.platform.service.wechat.handler.messageHandler;

import cn.hkxj.platform.builder.TextBuilder;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.session.WxSessionManager;
import me.chanjar.weixin.mp.api.WxMpMessageHandler;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.message.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutMessage;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Map;

@Slf4j
@Component
public class OpenidMessageHandler implements WxMpMessageHandler {

    @Resource
    private TextBuilder textBuilder;

    public WxMpXmlOutMessage handle(WxMpXmlMessage wxMpXmlMessage,
                                    Map<String, Object> map,
                                    WxMpService wxMpService,
                                    WxSessionManager wxSessionManager) {
        try {
            String gradesMsg=("你的openid为:\n"+wxMpXmlMessage.getFromUser());
            return textBuilder.build(gradesMsg, wxMpXmlMessage, wxMpService);
        } catch (Exception e) {
            log.error("在组装返回信息时出现错误", e);
        }

        return textBuilder.build("没有查询到相关成绩，晚点再来查吧~" , wxMpXmlMessage, wxMpService);
    }

}
