package cn.hkxj.platform.pojo;

import java.util.Date;

public class UrpExam {
    private Integer id;

    private String courseId;

    private Integer majorId;

    private Integer classId;

    private Integer planId;

    private Double averageScore;

    private String classNumber;

    private String courseSequenceNumber;

    private String coursePropertyCode;

    private String coursePropertyName;

    private String examtime;

    private String executiveEducationPlanNumber;

    private Integer maxScore;

    private Integer minScore;

    private String operator;

    private String operatetime;

    private String persentLevelPoint;

    private String termCode;

    private String termName;

    private Integer academy;

    private Date gmtCreate;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId == null ? null : courseId.trim();
    }

    public Integer getMajorId() {
        return majorId;
    }

    public void setMajorId(Integer majorId) {
        this.majorId = majorId;
    }

    public Integer getClassId() {
        return classId;
    }

    public void setClassId(Integer classId) {
        this.classId = classId;
    }

    public Integer getPlanId() {
        return planId;
    }

    public void setPlanId(Integer planId) {
        this.planId = planId;
    }

    public Double getAverageScore() {
        return averageScore;
    }

    public void setAverageScore(Double averageScore) {
        this.averageScore = averageScore;
    }

    public String getClassNumber() {
        return classNumber;
    }

    public void setClassNumber(String classNumber) {
        this.classNumber = classNumber == null ? null : classNumber.trim();
    }

    public String getCourseSequenceNumber() {
        return courseSequenceNumber;
    }

    public void setCourseSequenceNumber(String courseSequenceNumber) {
        this.courseSequenceNumber = courseSequenceNumber == null ? null : courseSequenceNumber.trim();
    }

    public String getCoursePropertyCode() {
        return coursePropertyCode;
    }

    public void setCoursePropertyCode(String coursePropertyCode) {
        this.coursePropertyCode = coursePropertyCode == null ? null : coursePropertyCode.trim();
    }

    public String getCoursePropertyName() {
        return coursePropertyName;
    }

    public void setCoursePropertyName(String coursePropertyName) {
        this.coursePropertyName = coursePropertyName == null ? null : coursePropertyName.trim();
    }

    public String getExamtime() {
        return examtime;
    }

    public void setExamtime(String examtime) {
        this.examtime = examtime == null ? null : examtime.trim();
    }

    public String getExecutiveEducationPlanNumber() {
        return executiveEducationPlanNumber;
    }

    public void setExecutiveEducationPlanNumber(String executiveEducationPlanNumber) {
        this.executiveEducationPlanNumber = executiveEducationPlanNumber == null ? null : executiveEducationPlanNumber.trim();
    }

    public Integer getMaxScore() {
        return maxScore;
    }

    public void setMaxScore(Integer maxScore) {
        this.maxScore = maxScore;
    }

    public Integer getMinScore() {
        return minScore;
    }

    public void setMinScore(Integer minScore) {
        this.minScore = minScore;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator == null ? null : operator.trim();
    }

    public String getOperatetime() {
        return operatetime;
    }

    public void setOperatetime(String operatetime) {
        this.operatetime = operatetime == null ? null : operatetime.trim();
    }

    public String getPersentLevelPoint() {
        return persentLevelPoint;
    }

    public void setPersentLevelPoint(String persentLevelPoint) {
        this.persentLevelPoint = persentLevelPoint == null ? null : persentLevelPoint.trim();
    }

    public String getTermCode() {
        return termCode;
    }

    public void setTermCode(String termCode) {
        this.termCode = termCode == null ? null : termCode.trim();
    }

    public String getTermName() {
        return termName;
    }

    public void setTermName(String termName) {
        this.termName = termName == null ? null : termName.trim();
    }

    public Integer getAcademy() {
        return academy;
    }

    public void setAcademy(Integer academy) {
        this.academy = academy;
    }

    public Date getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }
}