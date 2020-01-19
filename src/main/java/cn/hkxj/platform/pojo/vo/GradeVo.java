package cn.hkxj.platform.pojo.vo;

import cn.hkxj.platform.pojo.Course;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NonNull;
import lombok.experimental.Accessors;

import java.util.Date;

@Data
@Accessors(chain = true)
public class GradeVo implements Comparable{
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

    private Integer errorCode = 0;

    private String msg;

    private String coursePropertyCode;

    private String coursePropertyName;

    private boolean update = false;

    @Override
    public int compareTo(@NonNull Object o) {
        GradeVo o1 = (GradeVo) o;
        if(o1.getTermYear().compareTo(this.getTermYear()) == 0){
            if(o1.getTermOrder().compareTo(this.getTermOrder()) == 0){
                return o1.getOperateTime().compareTo(this.getOperateTime());
            }else {
                return o1.getTermOrder().compareTo(this.getTermOrder());
            }
        }else {
            return o1.getTermYear().compareTo(this.getTermYear());
        }

    }
}
