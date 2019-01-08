package cn.hkxj.platform.service;

import cn.hkxj.platform.mapper.OpenidMapper;
import cn.hkxj.platform.mapper.StudentMapper;
import cn.hkxj.platform.pojo.Openid;
import cn.hkxj.platform.pojo.OpenidExample;
import cn.hkxj.platform.pojo.Student;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author junrong.chen
 * @date 2018/10/22
 */
@Service("openIdService")
public class OpenIdService {
	@Resource
	private OpenidMapper openidMapper;
	@Resource
    private StudentMapper studentMapper;

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

	public Student getStudentByOpenId(String openid){
        List<Openid> openidList = getOpenid(openid);
        if (openidList.size() == 0){
            throw new IllegalArgumentException("user not bind openid: "+openid);
        }
        Integer account = openidList.get(0).getAccount();
        return studentMapper.selectByAccount(account);
    }

    public void openIdUnbind(String openid){
		openidMapper.openidUnbind(openid);
	}



}
