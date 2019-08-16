package cn.hkxj.platform.dao;

import cn.hkxj.platform.mapper.MajorMapper;
import cn.hkxj.platform.pojo.Major;
import cn.hkxj.platform.pojo.example.MajorExample;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author Yuki
 * @date 2019/8/16 10:57
 */
@Service
public class MajorDao {

    @Resource
    private MajorMapper majorMapper;

    public void insertMajor(Major major){
        majorMapper.insertSelective(major);
    }

    public Major getMajorByZyh(String zyh){
        MajorExample example = new MajorExample();
        example.createCriteria()
                .andProfessionalNumberEqualTo(zyh);
        return majorMapper.selectByExample(example).stream().findFirst().orElse(null);
    }

    public boolean ifExistMajor(String zyh){
        MajorExample example = new MajorExample();
        example.createCriteria()
                .andProfessionalNumberEqualTo(zyh);
        return majorMapper.countByExample(example) == 1;
    }
}
