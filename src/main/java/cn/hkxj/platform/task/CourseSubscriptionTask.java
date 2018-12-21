package cn.hkxj.platform.task;

import cn.hkxj.platform.pojo.CourseGroupMsg;
import cn.hkxj.platform.service.CourseSubscribeService;
import cn.hkxj.platform.service.SubscribeService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.kefu.WxMpKefuMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutMessage;
import me.chanjar.weixin.mp.bean.template.WxMpTemplateData;
import me.chanjar.weixin.mp.builder.outxml.TextBuilder;
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

    private CourseSubscribeService courseSubscribeService;
    private WxMpService wxMpService;
    private SubscribeService subscribeService;

    private static final String TEMPLATE_ID = "5TgQ5wk_3q01xfdqAqPDgAJDiT4YfmYOoIP6cnAhOKc";
    private static final Byte SEND_SUCCESS = 1;
    private static final Byte SEND_FAILED = 0;
    private static final String URL = "";

    @Scheduled(cron = "0 0 8 ? * MON-FRI")      //这个cron表达式的意思是星期一到星期五的早上8点执行一次
    public void sendCourseRemindMsg() {
        List<CourseGroupMsg> courseGroupMsgList = courseSubscribeService.getCoursesSubscribeForCurrentDay();
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
            msg.getOpenIds().stream().parallel().peek(this::messagePeek).filter(openid -> !Objects.isNull(openid)).forEach(openid -> {
                //尝试三次，如果成功就跳出循环发送下一个
                for (int i = 1; i <= 3; i++) {
                    try {
                        wxMpService.getKefuService().sendKefuMessage(getKufuMessage(openid, msg.getCourseContent()));
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

    private WxMpKefuMessage getKufuMessage(String openid, String msgContent) {
        WxMpKefuMessage wxMpKefuMessage = new WxMpKefuMessage();
        wxMpKefuMessage.setContent("今日课表\n" + msgContent);
        wxMpKefuMessage.setToUser(openid);
        wxMpKefuMessage.setMsgType("text");
        return wxMpKefuMessage;
    }

    /**
     * 给并行流添加一个监视
     * @param openid 要发送到用户的openid
     */
    private void messagePeek(String openid){
        log.info("thread name {} send course push to user {}", Thread.currentThread().getName(), openid);
    }
}
