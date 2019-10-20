package cn.hkxj.platform.spider.newmodel.searchcourse;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

@Data
public class SearchCourseResult {
    /**
     * 教师名称 多个以,分隔
     */
    @JSONField(name = "JSM")
    private String teacherNameList;
    /**
     * 教师的账号 多个以,分隔
     */
    @JSONField(name = "JSH")
    private String JSH;

    /**
     * 学期号 如 2019-2020-1-1
     */
    @JSONField(name = "ZXJXJHH")
    private String termNumber;
    /**
     * 学期名 如 2019-2020学年第一学期
     */
    @JSONField(name = "ZXJXJHM")
    private String termName;

    /**
     * 开课学院号
     */
    @JSONField(name = "KKXSH")
    private String academyCode;

    /**
     * 开课学院名
     */
    @JSONField(name = "KKXSM")
    private String academyName;

    /**
     * 考试类别代码
     */
    @JSONField(name = "KSLXDM")
    private String examTypeCode;

    /**
     * 课程类别名称  本科
     */
    @JSONField(name = "KSLXMC")
    private String examTypeName;

    /**
     * 学分  这个主意有可能是小数点开头  0.5 会显示成 .5
     */
    @JSONField(name = "XF")
    private String credit;

    /**
     * 课程号
     */
    @JSONField(name = "KCH")
    private String courseId;


    /**
     * 课程名
     */
    @JSONField(name = "KCM")
    private String courseName;


    /**
     * 课程类别代码
     */
    @JSONField(name = "KCLBDM")
    private String courseTypeCode;
    /**
     * 考试类别名称 考试
     */
    @JSONField(name = "KCLBMC")
    private String courseTypeName;

    /**
     * 课程序号
     */
    @JSONField(name = "KXH")
    private String courseOrder;

    private String RN;


    public String getCredit(){
        return credit.startsWith(".") ? "0" + credit : credit;
    }

    public String getTermYear(){
        return termName.substring(0, 9);
    }

}
