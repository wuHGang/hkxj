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

@Slf4j
@Component
public class ElectiveCourseMessageHandler implements WxMpMessageHandler {
    @Resource
    private TextBuilder textBuilder;

    @Resource
    private GradeSearchService gradeSearchService;

    @Resource
    private OpenIdService openIdService;

    @Resource
    TaskBindingService taskBindingService;

    public WxMpXmlOutMessage handle(WxMpXmlMessage wxMpXmlMessage,
                                    Map<String, Object> map,
                                    WxMpService wxMpService,
                                    WxSessionManager wxSessionManager) {
        try {
            Student student = openIdService.getStudentByOpenId(wxMpXmlMessage.getFromUser());

            ExecutorService singleThreadPool = Executors.newSingleThreadExecutor();

            List<GradeAndCourse> electiveCourseAsyncGrade = gradeSearchService.getElectiveCourseGradeAsync(student);
            if (CollectionUtils.isEmpty(electiveCourseAsyncGrade)) {
                singleThreadPool.execute(() -> {
                    List<GradeAndCourse> electiveCourseSyncGrade = gradeSearchService.getElectiveCourseGradeSync(student);
                    String gradeListToText = gradeSearchService.getElectiveCourseText(electiveCourseSyncGrade);
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
            String gradesMsg = gradeSearchService.getElectiveCourseText(electiveCourseAsyncGrade);

            WxMpKefuMessage wxMpKefuMessage = new WxMpKefuMessage();
            wxMpKefuMessage.setContent(gradesMsg);
            wxMpKefuMessage.setMsgType("text");
            wxMpKefuMessage.setToUser(wxMpXmlMessage.getFromUser());
            log.info("send student {} grade kefu message {}", student.getAccount(), gradesMsg);
            try {
                wxMpService.getKefuService().sendKefuMessage(wxMpKefuMessage);
            } catch (WxErrorException e) {
                log.error("send grade customer message error", e);
            }

            return textBuilder.build("", wxMpXmlMessage, wxMpService);
        } catch (Exception e) {
            log.error("在组装返回信息时出现错误", e);
        }

        return textBuilder.build("没有查询到相关成绩，晚点再来查吧~" , wxMpXmlMessage, wxMpService);
    }
}
