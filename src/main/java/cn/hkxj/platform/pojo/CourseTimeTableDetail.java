package cn.hkxj.platform.pojo;

import lombok.Data;

import java.util.Date;

@Data
public class CourseTimeTableDetail {
    private Integer id;

    private String roomName;

    private Integer courseTimeTableBasicInfoId;

    private String campusName;

    private Integer continuingSession;

    private String courseId;

    private String attendClassTeacher;

    private Integer day;

    private Integer order;

    private Integer startWeek;

    private Integer endWeek;

    private String week;

    private String courseSequenceNumber;

    private String weekDescription;

    private String sksj;

    private Byte distinct;

    private String termYear;

    private Integer termOrder;

    private Date gmtCreate;

    public Term getTermForCourseTimeTableDetail(){
        Term term = new Term();
        String[] years = termYear.split("-");
        term.setStartYear(Integer.parseInt(years[0]));
        term.setEndYear(Integer.parseInt(years[1]));
        term.setOrder(this.termOrder);
        return term;
    }

}