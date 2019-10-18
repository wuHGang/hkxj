package cn.hkxj.platform.service;

import cn.hkxj.platform.config.wechat.WechatMpPlusProperties;
import cn.hkxj.platform.mapper.OpenidMapper;
import cn.hkxj.platform.mapper.OpenidPlusMapper;
import cn.hkxj.platform.mapper.StudentMapper;
import cn.hkxj.platform.pojo.Classes;
import cn.hkxj.platform.pojo.ScheduleTask;
import cn.hkxj.platform.pojo.example.OpenidExample;
import cn.hkxj.platform.pojo.example.StudentExample;
import cn.hkxj.platform.pojo.Student;
import cn.hkxj.platform.pojo.wechat.Openid;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author junrong.chen
 * @date 2018/11/28
 */
@Slf4j
@Service
public class ExamTimeTableService {
    @Resource
    private ScheduleTaskService scheduleTaskService;
    @Resource
    private WechatMpPlusProperties wechatMpPlusProperties;
    @Resource
    private OpenidMapper openidMapper;
    @Resource
    private OpenidPlusMapper openidPlusMapper;
    @Resource
    private StudentMapper studentMapper;

    /**
     * 获取classes和scheduleTask的映射关系
     *
     * @param studentList      学生信息列表
     * @param openidList       openid实体列表
     * @param scheduleTaskList 定时任务实体列表
     * @return 映射关系
     */
    private Map<Classes, List<ScheduleTask>> getClassesMappingMap(List<Student> studentList, List<Openid> openidList, List<ScheduleTask> scheduleTaskList) {
        //如果当前班级没有订阅的学生，则返回null
        if (Objects.isNull(studentList)) {
            return null;
        }
        Map<String, Student> openidToStudentMapping = getOpenIdMap(studentList, openidList);
        Map<Classes, List<ScheduleTask>> classesMappingMap = Maps.newHashMap();
        openidToStudentMapping.forEach((openid, student) -> scheduleTaskList.forEach(task -> {
            if (Objects.equals(openid, task.getOpenid())) {
                List<ScheduleTask> temp = classesMappingMap.get(student.getClasses());
                if (Objects.isNull(temp)) {
                    temp = Lists.newArrayList();
                }
                temp.add(task);
                classesMappingMap.put(student.getClasses(), temp);
            }
        }));
        return classesMappingMap;
    }


    /**
     * 获取学生实体和openid的映射关系
     *
     * @param students 学生信息列表
     * @param openIds  openid列表
     * @return 映射关系
     */
    private Map<String, Student> getOpenIdMap(List<Student> students, List<Openid> openIds) {
        Map<String, Student> openIdMap = new HashMap<>(16);
        //学生openid如果和openid列表的一项相同，则放入到openIdMap中
        students.forEach(student ->
                openIds.stream().filter(openid -> Objects.equals(student.getAccount(), openid.getAccount()))
                        .forEach(openid -> openIdMap.put(openid.getOpenid(), student))
        );
        return openIdMap;
    }

    /**
     * 根据openid列表获取相应的openid实体
     *
     * @param openIds openid列表
     * @param appid   appid
     * @return openid实体列表
     */
    private List<Openid> getOpenIdList(List<String> openIds, String appid) {
        //如果openid为空，直接返回null
        if (CollectionUtils.isEmpty(openIds)) {
            return null;
        }
        OpenidExample openidExample = new OpenidExample();
        openidExample.createCriteria().andOpenidIn(openIds);
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
    private List<Student> getAllStudentsByOpenidList(List<Openid> openIds) {
        if (CollectionUtils.isEmpty(openIds)) {
            return null;
        }
        List<Integer> accounts = openIds.stream().map(Openid::getAccount).collect(Collectors.toList());
        StudentExample studentExample = new StudentExample();
        studentExample.createCriteria().andAccountIn(accounts);
        return studentMapper.selectByExample(studentExample);
    }
}
