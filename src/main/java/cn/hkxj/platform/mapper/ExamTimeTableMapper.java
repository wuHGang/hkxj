package cn.hkxj.platform.mapper;

import cn.hkxj.platform.pojo.ExamTimeTable;
import cn.hkxj.platform.pojo.ExamTimeTableExample;
import java.util.List;

public interface ExamTimeTableMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ExamTimeTable record);

    int insertSelective(ExamTimeTable record);

    List<ExamTimeTable> selectByExample(ExamTimeTableExample example);

    ExamTimeTable selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ExamTimeTable record);

    int updateByPrimaryKey(ExamTimeTable record);
}