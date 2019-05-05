package cn.hkxj.platform.service.wechat.handler.messageHandler;

import cn.hkxj.platform.builder.TextBuilder;
import cn.hkxj.platform.pojo.GradeAndCourse;
import cn.hkxj.platform.pojo.Student;
import cn.hkxj.platform.service.GradeSearchService;
import cn.hkxj.platform.service.OpenIdService;
import cn.hkxj.platform.service.TaskBindingService;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.common.session.WxSessionManager;
import me.chanjar.weixin.mp.api.WxMpMessageHandler;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.kefu.WxMpKefuMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutMessage;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author Yuki
 * @date 2018/7/15 15:31
 */
@Slf4j
@Component
public class GradeMessageHandler implements WxMpMessageHandler {
    @Resource
	private TextBuilder textBuilder;

    @Resource
	private GradeSearchService gradeSearchService;

    @Resource
    private OpenIdService openIdService;

    @Resource
    private TaskBindingService taskBindingService;

	public WxMpXmlOutMessage handle(WxMpXmlMessage wxMpXmlMessage,
                                    Map<String, Object> map,
                                    WxMpService wxMpService,
                                    WxSessionManager wxSessionManager) {
		try {
		    String appid = wxMpService.getWxMpConfigStorage().getAppId();
            Student student = openIdService.getStudentByOpenId(wxMpXmlMessage.getFromUser(), appid);
            ExecutorService singleThreadPool = Executors.newSingleThreadExecutor();
            singleThreadPool.execute(() -> taskBindingService.subscribeGradeUpdateBinding(wxMpXmlMessage.getFromUser(), wxMpService));
            List<GradeAndCourse> currentTermGrade = gradeSearchService.getCurrentTermGradeAsync(student);
            if (CollectionUtils.isEmpty(currentTermGrade)) {
                singleThreadPool.execute(() -> {
                    List<GradeAndCourse> gradeFromSpiderSync = gradeSearchService.getCurrentTermGradeSync(student);
                    String gradeListToText = gradeSearchService.gradeListToText(gradeFromSpiderSync);
                    WxMpKefuMessage wxMpKefuMessage = new WxMpKefuMessage();
                    wxMpKefuMessage.setContent(gradeListToText);
                    wxMpKefuMessage.setMsgType("text");
                    wxMpKefuMessage.setToUser(wxMpXmlMessage.getFromUser());
                    log.info("send student {} grade kefu message {}", student.getAccount(), gradeListToText);
                    try {
                        wxMpService.getKefuService().sendKefuMessage(wxMpKefuMessage);
                    } catch (WxErrorException e) {
                        log.error("send grade customer message error", e);
                    }
                });
                return textBuilder.build("服务器正在努力查询中", wxMpXmlMessage, wxMpService);
            }
            String gradesMsg = gradeSearchService.gradeListToText(currentTermGrade);


            return textBuilder.build(gradesMsg, wxMpXmlMessage, wxMpService);
		} catch (Exception e) {
            log.error("在组装返回信息时出现错误", e);
		}

		return textBuilder.build("没有查询到相关成绩，晚点再来查吧~" , wxMpXmlMessage, wxMpService);
	}



}
