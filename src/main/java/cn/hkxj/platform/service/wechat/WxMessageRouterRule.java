package cn.hkxj.platform.service.wechat;

import cn.hkxj.platform.exceptions.PasswordUnCorrectException;
import cn.hkxj.platform.interceptor.WxMessageInterceptor;
import cn.hkxj.platform.service.OpenIdService;
import cn.hkxj.platform.utils.ApplicationUtil;
import me.chanjar.weixin.common.api.WxErrorExceptionHandler;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.common.session.WxSessionManager;
import me.chanjar.weixin.mp.api.WxMpMessageHandler;
import me.chanjar.weixin.mp.api.WxMpMessageMatcher;
import me.chanjar.weixin.mp.api.WxMpMessageRouterRule;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.message.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutMessage;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author junrong.chen
 * @date 2018/11/17
 */
public class WxMessageRouterRule extends WxMpMessageRouterRule {
	private List<WxMessageInterceptor> interceptors = new ArrayList<>();
    private final WxMessageRouter routerBuilder;
	private OpenIdService openIdService = ApplicationUtil.getBean("openIdService");
	private static final String PATTERN = "<a href=\"%s/bind?openid=%s&appid=%s\">点击我绑定</a>";

    public WxMessageRouterRule(WxMessageRouter routerBuilder) {
        super(routerBuilder);
        this.routerBuilder = routerBuilder;
	}


	public WxMessageRouterRule interceptor(WxMessageInterceptor interceptor) {
		return interceptor(interceptor, (WxMessageInterceptor[]) null);
	}

	public WxMessageRouterRule interceptor(WxMessageInterceptor interceptor, WxMessageInterceptor... otherInterceptors) {
		this.interceptors.add(interceptor);
		if (otherInterceptors != null && otherInterceptors.length > 0) {
			this.interceptors.addAll(Arrays.asList(otherInterceptors));
		}
		return this;
	}

	/**
	 * 设置是否异步执行，默认是true
	 */
	@Override
	public WxMessageRouterRule async(boolean async) {
		super.async(async);
		return this;
	}

	/**
	 * 如果msgType等于某值
	 */
	@Override
	public WxMessageRouterRule msgType(String msgType) {
		super.msgType(msgType);
		return this;
	}

	/**
	 * 如果event等于某值
	 */
	@Override
	public WxMessageRouterRule event(String event) {
		super.event(event);
		return this;
	}

	/**
	 * 如果eventKey等于某值
	 */
	@Override
	public WxMessageRouterRule eventKey(String eventKey) {
		super.eventKey(eventKey);
		return this;
	}

	/**
	 * 如果eventKey匹配该正则表达式
	 */
	@Override
	public WxMessageRouterRule eventKeyRegex(String regex) {
		super.eventKeyRegex(regex);
		return this;
	}

	/**
	 * 如果content等于某值
	 */
	@Override
	public WxMessageRouterRule content(String content) {
		super.content(content);
		return this;
	}

	/**
	 * 如果content匹配该正则表达式
	 */
	@Override
	public WxMessageRouterRule rContent(String regex) {
		super.rContent(regex);
		return this;
	}

	/**
	 * 如果fromUser等于某值
	 */
	@Override
	public WxMessageRouterRule fromUser(String fromUser) {
		super.fromUser(fromUser);
		return this;
	}

	/**
	 * 如果消息匹配某个matcher，用在用户需要自定义更复杂的匹配规则的时候
	 */
	@Override
	public WxMessageRouterRule matcher(WxMpMessageMatcher matcher) {
		super.matcher(matcher);
		return this;
	}

	/**
	 * 设置微信消息处理器
	 */
	@Override
	public WxMessageRouterRule handler(WxMpMessageHandler handler) {
		return handler(handler, (WxMpMessageHandler[]) null);
	}

	/**
	 * 设置微信消息处理器
	 */
	@Override
	public WxMessageRouterRule handler(WxMpMessageHandler handler, WxMpMessageHandler... otherHandlers) {
		super.handler(handler, otherHandlers);
		return this;
	}

    /**
     * 规则结束，代表如果一个消息匹配该规则，那么它将不再会进入其他规则
     */
    @Override
    public WxMessageRouter end() {
        this.routerBuilder.getRules().add(this);
        return this.routerBuilder;
    }

    /**
     * 规则结束，但是消息还会进入其他规则
     */
    @Override
    public WxMessageRouter next() {
		return end();
    }


	@Override
	protected WxMpXmlOutMessage service(WxMpXmlMessage wxMessage, Map<String, Object> context, WxMpService wxMpService, WxSessionManager sessionManager, WxErrorExceptionHandler exceptionHandler) {
		if (context == null) {
			context = new HashMap<>();
		}

		try {
			// 如果拦截器不通过
			for (WxMessageInterceptor interceptor : this.interceptors) {
				if (!interceptor.intercept(wxMessage, context, wxMpService, sessionManager)) {
					return interceptor.handle(wxMessage, context, wxMpService, sessionManager);
				}
			}

			// 交给handler处理
			WxMpXmlOutMessage res = null;
			for (WxMpMessageHandler handler : super.getHandlers()) {
				// 返回最后handler的结果
				if (handler == null) {
					continue;
				}
				try {
					res = handler.handle(wxMessage, context, wxMpService, sessionManager);
				}catch (PasswordUnCorrectException e) {
					String fromUser = wxMessage.getFromUser();
					String appId = wxMpService.getWxMpConfigStorage().getAppId();
					openIdService.openIdUnbind(fromUser, appId);

					for (WxMessageInterceptor interceptor : this.interceptors) {
						if (!interceptor.intercept(wxMessage, context, wxMpService, sessionManager)) {
							return interceptor.handle(wxMessage, context, wxMpService, sessionManager);
						}
					}
				}

			}
			return res;
		} catch (WxErrorException e) {
			exceptionHandler.handle(e);
		}
		return null;
	}

    @Override
    protected boolean test(WxMpXmlMessage wxMessage) {
        return super.test(wxMessage);
    }
}
