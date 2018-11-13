package cn.hkxj.platform.mapper;

import cn.hkxj.platform.pojo.Openid;
import cn.hkxj.platform.pojo.OpenidExample;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author junrong.chen
 */
@Mapper
@Repository
public interface OpenidMapper {
    int countByExample(OpenidExample example);

    int deleteByExample(OpenidExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Openid record);

    int insertSelective(Openid record);

    List<Openid> selectByExample(OpenidExample example);

    Openid selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Openid record, @Param("example") OpenidExample example);

    int updateByExample(@Param("record") Openid record, @Param("example") OpenidExample example);

    int updateByPrimaryKeySelective(Openid record);

    int updateByPrimaryKey(Openid record);

    List<Openid> getOpenIdsByAccount(@Param("accounts") List<Integer> accounts);
}