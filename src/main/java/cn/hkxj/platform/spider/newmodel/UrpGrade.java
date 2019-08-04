package cn.hkxj.platform.spider.newmodel;

import cn.hkxj.platform.pojo.Grade;
import cn.hkxj.platform.pojo.Term;
import lombok.Data;

import java.util.StringTokenizer;

/**
 * @author Yuki
 * @date 2019/7/31 17:33
 */
@Data
public class UrpGrade {
    /**
     * 平均成绩
     */
    private Double avgcj;
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
    private int coursePropertyCode;
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
    private Double maxcj;
    /**
     * 最低成绩
     */
    private Double mincj;
    /**
     * 排名
     */
    private int rank;
    /**
     * 未通过原因编号
     */
    private int unpassedReasonCode;
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
     * 相关课程信息
     */
    private CourseForUrpGrade id;

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

    public Grade convertToGrade(){
        Grade grade = new Grade();
        Term term = getTermForUrpGrade();
        grade.setAccount(Integer.parseInt(this.id.getStudentNumber()));
        grade.setScore(this.courseScore);
        grade.setCourseId(this.id.getCourseNumber());
        grade.setPoint(this.credit.intValue());
        grade.setTerm((byte) term.getOrder());
        grade.setYear(term.getStartYear());
        return grade;
    }

}



