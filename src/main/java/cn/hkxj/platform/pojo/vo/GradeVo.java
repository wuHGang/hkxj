package cn.hkxj.platform.pojo.vo;

import cn.hkxj.platform.pojo.Course;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.util.Date;

@Data
@Accessors(chain = true)
public class GradeVo {
    private Course course;

    private Integer account;

    private Double score;

    private Double gradePoint;

    private String levelName;

    private String levelPoint;

    private Integer rank;

    private Date operateTime;

    private String operator;

    private Date examTime;

    private String examTimeStr;

    private String unpassedReasonCode;

    private String unpassedReasonExplain;

    private String remark;

    private String replaceCourseNumber;

    private String retakeCourseMark;

    private String retakecourseModeCode;

    private String retakeCourseModeExplain;

    private String standardPoint;

    private String termYear;

    private Integer termOrder;

    private boolean update = false;
}
