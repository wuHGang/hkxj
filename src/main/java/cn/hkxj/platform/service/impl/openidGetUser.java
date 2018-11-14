package cn.hkxj.platform.service.impl;

import cn.hkxj.platform.mapper.WechatOpenIdMapper;
import cn.hkxj.platform.pojo.Openid;
import cn.hkxj.platform.pojo.Wechatuser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class openidGetUser {


	@Autowired
	private WechatOpenIdMapper openIdMapper;

	/**
	 * 根据openId查找对应的用户的Wechatuser
	 *
	 * @param openId
	 * @return list
	 */
	public Wechatuser getStudentByOpenId(String openId) throws IOException {
		Openid user = new Openid();
		user.setOpenid(openId);
		Wechatuser wechatuser = openIdMapper.getStudentByOpenId(user.getOpenid());
		return wechatuser;
	}

}
