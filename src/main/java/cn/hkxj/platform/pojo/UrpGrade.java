package cn.hkxj.platform.pojo;

import lombok.Data;

import java.util.Date;

@Data
public class UrpGrade {
    private Integer id;

    private Integer examId;

    private Integer account;

    private Integer score;

    private Double gradePoint;

    private String levelName;

    private String levelPoint;

    private Integer rank;

    private String unpassedreasoncode;

    private String unpassedreasonexplain;

    private String remark;

    private String replacecoursenumber;

    private String retakecoursemark;

    private String retakecoursemodecode;

    private String retakecoursemodeexplain;

    private String standardpoint;

    private Date gmtCreate;

}