package cn.hkxj.platform.service;

import me.chanjar.weixin.mp.api.WxMpMessageHandler;
import me.chanjar.weixin.mp.api.WxMpMessageRouter;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutTextMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Service;

/**
 * @author JR Chan
 * @date 2018/6/7 14:32
 */
@Service
@EnableAutoConfiguration
public class WechatMessageService {
    private final WxMpMessageRouter wxMpMessageRouter;

    @Autowired
    public WechatMessageService(WxMpMessageRouter wxMpMessageRouter) {
        this.wxMpMessageRouter = wxMpMessageRouter;
    }


    public void handlerRegister(){

        WxMpMessageHandler handler = (wxMpXmlMessage, map, wxMpService, wxSessionManager) -> {
            WxMpXmlOutTextMessage m
                    = WxMpXmlOutMessage.TEXT().content("测试加密消息").fromUser(wxMpXmlMessage.getToUser())
                    .toUser(wxMpXmlMessage.getFromUser()).build();
            return m;
        };

        wxMpMessageRouter
                .rule()
                .async(false)
                .content("哈哈") // 拦截内容为“哈哈”的消息
                .handler(handler)
                .end();
    }
}
