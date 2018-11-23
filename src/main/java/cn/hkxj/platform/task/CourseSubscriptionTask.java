package cn.hkxj.platform.task;

import cn.hkxj.platform.pojo.CourseGroupMsg;
import cn.hkxj.platform.service.CourseService;
import cn.hkxj.platform.service.SubscribeService;
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
public class CourseSubscriptionTask {

    private CourseService courseService;
    private WxMpService wxMpService;
    private SubscribeService subscribeService;

    private static final String TEMPLATE_ID = "5TgQ5wk_3q01xfdqAqPDgAJDiT4YfmYOoIP6cnAhOKc";
    private static final Byte SEND_SUCCESS = 0;
    private static final Byte SEND_FAILED = 1;
    private static final String URL = "";

    @Scheduled(cron = "0 0 8 ? * MON-FRI")      //这个cron表达式的意思是星期一到星期五的早上8点执行一次
    public void sendCourseRemindMsg() {
        List<CourseGroupMsg> courseGroupMsgList = courseService.getCoursesSubscribeForCurrentDay();
        if (courseGroupMsgList == null) {
            log.error("course daily push get list of CourseGroupMsg size is null");
            return;
        }
        if (courseGroupMsgList.size() == 0) {
            log.error("course daily push get list of CourseGroupMsg size is 0");
            return;
        }
        for (CourseGroupMsg msg : courseGroupMsgList) {
            //获取一个并行流，添加监视messagePeek,设置过滤条件，然后每一个都进行消息发送
            msg.getOpenIds().stream().parallel().peek(this::messagePeek).filter(openid -> !Objects.equals(openid, null)).forEach(openid -> {
                //尝试三次，如果成功就跳出循环发送下一个
                for (int i = 1; i <= 3; i++) {
                    try {
                        wxMpService.getTemplateMsgService().sendTemplateMsg(getTemplateMessage(openid, msg.getCourseContent()));
                        log.info("{} time send course push to user {} success", i, openid);
                        subscribeService.updateCourseSubscribeMsgState(openid, SEND_SUCCESS);
                        break;
                    } catch (WxErrorException e) {
                        log.error("{} time send course push to user{} fail, error messasge {}", i, openid, e.getMessage());
                        subscribeService.updateCourseSubscribeMsgState(openid, SEND_FAILED);
                    }
                }

            });

        }

    }

    private WxMpTemplateMessage getTemplateMessage(String openid, String msgContent) {
        return WxMpTemplateMessage.builder()
                .toUser(openid)
                .templateId(TEMPLATE_ID)
                .data(getTemplateData(msgContent))
                .url(URL)
                .build();
    }

    private List<WxMpTemplateData> getTemplateData(String msgContent) {
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

    /**
     * 给并行流添加一个监视
     * @param openid 要发送到用户的openid
     */
    private void messagePeek(String openid){
        log.info("thread name {} send course push to user {}", Thread.currentThread().getName(), openid);
    }
}
