package cn.hkxj.platform.controller.wechat;

import cn.hkxj.platform.config.wechat.WechatMpConfiguration;
import cn.hkxj.platform.config.wechat.WechatMpPlusProperties;
import cn.hkxj.platform.pojo.Student;
import cn.hkxj.platform.pojo.timetable.CourseTimeTable;
import cn.hkxj.platform.service.CourseService;
import cn.hkxj.platform.service.SubscribeService;
import cn.hkxj.platform.service.wechat.StudentBindService;
import cn.hkxj.platform.utils.OneOffSubcriptionUtil;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpConfigStorage;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.kefu.WxMpKefuMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;
import java.util.Objects;

/**
 * @author junrong.chen
 * @date 2018/10/7
 */
@Controller
@RequestMapping("/wechat/sub/{appid}")
@Slf4j
public class WxSubscriptionController {
	@Resource(name = "studentBindService")
	private StudentBindService studentBindService;
	@Resource
	private SubscribeService subscribeService;
	@Resource
	private CourseService courseService;
	@Resource
	private HttpSession httpSession;
	@Resource
	private WechatMpPlusProperties wechatMpPlusProperties;

	/**
	 * @param openid     微信平台用户唯一标识
	 * @param templateId 订阅消息模板ID
	 * @param action     用户点击动作，”confirm”代表用户确认授权，”cancel”代表用户取消授权
	 * @param scene      点击的具体场景类型
	 * @param response   用于重定向
	 * @throws IOException 页面重定向异常
	 */
	@GetMapping("/test")
	public String testSubscription(
			@PathVariable String appid,
			@RequestParam(name = "openid", required = false) String openid,
			@RequestParam(name = "template_id", required = false) String templateId,
			@RequestParam(name = "action", required = false) String action,
			@RequestParam(name = "scene", required = false) String scene,
			HttpServletResponse response, HttpServletRequest request) throws IOException {
		log.info("{},{},{},{}", openid, templateId, action, scene);

		httpSession.setAttribute(openid+"_subscribe_scene", scene);
		httpSession.setAttribute("appid", appid);

		if (Objects.isNull(openid)) {
			log.info("redirect to login");
			return "LoginWeb/Login";
		}

		if (studentBindService.isStudentBind(openid, appid)) {
			Student student = studentBindService.getStudentByOpenID(openid, appid);
			httpSession.setAttribute("student", student);
			httpSession.setAttribute("account", student.getAccount().toString());
			log.info("redirect to timetable account：{}", student.getAccount());
			if(Objects.equals(wechatMpPlusProperties.getAppId(), appid)){
				//如果appid和plus的配置相等，执行plus的处理过程
				wxMpPlusToProcessing(action, appid, openid, scene, student);
			} else {
				wxMpProToProcessing(action, appid, openid, scene, student);
			}
			return "new";

		} else {
			httpSession.setAttribute("openid", openid);
			log.info("redirect to login");
			return "LoginWeb/Login";
		}
	}

	private void wxMpProToProcessing(String action, String appid, String openid, String scene, Student student) {
		WxMpService wxMpService = WechatMpConfiguration.getMpServices().get(appid);
		//判断该openId是否已经订阅过且一次性订阅的动作是否为confirm，没有插入一条数据
		//TODO 修改订阅的方法
		if(Objects.equals("confirm", action) && !subscribeService.isSubscribe(openid)){
			subscribeService.insertOneSubOpenid(openid, scene, appid);
		}
		if(Objects.equals("1005", scene)) {
			List<CourseTimeTable> courseTimeTableList = courseService.getCoursesCurrentDay(student.getAccount());
			WxMpKefuMessage wxMpKefuMessage = new WxMpKefuMessage();
			wxMpKefuMessage.setMsgType("text");
			wxMpKefuMessage.setContent(courseService.toText(courseTimeTableList));
			wxMpKefuMessage.setToUser(openid);
			try {
				wxMpService.getKefuService().sendKefuMessage(wxMpKefuMessage);
				log.info("send kefuMessage about course success openid:{} appid:{}", openid, appid);
			} catch (WxErrorException e) {
				log.info("send kefuMessage about course failed openid:{} appid:{}", openid, appid);
			}
		}
	}


	private void wxMpPlusToProcessing(String action, String appid, String openid, String scene, Student student) {
		WxMpService wxMpService = WechatMpConfiguration.getMpServices().get(appid);
		//判断该openId是否已经订阅过且一次性订阅的动作是否为confirm，没有插入一条数据
		if(Objects.equals("confirm", action) && !subscribeService.isSubscribe(openid)){
			subscribeService.insertOneSubOpenid(openid, scene, appid);
		}
		if(Objects.equals("1005", scene)) {
			List<CourseTimeTable> courseTimeTableList = courseService.getCoursesCurrentDay(student.getAccount());
			WxMpKefuMessage wxMpKefuMessage = new WxMpKefuMessage();
			wxMpKefuMessage.setMsgType("text");
			wxMpKefuMessage.setContent(courseService.toText(courseTimeTableList));
			wxMpKefuMessage.setToUser(openid);
			try {
				wxMpService.getKefuService().sendKefuMessage(wxMpKefuMessage);
				log.info("send kefuMessage about course success openid:{} appid:{}", openid, appid);
			} catch (WxErrorException e) {
				log.info("send kefuMessage about course failed openid:{} appid:{}", openid, appid);
			}
		}
	}
}
