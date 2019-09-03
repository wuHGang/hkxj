package cn.hkxj.platform.spider.newmodel.coursetimetable;

import cn.hkxj.platform.pojo.CourseTimeTableBasicInfo;
import cn.hkxj.platform.pojo.Plan;
import cn.hkxj.platform.spider.newmodel.CourseRelativeInfo;
import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

import java.util.List;

/**
 * @author Yuki
 * @date 2019/8/29 21:43
 */
@Data
public class UrpCourseTimeTable {
    /**
     * 任课教师
     */
    private String attendClassTeacher;
    /**
     * 课程分类编号
     */
    private String courseCategoryCode;
    /**
     * 课程分类名称
     */
    private String courseCategoryName;
    /**
     * 课程名称
     */
    private String courseName;
    /**
     * 课程属性编号
     */
    private String coursePropertiesCode;
    /**
     * 课程属性名称
     */
    private String coursePropertiesName;
    /**
     *
     */
    private String dgFlag;
    /**
     * 考试分类编号
     */
    private String examTypeCode;
    /**
     * 考试分类名称
     */
    private String examTypeName;
    /**
     * 标识
     */
    private String flag;
    /**
     * 课程相关信息
     */
    @JSONField(name = "id")
    private CourseRelativeInfo courseRelativeInfo;
    /**
     * 项目计划名称
     */
    private String programPlanName;
    /**
     * 项目计划编号
     */
    private String programPlanNumber;
    /**
     * 限制条件
     */
    private String restrictedCondition;
    /**
     *
     */
    private String rlFlag;
    /**
     * 选择课程状态编号
     */
    private String selectCourseStatusCode;
    /**
     * 选择课程状态名称
     */
    private String selectCourseStatusName;
    /**
     * 学习模式编号
     */
    private String studyModeCode;
    /**
     * 学习模式名称
     */
    private String studyModeName;
    /**
     * 时间和上课地点
     */
    private List<TimeAndPlace> timeAndPlaceList;
    /**
     * 学分
     */
    private Double unit;
    /**
     *
     */
    private String ywdgFlag;

    public CourseTimeTableBasicInfo convertToCourseTimeTableBasicInfo() {
        String[] termYearAndTermOrder = parseTermYearAndTermOrder(this.courseRelativeInfo.getExecutiveEducationPlanNumber());
        CourseTimeTableBasicInfo basicInfo = new CourseTimeTableBasicInfo();
        basicInfo.setCoursePropertiesCode(this.getCoursePropertiesCode());
        basicInfo.setCoursePropertiesName(this.getCoursePropertiesName());
        basicInfo.setDgFlag(this.getDgFlag());
        basicInfo.setFlag(this.getFlag());
        basicInfo.setRestrictedCondition(this.getRestrictedCondition());
        basicInfo.setRlFlag(this.getRlFlag());
        basicInfo.setSelectCourseStatusCode(this.getSelectCourseStatusCode());
        basicInfo.setSelectCourseStatusName(this.getSelectCourseStatusName());
        basicInfo.setStudyModeCode(this.getStudyModeCode());
        basicInfo.setStudyModeName(this.getStudyModeName());
        basicInfo.setYwdgFlag(this.getYwdgFlag());
        basicInfo.setCourseId(this.getCourseRelativeInfo().getCourseNumber());
        basicInfo.setTermYear(termYearAndTermOrder[0]);
        basicInfo.setTermOrder(Integer.parseInt(termYearAndTermOrder[1]));
        return basicInfo;
    }

    private String[] parseTermYearAndTermOrder(String executiveEducationPlanNumber) {
        String[] results = executiveEducationPlanNumber.split("-");
        return new String[]{results[0] + "-" + results[1], results[2]};
    }

    public Plan convertToPlan() {
        Plan plan = new Plan();
        plan.setPlanName(this.getProgramPlanName());
        plan.setPlanNumber(this.getProgramPlanNumber());
        plan.setProgramName(this.getProgramPlanName());
        return plan;
    }

}
