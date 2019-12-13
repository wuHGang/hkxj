package cn.hkxj.platform.dao;

import cn.hkxj.platform.config.wechat.MiniProgramProperties;
import cn.hkxj.platform.config.wechat.WechatMpPlusProperties;
import cn.hkxj.platform.mapper.ScheduleTaskMapper;
import cn.hkxj.platform.pojo.ScheduleTask;
import cn.hkxj.platform.pojo.constant.SubscribeScene;
import cn.hkxj.platform.pojo.example.ScheduleTaskExample;
import cn.hkxj.platform.pojo.example.SubscribeOpenidExample;
import cn.hkxj.platform.service.ScheduleTaskService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ScheduleTaskDao {

    @Resource
    private ScheduleTaskMapper scheduleTaskMapper;
    @Resource
    private WechatMpPlusProperties wechatMpPlusProperties;
    @Resource
    private MiniProgramProperties miniProgramProperties;


    public List<ScheduleTask> selectByPojo(ScheduleTask scheduleTask){
        ScheduleTaskExample example = new ScheduleTaskExample();
        ScheduleTaskExample.Criteria criteria = example.createCriteria();

        if(scheduleTask.getAppid() != null){
            criteria.andAppidEqualTo(scheduleTask.getAppid());
        }
        if(scheduleTask.getScene() != null){
            criteria.andSceneEqualTo(scheduleTask.getScene());
        }

        if(scheduleTask.getIsSubscribe() != null){
            criteria.andIsSubscribeEqualTo(scheduleTask.getIsSubscribe());
        }

        if(scheduleTask.getOpenid() != null){
            criteria.andOpenidEqualTo(scheduleTask.getOpenid());
        }

        return scheduleTaskMapper.selectByExample(example);

    }

    public List<ScheduleTask> getPlusSubscribeTask(SubscribeScene subscribeScene){
        ScheduleTask pojo = new ScheduleTask()
                .setAppid(wechatMpPlusProperties.getAppId())
                .setIsSubscribe(ScheduleTaskService.FUNCTION_ENABLE)
                .setScene(Integer.parseInt(subscribeScene.getScene()));

        return selectByPojo(pojo);
    }

    public List<ScheduleTask> getMiniProgramSubscribeTask(SubscribeScene subscribeScene){
        ScheduleTask pojo = new ScheduleTask()
                .setAppid(miniProgramProperties.getAppId())
                .setIsSubscribe(ScheduleTaskService.FUNCTION_ENABLE)
                .setScene(Integer.parseInt(subscribeScene.getScene()));

        return selectByPojo(pojo);
    }

    public ScheduleTask selectByOpenid(String openid, String appId, SubscribeScene subscribeScene){
        ScheduleTask pojo = new ScheduleTask()
                .setAppid(appId)
                .setIsSubscribe(ScheduleTaskService.FUNCTION_ENABLE)
                .setOpenid(openid)
                .setScene(Integer.parseInt(subscribeScene.getScene()));

        return selectByPojo(pojo).stream().findFirst().orElse(null);
    }


    public void insertSelective(ScheduleTask scheduleTask){
        scheduleTaskMapper.insertSelective(scheduleTask);
    }

}
