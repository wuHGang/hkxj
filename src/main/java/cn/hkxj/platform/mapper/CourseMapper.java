package cn.hkxj.platform.mapper;

import cn.hkxj.platform.pojo.Course;

import java.util.List;

public interface CourseMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Course record);

    int insertSelective(Course record);

    Course selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Course record);

    int updateByPrimaryKey(Course record);

    int insertStudentAndCourse(int account, String uid);

    List<Integer> getCourseIdsByStudentId(Integer studentId);

    List<Course> getCoursesByIds(String ids);
}