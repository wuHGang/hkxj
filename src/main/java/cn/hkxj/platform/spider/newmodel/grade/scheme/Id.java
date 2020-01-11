/**
 * Copyright 2020 bejson.com
 */
package cn.hkxj.platform.spider.newmodel.grade.scheme;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Id {

    private String executiveEducationPlanNumber;
    private String courseNumber;
    private String startTime;
    private String studentId;
    @JSONField(name = "coureSequenceNumber")
    private String courseSequenceNumber;
    private String kch_zj;


    public String getCourseSequenceNumber() {
        if (courseSequenceNumber.length() == 1) {
            return "0" + courseSequenceNumber;
        }
        return courseSequenceNumber;
    }

}