package cn.hkxj.platform.pojo.vo;


import cn.hkxj.platform.pojo.Grade;
import cn.hkxj.platform.pojo.Student;
import lombok.Data;

@Data
public class GradeVO {
    private Grade grade;
    private String courseName;
    private Student student;
}
