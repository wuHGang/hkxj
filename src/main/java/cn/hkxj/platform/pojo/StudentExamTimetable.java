package cn.hkxj.platform.pojo;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Date;

@Data
@Accessors(chain = true)
public class StudentExamTimetable {
    private Integer id;

    private String account;

    private Integer examTimetableId;

    private String termYear;

    private Integer termOrder;

    private Date gmtCreate;

    private Date gmtModify;
}