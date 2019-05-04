package cn.hkxj.platform.service.wechat.handler.messageHandler;

import cn.hkxj.platform.pojo.GradeAndCourse;
import cn.hkxj.platform.pojo.Student;
import cn.hkxj.platform.service.GradeSearchService;
import cn.hkxj.platform.service.OpenIdService;
import cn.hkxj.platform.service.TaskBindingService;
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

/**
 * @author Yuki
 * @date 2018/7/15 15:31
 */
@Slf4j
@Component
public class GradeMessageHandler implements WxMpMessageHandler {
    @Resource
	private GradeSearchService gradeSearchService;

    @Resource
    private OpenIdService openIdService;

    @Resource
    TaskBindingService taskBindingService;

    private ExecutorService cacheThreadPool = Executors.newCachedThreadPool();

	public WxMpXmlOutMessage handle(WxMpXmlMessage wxMpXmlMessage,
                                    Map<String, Object> map,
                                    WxMpService wxMpService,
                                    WxSessionManager wxSessionManager) {
        Student student = openIdService.getStudentByOpenId(wxMpXmlMessage.getFromUser());
        cacheThreadPool.execute(() -> taskBindingService.subscribeGradeUpdateBinding(wxMpXmlMessage.getFromUser()));
		try {
            Student student = openIdService.getStudentByOpenId(wxMpXmlMessage.getFromUser());
            //通过设置方法设置相应WxMpService对象
            taskBindingService.setWxMpService(wxMpService);

        Future<String> future = cacheThreadPool.submit(() -> {
            List<GradeAndCourse> gradeFromSpiderSync = gradeSearchService.getCurrentGradeFromSpider(student);
            return gradeSearchService.gradeListToText(gradeFromSpiderSync);
        });
        CustomerMessageService messageService = new CustomerMessageService(wxMpXmlMessage, wxMpService);

        return messageService.sendMessage(future, student);
	}



}
