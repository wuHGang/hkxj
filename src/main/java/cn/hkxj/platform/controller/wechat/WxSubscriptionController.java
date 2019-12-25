package cn.hkxj.platform.controller.wechat;

import cn.hkxj.platform.config.wechat.WechatMpConfiguration;
import cn.hkxj.platform.pojo.CourseTimeTableDetail;
import cn.hkxj.platform.pojo.Student;
import cn.hkxj.platform.pojo.constant.SubscribeScene;
import cn.hkxj.platform.pojo.wechat.OneOffSubscription;
import cn.hkxj.platform.service.CourseTimeTableService;
import cn.hkxj.platform.service.wechat.StudentBindService;
import cn.hkxj.platform.utils.OneOffSubcriptionUtil;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Objects;

/**
 * @author junrong.chen
 * @date 2018/10/7
 */
@Controller
@RequestMapping("/wechat/sub/{appid}")
@Slf4j
public class WxSubscriptionController {

    private final static String ACTION_CONFIRM = "confirm";
    private final static String ACTION_CANCEL = "cancel";

    @Resource(name = "studentBindService")
    private StudentBindService studentBindService;
    @Resource
    private CourseTimeTableService courseTimeTableService;
    @Resource
    private HttpSession httpSession;

    /**
     * @param openid     微信平台用户唯一标识
     * @param templateId 订阅消息模板ID
     * @param action     用户点击动作，”confirm”代表用户确认授权，”cancel”代表用户取消授权
     * @param scene      点击的具体场景类型
     */
    @GetMapping("/test")
    public String testSubscription(
            @PathVariable String appid,
            @RequestParam(name = "openid", required = false) String openid,
            @RequestParam(name = "template_id", required = false) String templateId,
            @RequestParam(name = "action", required = false) String action,
            @RequestParam(name = "scene", required = false) String scene) {
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
        if (Objects.equals(ACTION_CONFIRM, action)) {
            WxMpService wxMpService = WechatMpConfiguration.getMpServices().get(appid);

            if(Objects.equals(SubscribeScene.COURSE_PUSH.getScene(), scene)){
                processCourseSubscription(wxMpService, openid, scene, student);
            }
        }
    }

    private void processCourseSubscription(WxMpService wxMpService, String openid, String scene, Student student) {
        //判断场景值来决定更具体的处理
        if (Objects.equals(SubscribeScene.COURSE_PUSH.getScene(), scene)) {
            //场景为1005时
            List<CourseTimeTableDetail> detailList = courseTimeTableService.getDetailsForCurrentDay(student);
            String messageContent = courseTimeTableService.convertToText(detailList);
            //发送一条客服消息
            sendTemplateMessage(wxMpService, openid, scene, messageContent);
        }
    }

    /**
     * 给plus发送一条模板消息
     * @param wxMpService wxMpService
     * @param openid 发送者openid
     * @param scene 订阅场景值
     * @param content 消息内容
     */
    private void sendTemplateMessage(WxMpService wxMpService, String openid, String scene, String content){
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
            log.info("send templateMessage about course.json success appid:{} openid:{}", appid, openid);
        } catch (WxErrorException e) {
            log.info("send templateMessage about course.json failed appid:{} openid:{} message:{}", appid, openid, e.getMessage());
        }
    }

}
