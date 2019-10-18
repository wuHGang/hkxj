/**
 * Copyright 2019 bejson.com
 */
package cn.hkxj.platform.spider.newmodel.searchclass;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

/**
 * Auto-generated: 2019-10-08 22:32:16
 *
 * @author bejson.com (i@bejson.com)
 * @website http://www.bejson.com/java2pojo/
 */
@Data
public class ClassCourseSearchResult {

    private Id id;
    /**
     * 课程名
     */
    @JSONField(name = "kcm")
    private String courseName;
    private String jclxdm;
    /**
     * 学期名  如：2019-2020学年第一学期
     */
    @JSONField(name = "zxjxjhm")
    private String termName;
    /**
     * 上课周次中文描述 如：5-8周
     */
    @JSONField(name = "zcsm")
    private String classSchoolWeekName;
    private int cxjc;
    /**
     * 校区对应编号  目前只有主校区：01
     */
    @JSONField(name = "xqh")
    private String campusNumber;
    /**
     * 校区名  目前只有主校区
     */
    @JSONField(name = "xqm")
    private String campusName;
    /**
     * 教学楼对应编号
     */
    @JSONField(name = "jxlh")
    private String buildingNumber;
    /**
     * 教学楼对应名称
     */
    @JSONField(name = "jxlh")
    private String buildingName;
    /**
     * 课室对应编号
     */
    @JSONField(name = "jash")
    private String classRoomNumber;
    /**
     * 课室对应名称
     */
    @JSONField(name = "jash")
    private String classRoomName;
    private String kkxsh;
    private String xsh;
    /**
     * 教师名
     */
    @JSONField(name = "jsm")
    private String teacherName;
    /**
     * 教师性别
     */
    @JSONField(name = "xb")
    private String teacherSex;
    private String zcdm;
    private String jysh;
    /**
     * 班级号 2019010001,2019010002 逗号分隔
     */
    @JSONField(name = "bjh")
    private String classIdList;
    /**
     * 学生数
     */
    @JSONField(name = "xss")
    private int studentCount;
    private int bkskyl;
    /**
     * 学分  这个主意有可能是小数点开头  0.5 会显示成 .5
     */
    @JSONField(name = "xf")
    private String credit;
    /**
     * 考试类型 编号
     */
    @JSONField(name = "kslxdm")
    private String examTypeNumber;
    /**
     * 考试类型名称
     */
    @JSONField(name = "kslxmc")
    private String examTypeName;
    /**
     * 开课学院 College
     */
    @JSONField(name = "kkxsm")
    private String courseCollege;

    @Data
    public class Id{
        /**
         * 学期编号  如：2019-2020-1-1
         */
        @JSONField(name = "zxjxjhh")
        private String termNumber;
        /**
         * 教室编号
         */
        @JSONField(name = "jsh")
        private String classRoomNumber;
        /**
         * 课程序号
         */
        @JSONField(name = "kxh")
        private String courseOrderNumber;
        /**
         * 课程号
         */
        @JSONField(name = "kch")
        private String courseId;
        /**
         * 上课周次
         * 1表示对应的教学周需要上课
         * 000011110000000000000000
         */
        @JSONField(name = "skzc")
        private String classSchoolWeek;
        /**
         * 上课星期  如 3对应星期三
         */
        @JSONField(name = "skxq")
        private int classWeek;
        /**
         * 上课节次
         */
        @JSONField(name = "skxq")
        private int classOrderInWeek;
    }

}