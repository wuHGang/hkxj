package cn.hkxj.platform.service;

import cn.hkxj.platform.config.wechat.WechatMpPlusProperties;
import cn.hkxj.platform.mapper.*;
import cn.hkxj.platform.pojo.*;
import cn.hkxj.platform.pojo.constant.SubscribeScene;
import cn.hkxj.platform.pojo.dto.CourseTimeTableDetailDto;
import cn.hkxj.platform.pojo.example.OpenidExample;
import cn.hkxj.platform.pojo.example.StudentExample;
import cn.hkxj.platform.pojo.wechat.CourseGroupMsg;
import cn.hkxj.platform.pojo.wechat.CourseSubscriptionMessage;
import cn.hkxj.platform.pojo.wechat.Openid;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author Yuki
 * @date 2018/12/5 19:26
 */
@Slf4j
@Service
public class CourseSubscribeService {

    /**
     * 第一节
     */
    public final static int FIRST_SECTION = 1;
    /**
     * 第二节
     */
    public final static int SECOND_SECTION = 3;
    /**
     * 第三节
     */
    public final static int THIRD_SECTION = 5;
    /**
     * 第四节
     */
    public final static int FOURTH_SECTION = 7;
    /**
     * 第五节
     */
    public final static int FIFTH_SECTION = 9;

    @Resource
    private StudentMapper studentMapper;
    @Resource
    private OpenidMapper openidMapper;
    @Resource
    private ScheduleTaskService scheduleTaskService;
    @Resource
    private WechatMpPlusProperties wechatMpPlusProperties;
    @Resource
    private OpenidPlusMapper openidPlusMapper;
    @Resource
    private CourseTimeTableService courseTimeTableService;


    public Map<String, Set<CourseSubscriptionMessage>> getSubscriptionMessages(int condition){
        //获取appid和scheduleTask的映射关系
        Map<String, List<ScheduleTask>> scheduleTaskMap = scheduleTaskService.getSubscribeData(Integer.parseInt(SubscribeScene.COURSE_PUSH.getScene()));
        Map<String, Set<CourseSubscriptionMessage>> subscriptionMessageMap = Maps.newHashMap();
        scheduleTaskMap.forEach((appid, scheduleTasks) -> {
            List<String> openids = scheduleTasks.stream().map(ScheduleTask::getOpenid).collect(Collectors.toList());
            //获取openid和学生的映射关系
            Map<String, Student> studentMapping = getOpenIdMap(openids, appid);
            //根据这个获取相应的课程信息
            Set<CourseSubscriptionMessage> subscriptionMessages = getCourseSubscriptionMessages(scheduleTasks, studentMapping, condition);
            subscriptionMessageMap.put(appid, subscriptionMessages);
        });
        return subscriptionMessageMap;
    }

    /**
     * 获取订阅消息列表
     * @param scheduleTasks 订阅任务列表
     * @param studentMapping openid和学生实体的映射
     * @param section 节数
     * @return 返回订阅消息列表
     */
    private Set<CourseSubscriptionMessage> getCourseSubscriptionMessages(List<ScheduleTask> scheduleTasks,
                                                                         Map<String, Student> studentMapping, int section){
        Set<CourseSubscriptionMessage> subscriptionMessages = Sets.newHashSet();
        for (ScheduleTask task : scheduleTasks) {
            for (Map.Entry<String, Student> entry : studentMapping.entrySet()) {
                if (Objects.equals(entry.getKey(), task.getOpenid())) {
                    Student student = entry.getValue();
                    CourseTimeTableDetailDto detailDto = getCourseTimeTableDetailDtoForSection(student.getAccount(), section);
                    CourseSubscriptionMessage message = new CourseSubscriptionMessage();
                    message.setStudent(student);
                    message.setDetailDto(detailDto);
                    message.setTask(task);
                    message.setSection(section);
                    subscriptionMessages.add(message);
                }
            }
        }
        return subscriptionMessages;
    }

    private Map<String, Student> getOpenIdMap(List<String> openIds, String appid){
        //获得所有openid的实体
        List<Openid> openidObjects = getOpenIdList(openIds, appid);
        if(Objects.isNull(openidObjects)) { return null; }
        //根据openid实体的信息，找到对应的学生信息实体
        List<Student> students = getAllStudentsByOpenids(openidObjects);
        if(Objects.isNull(students)) { return null; }
        Map<String, Student> openIdMap = new HashMap<>(16);
        //学生openid如果和openid列表的一项相同，则放入到openIdMap中
        students.forEach(student ->
                openidObjects.stream().filter(openid -> Objects.equals(student.getAccount(), openid.getAccount()))
                        .forEach(openid -> openIdMap.put(openid.getOpenid(), student))
        );
        return openIdMap;
    }

    /**
     * 这个方法会返回班级对应的课表
     *
     * @param account 学号
     * @param section 节数
     * @return 如果从爬虫和数据库都无法获取到数据就会返回Null
     * 正常返回会返回一个正常的list
     */
    private CourseTimeTableDetailDto getCourseTimeTableDetailDtoForSection(int account, int section) {
        return getCourseTimeTablesSection(account, section);
    }

    /**
     * 根据班级信息获取当天的课程时间表
     * @param account 学号
     * @return 课表时间表实体列表
     */
    private CourseTimeTableDetailDto getCourseTimeTablesSection(int account, int section) {
        List<CourseTimeTableDetailDto> detailDtos = courseTimeTableService.getAppointSectionCourseTimeTableDetailDto(account, section);
        //因为查询的是当前节的课程，只会有一节，所以直接get(0)
        return detailDtos.size() > 0 ? detailDtos.get(0) : null;
    }

    /**
     * 根据openid列表获取相应的openid实体
     *
     * @param openIds openid列表
     * @param appid   appid
     * @return openid实体列表
     */
    private List<Openid> getOpenIdList(List<String> openIds, String appid) {
        //如果openid列表为空，直接返回null
        if (CollectionUtils.isEmpty(openIds)) {
            return null;
        }
        OpenidExample openidExample = new OpenidExample();
        openidExample.createCriteria()
                .andOpenidIn(openIds);
        //根据appid来判断查询哪张表
        if (Objects.equals(wechatMpPlusProperties.getAppId(), appid)) {
            return openidPlusMapper.selectByExample(openidExample);
        }
        return openidMapper.selectByExample(openidExample);
    }

    /**
     * 通过openid实体列表来获取相应的学生实体
     *
     * @param openIds openid实体列表
     * @return 学生实体列表
     */
    private List<Student> getAllStudentsByOpenids(List<Openid> openIds) {
        //如果openid实体列表为空，直接返回null
        if (CollectionUtils.isEmpty(openIds)) {
            return null;
        }
        List<Integer> accounts = openIds.stream().map(Openid::getAccount).collect(Collectors.toList());
        StudentExample studentExample = new StudentExample();
        //这里返回的学生实体需要密码是正确的
        studentExample.createCriteria()
                .andAccountIn(accounts)
                .andIsCorrectEqualTo(true);
        return studentMapper.selectByExample(studentExample);
    }

}
