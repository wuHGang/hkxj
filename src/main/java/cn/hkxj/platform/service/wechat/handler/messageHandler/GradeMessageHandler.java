package cn.hkxj.platform.service.wechat.handler.messageHandler;

import cn.hkxj.platform.builder.TemplateBuilder;
import cn.hkxj.platform.builder.TextBuilder;
import cn.hkxj.platform.mapper.OpenidMapper;
import cn.hkxj.platform.mapper.StudentMapper;
import cn.hkxj.platform.mapper.WechatOpenIdMapper;
import cn.hkxj.platform.pojo.Grade;
import cn.hkxj.platform.pojo.Openid;
import cn.hkxj.platform.pojo.Student;
import cn.hkxj.platform.pojo.Wechatuser;
import cn.hkxj.platform.service.GradeSearchService;
import cn.hkxj.platform.service.impl.GradeServiceImpl;
import cn.hkxj.platform.service.wechat.handler.AbstractHandler;
import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.common.session.WxSessionManager;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.message.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author Yuki
 * @date 2018/7/15 15:31
 */
@Component
public class GradeMessageHandler extends AbstractHandler {

	@Autowired
	private OpenidMapper openIdMapper;

	@Autowired
	private StudentMapper studentMapper;

	@Autowired
	private GradeServiceImpl gradeService;

	@Autowired
	private TextBuilder textBuilder;

	@Autowired
	private TemplateBuilder templateBuilder;

	@Autowired
	private GradeSearchService gradeSearchService;

	@Override
	public WxMpXmlOutMessage handle(WxMpXmlMessage wxMpXmlMessage, Map<String, Object> map, WxMpService wxMpService, WxSessionManager wxSessionManager) throws WxErrorException {
		List<String> wechatUser=new ArrayList();;
		wechatUser.add(wxMpXmlMessage.getFromUser());
		try {
			List<Openid> openId=openIdMapper.getOpenIdsByOpenIds(wechatUser);
			Student student=studentMapper.selectByAccount(openId.get(0).getAccount());
			List<Grade> studentGrades = gradeSearchService.getStudentGrades(student);
			String gradesMsg = gradeSearchService.toText(studentGrades);
			return textBuilder.build(gradesMsg , wxMpXmlMessage, wxMpService);
		} catch (Exception e) {
			this.logger.error("在组装返回信息时出现错误 {}", e.getMessage());
		}
		return null;
	}
}
