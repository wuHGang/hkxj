package cn.hkxj.platform.service.wechat.handler.messageHandler;

import cn.hkxj.platform.builder.TemplateBuilder;
import cn.hkxj.platform.builder.TextBuilder;
import cn.hkxj.platform.config.wechat.WechatMpPlusProperties;
import cn.hkxj.platform.config.wechat.WechatTemplateProperties;
import cn.hkxj.platform.pojo.ScheduleTask;
import cn.hkxj.platform.pojo.constant.SubscribeScene;
import cn.hkxj.platform.pojo.timetable.CourseTimeTable;
import cn.hkxj.platform.pojo.wechat.Openid;
import cn.hkxj.platform.service.CourseService;
import cn.hkxj.platform.service.OpenIdService;
import cn.hkxj.platform.service.ScheduleTaskService;
import cn.hkxj.platform.utils.OneOffSubcriptionUtil;
import cn.hkxj.platform.utils.SchoolTimeUtil;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.common.session.WxSessionManager;
import me.chanjar.weixin.mp.api.WxMpMessageHandler;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.message.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutMessage;
import me.chanjar.weixin.mp.bean.template.WxMpTemplateData;
import me.chanjar.weixin.mp.bean.template.WxMpTemplateMessage;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
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
    private CourseService courseService;
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
        //pro的话，直接返回一个包含一次性订阅连接的文本消息
        String hyperlink = OneOffSubcriptionUtil.getHyperlinks("点击领取今日课表", "1005", wxMpService);
        return new TextBuilder().build(hyperlink, wxMpXmlMessage, wxMpService);
    }

    private boolean isPlus(WxMpService wxMpService){
        return Objects.equals(wechatMpPlusProperties.getAppId(), wxMpService.getWxMpConfigStorage().getAppId());
    }

    //plus的处理逻辑
    private void plusProcessing(WxMpXmlMessage wxMpXmlMessage, WxMpService wxMpService){
        Openid openid = openIdService.getOpenid(wxMpXmlMessage.getFromUser(), wxMpService.getWxMpConfigStorage().getAppId()).get(0);
        List<CourseTimeTable> courseTimeTables = courseService.getCoursesCurrentDay(openid.getAccount());
        List<WxMpTemplateData> templateData = templateBuilder.assemblyTemplateContentForCourse(courseService.toText(courseTimeTables));
        WxMpTemplateMessage templateMessage =
                templateBuilder.buildWithNoMiniProgram(wxMpXmlMessage.getFromUser(), templateData,
                        wechatTemplateProperties.getPlusCourseTemplateId(), TEMPLATE_REDIRECT_URL);
        try {
            wxMpService.getTemplateMsgService().sendTemplateMsg(templateMessage);
        } catch (WxErrorException e) {
            log.error("course keyword reply occurred error message:{}", e.getMessage());
        }

    }

}
