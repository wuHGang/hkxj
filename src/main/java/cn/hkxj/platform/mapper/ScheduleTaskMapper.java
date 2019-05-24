package cn.hkxj.platform.mapper;

import java.util.List;

import cn.hkxj.platform.pojo.ScheduleTask;
import cn.hkxj.platform.pojo.example.ScheduleTaskExample;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface ScheduleTaskMapper {
    int countByExample(ScheduleTaskExample example);

    int deleteByExample(ScheduleTaskExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ScheduleTask record);

    int insertSelective(ScheduleTask record);

    List<ScheduleTask> selectByExample(ScheduleTaskExample example);

    ScheduleTask selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ScheduleTask record, @Param("example") ScheduleTaskExample example);

    int updateByExample(@Param("record") ScheduleTask record, @Param("example") ScheduleTaskExample example);

    int updateByPrimaryKeySelective(ScheduleTask record);

    int updateByPrimaryKey(ScheduleTask record);
}