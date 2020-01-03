package cn.hkxj.platform.pojo;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Date;

@Data
@Accessors(chain = true)
public class ExamTimetable {
    private Integer id;

    private String name;

    private String roomName;

    private String courseNum;

    private String courseOrder;

    private String termYear;

    private Integer termOrder;

    private Date examDate;

    private String startTime;

    private String endTime;

    private String day;

    private String schoolWek;

    private Date gmtCreate;

    private Date gmtModify;
}