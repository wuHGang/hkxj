package cn.hkxj.platform.pojo;

import com.google.common.collect.Lists;
import lombok.Data;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Data
public class CourseTimeTableBasicInfo {
    private Integer id;

    private Integer planId;

    private String courseId;

    private String coursePropertiesCode;

    private String coursePropertiesName;

    private String restrictedCondition;

    private String selectCourseStatusCode;

    private String selectCourseStatusName;

    private String studyModeCode;

    private String studyModeName;

    private String flag;

    private String dgFlag;

    private String ywdgFlag;

    private String rlFlag;

    private String termYear;

    private Integer termOrder;

    private Date gmtCreate;
}