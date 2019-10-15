package cn.hkxj.platform.pojo;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 学生学号和课程的关联映射
 */
@Data
@Accessors( chain = true)
public class StudentCourseTimeTable {
    private Integer id;
    private Integer account;
    private Integer courseTimeTableId;
    private String termYear;
    private Integer termOrder;
}
