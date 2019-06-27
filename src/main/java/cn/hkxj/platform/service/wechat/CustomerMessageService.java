package cn.hkxj.platform.service.wechat;

import cn.hkxj.platform.pojo.Grade;
import cn.hkxj.platform.pojo.GradeAndCourse;
import cn.hkxj.platform.pojo.Student;
import cn.hkxj.platform.service.GradeSearchService;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.kefu.WxMpKefuMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutTextMessage;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * @author JR Chan
 * @date 2019/5/3
 */
@Slf4j
public class CustomerMessageService {

    private WxMpXmlMessage wxMpXmlMessage;
    private WxMpService wxMpService;

    public CustomerMessageService(WxMpXmlMessage wxMpXmlMessage, WxMpService wxMpService) {
        this.wxMpService = wxMpService;
        this.wxMpXmlMessage = wxMpXmlMessage;
    }


    public WxMpXmlOutTextMessage sendMessage(CompletableFuture<String> future, Student student) {

        try {
            String result = future.get(4, TimeUnit.SECONDS);
            return buildMessage(result);
        } catch (TimeoutException | InterruptedException | ExecutionException e) {
            log.error("student {} from wechat message get grade error {}", student.getAccount(), e.getMessage());
            future.whenCompleteAsync((result, exception) -> sentTextMessage(wxMpXmlMessage, wxMpService, result));
        }
        return buildMessage("服务器正在努力查询中");
    }

    public WxMpXmlOutTextMessage sendGradeMessage(CompletableFuture<List<GradeAndCourse>> future, Student student) {

        try {
            List<GradeAndCourse> gradeAndCourses = future.get(3500, TimeUnit.MILLISECONDS);
            return buildMessage(GradeSearchService.gradeListToText(gradeAndCourses));
        } catch (TimeoutException | InterruptedException | ExecutionException e) {
            log.error("student {} from wechat message get grade error {}", student.getAccount(), e.getMessage());
            future.whenCompleteAsync((result, exception) -> {
                for (List<GradeAndCourse> gradeAndCourseList : Lists.partition(result, 10)) {
                    sentTextMessage(wxMpXmlMessage, wxMpService,
                            GradeSearchService.gradeListToText(gradeAndCourseList));
                }
            });
        }
        return buildMessage("服务器正在努力查询中");
    }

    private void sentTextMessage(WxMpXmlMessage wxMpXmlMessage, WxMpService wxMpService, String result) {

        WxMpKefuMessage wxMpKefuMessage = new WxMpKefuMessage();
        wxMpKefuMessage.setContent(result);
        wxMpKefuMessage.setMsgType("text");
        wxMpKefuMessage.setToUser(wxMpXmlMessage.getFromUser());
        try {
            wxMpService.getKefuService().sendKefuMessage(wxMpKefuMessage);
        } catch (WxErrorException e) {
            log.error("send grade customer message error", e);
        }
    }

    private WxMpXmlOutTextMessage buildMessage(String content) {
        return WxMpXmlOutMessage.TEXT().content(content)
                .fromUser(wxMpXmlMessage.getToUser()).toUser(wxMpXmlMessage.getFromUser())
                .build();
    }
}
