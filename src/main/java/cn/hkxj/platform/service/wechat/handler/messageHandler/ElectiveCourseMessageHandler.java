package cn.hkxj.platform.service.wechat.handler.messageHandler;

import cn.hkxj.platform.pojo.GradeAndCourse;
import cn.hkxj.platform.pojo.Student;
import cn.hkxj.platform.pojo.constant.Academy;
import cn.hkxj.platform.service.GradeSearchService;
import cn.hkxj.platform.service.NewGradeSearchService;
import cn.hkxj.platform.service.OpenIdService;
import cn.hkxj.platform.service.wechat.CustomerMessageService;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.session.WxSessionManager;
import me.chanjar.weixin.mp.api.WxMpMessageHandler;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.message.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutMessage;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

@Slf4j
@Component
public class ElectiveCourseMessageHandler implements WxMpMessageHandler {


    @Resource
    private GradeSearchService gradeSearchService;

    @Resource
    private OpenIdService openIdService;

    private static ExecutorService singleThreadPool = Executors.newSingleThreadExecutor();

    public WxMpXmlOutMessage handle(WxMpXmlMessage wxMpXmlMessage,
                                    Map<String, Object> map,
                                    WxMpService wxMpService,
                                    WxSessionManager wxSessionManager) {

        String appid = wxMpService.getWxMpConfigStorage().getAppId();
        Student student = openIdService.getStudentByOpenId(wxMpXmlMessage.getFromUser(), appid);

        CompletableFuture<String> completableFuture = CompletableFuture.supplyAsync(() -> getResult(student), singleThreadPool);

        CustomerMessageService messageService = new CustomerMessageService(wxMpXmlMessage, wxMpService);
        return messageService.sendMessage(completableFuture, student);
    }

    //TODO 添加过往成绩的接口
    private String getResult(Student student) {
        List<GradeAndCourse> electiveCourse = gradeSearchService.getEverGradeFromSpider(student).stream()
                .filter(gradeAndCourse -> gradeAndCourse.getCourse().getAcademy() == Academy.Web)
                .collect(Collectors.toList());
        return gradeSearchService.getElectiveCourseText(electiveCourse);
    }
}
