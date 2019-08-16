package cn.hkxj.platform.pojo;

import lombok.Data;

import java.util.Date;

@Data
public class UrpGradeDetail {
    private Integer id;

    private Integer gradeId;

    private String usualScoreCoefficient;

    private String midtermScoreCoefficient;

    private String endtermScoreCoefficient;

    private String scoreCoefficient;

    private String xsRemark;

    private Double totalGrade;

    private Double regularGrade;

    private Double midtermGrade;

    private Double finaltermGrade;

    private String rounding;

    private String mxRemark;

    private String mxRemark1;

    private String mxRemark2;

    private String firstcjxs;

    private String firstpscjxs;

    private String firstqzcjxs;

    private String firstqmcjxs;

    private String secondcjxs;

    private String secondpscjxs;

    private String secondqzcjxs;

    private String secondqmcjxs;

    private String scoreTypeCode;

    private Date gmtCreate;

}