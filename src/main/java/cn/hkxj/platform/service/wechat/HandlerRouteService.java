package cn.hkxj.platform.service.wechat;

import cn.hkxj.platform.service.wechat.handler.messageHandler.*;
import me.chanjar.weixin.mp.api.WxMpMessageRouter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author JR Chan
 * @date 2018/6/11 17:19
 * <p>
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
	LessonMessageHandler lessonMessageHandler;

	@Autowired
	ExampleTemplateHandler exampleTemplateHandler;

	public void handlerRegister() {
		System.out.println("register");

		router
				.rule()
				.async(false)
				.content("haha")
				.handler(new ExampleHandler())
				.end()
				.rule()
				.async(false)
				.content("考试安排")
				.handler(examMessageHandler)
				.end()
				.rule()
				.async(false)
				.content("课表")
				.handler(lessonMessageHandler)
				.end()
				.rule()
				.async(false)
				.content("成绩")
				.handler(gradeMessageHandler)
				.end()
				.rule()
				.async(false)
				.content("绑定学号")
				.handler(exampleTemplateHandler)
				.end();
	}
}
