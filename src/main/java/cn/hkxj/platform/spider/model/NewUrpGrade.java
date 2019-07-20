package cn.hkxj.platform.spider.model;

import lombok.Data;

@Data
public class NewUrpGrade {
    /**
     * 课程平均成绩
     */
    private String avgcj;
    /**
     * 班级号
     */
    private String bjh;
    /**
     * 班级名
     */
    private String bm;
    /**
     * 课程编号
     */
    private String coureSequenceNumber;
    /**
     * 课程名
     */
    private String courseName;
    /**
     * 课程属性代号
     */
    private String coursePropertyCode;
    /**
     * 课程属性名  必修 选修 任选
     */
    private String coursePropertyName;
    /**
     * 课程成绩
     */
    private String courseScore;
    /**
     * 课程学分
     */
    private String credit;
    /**
     * 课程英文名
     */
    private String englishCourseName;
    /**
     * 考试类型code
     */
    private String examTypeCode;
    /**
     * 考试类型名称 考试
     */
    private String examTypeName;
    /**
     *
     */
    private String freeCourseTypeCode;
    /**
     * 成绩对应的学分
     */
    private String gradePoint;

    private GradeIdItem id;

    private String inputMethodCode;
    private String inputStatusCode;
    private String inputStatusExplain;
    /**
     * 成绩对应的等级  优秀 良好
     */
    private String levelName;
    private String levlePoint;
    /**
     * 最高分
     */
    private String maxcj;
    /**
     * mincj
     */
    private String mincj;
    /**
     * 成绩录入时间
     */
    private String operatetime;
    /**
     * 录入成绩的账号
     */
    private String operator;
    private String persentlevlePoint;
    private String planName;
    private String planNumber;
    /**
     * 如：2018级的指导教学计划
     */
    private String programName;
    /**
     * 排名
     */
    private String rank;
    private String remark;
    private String replaceCourseNumber;
    private String retakeCourseMark;
    private String retakeCourseModeCode;
    private String retakeCourseModeExplain;
    private String standardPoint;
    /**
     * 学生名
     */
    private String studentName;
    private String studingModeCode;
    /**
     * 课程学时
     */
    private String studyHour;
    private String tPoint;
    /**
     * 2018-2019
     */
    private String termCode;
    /**
     * 第二学期
     */
    private String termName;

    private String unpassedReasonCode;

    private String unpassedReasonExplain;
    /**
     * 学院的代号
     */
    private String xsh;
    /**
     * 马克思主义学院
     */
    private String xsm;

    /**
     * 专业号
     */
    private String zyh;

    @Data
    public static class GradeIdItem{

        private String courseNumber;
        /**
         * 考试时间
         */
        private String examtime;
        /**
         * 成绩对应的学期
         */
        private String executiveEducationPlanNumber;
        /**
         * 学生学号
         */
        private String studentNumber;
    }
}
