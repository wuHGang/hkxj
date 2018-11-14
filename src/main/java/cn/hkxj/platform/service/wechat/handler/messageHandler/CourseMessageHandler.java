package cn.hkxj.platform.service.wechat.handler.messageHandler;

import cn.hkxj.platform.builder.TextBuilder;
import cn.hkxj.platform.mapper.OpenidMapper;
import cn.hkxj.platform.pojo.*;
import cn.hkxj.platform.service.CourseService;
import cn.hkxj.platform.service.wechat.handler.AbstractHandler;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.common.session.WxSessionManager;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.message.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutMessage;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * @author Yuki
 * @date 2018/7/15 15:39
 */
@Slf4j
@Component
@AllArgsConstructor
public class CourseMessageHandler extends AbstractHandler {

	private CourseService courseService;
	private OpenidMapper openidMapper;

	@Override
	public WxMpXmlOutMessage handle(WxMpXmlMessage wxMpXmlMessage, Map<String, Object> map, WxMpService wxMpService, WxSessionManager wxSessionManager) throws WxErrorException {
		String openid = wxMpXmlMessage.getFromUser();
		OpenidExample example = new OpenidExample();
		example.createCriteria()
				.andOpenidEqualTo(openid);
		Openid openidObject = openidMapper.selectByExample(example).get(0);
		List<CourseTimeTable> courseTimeTables = courseService.getCoursesCurrentDay(openidObject.getAccount());
		String examMsg = courseService.toText(courseTimeTables);
		return new TextBuilder().build(examMsg, wxMpXmlMessage, wxMpService);
	}
}
