package cn.hkxj.platform.mapper;

import cn.hkxj.platform.pojo.Wechatuser;
import cn.hkxj.platform.pojo.WechatuserExample;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface WechatuserMapper {
    int countByExample(WechatuserExample example);

    int deleteByExample(WechatuserExample example);

    int deleteByPrimaryKey(Integer account);

    int insert(Wechatuser record);

    int insertSelective(Wechatuser record);

    List<Wechatuser> selectByExample(WechatuserExample example);

    Wechatuser selectByPrimaryKey(Integer account);

    int updateByExampleSelective(@Param("record") Wechatuser record, @Param("example") WechatuserExample example);

    int updateByExample(@Param("record") Wechatuser record, @Param("example") WechatuserExample example);

    int updateByPrimaryKeySelective(Wechatuser record);

    int updateByPrimaryKey(Wechatuser record);
}