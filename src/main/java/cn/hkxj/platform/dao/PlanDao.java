package cn.hkxj.platform.dao;

import cn.hkxj.platform.mapper.PlanMapper;
import cn.hkxj.platform.pojo.Plan;
import cn.hkxj.platform.pojo.example.PlanExample;
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
}
