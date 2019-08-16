package cn.hkxj.platform.spider.newmodel;

import cn.hkxj.platform.pojo.*;
import lombok.Data;

/**
 * @author Yuki
 * @date 2019/7/31 17:33
 */
@Data
public class UrpGeneralGradeForSpider {
    /**
     * 平均成绩
     */
    private Double avgcj;
    /**
     * 班级号
     */
    private String bjh;
    /**
     * 班级名
     */
    private String bjm;
    /**
     * 课程名称
     */
    private String courseName;
    /**
     * 课序号
     */
    private String coureSequenceNumber;
    /**
     * 课程属性编号
     */
    private String coursePropertyCode;
    /**
     * 课程属性名称
     */
    private String coursePropertyName;
    /**
     * 课程成绩
     */
    private int courseScore;
    /**
     * 学分
     */
    private Double credit;
    /**
     * 课程英文名
     */
    private String englishCourseName;
    /**
     * 考试类型编号
     */
    private int examTypeCode;
    /**
     * 考试类型名称
     */
    private String examTypeName;
    /**
     * 绩点
     */
    private Double gradePoint;
    /**
     * 最高成绩
     */
    private int maxcj;
    /**
     * 最低成绩
     */
    private int mincj;
    /**
     * 排名
     */
    private int rank;
    /**
     * 未通过原因编号
     */
    private String unpassedReasonCode;
    /**
     * 未通过原因
     */
    private String unpassedReasonExplain;
    /**
     * 学期年份,如："2018-2019"
     */
    private String termCode;
    /**
     * 学期名称，如："第二学期"
     */
    private String termName;
    /**
     * 等级名
     */
    private String levelName;
    /**
     * 等级对应的数值
     */
    private String levlePoint;
    /**
     * 操作时间
     */
    private String operatetime;
    /**
     * 操作人
     */
    private String operator;
    /**
     *
     */
    private String persentlevlePoint;
    /**
     * 计划名
     */
    private String planName;
    /**
     * 计划号
     */
    private String planNumber;
    /**
     * 项目名
     */
    private String programName;
    /**
     * 备注
     */
    private String remark;
    /**
     * 替换课程编号
     */
    private String replaceCourseNumber;
    /**
     * 重修课程标识
     */
    private String retakeCourseMark;
    /**
     * 重修课程模式编号
     */
    private String retakeCourseModeCode;
    /**
     * 重修课程模式解释
     */
    private String retakeCourseModeExplain;
    /**
     * 标准分数
     */
    private String standardPoint;
    /**
     * 学习模式编号
     */
    private String studingModeCode;
    /**
     * 学时
     */
    private String studyHour;
    /**
     *
     */
    private Double tPoint;
    /**
     * 学院号
     */
    private int xsh;
    /**
     * 学院名
     */
    private String xsm;
    /**
     * 专业号
     */
    private String zyh;
    /**
     * 相关课程信息
     */
    private GradeRelativeInfo id;


    public Term getTermForUrpGrade(){
        String target = this.termCode;
        String[] years = target.split("-");
        int order;
        Term term = new Term();
        term.setStartYear(Integer.parseInt(years[0]));
        term.setEndYear(Integer.parseInt(years[1]));
        term.setOrder(getTermOrder());
        return term;
    }

    private int getTermOrder(){
        switch (this.termName){
            case "第一学期": return 1;
            case "第二学期": return 2;
            default: throw new IllegalArgumentException("illegal termName");
        }
    }

    public UrpGrade convertToUrpGrade(){
        UrpGrade urpGrade = new UrpGrade();
        urpGrade.setGradePoint(this.getGradePoint());
        urpGrade.setLevelName(this.getLevelName());
        urpGrade.setLevelPoint(this.getLevlePoint());
        urpGrade.setRank(this.getRank());
        urpGrade.setRemark(this.getRemark());
        urpGrade.setReplacecoursenumber(this.getReplaceCourseNumber());
        urpGrade.setRetakecoursemark(this.getRetakeCourseMark());
        urpGrade.setRetakecoursemodecode(this.getRetakeCourseModeCode());
        urpGrade.setRetakecoursemodeexplain(this.getRetakeCourseModeExplain());
        urpGrade.setScore(this.getCourseScore());
        urpGrade.setStandardpoint(this.getStandardPoint());
        urpGrade.setUnpassedreasoncode(this.getUnpassedReasonCode());
        urpGrade.setUnpassedreasonexplain(this.getUnpassedReasonExplain());
        return urpGrade;
    }

    public UrpExam convertToUrpExam(){
        UrpExam urpExam = new UrpExam();
        urpExam.setAvgcj(this.getAvgcj());
        urpExam.setBjh(this.getBjh());
        urpExam.setCourseId(this.getId().getCourseNumber());
        urpExam.setCoursepropertycode(this.getCoursePropertyCode());
        urpExam.setCoursepropertyname(this.getCoursePropertyName());
        urpExam.setCoursesequencenumber(this.getCoureSequenceNumber());
        urpExam.setExamtime(this.getId().getExamtime());
        urpExam.setExecutiveeducationplannumber(this.getId().getExecutiveEducationPlanNumber());
        urpExam.setMaxcj(this.getMaxcj());
        urpExam.setMincj(this.getMincj());
        urpExam.setOperatetime(this.getOperatetime());
        urpExam.setOperator(this.getOperator());
        urpExam.setPersentlevelpoint(this.getPersentlevlePoint());
        urpExam.setTermcode(this.getTermCode());
        urpExam.setTermname(this.getTermName());
        urpExam.setXsh(this.getXsh());
        return urpExam;
    }

    public Major convertToMajor(){
        Major major = new Major();
        major.setZyh(this.getZyh());
        major.setZym(this.getPlanName());
        return major;
    }

    public Plan convertToPlan(){
        Plan plan = new Plan();
        plan.setPlanName(this.getPlanName());
        plan.setPlanNumber(this.getPlanNumber());
        plan.setProgramName(this.programName);
        return plan;
    }
}



