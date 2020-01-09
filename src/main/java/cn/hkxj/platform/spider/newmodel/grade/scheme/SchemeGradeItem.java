/**
 * Copyright 2020 bejson.com
 */
package cn.hkxj.platform.spider.newmodel.grade.scheme;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;

/**
 * Auto-generated: 2020-01-09 1:8:0
 *
 * @author bejson.com (i@bejson.com)
 * @website http://www.bejson.com/java2pojo/
 */
@Data
public class SchemeGradeItem {

    private Id id;
    private String classNo;
    private String entryStatusCode;
    private String scoreEntryModeCode;
    private String gradePointScore;
    private String standardScore;
    private String percentileRankScore;
    private int gradeScore;
    private String planNO;
    private String courseAttributeName;
    private String courseAttributeCode;
    private String payableMoney;
    private String examTypeCode;
    private String examTime;
    private String electiveTypeCode;
    private String studyModeCode;
    private int courseScore;
    private String operator;
    private String operatingTime;
    private String makeupExaminationTypeCode;
    private String notByReasonCode;
    private String notByReasonName;
    private String remark;
    private String cycle;
    private String courseName;
    private String englishCourseName;
    private String planName;
    private String planName2;
    private String academicYearCode;
    private String termTypeCode;
    private String termTypeName;
    private String termCode;
    private String termName;
    private String gradeName;
    @JSONField(name = "cj")
    private String score;
    private String xkcsxdm;
    private String xkcsxmc;
    private String tdkcm;
    private String cjlrfsdm;
    private String bm;
    private String credit;
    private String tscore;
    private String substituteCourseNo;

    public String getCredit() {
        if (StringUtils.startsWith(credit, ".")) {
            return "0" + credit;
        }
        return credit;
    }
}