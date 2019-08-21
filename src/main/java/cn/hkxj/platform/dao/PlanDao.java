package cn.hkxj.platform.dao;

import cn.hkxj.platform.mapper.PlanMapper;
import cn.hkxj.platform.pojo.Plan;
import cn.hkxj.platform.pojo.example.PlanExample;
import cn.hkxj.platform.spider.newmodel.UrpGeneralGradeForSpider;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author Yuki
 * @date 2019/8/16 10:55
 */
@Service
public class PlanDao {

    @Resource
    private PlanMapper planMapper;

    public void insertPlan(Plan plan){
        planMapper.insertSelective(plan);
    }

    public Plan getPlanByPlanNumber(String planNumber){
        PlanExample example = new PlanExample();
        example.createCriteria()
                .andPlanNumberEqualTo(planNumber);
        return planMapper.selectByExample(example).stream().findFirst().orElse(null);
    }

    public boolean ifExistPlan(String planNumber){
        PlanExample example = new PlanExample();
        example.createCriteria()
                .andPlanNumberEqualTo(planNumber);
        return planMapper.countByExample(example) == 1;
    }

    /**
     * 将从爬虫爬取到的数据判断是需要存入数据库还是从数据库中进行获取
     * @param convertFromSpider 从爬虫爬取的信息中转化的计划实体
     * @param planNumber 项目编号
     * @return 教学计划实体
     */
    public Plan saveOrGetPlanFromDb(Plan convertFromSpider, String planNumber){
        //如果不存在相应的教学计划，就先插入再返回
        if(!ifExistPlan(planNumber)){
            insertPlan(convertFromSpider);
        } else {
            //如果存在从数据库中获取后返回
            convertFromSpider = getPlanByPlanNumber(planNumber);
        }
        return convertFromSpider;
    }
}
