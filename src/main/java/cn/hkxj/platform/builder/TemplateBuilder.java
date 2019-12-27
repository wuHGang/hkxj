package cn.hkxj.platform.builder;

import cn.hkxj.platform.pojo.Grade;
import cn.hkxj.platform.pojo.Student;
import cn.hkxj.platform.pojo.vo.GradeVo;
import cn.hkxj.platform.pojo.wechat.CourseGroupMsg;
import cn.hkxj.platform.pojo.wechat.CourseSubscriptionMessage;
import cn.hkxj.platform.utils.DateUtils;
import cn.hkxj.platform.utils.SchoolTimeUtil;
import me.chanjar.weixin.mp.bean.template.WxMpTemplateData;
import me.chanjar.weixin.mp.bean.template.WxMpTemplateMessage;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

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
    public WxMpTemplateMessage build(String openid, List<WxMpTemplateData> list, String templateId, WxMpTemplateMessage.MiniProgram miniProgram) {
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
     * @param templateId 模板id
     * @param miniProgram 小程序跳转
     * @param url 跳转地址
     * @return 模板消息
     */
	public WxMpTemplateMessage build(String openid, List<WxMpTemplateData> list, String templateId,
                                     WxMpTemplateMessage.MiniProgram miniProgram, String url) {
		return WxMpTemplateMessage.builder()
				.toUser(openid)
				.templateId(templateId)
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
        List<WxMpTemplateData> templateDataList = new ArrayList<>();
        //keyword1关键字
        WxMpTemplateData course = new WxMpTemplateData();
        course.setName("keyword1");
        course.setValue(content);
        //keyword2关键字
        WxMpTemplateData date = getCourseDateWithoutSpecificTime();
        //remark关键字
        WxMpTemplateData remark = getCourseRemark();

        templateDataList.add(course);
        templateDataList.add(date);
        templateDataList.add(remark);

        return templateDataList;
    }

    /**
     * 组装课程推送模板消息需要的WxMpTemplateData的列表
     * @param content 当天课表信息
     * @return List<WxMpTemplateData>
     */
    public List<WxMpTemplateData> assemblyTemplateContentForCourse(String content) {
        List<WxMpTemplateData> templateDataList = new ArrayList<>();
        WxMpTemplateData first = new WxMpTemplateData();
        first.setName("first");
        first.setValue("当日课表\n");
        WxMpTemplateData course = new WxMpTemplateData();
        course.setName("keyword1");
        course.setValue("\n" + content);
        WxMpTemplateData date = getCourseDateWithoutSpecificTime();
        WxMpTemplateData remark = getCourseRemark();
        templateDataList.add(first);
        templateDataList.add(course);
        templateDataList.add(date);
        templateDataList.add(remark);

        return templateDataList;
    }


    /**
     * 生成考试推送模板消息的remark
     * @return 考试推送模板消息的remark
     */
    private WxMpTemplateData getExamRemark(){
        WxMpTemplateData remark = new WxMpTemplateData();
        remark.setName("remark");
        remark.setValue("查询仅供参考，以学校下发的考试通知为准，如有疑问微信添加吴彦祖【hkdhd666】\n");
        return remark;
    }

    /**
     * 生成课程推送模板消息的remark
     * @return 课程推送模板消息的remark
     */
    private WxMpTemplateData getCourseRemark(){
        WxMpTemplateData remark = new WxMpTemplateData();
        remark.setName("remark");
        remark.setValue("点击即可查看详情，快放假了加油");
        return remark;
    }

    /**
     * 返回的日期只包含周数和天数
     * 如 13周 星期三
     * @return 包含日期的WxMpTemplateData
     */
    private WxMpTemplateData getCourseDateWithoutSpecificTime(){
        WxMpTemplateData date = new WxMpTemplateData();
        date.setName("keyword2");
        date.setValue("\n第" + SchoolTimeUtil.getSchoolWeek() + "周   " + SchoolTimeUtil.getDayOfWeekChinese());
        return date;
    }


    public List<WxMpTemplateData> gradeToTemplateData(Student student, GradeVo grade){
        List<WxMpTemplateData> templateDataList = new ArrayList<>();

        WxMpTemplateData first = new WxMpTemplateData();
        first.setName("first");
        first.setValue(student.getName()+"同学！你的成绩更新啦");
        //keyword2关键字
        WxMpTemplateData key1 = new WxMpTemplateData();
        key1.setName("keyword1");
        key1.setValue(grade.getCourse().getName() + "\n更新时间:"+DateUtils.dateToChinese(grade.getOperateTime())+"\n");
        //remark关键字
        WxMpTemplateData key2 = new WxMpTemplateData();
        key2.setName("keyword2");
        key2.setValue(grade.getScore().toString()+ "  排名："+grade.getRank()+" 绩点: "+grade.getGradePoint().toString()+
                        "\n");

        WxMpTemplateData remark = new WxMpTemplateData();
        remark.setName("remark");
        remark.setValue("点击卡片即可查看详情\n如果觉得好用把我们介绍给你的朋友吧~比心");
        templateDataList.add(first);
        templateDataList.add(key1);
        templateDataList.add(key2);
        templateDataList.add(remark);
        return templateDataList;
    }
}
