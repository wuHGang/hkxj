package cn.hkxj.platform.service.wechat.handler.messageHandler;

import cn.hkxj.platform.builder.TemplateBuilder;
import cn.hkxj.platform.service.wechat.handler.AbstractHandler;
import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.common.session.WxSessionManager;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.message.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutMessage;
import me.chanjar.weixin.mp.bean.template.WxMpTemplateData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component
public class ExampleTemplateHandler extends AbstractHandler {

	@Autowired
	private TemplateBuilder templateBuilder;

	@Override
	public WxMpXmlOutMessage handle(WxMpXmlMessage wxMpXmlMessage, Map<String, Object> map, WxMpService wxMpService, WxSessionManager wxSessionManager) throws WxErrorException {

		System.out.println("！！！模板测试！！！");
		try {
			List<WxMpTemplateData> datas = new ArrayList<WxMpTemplateData>();

			WxMpTemplateData first = new WxMpTemplateData();
			first.setName("first");
			first.setValue("绑定页面");
			WxMpTemplateData name = new WxMpTemplateData();
			name.setName("name");
			name.setValue("测试");
			WxMpTemplateData grade = new WxMpTemplateData();
			grade.setName("grade");
			grade.setValue("样例");
			datas.add(first);
			datas.add(name);
			datas.add(grade);
			String url = "http://suagr.tunnel.echomod.cn/Login?openid=" + wxMpXmlMessage.getFromUser();
			wxMpService.getTemplateMsgService().sendTemplateMsg(templateBuilder.build(wxMpXmlMessage, datas, url));
			return null;
		} catch (Exception e) {
			this.logger.error("在组装返回信息时出现错误 {}", e.getMessage());
		}
		return null;
	}

}
