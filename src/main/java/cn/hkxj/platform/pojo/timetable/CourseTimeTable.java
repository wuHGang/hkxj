package cn.hkxj.platform.pojo.timetable;

import cn.hkxj.platform.pojo.Course;
import cn.hkxj.platform.pojo.Room;
import lombok.Data;

@Data
public class CourseTimeTable {
    private Integer id;

    private Course course;

    private Integer year;

    private Integer term;

    private Integer start;

    private Integer end;

    private Integer week;

    private Integer order;

    private Integer distinct;

    private Room room;


}