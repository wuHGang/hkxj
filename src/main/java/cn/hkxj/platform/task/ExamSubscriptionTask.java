package cn.hkxj.platform.task;

import cn.hkxj.platform.builder.TemplateBuilder;
import cn.hkxj.platform.config.wechat.WechatMpConfiguration;
import cn.hkxj.platform.config.wechat.WechatMpPlusProperties;
import cn.hkxj.platform.config.wechat.WechatTemplateProperties;
import cn.hkxj.platform.pojo.ScheduleTask;
import cn.hkxj.platform.service.ExamTimeTableService;
import cn.hkxj.platform.service.ScheduleTaskService;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.mp.api.WxMpService;
import org.apache.commons.lang3.BooleanUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 1.通过定时任务的表把对应场景值中所有订阅状态可用的用户查询出来
 * 2.按照openid和appid属性去查找对应用户的考试时间
 * 3.根据具体的逻辑去处理上面的数据。
 * 通过schedule_task(openid, appid)的这两个属性 -> 在student表中找到学生对应的class_id  -> 然后用这个class_id
 * 去class_exam_timetable中找到所有关联的本学期的timetable_id -> 前面的timetable_id对应exam_timetable的主键id。
 *
 * @author FMC
 * @date 2019/6/22 12:36
 */

@Slf4j
@Service
public class ExamSubscriptionTask extends BaseSubscriptionTask{
    private static final String MSG_TITLE = "考试时间";
    @Resource
    private WechatMpPlusProperties wechatMpPlusProperties;
    @Resource
    private ExamTimeTableService examTimeTableService;
    @Resource
    private ScheduleTaskService scheduleTaskService;
    @Resource
    private TemplateBuilder templateBuilder;
    @Resource
    private WechatTemplateProperties wechatTemplateProperties;
    @Value("scheduled.gradeUpdate")
    private String updateSwitch;


    //@Scheduled(cron = "0/30 * * * * ?")
//    @Async
//    @Scheduled(cron = "0 0 20 ? * MON-FRI")      //这个cron表达式的意思是星期一到星期五的晚上8点执行一次
    void autoUpdateExam() {
        //执行前，检查定时任务的可用性
        if (isTaskEnable()){  return; }
        // 返回classes班级信息、examTimeTables明天的考试信息、scheduleTasks定时任务信息

    }




    /**
     * 根据appid获取相应的wxMpService
     * @param appid appid
     * @return wxMpService
     */
    private WxMpService getWxMpService(String appid) {
        return WechatMpConfiguration.getMpServices().get(appid);
    }

    /**
     * 给并行流添加一个监视
     */
    private void messagePeek(ScheduleTask scheduleTask) {
        log.info("send course.json push to user：{} appid:{}", scheduleTask.getOpenid(), scheduleTask.getAppid());
    }

    /**
     * 根据配置文件中的属性，判断该定时任务是否可用
     *
     * @return 可用结果
     */
    private boolean isTaskEnable() {
        return BooleanUtils.toBoolean(updateSwitch);
    }

}
