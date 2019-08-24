package cn.hkxj.platform.service.wechat.handler.messageHandler;

import cn.hkxj.platform.MDCThreadPool;
import cn.hkxj.platform.exceptions.PasswordUncorrectException;
import cn.hkxj.platform.exceptions.UrpEvaluationException;
import cn.hkxj.platform.pojo.GradeAndCourse;
import cn.hkxj.platform.pojo.GradeSearchResult;
import cn.hkxj.platform.pojo.ScheduleTask;
import cn.hkxj.platform.pojo.Student;
import cn.hkxj.platform.pojo.constant.SubscribeScene;
import cn.hkxj.platform.service.GradeSearchService;
import cn.hkxj.platform.service.NewGradeSearchService;
import cn.hkxj.platform.service.OpenIdService;
import cn.hkxj.platform.service.ScheduleTaskService;
import cn.hkxj.platform.service.wechat.CustomerMessageService;
import com.alibaba.ttl.TransmittableThreadLocal;
import com.alibaba.ttl.threadpool.TtlExecutors;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.session.WxSessionManager;
import me.chanjar.weixin.mp.api.WxMpMessageHandler;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.message.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutMessage;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.concurrent.*;

/**
 * @author Yuki
 * @date 2018/7/15 15:31
 */
@Slf4j
@Component
public class GradeMessageHandler implements WxMpMessageHandler {

    @Resource
    private NewGradeSearchService newGradeSearchService;
    @Resource
    private OpenIdService openIdService;
    @Resource
    private ScheduleTaskService scheduleTaskService;
    @Value("${domain}")
    private String domain;
    private static final String PATTERN = "<a href=\"%s/bind?openid=%s&appid=%s\">点击我绑定</a>";

    private static ExecutorService cacheThreadPool = TtlExecutors.getTtlExecutorService(
            new MDCThreadPool(7, 7, 0L,TimeUnit.SECONDS,
            new LinkedBlockingQueue<>(), r -> new Thread(r, "GradeMessageThread")));

    @Override
    public WxMpXmlOutMessage handle(WxMpXmlMessage wxMpXmlMessage,
                                    Map<String, Object> map,
                                    WxMpService wxMpService,
                                    WxSessionManager wxSessionManager) {
        String appId = wxMpService.getWxMpConfigStorage().getAppId();
        String openid = wxMpXmlMessage.getFromUser();

        Student student = openIdService.getStudentByOpenId(openid, appId);
        ScheduleTask scheduleTask = new ScheduleTask(appId, openid, SubscribeScene.GRADE_AUTO_UPDATE.getScene());

        cacheThreadPool.execute(() -> scheduleTaskService.checkAndSetSubscribeStatus(scheduleTask, true));

        CustomerMessageService messageService = new CustomerMessageService(wxMpXmlMessage, wxMpService);

        CompletableFuture<GradeSearchResult> completableFuture =
                CompletableFuture.supplyAsync(() -> { try {
                    return newGradeSearchService.getCurrentGrade(student);
                }catch (UrpEvaluationException e){
                    messageService.sentTextMessage("评估未完成 请到教务网完成评估\n\n地址： http://xsurp.usth.edu.cn");
                    throw e;
                }catch (PasswordUncorrectException e){
                    openIdService.openIdUnbind(openid, appId);
                    String content = String.format(PATTERN, domain, openid, appId);
                    messageService.sentTextMessage(content);
                    throw e;
                }
                }, cacheThreadPool);

        completableFuture.whenComplete((gradeSearchResult, throwable) -> {
            if(throwable != null){
                log.error("send grade message error", throwable);
                return;
            }

            try {
                String text = NewGradeSearchService.gradeListToText(gradeSearchResult.getData());

                if(StringUtils.isEmpty(text)){
                    messageService.sentTextMessage("暂时没查到成绩，请稍后重试");
                    return;
                }

                messageService.sentTextMessage(text);
            }catch (Exception e){
                log.error("", e);
            }

        });

        return null;
    }


    public static void main(String[] args) {
        CompletableFuture<Void> test = CompletableFuture.runAsync(() -> {
            throw new UrpEvaluationException("test");
        });

        test.whenComplete((aVoid, throwable) -> {
           if(throwable != null){
               System.out.println(throwable.getClass());
           }
        });
    }

}