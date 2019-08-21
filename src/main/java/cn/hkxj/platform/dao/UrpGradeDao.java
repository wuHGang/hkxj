package cn.hkxj.platform.dao;

import cn.hkxj.platform.mapper.UrpGradeMapper;
import cn.hkxj.platform.pojo.*;
import cn.hkxj.platform.pojo.example.UrpGradeExample;
import cn.hkxj.platform.service.NewUrpSpiderService;
import cn.hkxj.platform.spider.newmodel.CurrentGrade;
import cn.hkxj.platform.spider.newmodel.UrpCourseForSpider;
import cn.hkxj.platform.spider.newmodel.UrpGeneralGradeForSpider;
import cn.hkxj.platform.spider.newmodel.UrpGradeForSpider;
import com.google.common.collect.Lists;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author Yuki
 * @date 2019/8/14 22:10
 */
@Service
public class UrpGradeDao {

    @Resource
    private UrpGradeMapper urpGradeMapper;

    public int getTotalCount(){
        return urpGradeMapper.countByExample(new UrpGradeExample());
    }

    public void insertUrpGrade(UrpGrade urpGrade){
        urpGradeMapper.insertSelective(urpGrade);
    }

    public UrpGrade getUrpGradeByExamIdAndAccount(int examId, int account){
        UrpGradeExample example = new UrpGradeExample();
        example.createCriteria()
                .andAccountEqualTo(account)
                .andExamIdEqualTo(examId);
        return urpGradeMapper.selectByExample(example).stream().findFirst().orElse(null);
    }

    public List<UrpGrade> getCurrentTermAllUrpGrade(int account, List<Integer> examIds){
        UrpGradeExample example = new UrpGradeExample();
        example.createCriteria()
                .andAccountEqualTo(account)
                .andExamIdIn(examIds);
        return urpGradeMapper.selectByExample(example);
    }

    public boolean ifExistGrade(int examId, int account){
        UrpGradeExample example = new UrpGradeExample();
        example.createCriteria()
                .andExamIdEqualTo(examId)
                .andAccountEqualTo(account);
        return urpGradeMapper.countByExample(example) == 1;
    }

    /**
     * 将从爬虫爬取到的数据判断是需要存入数据库还是从数据库中进行获取
     * @param convertFromSpider 从爬虫爬取的信息中转化的成绩实体
     * @return 成绩实体
     */
    public UrpGrade saveOrGetUrpGradeFromDb(UrpGrade convertFromSpider){
        int urpExamId = convertFromSpider.getExamId();
        int account = convertFromSpider.getAccount();
        //如果不存在相应的成绩，就先插入再返回
        if(!ifExistGrade(urpExamId, account)){
            insertUrpGrade(convertFromSpider);
        }
        //如果存在从数据库中获取后返回
        convertFromSpider = getUrpGradeByExamIdAndAccount(urpExamId, account);
        return convertFromSpider;
    }
}
