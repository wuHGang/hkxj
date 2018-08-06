package cn.hkxj.platform.mapper;

import cn.hkxj.platform.pojo.Wechatuser;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface WechatOpenIdMapper {
	Wechatuser getStudentByOpenId(String openId);

}
