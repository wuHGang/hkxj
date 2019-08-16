package cn.hkxj.platform.mapper;

import java.util.List;

import cn.hkxj.platform.pojo.UrpGrade;
import cn.hkxj.platform.pojo.example.UrpGradeExample;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface UrpGradeMapper {
    int countByExample(UrpGradeExample example);

    int deleteByExample(UrpGradeExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(UrpGrade record);

    int insertSelective(UrpGrade record);

    List<UrpGrade> selectByExample(UrpGradeExample example);

    UrpGrade selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") UrpGrade record, @Param("example") UrpGradeExample example);

    int updateByExample(@Param("record") UrpGrade record, @Param("example") UrpGradeExample example);

    int updateByPrimaryKeySelective(UrpGrade record);

    int updateByPrimaryKey(UrpGrade record);
}