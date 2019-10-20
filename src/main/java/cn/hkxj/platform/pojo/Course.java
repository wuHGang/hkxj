package cn.hkxj.platform.pojo;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class Course {
    private Integer id;

    private String name;

    private String num;

    private String courseOrder;

    private String termYear;

    private Integer termOrder;

    private String teacherAccount;

    private String teacherName;

    private String examType;

    private String examTypeCode;

    private String academyName;

    private String academyCode;

    private String courseType;

    private String courseTypeCode;

    private String credit;
}