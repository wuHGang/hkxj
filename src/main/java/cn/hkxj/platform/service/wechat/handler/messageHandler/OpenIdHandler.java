package cn.hkxj.platform.service.wechat.handler.messageHandler;

import cn.hkxj.platform.builder.TextBuilder;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.common.session.WxSessionManager;
import me.chanjar.weixin.mp.api.WxMpMessageHandler;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.message.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutMessage;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @author junrong.chen
 * @date 2018/10/21
 */
@Component
@Slf4j
public class OpenIdHandler implements WxMpMessageHandler {

	private static String PATTERN = "<a href=\"%s\\bind?openid=%s\">点击我绑定</a>";

	@Override
	public WxMpXmlOutMessage handle(WxMpXmlMessage wxMpXmlMessage, Map<String, Object> map, WxMpService wxMpService, WxSessionManager wxSessionManager) throws WxErrorException {
		String openId = wxMpXmlMessage.getFromUser();
		String content = "test";
		log.info(content);
		return new TextBuilder().build(content, wxMpXmlMessage, wxMpService);
	}
}
