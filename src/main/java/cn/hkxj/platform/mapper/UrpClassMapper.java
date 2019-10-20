package cn.hkxj.platform.mapper;

import cn.hkxj.platform.pojo.UrpClass;
import cn.hkxj.platform.pojo.example.UrpClassExample;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface UrpClassMapper {
    int countByExample(UrpClassExample example);

    int deleteByExample(UrpClassExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(UrpClass record);

    int insertSelective(UrpClass record);

    List<UrpClass> selectByExample(UrpClassExample example);

    UrpClass selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") UrpClass record, @Param("example") UrpClassExample example);

    int updateByExample(@Param("record") UrpClass record, @Param("example") UrpClassExample example);

    int updateByPrimaryKeySelective(UrpClass record);

    int updateByPrimaryKey(UrpClass record);
}