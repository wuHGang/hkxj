package cn.hkxj.platform.interceptor;

import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.common.session.WxSessionManager;
import me.chanjar.weixin.mp.api.WxMpMessageInterceptor;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.message.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutMessage;

import java.util.Map;

/**
 * @author junrong.chen
 * @date 2018/11/17
 */
public interface WxMessageInterceptor extends WxMpMessageInterceptor {
	/**
	 * 拦截微信消息
	 *
	 * @param wxMessage
	 * @param context        上下文，如果handler或interceptor之间有信息要传递，可以用这个
	 * @param wxMpService
	 * @param sessionManager
	 * @return true代表OK，false代表不OK
	 */
    @Override
    boolean intercept(WxMpXmlMessage wxMessage,
	                  Map<String, Object> context,
	                  WxMpService wxMpService,
	                  WxSessionManager sessionManager) throws WxErrorException;


	/**
	 * 对于拦截成功的消息，统一返回自定义的结果
	 *
	 * @param wxMessage
	 * @param context
	 * @param wxMpService
	 * @param sessionManager
	 * @return 无消息返回直接返回null
	 * @throws WxErrorException
	 */
	WxMpXmlOutMessage handle(WxMpXmlMessage wxMessage,
	                         Map<String, Object> context,
	                         WxMpService wxMpService,
	                         WxSessionManager sessionManager) throws WxErrorException;
}
