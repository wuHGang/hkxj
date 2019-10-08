package cn.hkxj.platform.config.wechat;

import cn.hkxj.platform.interceptor.StudentInfoInterceptor;
import cn.hkxj.platform.interceptor.WechatOpenIdInterceptor;
import cn.hkxj.platform.service.wechat.WxMessageRouter;
import cn.hkxj.platform.service.wechat.handler.messageHandler.*;
import com.google.common.collect.Maps;
import me.chanjar.weixin.mp.api.WxMpInMemoryConfigStorage;
import me.chanjar.weixin.mp.api.WxMpMessageRouter;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.api.impl.WxMpServiceImpl;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Map;

/**
 * wechat mp configuration
 *
 * @author Binary Wang(https://github.com/binarywang)
 */
@Configuration
@ComponentScan(basePackages = "cn.hkxj.platform.config.*")
@Component
@EnableConfigurationProperties(value = {WechatMpProProperties.class, WechatMpPlusProperties.class, WechatTemplateProperties.class})
public class WechatMpConfiguration {

    @Resource
    private WechatMpProProperties wechatMpProProperties;

    @Resource
    private WechatMpPlusProperties wechatMpPlusProperties;

    @Resource
    private CourseMessageHandler courseMessageHandler;

    @Resource
    private MakeUpGradeHandler makeUpGradeHandler;

    @Resource
    private GradeMessageHandler gradeMessageHandler;

    @Resource
    private OpenidMessageHandler openidMessageHandler;

    @Resource
    private UnbindMessageHandler unbindMessageHandler;

    @Resource
    private EmptyRoomHandler emptyRoomHandler;

    @Resource
    private ExamMessageHandler examMessageHandler;

    @Resource
    private WechatOpenIdInterceptor wechatOpenIdInterceptor;

    @Resource
    private UnsubscribeMessageHandler unsubscribeMessageHandler;

    @Resource
    private SubscribeMessageHandler subscribeMessageHandler;

    @Resource
    private StudentInfoInterceptor studentInfoInterceptor;

    private static Map<String, WxMpMessageRouter> routers = Maps.newHashMap();
    private static Map<String, WxMpService> mpServices = Maps.newHashMap();

    @Bean
    public Object services() {
        //plus的配置
        WxMpInMemoryConfigStorage plusConfig = wechatMpPlusProperties.getWxMpInMemoryConfigStorage();
        WxMpService wxPlusMpService = new WxMpServiceImpl();
        wxPlusMpService.setWxMpConfigStorage(plusConfig);
        routers.put(wechatMpPlusProperties.getAppId(), this.newRouter(wxPlusMpService));
        mpServices.put(wechatMpPlusProperties.getAppId(), wxPlusMpService);

        //pro的配置
        WxMpInMemoryConfigStorage proConfig = wechatMpProProperties.getWxMpInMemoryConfigStorage();
        WxMpService wxProMpService = new WxMpServiceImpl();
        wxProMpService.setWxMpConfigStorage(proConfig);
        routers.put(wechatMpProProperties.getAppId(), this.newRouter(wxProMpService));
        mpServices.put(wechatMpProProperties.getAppId(), wxProMpService);
        return Boolean.TRUE;
    }

    private WxMpMessageRouter newRouter(WxMpService wxMpService) {
        final WxMessageRouter newRouter = new WxMessageRouter(wxMpService);
        newRouter
                .rule()
                .async(false)
                .rContent("(课表|课程|今日课表)")
                .interceptor(wechatOpenIdInterceptor)
                .interceptor(studentInfoInterceptor)
                .handler(courseMessageHandler)
                .end()
                .rule()
                .async(false)
                .rContent("课表推送|成绩推送|考试推送")
                .interceptor(wechatOpenIdInterceptor)
                .interceptor(studentInfoInterceptor)
                .handler(subscribeMessageHandler)
                .end()
                .rule()
                .async(true)
                .interceptor(wechatOpenIdInterceptor)
                .interceptor(studentInfoInterceptor)
                .rContent("补考成绩.*?")
                .handler(makeUpGradeHandler)
                .end()
                .rule()
                .async(true)
                .interceptor(wechatOpenIdInterceptor)
                .interceptor(studentInfoInterceptor)
                .rContent(".*?成绩.*?")
                .handler(gradeMessageHandler)
                .end()
//				.rule()
//				.async(false)
//				.interceptor(wechatOpenIdInterceptor)
//                .interceptor(studentInfoInterceptor)
//				.rContent("准考证号|四级|六级|准考证|四六级")
//				.handler(cetSearchHandler)
//				.end()
                .rule()
                .async(false)
                .interceptor(wechatOpenIdInterceptor)
                .interceptor(studentInfoInterceptor)
                .content("openid")
                .handler(openidMessageHandler)
                .end()
                .rule()
                .async(false)
                .interceptor(wechatOpenIdInterceptor)
                .interceptor(studentInfoInterceptor)
                .rContent("解绑|解除绑定")
                .handler(unbindMessageHandler)
                .end()
                .rule()
                .async(true)
                .rContent(".*?考试.*?")
                .interceptor(wechatOpenIdInterceptor)
                .interceptor(studentInfoInterceptor)
                .handler(examMessageHandler)
                .end()
                .rule()
                .async(false)
                .rContent("空教室.*?")
                .handler(emptyRoomHandler)
                .end()
                .rule()
                .async(false)
                .rContent("退订.*?")
                .interceptor(wechatOpenIdInterceptor)
                .interceptor(studentInfoInterceptor)
                .handler(unsubscribeMessageHandler)
                .end();
        return newRouter;
    }

    public static Map<String, WxMpMessageRouter> getRouters() {
        return routers;
    }

    public static Map<String, WxMpService> getMpServices() {
        return mpServices;
    }

}
