package cn.hkxj.platform.pojo.vo;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@Accessors(chain = true)
public class TermGradeVo implements Comparable<TermGradeVo>{
    private List<GradeVo> gradeList;

    private String termYear;

    private int termOrder;

    private boolean isCurrentTerm;

    public String getExecutiveEducationPlanNum() {
        return this.getTermYear() + "-" + this.getTermOrder()+ "-1";
    }

    @Override
    public int compareTo(TermGradeVo o) {

        return this.getExecutiveEducationPlanNum().compareTo(o.getExecutiveEducationPlanNum());
    }
}
