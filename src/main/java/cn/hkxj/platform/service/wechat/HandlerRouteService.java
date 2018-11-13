package cn.hkxj.platform.service.wechat;

import cn.hkxj.platform.interceptor.WechatOpenIdInterceptor;
import cn.hkxj.platform.service.wechat.handler.messageHandler.*;
import me.chanjar.weixin.mp.api.WxMpMessageRouter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author JR Chan
 * @date 2018/6/11 17:19
 * 所有handle的路由规则都在这个service里配置
 */
@Service
public class HandlerRouteService {
	@Autowired
	WxMpMessageRouter router;

	@Autowired
	ExamMessageHandler examMessageHandler;

	@Autowired
	GradeMessageHandler gradeMessageHandler;

	@Autowired
    CourseMessageHandler courseMessageHandler;

	@Autowired
	ExampleTemplateHandler exampleTemplateHandler;

	@Autowired
	private OpenIdHandler openIdHandler;

	@Resource
	private WechatOpenIdInterceptor wechatOpenIdInterceptor;


	public void handlerRegister() {
		router
				.rule()
					.async(false)
					.interceptor(wechatOpenIdInterceptor)
					.handler(openIdHandler)
				.end()
				.rule()
					.content("考试安排")
					.handler(examMessageHandler)
				.end()
				.rule()
					.content("课表")
					.handler(courseMessageHandler)
				.end()
				.rule()
					.content("成绩")
					.handler(gradeMessageHandler)
				.end()
				.rule()
					.content("绑定学号")
					.handler(exampleTemplateHandler)
				.end();
	}
}
