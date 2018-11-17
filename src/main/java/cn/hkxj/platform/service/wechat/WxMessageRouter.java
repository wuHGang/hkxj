package cn.hkxj.platform.service.wechat;

import me.chanjar.weixin.mp.api.WxMpMessageRouter;
import me.chanjar.weixin.mp.api.WxMpService;

/**
 * @author junrong.chen
 * @date 2018/11/17
 */
public class WxMessageRouter extends WxMpMessageRouter {
	public WxMessageRouter(WxMpService wxMpService) {
		super(wxMpService);
	}


	@Override
	public WxMessageRouterRule rule() {
		return new WxMessageRouterRule(this);
	}



}
