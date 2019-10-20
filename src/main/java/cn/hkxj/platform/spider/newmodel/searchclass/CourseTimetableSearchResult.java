/**
 * Copyright 2019 bejson.com
 */
package cn.hkxj.platform.spider.newmodel.searchclass;

import cn.hkxj.platform.pojo.CourseTimetable;
import cn.hkxj.platform.pojo.UrpClassroom;
import cn.hkxj.platform.spider.newmodel.coursetimetable.TimeAndPlace;
import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Auto-generated: 2019-10-08 22:32:16
 *
 * @author bejson.com (i@bejson.com)
 * @website http://www.bejson.com/java2pojo/
 */
@Data
public class CourseTimetableSearchResult {

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
    private String weekDescription;
    /**
     * 持续节次
     */
    @JSONField(name = "cxjc")
    private int continuingSession;
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
    @JSONField(name = "jxlm")
    private String buildingName;
    /**
     * 教室对应的账号
     */
    @JSONField(name = "jash")
    private String classRoomNumber;
    /**
     * 课室对应名称
     */
    @JSONField(name = "jasm")
    private String classRoomName;

    private String jysh;
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
    public class Id {
        /**
         * 学期编号  如：2019-2020-1-1
         */
        @JSONField(name = "zxjxjhh")
        private String termNumber;
        /**
         * 老师的账号
         */
        @JSONField(name = "jsh")
        private String teacherAccount;
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
        private String classInSchoolWeek;
        /**
         * 上课星期  如 3对应星期三
         */
        @JSONField(name = "skxq")
        private int classWeek;
        /**
         * 上课节次
         */
        @JSONField(name = "skjc")
        private int classOrderInWeek;
    }

    public int getTermOrder() {
        if (termName.contains("一")) {
            return 1;
        } else {
            return 2;
        }

    }

    public String getCredit() {
        return credit.startsWith(".") ? "0" + credit : credit;
    }

    public String getTermYear() {
        return termName.substring(0, 9);
    }


    public List<CourseTimetable> transToCourseTimetable() {
        return TimeAndPlace.parseWeek(weekDescription).stream().map(x ->
                new CourseTimetable()
                        .setCourseId(id.courseId)
                        .setCourseSequenceNumber(id.courseOrderNumber)
                        .setAttendClassTeacher(teacherName)
                        .setCampusName(campusName)
                        .setClassDay(id.classWeek)
                        .setClassOrder(id.classOrderInWeek)
                        .setStudentCount(studentCount)
                        .setContinuingSession(continuingSession)
                        .setRoomNumber(classRoomNumber)
                        .setRoomName(classRoomName)
                        .setClassInSchoolWeek(id.classInSchoolWeek)
                        .setTermYear(getTermYear())
                        .setTermOrder(getTermOrder())
                        .setWeekDescription(weekDescription)
                        .setStartWeek(x[0])
                        .setEndWeek(x[1])
                        .setClassDistinct(TimeAndPlace.parseDistinct(id.classInSchoolWeek, x[0], x[1])))

                .collect(Collectors.toList());


    }

    public UrpClassroom transToUrpClassRoom(){
        return new UrpClassroom()
                .setNumber(classRoomNumber)
                .setName(classRoomName)
                .setTeachingBuildingName(buildingName)
                .setTeachingBuildingNumber(buildingNumber)
                .setCampusName(campusName)
                .setCampusNumber(campusNumber)
                .setSeats(0);


    }
}