package cn.hkxj.platform.service.wechat;

import cn.hkxj.platform.interceptor.WechatOpenIdInterceptor;
import cn.hkxj.platform.service.wechat.handler.messageHandler.*;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author JR Chan
 * @date 2018/6/11 17:19
 * 所有handle的路由规则都在这个service里配置
 */
@Service
public class HandlerRouteService {
	@Resource
	private WxMessageRouter router;

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

	@Resource
	private CETSearchHandler cetSearchHandler;

	public void handlerRegister() {
		router
				.rule()
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
                    .rContent("空教室.*?")
                    .handler(emptyRoomHandler)
                .end();

	}
}
