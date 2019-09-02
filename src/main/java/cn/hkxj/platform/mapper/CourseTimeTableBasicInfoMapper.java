package cn.hkxj.platform.mapper;

import java.util.List;

import cn.hkxj.platform.pojo.CourseTimeTableBasicInfo;
import cn.hkxj.platform.pojo.example.CourseTimeTableBasicInfoExample;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface CourseTimeTableBasicInfoMapper {
    int countByExample(CourseTimeTableBasicInfoExample example);

    int deleteByExample(CourseTimeTableBasicInfoExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(CourseTimeTableBasicInfo record);

    int insertSelective(CourseTimeTableBasicInfo record);

    List<CourseTimeTableBasicInfo> selectByExample(CourseTimeTableBasicInfoExample example);

    CourseTimeTableBasicInfo selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") CourseTimeTableBasicInfo record, @Param("example") CourseTimeTableBasicInfoExample example);

    int updateByExample(@Param("record") CourseTimeTableBasicInfo record, @Param("example") CourseTimeTableBasicInfoExample example);

    int updateByPrimaryKeySelective(CourseTimeTableBasicInfo record);

    int updateByPrimaryKey(CourseTimeTableBasicInfo record);
}