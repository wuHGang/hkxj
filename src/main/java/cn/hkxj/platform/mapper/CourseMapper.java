package cn.hkxj.platform.mapper;

import cn.hkxj.platform.pojo.Course;
import org.apache.ibatis.annotations.Mapper;
<<<<<<< HEAD
import org.springframework.stereotype.Repository;

import java.util.List;

=======

>>>>>>> forkOrigin/dev
/**
 * @author junrong.chen
 */
@Mapper
<<<<<<< HEAD
@Repository
=======
>>>>>>> forkOrigin/dev
public interface CourseMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Course record);

    int insertSelective(Course record);

    Course selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Course record);

    int updateByPrimaryKey(Course record);

    int insertStudentAndCourse(int account, String uid);
<<<<<<< HEAD

    List<Integer> getCourseIdsByClassId(Integer classId);

    List<Course> getAllCourses(String ids);
=======
>>>>>>> forkOrigin/dev
}