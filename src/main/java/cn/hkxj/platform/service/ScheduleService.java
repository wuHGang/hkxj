package cn.hkxj.platform.service;

import cn.hkxj.platform.builder.TemplateBuilder;
import cn.hkxj.platform.pojo.CourseGroupMsg;
import cn.hkxj.platform.service.course.CourseService;
import com.google.common.base.Charsets;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.api.WxConsts;
import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.WxMpMassOpenIdsMessage;
import me.chanjar.weixin.mp.bean.kefu.WxMpKefuMessage;
import me.chanjar.weixin.mp.bean.result.WxMpMassSendResult;
import me.chanjar.weixin.mp.bean.template.WxMpTemplateData;
import me.chanjar.weixin.mp.bean.template.WxMpTemplateMessage;
import org.apache.commons.lang3.StringUtils;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
            log.error("课表每日推送中获取的CourseGroupMsg列表为空，发生异常");
            return;
        }
        for(CourseGroupMsg msg : courseGroupMsgList){
            msg.getOpenIds().stream().filter(openid -> !Objects.equals(openid, null)).forEach(openid -> {
                try {
                    wxMpService.getTemplateMsgService().sendTemplateMsg(getTemplateMessage(openid, msg.getCourseContent()));
                    log.info("发送课表推送消息给用户{}", openid);

                    subscribeService.updateCourseSubscribeMsgState(openid, SEND_SUCCESS);
                } catch (WxErrorException e) {
                    log.error("发送给用户{}的课表推送信息，发送失败, 错误信息{}", openid, e.getMessage());
                    subscribeService.updateCourseSubscribeMsgState(openid, SEND_FAILED);
                }
            });

        }

    }

    private WxMpTemplateMessage getTemplateMessage(String openid, String msgContent){
        WxMpTemplateMessage message = WxMpTemplateMessage.builder()
                .toUser(openid)
                .templateId(TEMPLATE_ID)
                .data(getTemplateData(msgContent))
                .url(URL)
                .build();
        return message;
    }

    private List<WxMpTemplateData> getTemplateData(String msgContent){
        List<WxMpTemplateData> datas = new ArrayList<WxMpTemplateData>();
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
