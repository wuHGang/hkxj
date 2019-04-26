package cn.hkxj.platform.mapper;

import cn.hkxj.platform.pojo.timetable.CourseTimeTable;
import cn.hkxj.platform.pojo.example.CourseTimeTableExample;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface CourseTimeTableMapper {
    int countByExample(CourseTimeTableExample example);

    int deleteByExample(CourseTimeTableExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(CourseTimeTable record);

    int insertSelective(CourseTimeTable record);

    List<CourseTimeTable> selectByExample(CourseTimeTableExample example);

    CourseTimeTable selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") CourseTimeTable record, @Param("example") CourseTimeTableExample example);

    int updateByExample(@Param("record") CourseTimeTable record, @Param("example") CourseTimeTableExample example);

    int updateByPrimaryKeySelective(CourseTimeTable record);

    int updateByPrimaryKey(CourseTimeTable record);

    Integer isCourseTimeTableExist(CourseTimeTable courseTimeTable);

}