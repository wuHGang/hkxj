package cn.hkxj.platform.dao;

import cn.hkxj.platform.mapper.MiniProgramOpenidMapper;
import cn.hkxj.platform.pojo.MiniProgramOpenid;
import cn.hkxj.platform.pojo.MiniProgramOpenidExample;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class MiniProgramOpenIdDao {
    @Resource
    private MiniProgramOpenidMapper miniProgramOpenidMapper;


    public void insertSelective(MiniProgramOpenid miniProgramOpenid){
        miniProgramOpenidMapper.insertSelective(miniProgramOpenid);
    }

    public List<MiniProgramOpenid> selectByPojo(MiniProgramOpenid miniProgramOpenid){
        MiniProgramOpenidExample example = new MiniProgramOpenidExample();
        MiniProgramOpenidExample.Criteria criteria = example.createCriteria();

        if(miniProgramOpenid.getOpenid() != null){
            criteria.andOpenidEqualTo(miniProgramOpenid.getOpenid());
        }


        return miniProgramOpenidMapper.selectByExample(example);
    }

    public boolean isOpenidExist(String openid){
        return selectByPojo(new MiniProgramOpenid().setOpenid(openid)).size() == 1;
    }

    public MiniProgramOpenid selectByOpenId(String openid){
        return selectByPojo(new MiniProgramOpenid().setOpenid(openid)).get(0);
    }

    public void updateByPrimaryKey(MiniProgramOpenid miniProgramOpenid){
        miniProgramOpenidMapper.updateByPrimaryKeySelective(miniProgramOpenid);
    }
}
