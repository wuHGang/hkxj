package cn.hkxj.platform.mapper;

import cn.hkxj.platform.pojo.ExamTimeTable;
import cn.hkxj.platform.pojo.ExamTimeTableExample;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Mapper
public interface ExamTimeTableMapper {
    int deleteByPrimaryKey(Integer id);

    @Transactional
    int insert(ExamTimeTable record);

    int insertSelective(ExamTimeTable record);

    List<ExamTimeTable> selectByExample(ExamTimeTableExample example);

    ExamTimeTable selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ExamTimeTable record);

    int updateByPrimaryKey(ExamTimeTable record);

    List<Integer> selectExamIdIdByClassId(@Param("id") int id);

    void insertClassAndExamRelation(@Param("class_id") int classId, @Param("exam_timetable_id") int examId);
}