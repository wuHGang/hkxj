package cn.hkxj.platform.controller;


import cn.hkxj.platform.config.wechat.WechatMpConfiguration;
import cn.hkxj.platform.config.wechat.WechatMpPlusProperties;
import cn.hkxj.platform.exceptions.OpenidExistException;
import cn.hkxj.platform.exceptions.PasswordUncorrectException;
import cn.hkxj.platform.pojo.constant.ErrorCode;
import cn.hkxj.platform.pojo.WebResponse;
import cn.hkxj.platform.pojo.timetable.CourseTimeTable;
import cn.hkxj.platform.service.CourseService;
import cn.hkxj.platform.service.wechat.StudentBindService;
import cn.hkxj.platform.utils.OneOffSubcriptionUtil;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.kefu.WxMpKefuMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Objects;

@Slf4j
@Controller
public class UserBindingController {
	@Resource
	private HttpSession httpSession;
	@Resource
	private StudentBindService studentBindService;
	@Resource
	private CourseService courseService;
	@Resource
	private WechatMpPlusProperties wechatMpPlusProperties;

	@RequestMapping(value = "/bind", method = RequestMethod.GET)
	public String loginHtml(@RequestParam(value = "openid", required = false) String openid,
							@RequestParam(value = "appid", required = false) String appid) {
		httpSession.setAttribute("openid", openid);
		httpSession.setAttribute("appid", appid);
		return "LoginWeb/Login";
	}


	@RequestMapping(value = "/bind/wechat", method = RequestMethod.POST, produces="application/json;charset=UTF-8")
	@ResponseBody
	public WebResponse loginHtmlPost(@RequestParam("account") String account, @RequestParam("password") String password, HttpServletRequest request) {
		log.info("student bind start account:{} password:{}", account, password);
		String openid = (String) httpSession.getAttribute("openid");
		String appid = (String) httpSession.getAttribute("appid");
		try {
			if (Objects.isNull(openid)){
				studentBindService.studentLogin(account,password);
			}
			else {
				studentBindService.studentBind(openid,account,password, appid);
			}
			httpSession.setAttribute("account", account);
		} catch (PasswordUncorrectException e) {
			log.info("student bind fail Password not correct account:{} password:{} openid:{}", account, password, openid);
			return WebResponse.fail(ErrorCode.ACCOUNT_OR_PASSWORD_INVALID.getErrorCode(), "账号或者密码错误");
		} catch (OpenidExistException e) {
			log.info("student bind fail openid is exist account:{} password:{}", account, password);
			return WebResponse.fail(ErrorCode.OPENID_EXIST.getErrorCode(), "该账号已经绑定");
		}

		String scene = (String) httpSession.getAttribute(openid + "_subscribe_scene");
//		if(!Objects.isNull(appid)){
//			if(Objects.equals(wechatMpPlusProperties.getAppId(), appid)){
//				WxMpService wxMpService = WechatMpConfiguration.getMpServices().get(appid);
//				if(Objects.equals("1005", scene)){
//					List<CourseTimeTable> courseTimeTableList = courseService.getCoursesCurrentDay(Integer.parseInt(account));
//					WxMpKefuMessage wxMpKefuMessage = new WxMpKefuMessage();
//					wxMpKefuMessage.setMsgType("text");
//					wxMpKefuMessage.setContent(courseService.toText(courseTimeTableList));
//					wxMpKefuMessage.setToUser(openid);
//					try {
//						wxMpService.getKefuService().sendKefuMessage(wxMpKefuMessage);
//						log.info("send kefuMessage about course success openid:{} appid:{}", openid, appid);
//					} catch (WxErrorException e) {
//						log.info("send kefuMessage about course failed openid:{} appid:{}", openid, appid);
//					}
//				}
//			}
//		}

		log.info("student bind success account:{} password:{} openid{}", account, password, openid);
		return WebResponse.success();
	}


}
