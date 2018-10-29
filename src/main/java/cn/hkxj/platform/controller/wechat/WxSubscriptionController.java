package cn.hkxj.platform.controller.wechat;

import cn.hkxj.platform.pojo.Student;
import cn.hkxj.platform.service.wechat.StudentBindService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @author junrong.chen
 * @date 2018/10/7
 */
@Controller
@RequestMapping("/wechat/sub")
@Slf4j
public class WxSubscriptionController {
	@Resource(name = "studentBindService")
	private StudentBindService studentBindService;
	@Autowired
	private HttpSession session;

	/**
	 * @param openid 微信平台用户唯一标识
	 * @param templateId 订阅消息模板ID
	 * @param action 用户点击动作，”confirm”代表用户确认授权，”cancel”代表用户取消授权
	 * @param scene 点击的具体场景类型
	 * @param response 用于重定向
	 * @throws IOException 页面重定向异常
	 */
	@GetMapping("/test")
	public String testSubscription(
			@RequestParam(name = "openid", required = false) String openid,
			@RequestParam(name = "template_id", required = false) String templateId,
			@RequestParam(name = "action", required = false) String action,
			@RequestParam(name = "scene", required = false) String scene,
			HttpServletResponse response) throws IOException {
		log.info("{},{},{},{}", openid, templateId, action, scene);

		if (studentBindService.isStudentBind(openid)) {

			Student student = studentBindService.getStudentByOpenID(openid);
			String account = student.getAccount().toString();
			session.setAttribute("account", account);
			log.info("redirect to timetable account：{}", account);
			return "new";
		}
		else {
			session.setAttribute("openid", openid);
			log.info("redirect to login");
			return "LoginWeb/Login";
		}
	}
}
