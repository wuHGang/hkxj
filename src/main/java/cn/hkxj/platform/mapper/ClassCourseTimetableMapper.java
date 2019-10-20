package cn.hkxj.platform.mapper;

import cn.hkxj.platform.pojo.ClassCourseTimetable;
import cn.hkxj.platform.pojo.example.ClassCourseTimetableExample;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface ClassCourseTimetableMapper {
    int countByExample(ClassCourseTimetableExample example);

    int deleteByExample(ClassCourseTimetableExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ClassCourseTimetable record);

    int insertSelective(ClassCourseTimetable record);

    List<ClassCourseTimetable> selectByExample(ClassCourseTimetableExample example);

    ClassCourseTimetable selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ClassCourseTimetable record, @Param("example") ClassCourseTimetableExample example);

    int updateByExample(@Param("record") ClassCourseTimetable record, @Param("example") ClassCourseTimetableExample example);

    int updateByPrimaryKeySelective(ClassCourseTimetable record);

    int updateByPrimaryKey(ClassCourseTimetable record);
}