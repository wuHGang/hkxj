package cn.hkxj.platform.pojo;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class TeacherClass {
    private Integer id;

    private String teacherId;

    private Integer classId;

    private String termYear;

    private Integer termOrder;
}