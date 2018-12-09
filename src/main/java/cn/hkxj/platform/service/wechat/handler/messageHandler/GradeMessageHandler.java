package cn.hkxj.platform.service.wechat.handler.messageHandler;

import cn.hkxj.platform.builder.TextBuilder;
import cn.hkxj.platform.mapper.OpenidMapper;
import cn.hkxj.platform.mapper.StudentMapper;
import cn.hkxj.platform.pojo.Openid;
import cn.hkxj.platform.pojo.Student;
import cn.hkxj.platform.service.GradeSearchService;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.common.session.WxSessionManager;
import me.chanjar.weixin.mp.api.WxMpMessageHandler;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.message.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutMessage;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author Yuki
 * @date 2018/7/15 15:31
 */
@Slf4j
@Component
public class GradeMessageHandler implements WxMpMessageHandler {

    @Resource
	private OpenidMapper openIdMapper;

    @Resource
	private StudentMapper studentMapper;

    @Resource
	private TextBuilder textBuilder;

    @Resource
	private GradeSearchService gradeSearchService;

	public WxMpXmlOutMessage handle(WxMpXmlMessage wxMpXmlMessage,
									Map<String, Object> map,
									WxMpService wxMpService,
									WxSessionManager wxSessionManager) throws WxErrorException {
        List<String> wechatUser = new ArrayList<>();
		wechatUser.add(wxMpXmlMessage.getFromUser());
		try {
			List<Openid> openId=openIdMapper.getOpenIdsByOpenIds(wechatUser);
			Student student=studentMapper.selectByAccount(openId.get(0).getAccount());
            String gradesMsg = gradeSearchService.toText(gradeSearchService.returnGrade(student.getAccount(), student.getPassword(), gradeSearchService.getGradeList(student.getAccount())));
			return textBuilder.build(gradesMsg , wxMpXmlMessage, wxMpService);
		} catch (Exception e) {
            log.error("在组装返回信息时出现错误 {}", e.getMessage());
		}

		return textBuilder.build("没有查询到相关成绩，晚点再来查吧~" , wxMpXmlMessage, wxMpService);
	}
}
