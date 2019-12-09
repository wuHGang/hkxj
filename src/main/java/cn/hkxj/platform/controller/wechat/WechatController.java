package cn.hkxj.platform.controller.wechat;

import cn.hkxj.platform.config.wechat.WechatMpConfiguration;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.message.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutMessage;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

/**
 * @author Binary Wang(https://github.com/binarywang)
 */
@Slf4j
@RestController
@RequestMapping("/wechat/portal/{appid}")
public class WechatController {

	@Resource
	private HttpSession httpSession;

	@GetMapping(produces = "text/plain;charset=utf-8")
	public String authGet(@PathVariable String appid,
			@RequestParam(name = "signature", required = false) String signature,
			@RequestParam(name = "timestamp", required = false) String timestamp,
			@RequestParam(name = "nonce", required = false) String nonce,
			@RequestParam(name = "echostr", required = false) String echostr) {

		log.info("\n接收到来自微信服务器的认证消息：[{}, {}, {}, {}]", signature,
				timestamp, nonce, echostr);

        String result = "非法请求";
		if (StringUtils.isAnyBlank(signature, timestamp, nonce, echostr)) {
            log.info("wechat portal response param error");
            return result;
		}
		final WxMpService wxMpService = WechatMpConfiguration.getMpServices().get(appid);
		if (wxMpService.checkSignature(timestamp, nonce, signature)) {
            log.info("response echo: {}", echostr);
		    return echostr;
		}
        log.info("wechat portal response fail: {}", result);
        return result;
	}

	@PostMapping(produces = "application/xml; charset=UTF-8")
	public String post(@PathVariable String appid,
					   @RequestBody String requestBody,
	                   @RequestParam("signature") String signature,
	                   @RequestParam("timestamp") String timestamp,
	                   @RequestParam("nonce") String nonce,
	                   @RequestParam(name = "encrypt_type",
			                   required = false) String encType,
	                   @RequestParam(name = "msg_signature",
			                   required = false) String msgSignature) {
		log.info(
				"\n接收微信请求：appid:{} [signature=[{}], encType=[{}], msgSignature=[{}],"
						+ " timestamp=[{}], nonce=[{}], requestBody=[\n{}\n] ",
				appid, signature, encType, msgSignature, timestamp, nonce, requestBody);
		final WxMpService wxService = WechatMpConfiguration.getMpServices().get(appid);

		if (!wxService.checkSignature(timestamp, nonce, signature)) {
			throw new IllegalArgumentException("非法请求，可能属于伪造的请求！");
		}

		String out = null;
		if (encType == null) {
			// 明文传输的消息

			WxMpXmlMessage inMessage = WxMpXmlMessage.fromXml(requestBody);
            MDC.put("openid", inMessage.getFromUser());
			WxMpXmlOutMessage outMessage = this.route(inMessage, appid);
			if (outMessage == null) {
				return "success";
			}

			out = outMessage.toXml();
		} else if ("aes".equals(encType)) {
			// aes加密的消息
			WxMpXmlMessage inMessage = WxMpXmlMessage.fromEncryptedXml(
					requestBody, wxService.getWxMpConfigStorage(), timestamp,
					nonce, msgSignature);
			log.debug("\n消息解密后内容为：\n{} ", inMessage.toString());
			httpSession.setAttribute("appid", appid);
			WxMpXmlOutMessage outMessage = this.route(inMessage, appid);
			if (outMessage == null) {
				return "";
			}

			out = outMessage.toEncryptedXml(wxService.getWxMpConfigStorage());
		}
        log.info("wechat message out {}", out);

		return out;
	}

	private WxMpXmlOutMessage route(WxMpXmlMessage message, String appid) {
		try {
			return WechatMpConfiguration.getRouters().get(appid).route(message);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}

		return null;
	}


}
