package cn.hkxj.platform.service.wechat.handler.messageHandler;

import cn.hkxj.platform.builder.TemplateBuilder;
import cn.hkxj.platform.config.wechat.WechatMpPlusProperties;
import cn.hkxj.platform.config.wechat.WechatTemplateProperties;
import cn.hkxj.platform.pojo.CourseTimeTableDetail;
import cn.hkxj.platform.pojo.ScheduleTask;
import cn.hkxj.platform.pojo.Student;
import cn.hkxj.platform.pojo.constant.MiniProgram;
import cn.hkxj.platform.pojo.constant.SubscribeScene;
import cn.hkxj.platform.pojo.wechat.Openid;
import cn.hkxj.platform.service.CourseTimeTableService;
import cn.hkxj.platform.service.OpenIdService;
import cn.hkxj.platform.service.ScheduleTaskService;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.api.WxConsts;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.common.session.WxSessionManager;
import me.chanjar.weixin.mp.api.WxMpMessageHandler;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.kefu.WxMpKefuMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutMessage;
import me.chanjar.weixin.mp.bean.template.WxMpTemplateData;
import me.chanjar.weixin.mp.bean.template.WxMpTemplateMessage;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
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

    private static final String TEMPLATE_REDIRECT_URL = "https://platform.hackerda.com/platform/course/timetable";

    @Resource
    private ScheduleTaskService scheduleTaskService;
    @Resource
    private TemplateBuilder templateBuilder;
    @Resource
    private CourseTimeTableService courseTimeTableService;
    @Resource
    private WechatMpPlusProperties wechatMpPlusProperties;
    @Resource
    private OpenIdService openIdService;
    @Resource
    private WechatTemplateProperties wechatTemplateProperties;

    @Override
    public WxMpXmlOutMessage handle(WxMpXmlMessage wxMpXmlMessage, Map<String, Object> map, WxMpService wxMpService, WxSessionManager wxSessionManager) {
        if (isPlus(wxMpService)) {
            ScheduleTask scheduleTask = new ScheduleTask(wxMpService, wxMpXmlMessage, SubscribeScene.COURSE_PUSH.getScene());
            //所有使用该接口的人，将其订阅状态都置为可用
            scheduleTaskService.checkAndSetSubscribeStatus(scheduleTask, true);
            plusProcessing(wxMpXmlMessage, wxMpService);
            //这里返回null，是因为plus发送模板消息后，不需要发送文本消息。
            return null;
        }
        String replyContent = getReplyContent(wxMpXmlMessage,wxMpService);
        WxMpKefuMessage textMessage = buildTextKefuMessage(wxMpXmlMessage.getFromUser(), WxConsts.KefuMsgType.TEXT, replyContent);
        WxMpKefuMessage miniProgramMessage = MiniProgram.COURSE_PATH.genCard(wxMpXmlMessage.getFromUser(),
                wxMpService.getWxMpConfigStorage().getAppId());
        sendKefuMessage(wxMpService, textMessage);
        sendKefuMessage(wxMpService, miniProgramMessage);
        return null;
    }

    private boolean isPlus(WxMpService wxMpService){
        return Objects.equals(wechatMpPlusProperties.getAppId(), wxMpService.getWxMpConfigStorage().getAppId());
    }

    private String getReplyContent(WxMpXmlMessage wxMpXmlMessage, WxMpService wxMpService){
        Openid openid = openIdService.getOpenid(wxMpXmlMessage.getFromUser(), wxMpService.getWxMpConfigStorage().getAppId()).get(0);
        Student student = openIdService.getStudentByOpenId(openid.getOpenid(), wxMpService.getWxMpConfigStorage().getAppId());
        List<CourseTimeTableDetail> details = courseTimeTableService.getDetailsForCurrentDay(student);
        return courseTimeTableService.convertToText(details);
    }

    //plus的处理逻辑
    private void plusProcessing(WxMpXmlMessage wxMpXmlMessage, WxMpService wxMpService){
        String replyContent = getReplyContent(wxMpXmlMessage, wxMpService);
        List<WxMpTemplateData> templateData = templateBuilder.assemblyTemplateContentForCourse(replyContent);
        WxMpTemplateMessage.MiniProgram miniProgram = new WxMpTemplateMessage.MiniProgram();
        miniProgram.setAppid(MiniProgram.APP_ID);
        miniProgram.setPagePath(MiniProgram.COURSE_PATH.getValue());
        WxMpTemplateMessage templateMessage = templateBuilder.build(wxMpXmlMessage.getFromUser(), templateData,
                wechatTemplateProperties.getPlusCourseTemplateId(),
                miniProgram);

        try {
            wxMpService.getTemplateMsgService().sendTemplateMsg(templateMessage);
        } catch (WxErrorException e) {
            log.error("course.json keyword reply occurred error message:{}", e.getMessage());
        }

    }


    private WxMpKefuMessage buildTextKefuMessage(String openid, String msgType, String content){
        WxMpKefuMessage wxMpKefuMessage = new WxMpKefuMessage();
        wxMpKefuMessage.setTitle("今日课表");
        wxMpKefuMessage.setMsgType(msgType);
        wxMpKefuMessage.setToUser(openid);
        wxMpKefuMessage.setContent(content);
        return wxMpKefuMessage;
    }

    private void sendKefuMessage(WxMpService wxMpService, WxMpKefuMessage wxMpKefuMessage){
        try {
            wxMpService.getKefuService().sendKefuMessage(wxMpKefuMessage);
        } catch (WxErrorException e) {
            log.info("send kefu message to {} fail {}", wxMpKefuMessage.getToUser(), e.getMessage());
        }
    }
}
