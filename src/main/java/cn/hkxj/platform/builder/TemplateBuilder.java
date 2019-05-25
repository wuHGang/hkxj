package cn.hkxj.platform.builder;

import me.chanjar.weixin.mp.bean.message.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.template.WxMpTemplateData;
import me.chanjar.weixin.mp.bean.template.WxMpTemplateMessage;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Syaeldon
 * 模板消息
 */
@Service
public class TemplateBuilder {

	private static final String PLUS_COURSE_TEMPLATE_ID = "XmX4UWJdtRDbDXhVToLAXYKJgHES2hNq9PT2N-XdzNs";
	private static final String TEST_COURSE_TEMPLATE_ID = "PVBBEzdHFhJAMsB3-P1y8OW20mY_oD_z6o86s2QQRJk";

	public WxMpTemplateMessage build(WxMpXmlMessage wxMessage, List<WxMpTemplateData> list, String url) {
		WxMpTemplateMessage m = WxMpTemplateMessage.builder()
				.toUser(wxMessage.getFromUser())
				.templateId("zJXOQMw1pnkk7oZpcUoWYVML7NfzwQRe-0BDS7wZDRU")
				.data(list)
				.url(url)
				.build();
		return m;
	}

	public WxMpTemplateMessage buildCourseMessage(String openid, List<WxMpTemplateData> list, String url){
		return WxMpTemplateMessage.builder()
				.toUser(openid)
				.templateId(PLUS_COURSE_TEMPLATE_ID)
				.data(list)
				.url(url)
				.build();
	}

	public WxMpTemplateMessage buildCourseMessage(WxMpXmlMessage wxMpXmlMessage, List<WxMpTemplateData> list, String url){
		return WxMpTemplateMessage.builder()
				.toUser(wxMpXmlMessage.getFromUser())
				.templateId(PLUS_COURSE_TEMPLATE_ID)
				.data(list)
				.url(url)
				.build();
	}
}
