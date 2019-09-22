package cn.hkxj.platform.service.wechat.handler.messageHandler;

import cn.hkxj.platform.builder.TextBuilder;
import cn.hkxj.platform.pojo.*;
import cn.hkxj.platform.pojo.constant.Building;
import cn.hkxj.platform.pojo.constant.LessonOrder;
import cn.hkxj.platform.pojo.constant.MiniProgram;
import cn.hkxj.platform.pojo.timetable.CourseTimeTable;
import cn.hkxj.platform.pojo.timetable.RoomTimeTable;
import cn.hkxj.platform.service.CourseService;
import cn.hkxj.platform.service.EmptyRoomService;
import cn.hkxj.platform.spider.newmodel.emptyroom.EmptyRoomPost;
import cn.hkxj.platform.utils.SchoolTimeUtil;
import com.google.common.base.Splitter;
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
        WxMpKefuMessage miniProgramMessage = buildKefuMessageWithMiniProgram(wxMpXmlMessage.getFromUser(), WxConsts.KefuMsgType.MINIPROGRAMPAGE);
        sendKefuMessage(wxMpService, miniProgramMessage);
        log.info("check empty room success openid:{}", wxMpXmlMessage.getFromUser());
        return null;
    }

    private WxMpKefuMessage buildKefuMessageWithMiniProgram(String openid, String msgType) {
        WxMpKefuMessage wxMpKefuMessage = new WxMpKefuMessage();
        wxMpKefuMessage.setMsgType(msgType);
        wxMpKefuMessage.setToUser(openid);
        wxMpKefuMessage.setMiniProgramAppId(MiniProgram.APPID.getValue());
        wxMpKefuMessage.setMiniProgramPagePath(MiniProgram.INDEX.getValue());
        wxMpKefuMessage.setTitle("小程序");
        wxMpKefuMessage.setThumbMediaId("qcf_h2hm7P1RL81csrh8ML3i-9lmYJAP3ihNZbOzEks");
        return wxMpKefuMessage;
    }

    private void sendKefuMessage(WxMpService wxMpService, WxMpKefuMessage wxMpKefuMessage) {
        try {
            wxMpService.getKefuService().sendKefuMessage(wxMpKefuMessage);
        } catch (WxErrorException e) {
            log.info("send kefu message to {} fail {}", wxMpKefuMessage.getToUser(), e.getMessage());
        }
    }


}
