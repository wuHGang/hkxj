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

	public WxMpTemplateMessage build(WxMpXmlMessage wxMessage, List<WxMpTemplateData> list, String url) {
		WxMpTemplateMessage m = WxMpTemplateMessage.builder()
				.toUser(wxMessage.getFromUser())
				.templateId("GaeZamuWQde7U67l0AznX8UOgqIWceK-mwXLUrVTDRs")
				.data(list)
				.url(url)
				.build();
		return m;
	}
}
