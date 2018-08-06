package cn.hkxj.platform.service.wechat.handler.messageHandler;

import cn.hkxj.platform.builder.TextBuilder;
import cn.hkxj.platform.mapper.WechatOpenIdMapper;
import cn.hkxj.platform.pojo.Lesson;
import cn.hkxj.platform.pojo.Wechatuser;
import cn.hkxj.platform.service.wechat.common.lesson.impl.LessonServiceImpl;
import cn.hkxj.platform.service.wechat.handler.AbstractHandler;
import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.common.session.WxSessionManager;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.message.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * @author Yuki
 * @date 2018/7/15 15:39
 */
@Component
public class LessonMessageHandler extends AbstractHandler {

	@Autowired
	private WechatOpenIdMapper openIdMapper;

	@Autowired
	private LessonServiceImpl lessonService;

	@Autowired
	private TextBuilder textBuilder;

	@Override
	public WxMpXmlOutMessage handle(WxMpXmlMessage wxMpXmlMessage, Map<String, Object> map, WxMpService wxMpService, WxSessionManager wxSessionManager) throws WxErrorException {
		String openid = wxMpXmlMessage.getFromUser();
		Wechatuser wechatuser = openIdMapper.getStudentByOpenId(openid);
		try {
			List<Lesson> examList = lessonService.getLessonsByClassname(wechatuser);
			String examMsg = lessonService.toText(examList);
			return textBuilder.build(examMsg, wxMpXmlMessage, wxMpService);
		} catch (IOException e) {
			this.logger.error("在组装返回信息时出现错误 {}", e.getMessage());
		}
		return null;
	}
}
