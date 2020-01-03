package cn.hkxj.platform.mapper;

import cn.hkxj.platform.pojo.ExamTimetable;
import cn.hkxj.platform.pojo.ExamTimetableExample;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface ExamTimetableMapper {
    int countByExample(ExamTimetableExample example);

    int deleteByExample(ExamTimetableExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ExamTimetable record);

    int insertSelective(ExamTimetable record);

    List<ExamTimetable> selectByExample(ExamTimetableExample example);

    ExamTimetable selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ExamTimetable record, @Param("example") ExamTimetableExample example);

    int updateByExample(@Param("record") ExamTimetable record, @Param("example") ExamTimetableExample example);

    int updateByPrimaryKeySelective(ExamTimetable record);

    int updateByPrimaryKey(ExamTimetable record);
}