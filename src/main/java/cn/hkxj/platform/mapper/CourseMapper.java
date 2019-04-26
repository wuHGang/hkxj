package cn.hkxj.platform.mapper;

import cn.hkxj.platform.pojo.Course;
import cn.hkxj.platform.pojo.example.CourseExample;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Mapper
@Repository
public interface CourseMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Course record);

    int insertSelective(Course record);

    List<Course> selectByExample(CourseExample example);

    Course selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Course record);

    int updateByPrimaryKey(Course record);

    int insertStudentAndCourse(int account, String uid);

    List<Integer> getCourseIdsByClassId(Integer classId);

    List<Course> getAllCourses(@Param("ids") List<Integer> ids);

    List<Course> selectNameByUid(String uid);

    boolean ifExistCourse(String uid);

    List<Course> selectCourseByName(String name);

}