package cn.hkxj.platform.service.wechat.handler.messageHandler;

import cn.hkxj.platform.builder.TextBuilder;
import cn.hkxj.platform.pojo.AllGradeAndCourse;
import cn.hkxj.platform.pojo.GradeAndCourse;
import cn.hkxj.platform.pojo.Student;
import cn.hkxj.platform.service.GradeSearchService;
import cn.hkxj.platform.service.OpenIdService;
import cn.hkxj.platform.service.TaskBindingService;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.exception.WxErrorException;
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

/**
 * @author Yuki
 * @date 2018/7/15 15:31
 */
@Slf4j
@Component
public class GradeMessageHandler implements WxMpMessageHandler {
    @Resource
	private TextBuilder textBuilder;

    @Resource
	private GradeSearchService gradeSearchService;

    @Resource
    private OpenIdService openIdService;

    @Resource
    TaskBindingService taskBindingService;

	public WxMpXmlOutMessage handle(WxMpXmlMessage wxMpXmlMessage,
									Map<String, Object> map,
									WxMpService wxMpService,
									WxSessionManager wxSessionManager) throws WxErrorException {
		try {
            Student student = openIdService.getStudentByOpenId(wxMpXmlMessage.getFromUser());
            List<GradeAndCourse> currentTermGrade = gradeSearchService.getCurrentTermGrade(student);
            String gradesMsg = GradeListToText(currentTermGrade);

            ExecutorService singleThreadPool = Executors.newSingleThreadExecutor();
            singleThreadPool.execute(()->{
                taskBindingService.subscribeGradeUpdateBinding(wxMpXmlMessage.getFromUser());
            });

            return textBuilder.build(gradesMsg, wxMpXmlMessage, wxMpService);
		} catch (Exception e) {
            log.error("在组装返回信息时出现错误 {}", e.getMessage());
		}

		return textBuilder.build("没有查询到相关成绩，晚点再来查吧~" , wxMpXmlMessage, wxMpService);
	}

    /**
     * 将学生成绩文本化
     *
     * @param studentGrades 学生全部成绩
     */
    public String GradeListToText(List<GradeAndCourse> studentGrades) {
        StringBuffer buffer = new StringBuffer();
        boolean i = true;
        if (studentGrades.size() == 0) {
            buffer.append("尚无本学期成绩");
        } else {
            AllGradeAndCourse allGradeAndCourse = new AllGradeAndCourse();
            allGradeAndCourse.addGradeAndCourse(studentGrades);
            for (GradeAndCourse gradeAndCourse : allGradeAndCourse.getCurrentTermGrade()) {
                if (i) {
                    i = false;
                    buffer.append("- - - - - - - - - - - - - -\n");
                    buffer.append("|").append(gradeAndCourse.getGrade().getYear()).append("学年，第").append(gradeAndCourse.getGrade().getTerm()).append("学期|\n");
                    buffer.append("- - - - - - - - - - - - - -\n\n");
                }
                int grade = gradeAndCourse.getGrade().getScore();
                buffer.append("考试名称：").append(gradeAndCourse.getCourse().getName()).append("\n")
                        .append("成绩：").append(grade == -1 ? "" : grade / 10).append("   学分：")
                        .append(gradeAndCourse.getGrade().getPoint() / 10).append("\n\n");
            }
        }
        return buffer.toString();
    }


}
