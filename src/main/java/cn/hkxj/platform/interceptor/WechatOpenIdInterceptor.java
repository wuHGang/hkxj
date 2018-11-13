package cn.hkxj.platform.interceptor;

import cn.hkxj.platform.mapper.OpenidMapper;
import cn.hkxj.platform.service.OpenIdService;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.common.session.WxSessionManager;
import me.chanjar.weixin.mp.api.WxMpMessageInterceptor;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.message.WxMpXmlMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Map;

/**
 * @author junrong.chen
 * @date 2018/10/22
 */
@Component("wechatOpenIdInterceptor")
@Slf4j
public class WechatOpenIdInterceptor implements WxMpMessageInterceptor {
	@Resource
	private OpenIdService openIdService;


	@Override
	public boolean intercept(WxMpXmlMessage wxMessage, Map<String, Object> context, WxMpService wxMpService, WxSessionManager sessionManager) {
		return openIdService.openidIsExist(wxMessage.getFromUser());
	}
}
