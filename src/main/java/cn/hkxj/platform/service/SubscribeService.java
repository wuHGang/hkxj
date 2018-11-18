package cn.hkxj.platform.service;

import cn.hkxj.platform.mapper.SubscribeOpenidMapper;
import cn.hkxj.platform.pojo.SubscribeOpenid;
import cn.hkxj.platform.pojo.SubscribeOpenidExample;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @author Yuki
 * @date 2018/11/10 21:54
 */
@Slf4j
@Service
@AllArgsConstructor
public class SubscribeService {

    private SubscribeOpenidMapper subscribeOpenidMapper;

    public boolean isSubscribe(String openid){
        SubscribeOpenidExample example = new SubscribeOpenidExample();
        example.createCriteria()
                .andOpenidEqualTo(openid);
        return subscribeOpenidMapper.selectByExample(example).size() == 1;
    }

    public void insertOneSubOpenid(String openid, String scene){
        SubscribeOpenid subscribeOpenid =  new SubscribeOpenid();
        subscribeOpenid.setOpenid(openid);
        subscribeOpenid.setSubType(Integer.parseInt(scene));
        subscribeOpenid.setGmtCreate(new Date());
        subscribeOpenid.setIsSend((byte) 1);

        log.info("insert record into subscribe_openid ，content {}", subscribeOpenid.toString());
        if(subscribeOpenidMapper.insert(subscribeOpenid) == 0){
            log.error("insert record into subscribe_openid, content{},insert failed", subscribeOpenid.toString());
        }
    }

    public void updateCourseSubscribeMsgState(String openid, Byte sub_type){
        SubscribeOpenid subscribeOpenid = new SubscribeOpenid();
        subscribeOpenid.setIsSend(sub_type);
        subscribeOpenid.setGmtCreate(new Date());
        SubscribeOpenidExample example = new SubscribeOpenidExample();
        example.createCriteria()
                .andOpenidEqualTo(openid);
        subscribeOpenidMapper.updateByExampleSelective(subscribeOpenid, example);
    }
}
