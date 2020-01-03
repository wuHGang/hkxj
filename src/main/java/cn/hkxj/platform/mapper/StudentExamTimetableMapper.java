package cn.hkxj.platform.mapper;

import cn.hkxj.platform.pojo.StudentExamTimetable;
import cn.hkxj.platform.pojo.StudentExamTimetableExample;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface StudentExamTimetableMapper {
    int countByExample(StudentExamTimetableExample example);

    int deleteByExample(StudentExamTimetableExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(StudentExamTimetable record);

    int insertSelective(StudentExamTimetable record);

    List<StudentExamTimetable> selectByExample(StudentExamTimetableExample example);

    StudentExamTimetable selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") StudentExamTimetable record, @Param("example") StudentExamTimetableExample example);

    int updateByExample(@Param("record") StudentExamTimetable record, @Param("example") StudentExamTimetableExample example);

    int updateByPrimaryKeySelective(StudentExamTimetable record);

    int updateByPrimaryKey(StudentExamTimetable record);
}