package cn.hkxj.platform.service.openid;

import cn.hkxj.platform.mapper.WechatOpenIdMapper;
import cn.hkxj.platform.pojo.OpenId;
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
        OpenId user = new OpenId();
        user.setOpenId(openId);
        Wechatuser wechatuser = openIdMapper.getStudentByOpenId(user.getOpenId());
        return wechatuser;
    }

}
