package cn.hkxj.platform.spider.newmodel.searchteacher;

import lombok.Data;

@Data
public class SearchTeacherResult {
    private String departmentName;

    private String teacherName;

    private String zxjxjhh;

    private String sex;

    private String executiveEducationPlanName;

    private String departmentNum;

    private String professionalTitle;

    private Id id;

    @Data
    public class Id
    {
        private String teacherNumber;

        private String executiveEducationPlanNumber;

    }
}
