package cn.hkxj.platform.service.wechat.common.course;

import cn.hkxj.platform.pojo.Course;

import java.util.List;

/**
 * @author Yuki
 * @date 2018/10/10 23:35
 */
public interface CourseService {

    /**
     * 通过学号获取相应的课程列表
     * @param account 学号
     * @return 学号列表
     */
    List<Course> getCoursesByAccount(Integer account);
}
