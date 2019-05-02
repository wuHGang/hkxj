package cn.hkxj.platform.service.wechat.handler.messageHandler;

import cn.hkxj.platform.builder.TextBuilder;
import cn.hkxj.platform.pojo.GradeAndCourse;
import cn.hkxj.platform.pojo.Student;
import cn.hkxj.platform.pojo.constant.Academy;
import cn.hkxj.platform.service.GradeSearchService;
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
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.stream.Collectors;

@Slf4j
@Component
public class ElectiveCourseMessageHandler implements WxMpMessageHandler {
    @Resource
    private TextBuilder textBuilder;

    @Resource
    private GradeSearchService gradeSearchService;

    @Resource
    private OpenIdService openIdService;

    @Resource
    private CustomerMessageService customerMessageService;


    public WxMpXmlOutMessage handle(WxMpXmlMessage wxMpXmlMessage,
                                    Map<String, Object> map,
                                    WxMpService wxMpService,
                                    WxSessionManager wxSessionManager) {
        ExecutorService singleThreadPool = Executors.newSingleThreadExecutor();
        Student student = openIdService.getStudentByOpenId(wxMpXmlMessage.getFromUser());

        Future<String> future = singleThreadPool.submit(() -> {
            return getResult(student);
        });

        CustomerMessageService messageService = new CustomerMessageService(wxMpXmlMessage, wxMpService);
        return messageService.sendMessage(future, student);
    }


    private String getResult(Student student) {
        List<GradeAndCourse> electiveCourse = gradeSearchService.getEverGradeFromSpider(student).stream()
                .filter(gradeAndCourse -> gradeAndCourse.getCourse().getAcademy() == Academy.Web)
                .collect(Collectors.toList());
        return gradeSearchService.getElectiveCourseText(electiveCourse);
    }
}
