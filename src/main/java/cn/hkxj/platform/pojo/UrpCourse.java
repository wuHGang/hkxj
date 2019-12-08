package cn.hkxj.platform.pojo;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Date;

@Data
@Accessors(chain = true)
public class UrpCourse {
    private Integer id;

    private String undergraduatePostgraduateFlag;

    private String remark;

    private Integer standardPersonNumber;

    private String referenceBook;

    private String teachingMaterial;

    private Integer totalHourOfTeaching;

    private String jsm;

    private String endDate;

    private String jxdg;

    private String teachingMethod;

    private String courseNumber;

    private String basicNameOfCourse;

    private String courseType;

    private String courseName;

    private String courseDescription;

    private String courseStatus;

    private String semester;

    private Integer totalHourInClass;

    private String courseFeeClassCode;

    private String examinationName;

    private String startDate;

    private Integer extracurricularCredit;

    private Integer extracurricularTotalHour;

    private String contentAbstract;

    private Integer onComputerHour;

    private Integer practiceWeek;

    private String numberCoeffcientCode;

    private String chargeCategoryName;

    private Integer designTotalHour;

    private Integer designHomeworkTotalHour;

    private Integer experimentHour;

    private String teachingStaff;

    private Integer discussCounselingTotalHour;

    private Double credit;

    private String subjectCategoryNumber;

    private String subjectCategoryName;

    private String campusNumber;

    private String campusName;

    private Integer classHour;

    private Integer academy;

    private String xxkch;

    private String englishSyllabus;

    private String englishCourseName;

    private String englishContentBrief;

    private String courseTarget;

    private Date gmtCreate;

}