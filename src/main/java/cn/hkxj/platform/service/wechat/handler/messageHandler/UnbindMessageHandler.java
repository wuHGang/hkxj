package cn.hkxj.platform.service.wechat.handler.messageHandler;

import cn.hkxj.platform.builder.TextBuilder;
import cn.hkxj.platform.mapper.OpenidMapper;
import cn.hkxj.platform.pojo.Openid;
import cn.hkxj.platform.service.OpenIdService;
import jdk.internal.dynalink.linker.LinkerServices;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.common.session.WxSessionManager;
import me.chanjar.weixin.mp.api.WxMpMessageHandler;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.message.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutMessage;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Slf4j
@Component
public class UnbindMessageHandler implements WxMpMessageHandler {
    @Resource
    private TextBuilder textBuilder;

    @Resource
    private OpenidMapper openidMapper;

    @Resource
    private OpenIdService openIdService;

    public WxMpXmlOutMessage handle(WxMpXmlMessage wxMpXmlMessage,
                                    Map<String, Object> map,
                                    WxMpService wxMpService,
                                    WxSessionManager wxSessionManager) throws WxErrorException {
        try {
            openIdService.openIdUnbind(wxMpXmlMessage.getFromUser());
            String gradesMsg=("你的账号已解绑成功\n");
            return textBuilder.build(gradesMsg, wxMpXmlMessage, wxMpService);
        } catch (Exception e) {
            log.error("在组装返回信息时出现错误", e);
        }

        return textBuilder.build("没有查询到相关成绩，晚点再来查吧~" , wxMpXmlMessage, wxMpService);
    }
}
