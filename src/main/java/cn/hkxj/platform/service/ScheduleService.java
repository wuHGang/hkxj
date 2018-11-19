package cn.hkxj.platform.service;

import cn.hkxj.platform.pojo.CourseGroupMsg;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.template.WxMpTemplateData;
import me.chanjar.weixin.mp.bean.template.WxMpTemplateMessage;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author Yuki
 * @date 2018/11/6 20:36
 */
@Slf4j
@Service
@AllArgsConstructor
public class ScheduleService {

    private CourseService courseService;
    private WxMpService wxMpService;
    private SubscribeService subscribeService;

    private static final String TEMPLATE_ID = "5TgQ5wk_3q01xfdqAqPDgAJDiT4YfmYOoIP6cnAhOKc";
    private static final Byte SEND_SUCCESS = 0;
    private static final Byte SEND_FAILED = 1;
    private static  final String URL = "";

    @Scheduled(cron = "0 0 8 ? * MON-FRI")      //这个cron表达式的意思是星期一到星期五的早上8点执行一次
    public void sendCourseRemindMsg()  {
        List<CourseGroupMsg> courseGroupMsgList = courseService.getCoursesSubscribeForCurrentDay();
        if(courseGroupMsgList.size() == 0){
            log.error("course daily push get list of CourseGroupMsg size is 0");
            return;
        }
        for(CourseGroupMsg msg : courseGroupMsgList){
            msg.getOpenIds().stream().filter(openid -> !Objects.equals(openid, null)).forEach(openid -> {
                try {
                    wxMpService.getTemplateMsgService().sendTemplateMsg(getTemplateMessage(openid, msg.getCourseContent()));
                    log.info("send course push to user{}", openid);

                    subscribeService.updateCourseSubscribeMsgState(openid, SEND_SUCCESS);
                } catch (WxErrorException e) {
                    log.error("send course push to user{}，send failed, error messasge {}", openid, e.getMessage());
                    subscribeService.updateCourseSubscribeMsgState(openid, SEND_FAILED);
                }
            });

        }

    }

    private WxMpTemplateMessage getTemplateMessage(String openid, String msgContent){
        return  WxMpTemplateMessage.builder()
                .toUser(openid)
                .templateId(TEMPLATE_ID)
                .data(getTemplateData(msgContent))
                .url(URL)
                .build();
    }

    private List<WxMpTemplateData> getTemplateData(String msgContent){
        List<WxMpTemplateData> datas = new ArrayList<>();
        WxMpTemplateData title = new WxMpTemplateData();
        title.setName("first");
        title.setValue("今日课表");
        WxMpTemplateData content = new WxMpTemplateData();
        content.setName("second");
        content.setValue("\n" + msgContent);
        datas.add(title);
        datas.add(content);
        return datas;
    }




}
