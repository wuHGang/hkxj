package cn.hkxj.platform.service.wechat.handler.messageHandler;

import cn.hkxj.platform.MDCThreadPool;
import cn.hkxj.platform.builder.TextBuilder;
import cn.hkxj.platform.exceptions.UrpEvaluationException;
import cn.hkxj.platform.pojo.GradeSearchResult;
import cn.hkxj.platform.pojo.ScheduleTask;
import cn.hkxj.platform.pojo.Student;
import cn.hkxj.platform.pojo.constant.SubscribeScene;
import cn.hkxj.platform.service.NewGradeSearchService;
import cn.hkxj.platform.service.OpenIdService;
import cn.hkxj.platform.service.ScheduleTaskService;
import com.alibaba.ttl.threadpool.TtlExecutors;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.session.WxSessionManager;
import me.chanjar.weixin.mp.api.WxMpMessageHandler;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.message.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutMessage;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

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
    @Resource
    private TextBuilder textBuilder;

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

        try {
            GradeSearchResult currentGrade = newGradeSearchService.getCurrentGrade(student);
            String text = NewGradeSearchService.gradeListToText(currentGrade.getData());

            if(StringUtils.isEmpty(text)){
                return textBuilder.build("暂时没查到成绩，请稍后重试", wxMpXmlMessage, wxMpService);
            }

            return textBuilder.build(text, wxMpXmlMessage, wxMpService);
        }catch (UrpEvaluationException e){
            return textBuilder.build("问卷未完成", wxMpXmlMessage, wxMpService);
        }


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