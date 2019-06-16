package cn.hkxj.platform.builder;

import cn.hkxj.platform.pojo.GradeAndCourse;
import cn.hkxj.platform.pojo.timetable.CourseTimeTable;
import cn.hkxj.platform.pojo.wechat.CourseGroupMsg;
import cn.hkxj.platform.utils.DateUtils;
import cn.hkxj.platform.utils.SchoolTimeUtil;
import me.chanjar.weixin.mp.bean.message.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.template.WxMpTemplateData;
import me.chanjar.weixin.mp.bean.template.WxMpTemplateMessage;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Syaeldon
 * 模板消息
 */
@Service
public class TemplateBuilder {

	private static final String PLUS_COURSE_TEMPLATE_ID = "XmX4UWJdtRDbDXhVToLAXYKJgHES2hNq9PT2N-XdzNs";
	private static final String PLUS_GRADE_UPDATE_TEMPLDATE_ID = "U10CT2cy2feEGgwlVygMvT7jYTDybG9HREKacuTViyI";
	private static final String PLUS_TIPS_TEMPLATE_ID = "5Wh5PCHrSg0DAYy8iASRFXVT-MEUkRl-FYZ3-0CzNa8";

	private static final String TEST_COURSE_TEMPLATE_ID = "PVBBEzdHFhJAMsB3-P1y8OW20mY_oD_z6o86s2QQRJk";
	private static final String TEST_GRADE_TEMPLATE_ID = "5kdSuhc4-7VqjZVqrIwBlPMPL6cigfjAA9eoGZhE2cA";
	private static final String TEST_TIPS_TEMPLATE_ID = "3fQ5xvLTmD2fnPS_CZnBquQjvaXA9AXdiLwjbexKeoU";

	public WxMpTemplateMessage build(WxMpXmlMessage wxMessage, List<WxMpTemplateData> list, String url) {
		return WxMpTemplateMessage.builder()
				.toUser(wxMessage.getFromUser())
				.templateId("zJXOQMw1pnkk7oZpcUoWYVML7NfzwQRe-0BDS7wZDRU")
				.data(list)
				.url(url)
				.build();
	}

	public WxMpTemplateMessage buildCourseMessage(String openid, List<WxMpTemplateData> list, String url){
		return WxMpTemplateMessage.builder()
				.toUser(openid)
				.templateId(PLUS_COURSE_TEMPLATE_ID)
				.data(list)
				.url(url)
				.build();
	}

	public WxMpTemplateMessage buildCourseMessage(String openid, List<WxMpTemplateData> list, String url, WxMpTemplateMessage.MiniProgram miniProgram){
		return WxMpTemplateMessage.builder()
				.toUser(openid)
				.templateId(PLUS_COURSE_TEMPLATE_ID)
				.data(list)
				.miniProgram(miniProgram)
				.url(url)
				.build();
	}


	public WxMpTemplateMessage buildCourseMessage(WxMpXmlMessage wxMpXmlMessage, List<WxMpTemplateData> list, String url){
		return WxMpTemplateMessage.builder()
				.toUser(wxMpXmlMessage.getFromUser())
				.templateId(PLUS_COURSE_TEMPLATE_ID)
				.data(list)
				.url(url)
				.build();
	}

	public WxMpTemplateMessage buildGradeUpdateMessage(String openid, List<WxMpTemplateData> list, String url){
        return WxMpTemplateMessage.builder()
                .toUser(openid)
                .templateId(PLUS_GRADE_UPDATE_TEMPLDATE_ID)
                .data(list)
                .url(url)
                .build();
    }

	public WxMpTemplateMessage buildGradeUpdateMessage(String openid, List<WxMpTemplateData> list, WxMpTemplateMessage.MiniProgram miniProgram){
		return WxMpTemplateMessage.builder()
				.toUser(openid)
				.templateId(PLUS_GRADE_UPDATE_TEMPLDATE_ID)
				.data(list)
				.miniProgram(miniProgram)
				.build();
	}

	public WxMpTemplateMessage buildTipsMessage(String openid, List<WxMpTemplateData> list, String url){
		return WxMpTemplateMessage.builder()
				.toUser(openid)
				.templateId(PLUS_TIPS_TEMPLATE_ID)
				.data(list)
				.url(url)
				.build();
	}

