package cn.hkxj.platform.service;

import cn.hkxj.platform.config.wechat.WechatMpPlusProperties;
import cn.hkxj.platform.config.wechat.WechatMpProProperties;
import cn.hkxj.platform.mapper.OpenidMapper;
import cn.hkxj.platform.mapper.OpenidPlusMapper;
import cn.hkxj.platform.mapper.StudentMapper;
import cn.hkxj.platform.pojo.wechat.Openid;
import cn.hkxj.platform.pojo.example.OpenidExample;
import cn.hkxj.platform.pojo.Student;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Objects;

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
	@Resource
	private OpenidPlusMapper openidPlusMapper;
	@Resource
	private WechatMpPlusProperties wechatMpPlusProperties;

	public boolean openidIsExist(String openid, String appid) {
		return getOpenid(openid, appid).size() == 1;
	}

	public boolean openidIsBind(String openid, String appid) {
		if(isPlus(appid)){
			return openidPlusMapper.isOpenidBind(openid)==1;
		}
		return openidMapper.isOpenidBind(openid)==1;
	}

	public List<Openid> getOpenid(String openid, String appid) {
		OpenidExample openidExample = new OpenidExample();
		openidExample
				.createCriteria()
				.andOpenidEqualTo(openid);
		if(isPlus(appid)){
			return openidPlusMapper.selectByExample(openidExample);
		}
		return openidMapper.selectByExample(openidExample);
	}

	public Student getStudentByOpenId(String openid, String appid){
        List<Openid> openidList = getOpenid(openid, appid);
        if (openidList.size() == 0){
            throw new IllegalArgumentException("user not bind openid: "+openid + " appid: " + appid);
        }
        Integer account = openidList.get(0).getAccount();
        return studentMapper.selectByAccount(account);
    }

    public void openIdUnbind(String openid, String appid){
		if(isPlus(appid)){
			openidPlusMapper.openidUnbind(openid);
		} else {
			openidMapper.openidUnbind(openid);
		}
	}

	public void openIdUnbindAllPlatform(Openid openid, String appid){
        OpenidExample openidExample = new OpenidExample();
        openidExample.createCriteria().andAccountEqualTo(openid.getAccount());
	    openid.setIsBind(false);
	    if(isPlus(appid)){
	        openidPlusMapper.openidUnbind(openid.getOpenid());
	        Openid proOpenid = openidMapper.selectByExample(openidExample).get(0);
	        if(Objects.nonNull(proOpenid)){
	            proOpenid.setIsBind(false);
	            openidMapper.updateByPrimaryKey(proOpenid);
            }
        } else {
	        openidMapper.openidUnbind(openid.getOpenid());
            Openid plusOpenid = openidPlusMapper.selectByExample(openidExample).get(0);
            if(Objects.nonNull(plusOpenid)){
                plusOpenid.setIsBind(false);
                openidMapper.updateByPrimaryKey(plusOpenid);
            }
        }
    }

    private boolean isPlus(String appid){
        return Objects.equals(wechatMpPlusProperties.getAppId(), appid);
    }
}
