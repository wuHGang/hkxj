package cn.hkxj.platform.controller.wechat;

import cn.hkxj.platform.config.wechat.WechatMpConfiguration;
import cn.hkxj.platform.config.wechat.WechatMpPlusProperties;
import cn.hkxj.platform.pojo.ScheduleTask;
import cn.hkxj.platform.pojo.Student;
import cn.hkxj.platform.pojo.wechat.OneOffSubscription;
import cn.hkxj.platform.service.CourseService;
import cn.hkxj.platform.service.ScheduleTaskService;
import cn.hkxj.platform.service.wechat.StudentBindService;
import cn.hkxj.platform.utils.OneOffSubcriptionUtil;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.kefu.WxMpKefuMessage;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Objects;

/**
 * @author junrong.chen
 * @date 2018/10/7
 */
@Controller
@RequestMapping("/wechat/sub/{appid}")
@Slf4j
public class WxSubscriptionController {

    @Resource(name = "studentBindService")
    private StudentBindService studentBindService;
    @Resource
    private ScheduleTaskService scheduleTaskService;
    @Resource
    private CourseService courseService;
    @Resource
    private HttpSession httpSession;
    @Resource
    private WechatMpPlusProperties wechatMpPlusProperties;

    /**
     * @param openid     微信平台用户唯一标识
     * @param templateId 订阅消息模板ID
     * @param action     用户点击动作，”confirm”代表用户确认授权，”cancel”代表用户取消授权
     * @param scene      点击的具体场景类型
     * @param response   用于重定向
     * @throws IOException 页面重定向异常
     */
    @GetMapping("/test")
    public String testSubscription(
            @PathVariable String appid,
            @RequestParam(name = "openid", required = false) String openid,
            @RequestParam(name = "template_id", required = false) String templateId,
            @RequestParam(name = "action", required = false) String action,
            @RequestParam(name = "scene", required = false) String scene,
            HttpServletResponse response, HttpServletRequest request) throws IOException {
        log.info("{},{},{},{}", openid, templateId, action, scene);

        httpSession.setAttribute(openid + "_subscribe_scene", scene);
        httpSession.setAttribute("appid", appid);

        if (Objects.isNull(openid)) {
            log.info("redirect to getStudentInfo");
            return "LoginWeb/Login";
        }

        if (studentBindService.isStudentBind(openid, appid)) {
            Student student = studentBindService.getStudentByOpenID(openid, appid);
            httpSession.setAttribute("student", student);
            httpSession.setAttribute("account", student.getAccount().toString());
            log.info("redirect to timetable account：{}", student.getAccount());
            //只有当action等于确认的时候才进入处理
            actionEqualToConfirm(student, action, appid, openid, scene);
            return "new";

        } else {
            httpSession.setAttribute("openid", openid);
            log.info("redirect to getStudentInfo");
            return "LoginWeb/Login";
        }
    }

    /**
     * 当一次性订阅的action等于confirm的时候的处理函数
     * @param student 学生实体
     * @param action 确认动作
     * @param appid 公众号的appid
     * @param openid 用户的openid
     * @param scene 订阅的场景值
     */
    private void actionEqualToConfirm(Student student, String action, String appid, String openid, String scene){
        if (Objects.equals("confirm", action)) {
            WxMpService wxMpService = WechatMpConfiguration.getMpServices().get(appid);
            //没有订阅的话，进行插入
            if (Objects.equals(wechatMpPlusProperties.getAppId(), appid)) {
                //plus的处理过程
                wxMpPlusToProcessing(wxMpService, openid, scene, student);
            } else {
                //pro的处理过程
                wxMpProToProcessing(wxMpService, openid, scene, student);
            }
        }
    }

    private void wxMpProToProcessing(WxMpService wxMpService, String openid, String scene, Student student) {
        String appid = wxMpService.getWxMpConfigStorage().getAppId();
        ScheduleTask scheduleTask = new ScheduleTask(appid, openid, scene);
        //使用该接口时自动将订阅状态置为可用
        scheduleTaskService.checkAndSetSubscribeStatus(scheduleTask, true);
        //判断场景值来决定更具体的处理
        if (Objects.equals("1005", scene)) {
            //场景为1005时
            //发送一条包含当天课表信息的客服信息给指定的用户
            String messageContent = courseService.getCurrentDayCoursesForString(student.getAccount());
            //发送一条客服消息
            sendKefuMessageForPro(wxMpService, scheduleTask, messageContent);
        }
    }


    private void wxMpPlusToProcessing(WxMpService wxMpService, String openid, String scene, Student student) {
        //因为plus是服务号，所以直接通过一次性订阅接口来返回模板消息，无需发送客服消息
        //根据scene来决定更具体的查理过程
        if (Objects.equals("1005", scene)) {
            //直接返回包含课表消息的模板消息
            String messageContent = courseService.getCurrentDayCoursesForString(student.getAccount());
            sendTemplateMessageForPlus(wxMpService, openid, scene, messageContent);
        }
    }

    /**
     * 给plus发送一条模板消息
     * @param wxMpService wxMpService
     * @param openid 发送者openid
     * @param scene 订阅场景值
     * @param content 消息内容
     */
    private void sendTemplateMessageForPlus(WxMpService wxMpService, String openid, String scene, String content){
        String appid = wxMpService.getWxMpConfigStorage().getAppId();
        //通过wxMpConfigStorage来获取一次性订阅的模板消息id
        String templateId = wxMpService.getWxMpConfigStorage().getTemplateId();
        OneOffSubscription oneOffSubscription = new OneOffSubscription.Builder()
                .touser(openid)
                .scene(scene)
                .title("今日课表")
                .templateId(templateId)
                .data(content)
                .build();
        try {
            OneOffSubcriptionUtil.sendTemplateMessageToUser(oneOffSubscription, wxMpService);
            log.info("send templateMessage about course success appid:{} openid:{}", appid, openid);
        } catch (WxErrorException e) {
            log.info("send templateMessage about course failed appid:{} openid:{} message:{}", appid, openid, e.getMessage());
        }
    }

    /**
     * 为pro发送一条客服消息
     * @param wxMpService wxMpService
     * @param scheduleTask 这里用这个参数，是因为这个实体内刚好含有openid,appid等属性
     * @param content 客服消息的内容
     */
    private void sendKefuMessageForPro(WxMpService wxMpService, ScheduleTask scheduleTask, String content){
        WxMpKefuMessage wxMpKefuMessage = new WxMpKefuMessage();
        wxMpKefuMessage.setMsgType("text");
        wxMpKefuMessage.setContent(content);
        wxMpKefuMessage.setToUser(scheduleTask.getOpenid());
        try {
            wxMpService.getKefuService().sendKefuMessage(wxMpKefuMessage);
            log.info("send kefuMessage about course success openid:{} appid:{}", scheduleTask.getOpenid(), scheduleTask.getAppid());
        } catch (WxErrorException e) {
            log.error("send kefuMessage about course failed openid:{} appid:{} message:{}",
                    scheduleTask.getOpenid(), scheduleTask.getAppid(), e.getMessage());
        }
    }
}
