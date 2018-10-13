package cn.hkxj.platform.controller.wechat;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author junrong.chen
 * @date 2018/10/7
 */
@RestController
@RequestMapping("/wechat/sub")
@Slf4j
public class WxSubscriptionController {
	@GetMapping("/test")
	public String testSubscription(
			@RequestParam(name = "openid", required = false) String openid,
			@RequestParam(name = "template_id", required = false) String templateId,
			@RequestParam(name = "action", required = false) String action,
			@RequestParam(name = "scene", required = false) String scene){
		log.info("{},{},{},{}", openid, templateId, action, scene);
		return "ok";
	}
}
