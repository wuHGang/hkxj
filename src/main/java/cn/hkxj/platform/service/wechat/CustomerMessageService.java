package cn.hkxj.platform.service.wechat;

import cn.hkxj.platform.pojo.GradeSearchResult;
import cn.hkxj.platform.pojo.Student;
import cn.hkxj.platform.pojo.UrpGradeAndUrpCourse;
import cn.hkxj.platform.service.NewGradeSearchService;
import cn.hkxj.platform.service.OpenIdService;
import com.google.common.util.concurrent.ThreadFactoryBuilder;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.api.WxConsts;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.kefu.WxMpKefuMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutTextMessage;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.concurrent.*;

/**
 * @author JR Chan
 * @date 2019/5/3
 */
@Slf4j
@Service
@NoArgsConstructor
public class CustomerMessageService {

    @Resource
    private OpenIdService openIdService;

    private static ThreadFactory gradeUpdateThreadFactory = new ThreadFactoryBuilder().setNameFormat("grade-notice-%d").build();
    private static ExecutorService gradeUpdateNotice = new ThreadPoolExecutor(1, 1,
            0L, TimeUnit.MILLISECONDS,
            new LinkedBlockingQueue<Runnable>(), gradeUpdateThreadFactory);

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
            future.whenCompleteAsync((result, exception) -> sentTextMessage(result));
        }
        return buildMessage("服务器正在努力查询中");
    }

    public WxMpXmlOutTextMessage sendGradeMessage(CompletableFuture<GradeSearchResult> future, Student student) {

        try {
            GradeSearchResult searchResult = future.get(3500, TimeUnit.MILLISECONDS);
            if(searchResult.isUpdate()){
                gradeUpdateNotice.submit(() -> sendGradeUpdateNotice(wxMpService, student, wxMpXmlMessage.getFromUser()));
            }
            List<UrpGradeAndUrpCourse> gradeAndCourses = searchResult.getData();
            return buildMessage(NewGradeSearchService.gradeListToText(gradeAndCourses));
        } catch (TimeoutException | InterruptedException | ExecutionException e) {
            future.whenCompleteAsync((result, exception) -> {
                sentTextMessage(NewGradeSearchService.gradeListToText(result.getData()));
                if(exception != null){
                    log.error("student {} from wechat message get grade error {}", student.getAccount(), exception);
                }
            });
        }
        return null;
    }

    private void sendGradeUpdateNotice(WxMpService wxMpService, Student student, String openid){
        List<String> openids = openIdService.getAllOpenidsFromOneClass(student.getClasses(), openid, wxMpService.getWxMpConfigStorage().getAppId());
        String content = "通知/n你有新的成绩，请注意查收";
        for(String noticedOpenid : openids){
            WxMpKefuMessage wxMpKefuMessage = new WxMpKefuMessage();
            wxMpKefuMessage.setContent(content);
            wxMpKefuMessage.setMsgType("text");
            wxMpKefuMessage.setToUser(noticedOpenid);
            try {
                log.info("send customer message {}", content);
                wxMpService.getKefuService().sendKefuMessage(wxMpKefuMessage);
            } catch (WxErrorException e) {
                log.error("send customer message error", e);
            }
        }
    }

    public void sentTextMessage(String result) {

        WxMpKefuMessage wxMpKefuMessage = new WxMpKefuMessage();
        wxMpKefuMessage.setContent(result);
        wxMpKefuMessage.setMsgType("text");
        wxMpKefuMessage.setToUser(wxMpXmlMessage.getFromUser());
        try {
            log.info("send customer message {}", result);
            wxMpService.getKefuService().sendKefuMessage(wxMpKefuMessage);
        } catch (WxErrorException e) {
            log.error("send customer message error", e);
        }
    }

    void sendMessage(WxMpXmlOutMessage message) {
        WxMpKefuMessage wxMpKefuMessage = new WxMpKefuMessage();
        wxMpKefuMessage.setToUser(wxMpXmlMessage.getFromUser());
        if(message.getMsgType().equals(WxConsts.MassMsgType.TEXT)){
            WxMpXmlOutTextMessage textMessage = (WxMpXmlOutTextMessage) message;
            wxMpKefuMessage.setContent(textMessage.getContent());
            wxMpKefuMessage.setMsgType("text");
            log.info("send customer message {}", textMessage.getContent());
        }else {
            throw new RuntimeException("unSupport message type");
        }

        try {

            wxMpService.getKefuService().sendKefuMessage(wxMpKefuMessage);
        } catch (WxErrorException e) {
            log.error("send customer message error", e);
        }
    }

    private WxMpXmlOutTextMessage buildMessage(String content) {
        return WxMpXmlOutMessage.TEXT().content(content)
                .fromUser(wxMpXmlMessage.getToUser()).toUser(wxMpXmlMessage.getFromUser())
                .build();
    }

}
