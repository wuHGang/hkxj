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

    public static final Byte SEND_SUCCESS = 1;
    public static final Byte SEND_FAIL = 0;
    public static final Byte FUNCTION_ENABLE = 1;
    public static final Byte FUNCTION_DISABLE = 0;

    @Resource
    private ScheduleTaskMapper scheduleTaskMapper;
    @Resource
    private WechatMpPlusProperties wechatMpPlusProperties;
    @Resource
    private WechatMpProProperties wechatMpProProperties;

    public int addScheduleTaskRecord(String appid, String openid, String scene) {
        ScheduleTask scheduleTask = new ScheduleTask();
        scheduleTask.setOpenid(openid);
        scheduleTask.setAppid(appid);
        scheduleTask.setScene(Integer.parseInt(scene));
        scheduleTask.setSendStatus(SEND_SUCCESS);
        scheduleTask.setIsSubscribe(FUNCTION_ENABLE);
        return scheduleTaskMapper.insertSelective(scheduleTask);
    }

    public Map<String, List<ScheduleTask>> getSubscribeData(int scene, Byte is_enable) {
        if (is_enable == null) {
            is_enable = FUNCTION_ENABLE;
        }
        Map<String, List<ScheduleTask>> resultMap = Maps.newHashMap();
        ScheduleTaskExample scheduleTaskExample = new ScheduleTaskExample();
        scheduleTaskExample.createCriteria()
                .andIsSubscribeEqualTo(is_enable)
                .andSceneEqualTo(scene);
        String plusAppid = wechatMpPlusProperties.getAppId();
        String proAppid = wechatMpProProperties.getAppId();
        List<ScheduleTask> scheduleTasks = scheduleTaskMapper.selectByExample(scheduleTaskExample);
        List<ScheduleTask> plusScheduleTasks = scheduleTasks.stream()
                .filter(task -> Objects.equals(task.getAppid(), plusAppid)).collect(Collectors.toList());
        List<ScheduleTask> proScheduleTasks = scheduleTasks.stream()
                .filter(task -> Objects.equals(task.getAppid(), proAppid)).collect(Collectors.toList());
        resultMap.put(plusAppid, plusScheduleTasks);
        resultMap.put(proAppid, proScheduleTasks);
        return resultMap;
    }

    public int updateSendStatus(ScheduleTask scheduleTask, Byte send_status) {
        scheduleTask.setSendStatus(send_status);
        scheduleTask.setTaskCount(scheduleTask.getTaskCount() + 1);
        return scheduleTaskMapper.updateByPrimaryKey(scheduleTask);
    }

    public int updateSubscribeStatus(ScheduleTask scheduleTask, Byte subscribe_status) {
        scheduleTask.setIsSubscribe(subscribe_status);
        scheduleTask.setTaskCount(scheduleTask.getTaskCount() + 1);
        return scheduleTaskMapper.updateByPrimaryKey(scheduleTask);
    }

    public int updateSubscribeStatus(String appid, String openid, String scene, Byte subscribe_status) {
        ScheduleTaskExample example = new ScheduleTaskExample();
        example.createCriteria()
                .andAppidEqualTo(appid)
                .andOpenidEqualTo(openid)
                .andSceneEqualTo(Integer.parseInt(scene));
        ScheduleTask scheduleTask = new ScheduleTask();
        scheduleTask.setIsSubscribe(subscribe_status);
        return scheduleTaskMapper.updateByExampleSelective(scheduleTask, example);
    }

    private  List<Map<String, ScheduleTask>> getMappingList(List<ScheduleTask> scheduleTasks, String appid){
        List<Map<String, ScheduleTask>> mappingList = new ArrayList<>();
        scheduleTasks.stream().filter(task -> Objects.equals(task.getAppid(), appid))
                .forEach(task -> {
                    Map<String, ScheduleTask> map = new HashMap<>();
                    map.put(task.getAppid(), task);
                    mappingList.add(map);
                });
        return mappingList;
    }

    public boolean isExistSubscribeRecode(String appid, String openid, String scene){
        ScheduleTask scheduleTask = new ScheduleTask();
        scheduleTask.setOpenid(openid);
        scheduleTask.setScene(Integer.parseInt(scene));
        scheduleTask.setAppid(appid);
        return scheduleTaskMapper.isExistSubscribeRecode(scheduleTask);
    }
}
