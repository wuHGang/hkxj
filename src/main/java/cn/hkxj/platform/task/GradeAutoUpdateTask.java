package cn.hkxj.platform.task;

import cn.hkxj.platform.builder.TemplateBuilder;
import cn.hkxj.platform.config.wechat.WechatMpConfiguration;
import cn.hkxj.platform.config.wechat.WechatMpPlusProperties;
import cn.hkxj.platform.config.wechat.WechatTemplateProperties;
import cn.hkxj.platform.dao.ScheduleTaskDao;
import cn.hkxj.platform.pojo.Grade;
import cn.hkxj.platform.pojo.ScheduleTask;
import cn.hkxj.platform.pojo.Student;
import cn.hkxj.platform.pojo.constant.MiniProgram;
import cn.hkxj.platform.pojo.constant.SubscribeScene;
import cn.hkxj.platform.pojo.vo.GradeVo;
import cn.hkxj.platform.pojo.wechat.Openid;
import cn.hkxj.platform.service.NewGradeSearchService;
import cn.hkxj.platform.service.OpenIdService;
import cn.hkxj.platform.service.ScheduleTaskService;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.kefu.WxMpKefuMessage;
import me.chanjar.weixin.mp.bean.template.WxMpTemplateData;
import me.chanjar.weixin.mp.bean.template.WxMpTemplateMessage;
import org.apache.commons.lang3.BooleanUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * @author Yuki
 * @date 2019/6/6 10:55
 */
@Slf4j
@Service
public class GradeAutoUpdateTask extends BaseSubscriptionTask {
    //这里设置拒绝策略为调用者运行，这样可以降低产生任务的速率
    private static ExecutorService gradeAutoUpdatePool = new ThreadPoolExecutor(10, 10,
            0L, TimeUnit.MILLISECONDS, new LinkedBlockingDeque<>(20), new ThreadPoolExecutor.CallerRunsPolicy());

    private static final String ERROR_CONTENT = "请更新你的账号\n" +
            "你的账号密码可能有误，请点击这里的链接进行更新。否则部分功能将无法使用\n";
    private static final String BIND_URL = "https://platform.hackerda.com/platform/bind";

    @Resource
    private ScheduleTaskService scheduleTaskService;
    @Resource
    private NewGradeSearchService newGradeSearchService;
    @Resource
    private OpenIdService openIdService;
    @Resource
    private WechatMpPlusProperties wechatMpPlusProperties;
    @Resource
    private TemplateBuilder templateBuilder;
    @Resource
    private WechatTemplateProperties wechatTemplateProperties;
    @Resource
    private ScheduleTaskDao scheduleTaskDao;

    @Value("scheduled.gradeUpdate")
    private String updateSwitch;

    //    @Async
    @Scheduled(cron = "0 0/20 * * * ? ") //每20分钟执行一次
    void autoUpdateGrade() {
        //执行前，检查定时任务的可用性
        if (isTaskEnable()) {
            return;
        }

        for (ScheduleTask task : scheduleTaskDao.getPlusSubscribeTask(SubscribeScene.GRADE_AUTO_UPDATE)) {
            processScheduleTask(task);
        }
        ;

    }

    /**
     * 处理每一个定时任务
     *
     * @param task 定时任务
     */
    void processScheduleTask(ScheduleTask task) {
        Student student = openIdService.getStudentByOpenId(task.getOpenid(), task.getAppid());
        List<GradeVo> termGrade = newGradeSearchService.getCurrentTermGradeSync(student);
        List<GradeVo> updateList = termGrade.stream().filter(GradeVo::isUpdate).collect(Collectors.toList());
        WxMpService service = WechatMpConfiguration.getMpServices().get(task.getAppid());
        if (!CollectionUtils.isEmpty(updateList)) {
            for (GradeVo gradeVo : updateList) {
                List<WxMpTemplateData> templateData = templateBuilder.gradeToTemplateData(student, gradeVo);
                WxMpTemplateMessage.MiniProgram miniProgram = new WxMpTemplateMessage.MiniProgram();
                miniProgram.setAppid(MiniProgram.APP_ID);
                miniProgram.setPagePath(MiniProgram.GRADE_PATH.getValue());
                //构建一个课程推送的模板消息
                WxMpTemplateMessage templateMessage =
                        templateBuilder.build(task.getOpenid(), templateData, wechatTemplateProperties.getPlusGradeUpdateTemplateId(),
                                miniProgram);

                sendTemplateMessage(service, templateMessage, task);
            }

        }
    }

    /**
     * 当使用urp爬虫密码错误时，会将student的isCorrect属性置为0。
     * 所以当isUrpPassowrdCorrect返回false时。解绑所有的绑定
     *
     * @param task        定时任务
     * @param wxMpService wxMpService
     * @param appid       公众号的appid
     */
    private void processUrpPasswordNotCorrect(ScheduleTask task, WxMpService wxMpService, String appid) {
        Openid openid = openIdService.getOpenid(task.getOpenid(), appid).get(0);
        //将plus和pro都解绑
        openIdService.openIdUnbindAllPlatform(openid, appid);
        //将其相关联的定时任务也设为不可用
        scheduleTaskService.updateSubscribeStatus(task, ScheduleTaskService.FUNCTION_DISABLE);
        if (isPlus(appid)) {
            List<WxMpTemplateData> templateData = templateBuilder.assemblyTemplateContentForTips(ERROR_CONTENT);
            WxMpTemplateMessage templateMessage =
                    templateBuilder.buildWithNoMiniProgram(task.getOpenid(),
                            templateData, wechatTemplateProperties.getPlusTipsTemplateId(), BIND_URL);
            sendTemplateMessage(wxMpService, templateMessage, task);
        } else {
            sendKefuMessage(wxMpService, task.getOpenid(), ERROR_CONTENT);
            sendKefuMessage(wxMpService, task.getOpenid(), BIND_URL);
        }
    }


    /**
     * 生成对应的客服消息，调用父类的方法来发送
     *
     * @param wxMpService wxMpService
     * @param openid      openid
     * @param content     内容
     */
    private void sendKefuMessage(WxMpService wxMpService, String openid, String content) {
        WxMpKefuMessage wxMpKefuMessage = new WxMpKefuMessage();
        wxMpKefuMessage.setToUser(openid);
        wxMpKefuMessage.setMsgType("text");
        wxMpKefuMessage.setContent(content);
        //调用BaseSubscriptionTask的方法来发送消息
        sendKefuMessage(wxMpService, wxMpKefuMessage);
    }

    /**
     * 判断当前任务属于那个公众号
     *
     * @param appid 公众号的appid
     * @return 是否属于
     */
    private boolean isPlus(String appid) {
        return Objects.equals(wechatMpPlusProperties.getAppId(), appid);
    }

    /**
     * 判断student的isCorrect属性
     *
     * @param student 学生实体
     * @return isCorrect的值
     */
    private boolean isUrpPasswordCorrect(Student student) {
        return student.getIsCorrect();
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