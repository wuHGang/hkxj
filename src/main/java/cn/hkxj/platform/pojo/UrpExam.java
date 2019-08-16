package cn.hkxj.platform.pojo;

import java.util.Date;

public class UrpExam {
    private Integer id;

    private String courseId;

    private Integer majorId;

    private Integer classId;

    private Integer planId;

    private Double avgcj;

    private String bjh;

    private String coursesequencenumber;

    private String coursepropertycode;

    private String coursepropertyname;

    private String examtime;

    private String executiveeducationplannumber;

    private Integer maxcj;

    private Integer mincj;

    private String operator;

    private String operatetime;

    private String persentlevelpoint;

    private String termcode;

    private String termname;

    private Integer xsh;

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

    public Double getAvgcj() {
        return avgcj;
    }

    public void setAvgcj(Double avgcj) {
        this.avgcj = avgcj;
    }

    public String getBjh() {
        return bjh;
    }

    public void setBjh(String bjh) {
        this.bjh = bjh == null ? null : bjh.trim();
    }

    public String getCoursesequencenumber() {
        return coursesequencenumber;
    }

    public void setCoursesequencenumber(String coursesequencenumber) {
        this.coursesequencenumber = coursesequencenumber == null ? null : coursesequencenumber.trim();
    }

    public String getCoursepropertycode() {
        return coursepropertycode;
    }

    public void setCoursepropertycode(String coursepropertycode) {
        this.coursepropertycode = coursepropertycode == null ? null : coursepropertycode.trim();
    }

    public String getCoursepropertyname() {
        return coursepropertyname;
    }

    public void setCoursepropertyname(String coursepropertyname) {
        this.coursepropertyname = coursepropertyname == null ? null : coursepropertyname.trim();
    }

    public String getExamtime() {
        return examtime;
    }

    public void setExamtime(String examtime) {
        this.examtime = examtime == null ? null : examtime.trim();
    }

    public String getExecutiveeducationplannumber() {
        return executiveeducationplannumber;
    }

    public void setExecutiveeducationplannumber(String executiveeducationplannumber) {
        this.executiveeducationplannumber = executiveeducationplannumber == null ? null : executiveeducationplannumber.trim();
    }

    public Integer getMaxcj() {
        return maxcj;
    }

    public void setMaxcj(Integer maxcj) {
        this.maxcj = maxcj;
    }

    public Integer getMincj() {
        return mincj;
    }

    public void setMincj(Integer mincj) {
        this.mincj = mincj;
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

    public String getPersentlevelpoint() {
        return persentlevelpoint;
    }

    public void setPersentlevelpoint(String persentlevelpoint) {
        this.persentlevelpoint = persentlevelpoint == null ? null : persentlevelpoint.trim();
    }

    public String getTermcode() {
        return termcode;
    }

    public void setTermcode(String termcode) {
        this.termcode = termcode == null ? null : termcode.trim();
    }

    public String getTermname() {
        return termname;
    }

    public void setTermname(String termname) {
        this.termname = termname == null ? null : termname.trim();
    }

    public Integer getXsh() {
        return xsh;
    }

    public void setXsh(Integer xsh) {
        this.xsh = xsh;
    }

    public Date getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }
}