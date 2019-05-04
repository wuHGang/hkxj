package cn.hkxj.platform.config.wechat;

import cn.hkxj.platform.interceptor.WechatOpenIdInterceptor;
import cn.hkxj.platform.service.wechat.WxMessageRouter;
import cn.hkxj.platform.service.wechat.handler.LogHandler;
import cn.hkxj.platform.service.wechat.handler.messageHandler.*;
import com.google.common.collect.Maps;
import me.chanjar.weixin.mp.api.WxMpConfigStorage;
import me.chanjar.weixin.mp.api.WxMpInMemoryConfigStorage;
import me.chanjar.weixin.mp.api.WxMpMessageRouter;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.api.impl.WxMpServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * wechat mp configuration
 *
 * @author Binary Wang(https://github.com/binarywang)
 */
@Configuration
@ComponentScan(basePackages = "cn.hkxj.platform.*")
@EnableConfigurationProperties(WechatMpProperties.class)
public class WechatMpConfiguration {

	@Resource
	private LogHandler logHandler;

	@Resource
	private WechatMpProperties wechatMpProperties;

	@Resource
	private CourseMessageHandler courseMessageHandler;

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

	@Autowired
	private CETSearchHandler cetSearchHandler;

	@Resource
	private ElectiveCourseMessageHandler electiveCourseMessageHandler;

	private static Map<String, WxMpMessageRouter> routers = Maps.newHashMap();
	private static Map<String, WxMpService> mpServices = Maps.newHashMap();

	@Bean
	public Object services(){
		mpServices = this.wechatMpProperties.getConfigs()
				.stream()
				.map(appConfig ->{
					WxMpInMemoryConfigStorage configStorage = new WxMpInMemoryConfigStorage();
					configStorage.setAppId(appConfig.getAppId());
					configStorage.setSecret(appConfig.getSecret());
					configStorage.setToken(appConfig.getToken());
					configStorage.setAesKey(appConfig.getAesKey());
					WxMpService wxMpService = new WxMpServiceImpl();
					wxMpService.setWxMpConfigStorage(configStorage);
					routers.put(appConfig.getAppId(), this.newRouter(wxMpService));
					return wxMpService;
				}).collect(Collectors.toMap(wxMpService -> wxMpService.getWxMpConfigStorage().getAppId(), wxMpService -> wxMpService));
		return Boolean.TRUE;
	}

	private WxMpMessageRouter newRouter(WxMpService wxMpService){
		final WxMessageRouter newRouter = new WxMessageRouter(wxMpService);
		newRouter.rule()
				.async(false)
				.rContent("(课表|课程|今日课表)")
				.interceptor(wechatOpenIdInterceptor)
				.handler(courseMessageHandler)
				.end()
				.rule()
				.async(false)
				.interceptor(wechatOpenIdInterceptor)
				.rContent(".*?成绩.*?")
				.handler(gradeMessageHandler)
				.end()
				.rule()
				.async(false)
				.interceptor(wechatOpenIdInterceptor)
				.rContent("准考证号|四级|六级|准考证|四六级")
				.handler(cetSearchHandler)
				.end()
				.rule()
				.async(false)
				.interceptor(wechatOpenIdInterceptor)
				.content("openid")
				.handler(openidMessageHandler)
				.end()
				.rule()
				.async(false)
				.interceptor(wechatOpenIdInterceptor)
				.content("解绑")
				.handler(unbindMessageHandler)
				.end()
				.rule()
				.async(false)
				.rContent(".*?考试.*?")
				.interceptor(wechatOpenIdInterceptor)
				.handler(examMessageHandler)
				.end()
				.rule()
				.async(false)
				.rContent(".*?选修.*?")
				.interceptor(wechatOpenIdInterceptor)
				.handler(electiveCourseMessageHandler)
				.end()
				.rule()
				.async(false)
				.rContent("空教室.*?")
				.handler(emptyRoomHandler)
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
