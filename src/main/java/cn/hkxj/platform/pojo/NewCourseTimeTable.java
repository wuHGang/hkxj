package cn.hkxj.platform.pojo;

import lombok.Data;

import java.util.List;

/**
 * @author Yuki
 * @date 2019/8/30 10:31
 */
@Data
public class NewCourseTimeTable {

    private CourseTimeTableBasicInfo basicInfo;

    private List<CourseTimeTableDetail> details;
}
