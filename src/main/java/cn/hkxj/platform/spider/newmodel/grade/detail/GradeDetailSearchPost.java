package cn.hkxj.platform.spider.newmodel.grade.detail;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class GradeDetailSearchPost {
    private String executiveEducationPlanNumber;

    private String courseNumber;

    private String examTime = "";

    private String courseSequenceNumber = "";
}
