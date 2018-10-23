package cn.hkxj.platform.controller;


import cn.hkxj.platform.exceptions.OpenidExistException;
import cn.hkxj.platform.exceptions.PasswordUncorrectException;
import cn.hkxj.platform.exceptions.ReadTimeoutException;
import cn.hkxj.platform.pojo.ErrorCode;
import cn.hkxj.platform.pojo.WebResponse;
import cn.hkxj.platform.service.wechat.StudentBindService;
import cn.hkxj.platform.utils.JsonUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Objects;

@Slf4j
@Controller
public class UserBindingController {
	@Autowired
	private HttpSession session;
	@Autowired
	private StudentBindService studentBindService;

	@RequestMapping(value = "/bind", method = RequestMethod.GET)
	public String loginHtml(@RequestParam(value = "openid", required = false) String openid) {
		session.setAttribute("openid", openid);
		return "LoginWeb/Login";
	}


	@RequestMapping(value = "/bind/wechat", method = RequestMethod.POST, produces="application/json;charset=UTF-8")
	@ResponseBody
	public WebResponse loginHtmlPost(@RequestParam("account") String account, @RequestParam("password") String password) {
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
			return WebResponse.fail(ErrorCode.ACCOUNT_OR_PASSWORD_INVALID.getErrorCode(), "账号或者密码错误");
		} catch (OpenidExistException e) {
			return WebResponse.fail(ErrorCode.OPENID_EXIST.getErrorCode(), "该账号已经绑定");
		}


		return WebResponse.success();
	}

}
