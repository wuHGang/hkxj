package cn.hkxj.platform.controller;


import cn.hkxj.platform.exceptions.OpenidExistException;
import cn.hkxj.platform.exceptions.PasswordUncorrectException;
import cn.hkxj.platform.exceptions.ReadTimeoutException;
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

@Slf4j
@Controller
public class UserBinding {
	@Autowired
	private HttpSession session;
	@Autowired
	private StudentBindService studentBindService;

	@RequestMapping(value = "/login/{openid}", method = RequestMethod.GET)
	public String loginHtml(@PathVariable("openid") String openid) {
		session.setAttribute("openid", openid);
		return "LoginWeb/Login";
	}


	@RequestMapping(value = "/login", method = RequestMethod.POST, produces="application/json;charset=UTF-8")
	@ResponseBody
	public String loginHtmlPost(@RequestParam("account") String account, @RequestParam("password") String password) {
		String openid = session.getAttribute("openid").toString();
		int statu = 200;
		String message = "绑定成功";
		try {
			studentBindService.studentBind(openid,account,password);
		} catch (PasswordUncorrectException e) {
			statu = 300;
			message = "密码错误";
		} catch (OpenidExistException e) {
			statu = 400;
			message = "账号已绑定";
		} catch (ReadTimeoutException e) {
			statu = 500;
			message = "服务器读取超时";
		}

		return JsonUtils.reponseToJson(statu, message);
	}

}
