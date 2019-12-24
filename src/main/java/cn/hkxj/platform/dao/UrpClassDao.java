package cn.hkxj.platform.dao;

import cn.hkxj.platform.mapper.UrpClassMapper;
import cn.hkxj.platform.pojo.UrpClass;
import cn.hkxj.platform.pojo.example.UrpClassExample;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UrpClassDao {
    @Resource
    private UrpClassMapper urpClassMapper;


    public UrpClass selectByClassNumber(String classNumber){
        UrpClassExample example = new UrpClassExample();
        example.createCriteria()
                .andClassNumEqualTo(classNumber);

        return urpClassMapper.selectByExample(example).stream().findFirst().orElse(null);

    }


    public UrpClass selectByName(String name){
        UrpClassExample example = new UrpClassExample();
        example.createCriteria()
                .andClassNameEqualTo(name);

        return urpClassMapper.selectByExample(example).stream().findFirst().orElse(null);
    }

}
