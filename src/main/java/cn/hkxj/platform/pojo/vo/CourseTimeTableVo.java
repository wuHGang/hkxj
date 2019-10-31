package cn.hkxj.platform.pojo.vo;

import cn.hkxj.platform.pojo.Course;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class CourseTimeTableVo {
    private String roomName;

    private String roomNumber;

    private String campusName;

    private Integer continuingSession;

    private String attendClassTeacher;

    private Integer studentCount;

    private Integer classDay;

    private Integer classOrder;

    private Integer startWeek;

    private Integer endWeek;

    private String classInSchoolWeek;

    private String weekDescription;

    private String termYear;

    private Integer termOrder;

    private Course course;
}
