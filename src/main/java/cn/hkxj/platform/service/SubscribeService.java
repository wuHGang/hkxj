package cn.hkxj.platform.service;

import cn.hkxj.platform.dao.ScheduleTaskDao;
import cn.hkxj.platform.mapper.OpenidMapper;
import cn.hkxj.platform.mapper.SubscribeOpenidMapper;
import cn.hkxj.platform.pojo.ScheduleTask;
import cn.hkxj.platform.pojo.Student;
import cn.hkxj.platform.pojo.constant.SubscribeScene;
import cn.hkxj.platform.pojo.wechat.SubscribeOpenid;
import cn.hkxj.platform.pojo.example.SubscribeOpenidExample;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author Yuki
 * @date 2018/11/10 21:54
 */
@Slf4j
@Service
@AllArgsConstructor
public class SubscribeService {

    private SubscribeOpenidMapper subscribeOpenidMapper;
    private ScheduleTaskDao scheduleTaskDao;
    private OpenidMapper openidMapper;
    private OpenIdService openIdService;
    private NewGradeSearchService newGradeSearchService;

    public boolean isSubscribe(String openid){
        SubscribeOpenidExample example = new SubscribeOpenidExample();
        example.createCriteria()
                .andOpenidEqualTo(openid);
        return subscribeOpenidMapper.selectByExample(example).size() == 1;
    }

    public void insertOneSubOpenid(String openid, String scene, String appid){
        if(Objects.isNull(openidMapper.isOpenidExist(openid))){
            log.warn("openid that try to subscribe do not exists in table openid --openid {}", openid);
            return;
        }
        if(isSubscribe(openid)){
            log.info("openid is already subscribed --openid {}", openid);
            return;
        }
        SubscribeOpenid subscribeOpenid =  new SubscribeOpenid();
        subscribeOpenid.setOpenid(openid);
        subscribeOpenid.setSubType(Integer.parseInt(scene));
        subscribeOpenid.setGmtCreate(new Date());
        subscribeOpenid.setIsSend((byte) 1);

        if(subscribeOpenidMapper.insert(subscribeOpenid) == 0){
            log.error("insert record into subscribe_openid, insert failed --content{}", subscribeOpenid.toString());
            return;
        }
        log.info("insert record into subscribe_openid --content {}", subscribeOpenid.toString());
    }

    public void updateCourseSubscribeMsgState(String openid, Byte sub_type){
        if(!isSubscribe(openid)){
            log.warn("update msgState fail reason: openid {} do not exist", openid);
            return;
        }
        SubscribeOpenid subscribeOpenid = new SubscribeOpenid();
        subscribeOpenid.setIsSend(sub_type);
        subscribeOpenid.setGmtCreate(new Date());
        SubscribeOpenidExample example = new SubscribeOpenidExample();
        example.createCriteria()
                .andOpenidEqualTo(openid);
        if(subscribeOpenidMapper.updateByExampleSelective(subscribeOpenid, example) == 0){
            log.error("update msgState fail --openid {} is_send {}", openid, sub_type);
            return;
        }
        log.info("update msgState success --openid {} is_send {}", openid, sub_type);
    }


    public Set<Student> getGradeUpdateSubscribeStudent(){
        List<ScheduleTask> scheduleTaskList = scheduleTaskDao.selectByPojo(new ScheduleTask().setScene(Integer.parseInt(SubscribeScene.GRADE_AUTO_UPDATE.getScene())));

        return scheduleTaskList.stream()
                .map(x-> openIdService.getStudentByOpenId(x.getOpenid(), x.getAppid()))
                .filter(Objects::nonNull)
                .filter(Student::getIsCorrect)
                .filter(x-> !newGradeSearchService.isCurrentFinishFetch(x.getAccount().toString()))
                .collect(Collectors.toSet());


    }

}
