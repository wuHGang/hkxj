package cn.hkxj.platform.mapper;

import java.util.List;

import cn.hkxj.platform.pojo.CourseTimeTableDetail;
import cn.hkxj.platform.pojo.StudentCourseTable;
import cn.hkxj.platform.pojo.StudentCourseTimeTable;
import cn.hkxj.platform.pojo.example.CourseTimeTableDetailExample;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface CourseTimeTableDetailMapper {
    int countByExample(CourseTimeTableDetailExample example);

    int deleteByExample(CourseTimeTableDetailExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(CourseTimeTableDetail record);

    int insertSelective(CourseTimeTableDetail record);

    List<CourseTimeTableDetail> selectByExample(CourseTimeTableDetailExample example);

    CourseTimeTableDetail selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") CourseTimeTableDetail record, @Param("example") CourseTimeTableDetailExample example);

    int updateByExample(@Param("record") CourseTimeTableDetail record, @Param("example") CourseTimeTableDetailExample example);

    int updateByPrimaryKeySelective(CourseTimeTableDetail record);

    int updateByPrimaryKey(CourseTimeTableDetail record);

    List<Integer> getCourseTimeTableDetailIdsByClassId(@Param("classesId") int classesId);

    void insertClassesCourseTimeTableBatch(@Param("ids") List<Integer> ids, @Param("classesId") int classesId);

    void insertStudentCourseTimeTableBatch(@Param("courseTimeTableIdList") List<Integer> courseTimeTableIdList,
                                           @Param("account") int account, @Param("termYear") String termYear, @Param(
                                                   "termOrder") int termOrder);

    List<StudentCourseTable> selectStudentCourseTimeTableRelative(StudentCourseTable studentCourseTimeTable);
}