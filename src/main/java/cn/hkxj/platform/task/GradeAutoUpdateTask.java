package cn.hkxj.platform.task;

import cn.hkxj.platform.config.wechat.WechatMpConfiguration;
import cn.hkxj.platform.pojo.GradeAndCourse;
import cn.hkxj.platform.pojo.ScheduleTask;
import cn.hkxj.platform.pojo.Student;
import cn.hkxj.platform.pojo.constant.SubscribeScene;
import cn.hkxj.platform.service.GradeSearchService;
import cn.hkxj.platform.service.OpenIdService;
import cn.hkxj.platform.service.ScheduleTaskService;
import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpConfigStorage;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.kefu.WxMpKefuMessage;
import org.apache.commons.lang3.BooleanUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author Yuki
 * @date 2019/6/6 10:55
 */
@Slf4j
@Service
public class GradeAutoUpdateTask {

    @Resource
    private ScheduleTaskService scheduleTaskService;
    @Resource
    private GradeSearchService gradeSearchService;
    @Resource
    private OpenIdService openIdService;

    @Value("scheduled.gradeUpdate")
    private String updateSwitch;

    @Scheduled(cron = "0 0/20 * * * ?")
    private void autoUpdateGrade() {
        //执行前，检查定时任务的可用性
        if(isTaskEnable()){
            return;
        }
        ExecutorService gradeAutoUpdatePool = Executors.newCachedThreadPool();
        Map<String, List<ScheduleTask>> appMappingScheduleTask =
                scheduleTaskService.getSubscribeData(Integer.parseInt(SubscribeScene.GRADE_AUTO_UPDATE.getScene()));
        appMappingScheduleTask.forEach((appid, tasks) -> {
            tasks.forEach(task -> {
                Student student = openIdService.getStudentByOpenId(task.getOpenid(), task.getAppid());
                //通过appid和openid如果没找到相应的学生信息，跳出循环并记录日志
                if(Objects.isNull(student)){
                    log.info("appid:{} openid:{} grade update failed. no opposite student info", task.getAppid(), task.getOpenid());
                    return;
                }
                //创建一个CompletableFuture来获取成绩
                CompletableFuture.supplyAsync(() ->{
                    List<GradeAndCourse> gradeFromSpiderSync = gradeSearchService.getCurrentGradeFromSpider(student);
                    return gradeSearchService.gradeListToText(gradeFromSpiderSync);
                }, gradeAutoUpdatePool).whenComplete((value, error) -> {
                    //完成时发送客服信息
                    WxMpService currentMpService = getWxMpService(appid);
                    try {
                        currentMpService.getKefuService().sendKefuMessage(getKefuMessage(task, value));
                    } catch (WxErrorException e) {
                        log.error("grade update task error", e);
                    }
                });
            });
        });
        gradeAutoUpdatePool.shutdown();
    }

    private boolean isTaskEnable(){
        return BooleanUtils.toBoolean(updateSwitch);
    }

    private WxMpService getWxMpService(String appid){
        return WechatMpConfiguration.getMpServices().get(appid);
    }

    private WxMpKefuMessage getKefuMessage(ScheduleTask scheduleTask, String content){
        WxMpKefuMessage wxMpKefuMessage = new WxMpKefuMessage();
        wxMpKefuMessage.setContent("成绩更新\n"+content );
        wxMpKefuMessage.setToUser(scheduleTask.getOpenid());
        wxMpKefuMessage.setMsgType("text");

        log.info("appid:{} openid:{} grade update {}", scheduleTask.getAppid(), scheduleTask.getOpenid(), content);

        return wxMpKefuMessage;
    }
}