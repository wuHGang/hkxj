package cn.hkxj.platform.mapper;

import cn.hkxj.platform.pojo.Course;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author junrong.chen
 */
@Mapper
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