package cn.hkxj.platform.pojo;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class UrpClass {
    private Integer id;

    private String admissionGrade;

    private String classNum;

    private String className;

    private String academyName;

    private String academyNum;

    private String subjectName;

    private String subjectNum;

}