package cn.hkxj.platform.mapper;

import cn.hkxj.platform.pojo.MiniProgramOpenid;
import cn.hkxj.platform.pojo.MiniProgramOpenidExample;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface MiniProgramOpenidMapper {
    int countByExample(MiniProgramOpenidExample example);

    int deleteByExample(MiniProgramOpenidExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(MiniProgramOpenid record);

    int insertSelective(MiniProgramOpenid record);

    List<MiniProgramOpenid> selectByExample(MiniProgramOpenidExample example);

    MiniProgramOpenid selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") MiniProgramOpenid record, @Param("example") MiniProgramOpenidExample example);

    int updateByExample(@Param("record") MiniProgramOpenid record, @Param("example") MiniProgramOpenidExample example);

    int updateByPrimaryKeySelective(MiniProgramOpenid record);

    int updateByPrimaryKey(MiniProgramOpenid record);
}