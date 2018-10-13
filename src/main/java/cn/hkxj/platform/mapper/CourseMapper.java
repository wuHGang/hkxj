package cn.hkxj.platform.mapper;

import cn.hkxj.platform.pojo.Course;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author junrong.chen
 */
@Mapper
@Repository
public interface CourseMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Course record);

    int insertSelective(Course record);

    Course selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Course record);

    int updateByPrimaryKey(Course record);

    int insertStudentAndCourse(int account, String uid);

    List<Integer> getCourseIdsByClassId(Integer classId);

    List<Course> getAllCourses(String ids);

}