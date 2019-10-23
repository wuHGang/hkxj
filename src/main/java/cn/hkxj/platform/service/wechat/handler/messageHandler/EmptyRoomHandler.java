package cn.hkxj.platform.service.wechat.handler.messageHandler;

import cn.hkxj.platform.pojo.constant.MiniProgram;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.api.WxConsts;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.common.session.WxSessionManager;
import me.chanjar.weixin.mp.api.WxMpMessageHandler;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.kefu.WxMpKefuMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutMessage;
import org.springframework.stereotype.Component;

import java.util.Map;


/**
 * @author junrong.chen
 * @date 2018/10/31
 */
@Component
@Slf4j
public class EmptyRoomHandler implements WxMpMessageHandler {

    @Override
    public WxMpXmlOutMessage handle(WxMpXmlMessage wxMpXmlMessage, Map<String, Object> context, WxMpService wxMpService, WxSessionManager sessionManager) {
        WxMpKefuMessage miniProgramMessage = MiniProgram.EMPTY_CLASSROOM.genCard(wxMpXmlMessage.getFromUser(),
                wxMpService.getWxMpConfigStorage().getAppId());
        sendKefuMessage(wxMpService, miniProgramMessage);
        log.info("check empty room success openid:{}", wxMpXmlMessage.getFromUser());
        return null;
    }



    private void sendKefuMessage(WxMpService wxMpService, WxMpKefuMessage wxMpKefuMessage) {
        try {
            wxMpService.getKefuService().sendKefuMessage(wxMpKefuMessage);
        } catch (WxErrorException e) {
            log.info("send kefu message to {} fail {}", wxMpKefuMessage.getToUser(), e);
        }
    }


}
