package cn.hkxj.platform.controller;

import cn.hkxj.platform.exceptions.PasswordUncorrectException;
import cn.hkxj.platform.pojo.Student;
import cn.hkxj.platform.pojo.WebResponse;
import cn.hkxj.platform.service.wechat.StudentBindService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

/**
 * @author junrong.chen
 * @date 2018/10/13
 * 提供登录API
 */
@RestController
@RequestMapping("/login")
@Slf4j
public class LoginController {
	@Resource(name = "studentBindService")
	private StudentBindService studentBindService;
	@Autowired
	private HttpSession session;

	@PostMapping("/student")
	public WebResponse studentLogin(@RequestParam("account")String account, @RequestParam("password")String password) throws PasswordUncorrectException {
		log.info("student login--account:{} password:{}", account, password);
		Student student = studentBindService.studentLogin(account, password);
		session.setAttribute("student", student);
		log.info("student login success--account:{} password:{}", account, password);

		return WebResponse.success(student);
	}


}
