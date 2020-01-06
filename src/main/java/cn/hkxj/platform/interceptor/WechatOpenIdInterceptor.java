package cn.hkxj.platform.interceptor;

import cn.hkxj.platform.builder.TextBuilder;
import cn.hkxj.platform.service.OpenIdService;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.session.WxSessionManager;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.message.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutMessage;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Map;

/**
 * @author junrong.chen
 * @date 2018/10/22
 */
@Service("wechatOpenIdInterceptor")
@Slf4j
public class WechatOpenIdInterceptor implements WxMessageInterceptor {
	@Resource
	private OpenIdService openIdService;
	@Value("${domain}")
	private String domain;
	private static final String PATTERN = "<a href=\"%s/bind?openid=%s&appid=%s\">使用这个功能前，请点击这个按钮绑定</a>";


	@Override
	public boolean intercept(WxMpXmlMessage wxMessage, Map<String, Object> context, WxMpService wxMpService, WxSessionManager sessionManager) {
		String appid = wxMpService.getWxMpConfigStorage().getAppId();
		return openIdService.openidIsExist(wxMessage.getFromUser(), appid) && openIdService.openidIsBind(wxMessage.getFromUser(), appid);

	}

	@Override
	public WxMpXmlOutMessage handle(WxMpXmlMessage wxMessage, Map<String, Object> context, WxMpService wxMpService, WxSessionManager sessionManager) {
		String appId = wxMpService.getWxMpConfigStorage().getAppId();
		String fromUser = wxMessage.getFromUser();
		String content = String.format(PATTERN, domain, fromUser, appId);
		log.info("appid:{} content {} check openid {} is not bind", appId, wxMessage.getContent(), wxMessage.getFromUser());
		return new TextBuilder().build(content, wxMessage, wxMpService);
	}

}
