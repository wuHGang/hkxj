package cn.hkxj.platform.service.wechat.handler.messageHandler;

import cn.hkxj.platform.builder.TemplateBuilder;
import cn.hkxj.platform.builder.TextBuilder;
import cn.hkxj.platform.pojo.Course;
import cn.hkxj.platform.pojo.timetable.CourseTimeTable;
import cn.hkxj.platform.service.CourseService;
import cn.hkxj.platform.service.wechat.handler.AbstractHandler;
import cn.hkxj.platform.utils.OneOffSubcriptionUtil;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.common.session.WxSessionManager;
import me.chanjar.weixin.mp.api.WxMpMessageHandler;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.message.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutMessage;
import me.chanjar.weixin.mp.bean.template.WxMpTemplateData;
import me.chanjar.weixin.mp.bean.template.WxMpTemplateMessage;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * @author Yuki
 * @date 2018/7/15 15:39
 */
@Slf4j
@Component
public class CourseMessageHandler implements WxMpMessageHandler {

	private static final String URL = "https://7c8aab51.ngrok.io/login";

	@Resource
	private TemplateBuilder templateBuilder;

	@Resource
	private CourseService courseService;

	@Override
	public WxMpXmlOutMessage handle(WxMpXmlMessage wxMpXmlMessage, Map<String, Object> map, WxMpService wxMpService, WxSessionManager wxSessionManager) {
		if(Objects.equals("wx342acff3b65da191", wxMpService.getWxMpConfigStorage().getAppId())){
			List<CourseTimeTable> courseTimeTables = courseService.getCoursesCurrentDay(2016024170);
			List<WxMpTemplateData> templateData = new ArrayList<>();
			WxMpTemplateData wxMpTemplateData = new WxMpTemplateData();
			wxMpTemplateData.setName("first");
			wxMpTemplateData.setValue(courseService.toText(courseTimeTables));
			wxMpTemplateData.setName("second");
			wxMpTemplateData.setValue("啦啦啦啦啦啦啦啦啦啦啦啦啦啦啦啦");
			WxMpTemplateMessage wxMpTemplateMessage = templateBuilder.build(wxMpXmlMessage, templateData, URL);
			wxMpTemplateMessage.setToUser(wxMpXmlMessage.getFromUser());
			try {
				wxMpService.getTemplateMsgService().sendTemplateMsg(wxMpTemplateMessage);
				wxMpService.getTemplateMsgService().getAllPrivateTemplate();
			} catch (WxErrorException e) {
				e.printStackTrace();
			}
			return null;
//			return new TextBuilder().build("我是第二个公众号啦啦啦啦", wxMpXmlMessage, wxMpService);
		}
		String examMsg = OneOffSubcriptionUtil.getHyperlinks("点击领取今日课表", "1005", wxMpService);
		return new TextBuilder().build(examMsg, wxMpXmlMessage, wxMpService);
	}
}
