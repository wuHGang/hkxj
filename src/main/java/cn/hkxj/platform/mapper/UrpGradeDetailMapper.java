package cn.hkxj.platform.mapper;

import java.util.List;

import cn.hkxj.platform.pojo.UrpGradeDetail;
import cn.hkxj.platform.pojo.example.UrpGradeDetailExample;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface UrpGradeDetailMapper {
    int countByExample(UrpGradeDetailExample example);

    int deleteByExample(UrpGradeDetailExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(UrpGradeDetail record);

    int insertSelective(UrpGradeDetail record);

    List<UrpGradeDetail> selectByExample(UrpGradeDetailExample example);

    UrpGradeDetail selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") UrpGradeDetail record, @Param("example") UrpGradeDetailExample example);

    int updateByExample(@Param("record") UrpGradeDetail record, @Param("example") UrpGradeDetailExample example);

    int updateByPrimaryKeySelective(UrpGradeDetail record);

    int updateByPrimaryKey(UrpGradeDetail record);
}