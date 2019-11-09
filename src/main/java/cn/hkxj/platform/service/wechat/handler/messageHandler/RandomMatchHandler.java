package cn.hkxj.platform.service.wechat.handler.messageHandler;

import cn.hkxj.platform.builder.TextBuilder;
import cn.hkxj.platform.service.RandomMatchService;
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

@Slf4j
@Component
public class RandomMatchHandler implements WxMpMessageHandler {

    @Resource
    private RandomMatchService randomMatchService;
    @Resource
    private TextBuilder textBuilder;

    @Override
    public WxMpXmlOutMessage handle(WxMpXmlMessage wxMpXmlMessage, Map<String, Object> map, WxMpService wxMpService, WxSessionManager wxSessionManager) throws WxErrorException {
        try {
            randomMatchService.match(wxMpXmlMessage.getFromUser(), wxMpService.getWxMpConfigStorage().getAppId());
            return textBuilder.build("已成功进入匹配队列", wxMpXmlMessage, wxMpService);
        } catch (Exception e) {
            log.error("在组装返回信息时出现错误", e);
        }

        return null;
    }
}