    /**
     * 组装提示模板消息需要的WxMpTemplateData的列表
     * @param errorContent 错误消息
     * @return List<WxMpTemplateData>
     */
    public List<WxMpTemplateData> assemblyTemplateContentForTips(String errorContent) {
        List<WxMpTemplateData> templateDatas = new ArrayList<>();
        //first关键字
        WxMpTemplateData first = new WxMpTemplateData();
        first.setName("first");
        first.setValue("温馨提示\n");
        //keyword1关键字
        WxMpTemplateData sendDate = new WxMpTemplateData();
        sendDate.setName("keyword1");
        sendDate.setValue(DateUtils.getTimeOfPattern(LocalDateTime.now(), DateUtils.YYYY_MM_DD_PATTERN) + "\n");
        //keyword2关键字
        WxMpTemplateData sendTime = new WxMpTemplateData();
        sendTime.setName("keyword2");
        sendTime.setValue(DateUtils.getTimeOfPattern(LocalDateTime.now(), DateUtils.HH_MM_SS_PATTERN) + "\n");
        //remark关键字
        WxMpTemplateData remark = new WxMpTemplateData();
        remark.setName("remark");
        remark.setValue(errorContent + "\n" + "如有疑问微信添加吴彦祖【hkdhd666】");

        templateDatas.add(first);
        templateDatas.add(sendDate);
        templateDatas.add(sendTime);
        templateDatas.add(remark);

        return templateDatas;
    }

    /**
     * 组装成绩更新模板消息需要的WxMpTemplateData的列表
     *
     * @param gradeAndCourse 课程和成绩信息
     * @return List<WxMpTemplateData>
     */
    public List<WxMpTemplateData> assemblyTemplateContentForGradeUpadte(GradeAndCourse gradeAndCourse) {
        List<WxMpTemplateData> templateDatas = new ArrayList<>();
        //first关键字
        WxMpTemplateData first = new WxMpTemplateData();
        first.setName("first");
        first.setValue("您有新的成绩更新\n\n");
        //keyword1关键字
        WxMpTemplateData courseName = new WxMpTemplateData();
        courseName.setName("keyword1");
        courseName.setValue(gradeAndCourse.getCourse().getName() + "\n\n");
        //keyword2关键字
        Integer grade = gradeAndCourse.getGrade().getScore() / 10;
        WxMpTemplateData score = new WxMpTemplateData();
        score.setName("keyword2");
        score.setValue(grade.toString() + "\n\n");
        //remark关键字
        WxMpTemplateData remark = new WxMpTemplateData();
        remark.setName("remark");
        remark.setValue("查询仅供参考，以教务网的成绩为准，如有疑问微信添加吴彦祖【hkdhd666】");

        templateDatas.add(first);
        templateDatas.add(courseName);
        templateDatas.add(score);
        templateDatas.add(remark);

        return templateDatas;
    }

    /**
     * 组装课程推送模板消息需要的WxMpTemplateData的列表
     * @param msg 课程推送信息
     * @return List<WxMpTemplateData>
     */
    public List<WxMpTemplateData> assemblyTemplateContentForCourse(CourseGroupMsg msg) {
        List<WxMpTemplateData> templateDatas = new ArrayList<>();
        //first关键字
        WxMpTemplateData first = new WxMpTemplateData();
        first.setName("first");
        first.setValue("当日课表\n");
        //keyword1关键字
        WxMpTemplateData course = new WxMpTemplateData();
        course.setName("keyword1");
        course.setValue("\n" + msg.getCourseContent() + "\n");
        //keyword2关键字
        WxMpTemplateData date = new WxMpTemplateData();
        date.setName("keyword2");
        date.setValue("第" + SchoolTimeUtil.getSchoolWeek() + "周   " + SchoolTimeUtil.getDayOfWeekChinese());
        //remark关键字
        WxMpTemplateData remark = new WxMpTemplateData();
        remark.setName("remark");
        remark.setValue("查询仅供参考，以学校下发的课表为准，如有疑问微信添加吴彦祖【hkdhd666】");

        templateDatas.add(first);
        templateDatas.add(course);
        templateDatas.add(date);
        templateDatas.add(remark);

        return templateDatas;
    }

    /**
     * 组装课程推送模板消息需要的WxMpTemplateData的列表
     * @param content 当天课表信息
     * @return List<WxMpTemplateData>
     */
    public List<WxMpTemplateData> assemblyTemplateContentForCourse(String content) {
        List<WxMpTemplateData> templateDatas = new ArrayList<>();
        WxMpTemplateData first = new WxMpTemplateData();
        first.setName("first");
        first.setValue("当日课表\n");
        WxMpTemplateData course = new WxMpTemplateData();
        course.setName("keyword1");
        course.setValue("\n" + content);
        WxMpTemplateData date = new WxMpTemplateData();
        date.setName("keyword2");
        date.setValue("\n第" + SchoolTimeUtil.getSchoolWeek() + "周   " + SchoolTimeUtil.getDayOfWeekChinese());
        WxMpTemplateData remark = new WxMpTemplateData();
        remark.setName("remark");
        remark.setValue("查询仅供参考，以学校下发的课表为准，如有疑问微信添加吴彦祖【hkdhd666】");

        templateDatas.add(first);
        templateDatas.add(course);
        templateDatas.add(date);
        templateDatas.add(remark);

        return templateDatas;
    }

}
