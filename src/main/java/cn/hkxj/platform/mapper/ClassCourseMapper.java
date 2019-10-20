package cn.hkxj.platform.mapper;

import cn.hkxj.platform.pojo.ClassCourse;
import cn.hkxj.platform.pojo.example.ClassCourseExample;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface ClassCourseMapper {
    int countByExample(ClassCourseExample example);

    int deleteByExample(ClassCourseExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ClassCourse record);

    int insertSelective(ClassCourse record);

    List<ClassCourse> selectByExample(ClassCourseExample example);

    ClassCourse selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ClassCourse record, @Param("example") ClassCourseExample example);

    int updateByExample(@Param("record") ClassCourse record, @Param("example") ClassCourseExample example);

    int updateByPrimaryKeySelective(ClassCourse record);

    int updateByPrimaryKey(ClassCourse record);
}