package cn.hkxj.platform.dao;

import cn.hkxj.platform.mapper.MajorMapper;
import cn.hkxj.platform.pojo.Major;
import cn.hkxj.platform.pojo.example.MajorExample;
import cn.hkxj.platform.spider.newmodel.UrpGeneralGradeForSpider;
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

    /**
     * 将从爬虫爬取到的数据判断是需要存入数据库还是从数据库中进行获取
     * @param convertFromSpider 从爬虫爬取的信息中转化的专业实体
     * @param majorNumber 专业号，和爬虫结果中的zyh属性对应
     * @return 专业实体
     */
    public Major saveOrGetMajorFromDb(Major convertFromSpider, String majorNumber){
        //如果不存在相应的专业，就先插入再返回
        if(!ifExistMajor(majorNumber)){
           insertMajor(convertFromSpider);
        } else {
            //如果存在从数据库中获取后返回
            convertFromSpider = getMajorByZyh(majorNumber);
        }
        return convertFromSpider;
    }
}
