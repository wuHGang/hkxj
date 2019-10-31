package cn.hkxj.platform.pojo;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 保存学生与课表之间的关系，之后会被替换
 */
@Deprecated
@Data
@Accessors( chain = true)
public class StudentCourseTable {
    private Integer id;
    private Integer account;
    private Integer courseTimeTableId;
    private String termYear;
    private Integer termOrder;
}
