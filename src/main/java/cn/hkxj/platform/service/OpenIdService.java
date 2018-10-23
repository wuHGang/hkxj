package cn.hkxj.platform.service;

import cn.hkxj.platform.mapper.OpenidMapper;
import cn.hkxj.platform.pojo.Openid;
import cn.hkxj.platform.pojo.OpenidExample;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author junrong.chen
 * @date 2018/10/22
 */
@Service("openIdService")
public class OpenIdService {
	@Resource
	private OpenidMapper openidMapper;

	public boolean openidIsExist(String openid) {
		return getOpenid(openid).size() == 1;
	}

	public List<Openid> getOpenid(String openid) {
		OpenidExample openidExample = new OpenidExample();
		openidExample
				.createCriteria()
				.andOpenidEqualTo(openid);
		return openidMapper.selectByExample(openidExample);
	}



}
