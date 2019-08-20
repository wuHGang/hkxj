package cn.hkxj.platform.service.wechat.handler.messageHandler;

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
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.session.WxSessionManager;
import me.chanjar.weixin.mp.api.WxMpMessageHandler;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.message.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutMessage;
import org.apache.commons.lang3.StringUtils;
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

    private ExecutorService cacheThreadPool = new ThreadPoolExecutor(3, 3, 0L, TimeUnit.SECONDS,
            new LinkedBlockingQueue<>(), r -> new Thread(r, "GradeMessageThread"));

    @Override
    public WxMpXmlOutMessage handle(WxMpXmlMessage wxMpXmlMessage,
                                    Map<String, Object> map,
                                    WxMpService wxMpService,
                                    WxSessionManager wxSessionManager) {
        String appid = wxMpService.getWxMpConfigStorage().getAppId();
        String openid = wxMpXmlMessage.getFromUser();

        Student student = openIdService.getStudentByOpenId(openid, appid);
        ScheduleTask scheduleTask = new ScheduleTask(appid, openid, SubscribeScene.GRADE_AUTO_UPDATE.getScene());

        cacheThreadPool.execute(() -> scheduleTaskService.checkAndSetSubscribeStatus(scheduleTask, true));

        CompletableFuture<GradeSearchResult> completableFuture = CompletableFuture.supplyAsync(() -> newGradeSearchService.getCurrentGrade(student), cacheThreadPool);

        CustomerMessageService messageService = new CustomerMessageService(wxMpXmlMessage, wxMpService);

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
        log.info("send message finish {}", openid);

        return null;
    }



}