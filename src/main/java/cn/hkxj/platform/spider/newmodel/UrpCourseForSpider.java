package cn.hkxj.platform.spider.newmodel;

import cn.hkxj.platform.pojo.UrpCourse;
import lombok.Data;

/**
 * @author Yuki
 * @date 2019/8/1 17:32
 */
@Data
public class UrpCourseForSpider {
    /**
     * 课程号
     */
    private String kch;
    /**
     * 课程名称
     */
    private String kcm;
    /**
     * 学院编号
     */
    private Integer xsh;
    /**
     * 学院名称
     */
    private String xsm;
    /**
     * 学分
     */
    private Double xf;
    /**
     * 本研标识
     */
    private String bybz;
    /**
     * 备注
     */
    private String bz;
    /**
     * 标准人数
     */
    private int bzrs;
    /**
     * 参考书
     */
    private String cks;
    /**
     * 教材
     */
    private String jc;
    /**
     * 授课总学时
     */
    private int jkzxs;
    /**
     *
     */
    private String jsm;
    /**
     * 结束日期
     */
    private String jsrq;
    /**
     *
     */
    private String jxdg;
    /**
     * 教学方式
     */
    private String jxfssm;
    /**
     * 课程基本名称
     */
    private String kcjbmc;
    /**
     * 课程类型名称
     * 示例："kclbmc": "本科"
     */
    private String kclbmc;
    /**
     * 课程说明
     */
    private String kcsm;
    /**
     * 课程状态
     */
    private String kcztdm;
    /**
     * 开课学期
     */
    private String kkxq;
    /**
     * 课内总学时
     */
    private int knzxs;
    /**
     * 课时费类别代码
     */
    private String ksflbdm;
    /**
     * 考试类型名称
     */
    private String kslxmc;
    /**
     * 开始日期
     */
    private String ksrq;
    /**
     * 课外学分
     */
    private int kwxf;
    /**
     * 课外总学时
     */
    private int kwzxs;
    /**
     * 内容简介
     */
    private String nrjj;
    /**
     * 其中上机总学时
     */
    private int qzsjzxs;
    /**
     * 人数系数代码
     */
    private String rsxsdm;
    /**
     * 收费类别名称
     */
    private String sflbmc;
    /**
     * 实践周数
     */
    private int sjzs;
    /**
     * 设计总学时
     */
    private int sjzxs;
    /**
     * 设计作业总学时
     */
    private int sjzyzxs;
    /**
     * 试验总学时
     */
    private int syzxs;
    /**
     * 师资队伍
     */
    private String szdw;
    /**
     * 讨论辅导总学时
     */
    private int tlfdzxs;
    /**
     * 学科门类号
     */
    private String xkmlh;
    /**
     * 学科门类名
     */
    private String xkmlm;
    /**
     * 校区号
     */
    private String xqh;
    /**
     * 校区名
     */
    private String xqm;
    /**
     * 学时
     */
    private int xs;
    /**
     *
     */
    private String xxkch;
    /**
     * 英语教学大纲
     */
    private String ywjxdg;
    /**
     * 英语课程名
     */
    private String ywkcm;
    /**
     * 英语内容简介
     */
    private String ywnrjj;
    /**
     * 主要修课对象
     */
    private String zyxkdx;

    public UrpCourse convertToUrpCourse(){
        UrpCourse urpCourse = new UrpCourse();
        urpCourse.setUndergraduatePostgraduateFlag(this.getBybz());
        urpCourse.setRemark(this.getBz());
        urpCourse.setStandardPersonNumber(this.getBzrs());
        urpCourse.setReferenceBook(this.getCks());
        urpCourse.setTeachingMaterial(this.getJc());
        urpCourse.setTotalHourInClass(this.getJkzxs());
        urpCourse.setJsm(this.getJsm());
        urpCourse.setEndDate(this.getJsrq());
        urpCourse.setJxdg(this.getJxdg());
        urpCourse.setTeachingMethod(this.getJxfssm());
        urpCourse.setCourseNumber(this.getKch());
        urpCourse.setBasicNameOfCourse(this.getKcjbmc());
        urpCourse.setCourseType(this.getKclbmc());
        urpCourse.setCourseName(this.getKcm());
        urpCourse.setCourseDescription(this.getKcsm());
        urpCourse.setCourseStatus(this.getKcztdm());
        urpCourse.setSemester(this.getKkxq());
        urpCourse.setTotalHourInClass(this.getKnzxs());
        urpCourse.setCourseFeeClassCode(this.getKsflbdm());
        urpCourse.setExaminationName(this.getKslxmc());
        urpCourse.setStartDate(this.getKsrq());
        urpCourse.setExtracurricularCredit(this.getKwxf());
        urpCourse.setExtracurricularTotalHour(this.getKwzxs());
        urpCourse.setContentAbstract(this.getNrjj());
        urpCourse.setOnComputerHour(this.getQzsjzxs());
        urpCourse.setNumberCoeffcientCode(this.getRsxsdm());
        urpCourse.setChargeCategoryName(this.getSflbmc());
        urpCourse.setPracticeWeek(this.getSjzs());
        urpCourse.setDesignTotalHour(this.getSjzxs());
        urpCourse.setDesignHomeworkTotalHour(this.getSjzyzxs());
        urpCourse.setExperimentHour(this.getSyzxs());
        urpCourse.setTeachingStaff(this.getSzdw());
        urpCourse.setDiscussCounselingTotalHour(this.getTlfdzxs());
        urpCourse.setCredit(this.getXf());
        urpCourse.setSubjectCategoryNumber(this.getXkmlh());
        urpCourse.setSubjectCategoryName(this.getXkmlm());
        urpCourse.setCampusNumber(this.getXqh());
        urpCourse.setCampusName(this.getXqm());
        urpCourse.setClassHour(this.getXs());
        urpCourse.setAcademy(this.getXsh());
        urpCourse.setXxkch(this.getXxkch());
        urpCourse.setEnglishSyllabus(this.getYwjxdg());
        urpCourse.setEnglishCourseName(this.getYwkcm());
        urpCourse.setEnglishContentBrief(this.getYwnrjj());
        urpCourse.setCourseType(this.getZyxkdx());
        return urpCourse;
    }
}

