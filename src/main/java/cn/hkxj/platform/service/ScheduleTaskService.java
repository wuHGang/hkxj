package cn.hkxj.platform.service;

import cn.hkxj.platform.config.wechat.WechatMpPlusProperties;
import cn.hkxj.platform.config.wechat.WechatMpProProperties;
import cn.hkxj.platform.mapper.ScheduleTaskMapper;
import cn.hkxj.platform.pojo.ScheduleTask;
import cn.hkxj.platform.pojo.example.ScheduleTaskExample;
import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author Yuki
 * @date 2019/5/9 8:28
 */
@Slf4j
@Service
public class ScheduleTaskService {

    public static final byte SEND_SUCCESS = 1;
    public static final byte SEND_FAIL = 0;
    public static final byte FUNCTION_ENABLE = 1;
    public static final byte FUNCTION_DISABLE = 0;

    @Resource
    private ScheduleTaskMapper scheduleTaskMapper;
    @Resource
    private WechatMpPlusProperties wechatMpPlusProperties;
    @Resource
    private WechatMpProProperties wechatMpProProperties;

    /**
     * 添加一条新的ScheduleTask的记录
     * @param appid appid
     * @param openid 用户的openid
     * @param scene 订阅场景值
     * @return 受影响的行数
     */
    public int addScheduleTaskRecord(String appid, String openid, String scene) {
        //发送状态和订阅状态初始值都是1，表示可用
        ScheduleTask scheduleTask = new ScheduleTask();
        scheduleTask.setOpenid(openid);
        scheduleTask.setAppid(appid);
        scheduleTask.setScene(Integer.parseInt(scene));
        scheduleTask.setSendStatus(SEND_SUCCESS);
        scheduleTask.setIsSubscribe(FUNCTION_ENABLE);
        return scheduleTaskMapper.insertSelective(scheduleTask);
    }

    /**
     * 获取一个appid和定时任务列表的映射，返回的是订阅状态可用的用户
     * @param scene 订阅场景值
     * @return 映射关系
     */
    public Map<String, List<ScheduleTask>> getSubscribeData(int scene) {
       return getSubscribeData(scene, true);
    }

    /**
     * 获取一个appid和定时任务列表的映射
     * isEnable = true，对应的订阅状态是可用。
     * isEnable = false，对应的订阅状态是不可用
     * @param scene 订阅场景值
     * @param isEnable 是否订阅
     * @return 映射关系
     */
    public Map<String, List<ScheduleTask>> getSubscribeData(int scene, boolean isEnable) {
        String plusAppid = wechatMpPlusProperties.getAppId();
        String proAppid = wechatMpProProperties.getAppId();
        Map<String, List<ScheduleTask>> resultMap = Maps.newHashMap();
        //先根据isEnable的值来获取订阅用户的数据
        List<ScheduleTask> scheduleTasks = getScheduleTasks(scene, isEnable ? FUNCTION_ENABLE : FUNCTION_DISABLE);
        //获取所有appid为plus的appid的用户列表
        List<ScheduleTask> plusScheduleTasks = scheduleTasks.stream()
                .filter(task -> Objects.equals(task.getAppid(), plusAppid)).collect(Collectors.toList());
        //获取所有appid为pro的appid的用户列表
        List<ScheduleTask> proScheduleTasks = scheduleTasks.stream()
                .filter(task -> Objects.equals(task.getAppid(), proAppid)).collect(Collectors.toList());
        //建立映射关系
        resultMap.put(plusAppid, plusScheduleTasks);
        resultMap.put(proAppid, proScheduleTasks);
        return resultMap;
    }

    /**
     * 更新发送状态。默认调用后任务计数会加一
     * @param scheduleTask 对应的定时任务的实体
     * @param sendStatus 发送状态
     * @return 受影响行数
     */
    public int updateSendStatus(ScheduleTask scheduleTask, byte sendStatus) {
        scheduleTask.setSendStatus(sendStatus);
        scheduleTask.setTaskCount(scheduleTask.getTaskCount() + 1);
        return scheduleTaskMapper.updateByPrimaryKey(scheduleTask);
    }

    /**
     * 更新订阅状态，需要实体id有具体的值
     * @param scheduleTask 对应的定时任务的实体
     * @param subscribeStatus 订阅状态
     * @return 受影响行数
     */
    public int updateSubscribeStatus(ScheduleTask scheduleTask, Byte subscribeStatus) {
        scheduleTask.setIsSubscribe(subscribeStatus);
        return scheduleTaskMapper.updateByPrimaryKey(scheduleTask);
    }

    /**
     * 更新订阅状态
     * @param appid appid
     * @param openid 用户的openid
     * @param scene 订阅场景值
     * @param subscribeStatus 订阅状态
     * @return 受影响行数
     */
    public int updateSubscribeStatus(String appid, String openid, String scene, byte subscribeStatus) {
        ScheduleTaskExample example = new ScheduleTaskExample();
        //设置查找相应记录的条件
        example.createCriteria()
                .andAppidEqualTo(appid)
                .andOpenidEqualTo(openid)
                .andSceneEqualTo(Integer.parseInt(scene));
        ScheduleTask scheduleTask = new ScheduleTask();
        //设置要更新的列
        scheduleTask.setIsSubscribe(subscribeStatus);
        return scheduleTaskMapper.updateByExampleSelective(scheduleTask, example);
    }

    /**
     * 是否存在相应的记录
     * @param appid appid
     * @param openid 用户的openid
     * @param scene 订阅场景值
     * @return true为存在，false为不存在
     */
    public boolean isExistSubscribeRecode(String appid, String openid, String scene){
        ScheduleTask scheduleTask = new ScheduleTask();
        scheduleTask.setOpenid(openid);
        scheduleTask.setScene(Integer.parseInt(scene));
        scheduleTask.setAppid(appid);
        return scheduleTaskMapper.isExistSubscribeRecode(scheduleTask);
    }

    /**
     * 根据订阅场景值和订阅状态来获取相应的用户列表
     * @param scene 订阅场景值
     * @param enable 订阅状态
     * @return 用户列表
     */
    private List<ScheduleTask> getScheduleTasks(int scene, byte enable){
        ScheduleTaskExample scheduleTaskExample = new ScheduleTaskExample();
        scheduleTaskExample.createCriteria()
                .andIsSubscribeEqualTo(enable)
                .andSceneEqualTo(scene);
        return scheduleTaskMapper.selectByExample(scheduleTaskExample);
    }
}
