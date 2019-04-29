package cn.hkxj.platform.mapper;

import cn.hkxj.platform.pojo.Openid;
import cn.hkxj.platform.pojo.SubscribeOpenid;
import cn.hkxj.platform.pojo.SubscribeOpenidExample;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface SubscribeOpenidMapper {
    int countByExample(SubscribeOpenidExample example);

    int deleteByExample(SubscribeOpenidExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(SubscribeOpenid record);

    int insertSelective(SubscribeOpenid record);

    List<SubscribeOpenid> selectByExample(SubscribeOpenidExample example);

    SubscribeOpenid selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") SubscribeOpenid record, @Param("example") SubscribeOpenidExample example);

    int updateByExample(@Param("record") SubscribeOpenid record, @Param("example") SubscribeOpenidExample example);

    int updateByPrimaryKeySelective(SubscribeOpenid record);

    int updateByPrimaryKey(SubscribeOpenid record);

    List<String> getSubscribeOpenids(@Param("openids") List<Openid> openids);

    List<String> getAllSubscribeOpenids();

    List<String> getOnlySubcribeOpenids();
}