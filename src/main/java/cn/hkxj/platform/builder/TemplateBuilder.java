package cn.hkxj.platform.builder;

import cn.hkxj.platform.pojo.GradeAndCourse;
import cn.hkxj.platform.pojo.wechat.CourseGroupMsg;
import cn.hkxj.platform.pojo.wechat.CourseSubscriptionMessage;
import cn.hkxj.platform.pojo.wechat.ExamGroupMsg;
import cn.hkxj.platform.utils.DateUtils;
import cn.hkxj.platform.utils.SchoolTimeUtil;
import me.chanjar.weixin.mp.bean.template.WxMpTemplateData;
import me.chanjar.weixin.mp.bean.template.WxMpTemplateMessage;
import org.apache.commons.lang3.StringUtils;
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

    /**
     * 生成不带小程序跳转的模板消息
     * @param openid 用户的openid
     * @param list 模板消息的内容
     * @param templateId 模板id
     * @param url 跳转地址
     * @return 模板消息
     */
    public WxMpTemplateMessage buildWithNoMiniProgram(String openid, List<WxMpTemplateData> list, String templateId, String url) {
        return build(openid, list, templateId, null, url);
    }

    /**
     * 生成不带url跳转的模板消息
     * @param openid 用户的openid
     * @param list 模板消息的内容
     * @param templateId 模板id
     * @param miniProgram 小程序跳转
     * @return 模板消息
     */
    public WxMpTemplateMessage buildWithNoUrl(String openid, List<WxMpTemplateData> list, String templateId, WxMpTemplateMessage.MiniProgram miniProgram) {
        return build(openid, list, templateId, miniProgram, null);
    }

    /**
     * 生成不带小程序跳转和url跳转的模板消息
     * @param openid 用户的openid
     * @param list 模板消息的内容
     * @param templateId 模板id
     * @return 模板消息
     */
    public WxMpTemplateMessage buildWithNoUrlAndMiniProgram(String openid, List<WxMpTemplateData> list, String templateId){
        return build(openid, list, templateId, null, null);
    }

    /**
     *
     * @param openid 用户的openid
     * @param list 模板消息的内容
     * @param templdateId 模板id
     * @param miniProgram 小程序跳转
     * @param url 跳转地址
     * @return 模板消息
     */
	public WxMpTemplateMessage build(String openid, List<WxMpTemplateData> list, String templdateId,
                                     WxMpTemplateMessage.MiniProgram miniProgram, String url) {
		return WxMpTemplateMessage.builder()
				.toUser(openid)
				.templateId(templdateId)
                .miniProgram(miniProgram)
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
    public List<WxMpTemplateData> assemblyTemplateContentForGradeUpdate(GradeAndCourse gradeAndCourse) {
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
        String content = msg.getCourseContent();
        if(StringUtils.isEmpty(content)) { return null; }
        List<WxMpTemplateData> templateDatas = new ArrayList<>();
        //first关键字
        WxMpTemplateData first = new WxMpTemplateData();
        first.setName("first");
        first.setValue("当日课表\n");
        //keyword1关键字
        WxMpTemplateData course = new WxMpTemplateData();
        course.setName("keyword1");
        course.setValue("\n" + content + "\n");
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
     * @param msg 课程推送信息
     * @return List<WxMpTemplateData>
     */
    public List<WxMpTemplateData> assemblyTemplateContentForCourse(CourseSubscriptionMessage msg) {
        String content = msg.getPushContent();
        if(StringUtils.isEmpty(content)) { return null; }
        List<WxMpTemplateData> templateDatas = new ArrayList<>();
        //keyword1关键字
        WxMpTemplateData course = new WxMpTemplateData();
        course.setName("keyword1");
        course.setValue(content);
        //keyword2关键字
        WxMpTemplateData date = new WxMpTemplateData();
        date.setName("keyword2");
        date.setValue("第" + SchoolTimeUtil.getSchoolWeek() + "周   " + SchoolTimeUtil.getDayOfWeekChinese());
        //remark关键字
        WxMpTemplateData remark = new WxMpTemplateData();
        remark.setName("remark");
        remark.setValue("查询仅供参考，以学校下发的课表为准，如有疑问微信添加吴彦祖【hkdhd666】");

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

    /**
     * 组装课程推送模板消息需要的WxMpTemplateData的列表
     * @param msg 课程推送信息
     * @return List<WxMpTemplateData>
     */
    public List<WxMpTemplateData> assemblyTemplateContentForExam(ExamGroupMsg msg) {
        List<WxMpTemplateData> templateDatas = new ArrayList<>();
        //first关键字
        WxMpTemplateData first = new WxMpTemplateData();
        first.setName("first");
        first.setValue("考试推送\n");
        //keyword1关键字
        WxMpTemplateData course = new WxMpTemplateData();
        course.setName("keyword1");
        course.setValue("\n" + msg.getCourseContent() + "\n");

        WxMpTemplateData remark = new WxMpTemplateData();
        remark.setName("remark");
        remark.setValue("查询仅供参考，以学校下发的考试通知为准，如有疑问微信添加吴彦祖【hkdhd666】\n");

        templateDatas.add(first);
        templateDatas.add(course);
        templateDatas.add(remark);

        return templateDatas;
    }

}
