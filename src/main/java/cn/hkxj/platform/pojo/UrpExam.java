package cn.hkxj.platform.pojo;

import lombok.Data;

import java.util.Date;

@Data
public class UrpExam {
    private Integer id;

    private String courseId;

    private Integer majorId;

    private Integer classId;

    private Integer planId;

    private Double averageScore;

    private String classNumber;

    private String courseSequenceNumber;

    private String coursePropertyCode;

    private String coursePropertyName;

    private String examtime;

    private String executiveEducationPlanNumber;

    private Double maxScore;

    private Double minScore;

    private String operator;

    private String operatetime;

    private String persentLevelPoint;

    private String termCode;

    private String termName;

    private Integer academy;

    private Date gmtCreate;

  }