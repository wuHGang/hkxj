package cn.hkxj.platform.dao;

import cn.hkxj.platform.mapper.WechatBindRecordMapper;
import cn.hkxj.platform.pojo.WechatBindRecord;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class WechatBindRecordDao {
    @Resource
    private WechatBindRecordMapper wechatBindRecordMapper;


    public void insertSelective(WechatBindRecord wechatBindRecord){
        wechatBindRecordMapper.insertSelective(wechatBindRecord);
    }

}
