package cn.hkxj.platform.spider.newmodel;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Data;

/**
 * @author Yuki
 * @date 2019/7/31 20:12
 */
@Data
public class CourseRelativeInfo {

    @JSONField(name = "courseNumber", alternateNames = {"coureNumber"})
    private String courseNumber;
    @JSONField(name = "courseSequenceNumber", alternateNames = "{coureSequenceNumber}")
    private String courseSequenceNumber;

    private String examtime;

    private String executiveEducationPlanNumber;

    private String studentNumber;

    private String scoreTypeCode;

    public String getTermYear(){
        return executiveEducationPlanNumber.substring(0, 9);
    }

    public int getTermOrder(){
        return Integer.parseInt(executiveEducationPlanNumber.substring(10, 11));
    }


}
