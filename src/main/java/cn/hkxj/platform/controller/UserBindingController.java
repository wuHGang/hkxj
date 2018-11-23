package cn.hkxj.platform.controller;


import cn.hkxj.platform.exceptions.OpenidExistException;
import cn.hkxj.platform.exceptions.PasswordUncorrectException;
import cn.hkxj.platform.pojo.ErrorCode;
import cn.hkxj.platform.pojo.WebResponse;
import cn.hkxj.platform.service.SubscribeService;
import cn.hkxj.platform.service.wechat.StudentBindService;
import cn.hkxj.platform.utils.OneOffSubcriptionUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Objects;

@Slf4j
@Controller
public class UserBindingController {
	@Autowired
	private HttpSession session;
	@Autowired
	private StudentBindService studentBindService;
	@Autowired
	private SubscribeService subscribeService;

	@RequestMapping(value = "/bind", method = RequestMethod.GET)
	public String loginHtml(@RequestParam(value = "openid", required = false) String openid) {
		session.setAttribute("openid", openid);
		return "LoginWeb/Login";
	}


	@RequestMapping(value = "/bind/wechat", method = RequestMethod.POST, produces="application/json;charset=UTF-8")
	@ResponseBody
	public WebResponse loginHtmlPost(@RequestParam("account") String account, @RequestParam("password") String password, HttpServletRequest request) {
		log.info("student bind start account:{} password:{}", account, password);
		String openid = (String) session.getAttribute("openid");

		try {
			if (Objects.isNull(openid)){
				studentBindService.studentLogin(account,password);
			}
			else {
				studentBindService.studentBind(openid,account,password);
			}
			session.setAttribute("account", account);
		} catch (PasswordUncorrectException e) {
			log.info("student bind fail Password not correct account:{} password:{} openid:{}", account, password, openid);
			return WebResponse.fail(ErrorCode.ACCOUNT_OR_PASSWORD_INVALID.getErrorCode(), "账号或者密码错误");
		} catch (OpenidExistException e) {
			log.info("student bind fail openid is exist account:{} password:{}", account, password);
			return WebResponse.fail(ErrorCode.OPENID_EXIST.getErrorCode(), "该账号已经绑定");
		}

		String scene = (String) session.getAttribute(openid + "_subscribe_scene");
		if(!Objects.isNull(scene)){
			if(Objects.equals("1005", scene)){
				OneOffSubcriptionUtil.sendTemplateMessageToUser(openid, "1005");
			}
		}

		log.info("student bind success account:{} password:{} openid{}", account, password, openid);
		return WebResponse.success();
	}

}
