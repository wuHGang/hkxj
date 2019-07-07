package cn.hkxj.platform.service;

import cn.hkxj.platform.config.wechat.WechatMpPlusProperties;
import cn.hkxj.platform.exceptions.PasswordUncorrectException;
import cn.hkxj.platform.mapper.ExamTimeTableMapper;
import cn.hkxj.platform.mapper.OpenidMapper;
import cn.hkxj.platform.mapper.OpenidPlusMapper;
import cn.hkxj.platform.mapper.StudentMapper;
import cn.hkxj.platform.pojo.Classes;
import cn.hkxj.platform.pojo.ScheduleTask;
import cn.hkxj.platform.pojo.constant.SubscribeScene;
import cn.hkxj.platform.pojo.example.OpenidExample;
import cn.hkxj.platform.pojo.example.StudentExample;
import cn.hkxj.platform.pojo.timetable.CourseTimeTable;
import cn.hkxj.platform.pojo.timetable.ExamTimeTable;
import cn.hkxj.platform.pojo.example.ExamTimeTableExample;
import cn.hkxj.platform.pojo.Student;
import cn.hkxj.platform.pojo.wechat.CourseGroupMsg;
import cn.hkxj.platform.pojo.wechat.ExamGroupMsg;
import cn.hkxj.platform.pojo.wechat.Openid;
import cn.hkxj.platform.utils.DateUtils;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

/**
 * @author junrong.chen
 * @date 2018/11/28
 */
@Slf4j
@Service
public class ExamTimeTableService {
    @Resource
    private AppSpiderService appSpiderService;
    @Resource
    private ExamTimeTableMapper examTimeTableMapper;
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
     * 获取一个appid和CourseGroupMsg的映射关系
     * @return 映射关系
     */
    public Map<String, Set<ExamGroupMsg>> getExamSubscribeForCurrentDay() {
        //获取appid和scheduleTask的映射关系
        Map<String, List<ScheduleTask>> scheduleTaskMap = scheduleTaskService.getSubscribeData(Integer.parseInt(SubscribeScene.EXAM_SUBSCRUBE.getScene()));
        Map<String, Set<ExamGroupMsg>> examGroupMsgMap = Maps.newHashMap();
        //组装映射关系
        scheduleTaskMap.forEach((appid, scheduleTasks) -> {
            //先获取所有的用户的openid的集合
            List<String> openids = scheduleTasks.stream().map(ScheduleTask::getOpenid).collect(Collectors.toList());
            //获得所有openid的实体
            List<Openid> openidObjects = getOpenIdList(openids, appid);
            //根据openid实体的信息，找到对应的学生信息实体
            List<Student> students = getAllStudentsByOpenids(openidObjects);
            //组装classes和scheduleTask的映射关系
            Map<Classes, List<ScheduleTask>> classesMapping = getClassesMappingMap(students, openidObjects, scheduleTasks);
            //组装appid和ExamGroupMsg的映射关系
            Set<ExamGroupMsg> courseGroupMsgSet = getExamGroupMsgs(classesMapping);
            examGroupMsgMap.put(appid, courseGroupMsgSet);
        });
        return examGroupMsgMap;
    }

    /**
     * 通过classes和scheduleTask的映射关系来创建ExamGroupMsg的集合
     * @param classesMappingMap classes和scheduleTask的映射关系
     * @return CourseGroupMsg的集合
     */
    private Set<ExamGroupMsg> getExamGroupMsgs(Map<Classes, List<ScheduleTask>> classesMappingMap) {
        if (classesMappingMap == null)
            return null;
        Set<ExamGroupMsg> examGroupMsgs = Sets.newHashSet();
        //每一个CourseGroupMsg都对应着一个班级的课程和订阅者的信息
        classesMappingMap.forEach((classes, scheduleTasks) -> {
            ExamGroupMsg examGroupMsg = new ExamGroupMsg();
            //根据班级来获取明天的考试信息
            List<ExamTimeTable> examTimeTables = getExamTimeTableTomorrow(classes);
            //设置班级信息
            examGroupMsg.setClasses(classes);
            //设置考试信息
            examGroupMsg.setExamTimeTables(examTimeTables);
            //设置关联的定时任务信息
            examGroupMsg.setScheduleTasks(scheduleTasks);
            examGroupMsgs.add(examGroupMsg);
        });
        return examGroupMsgs;
    }

    /**
     * 根据班级信息获取明天的考试时间表
     * @param classes 班级信息
     * @return 课表时间表实体列表
     */
    private List<ExamTimeTable> getExamTimeTableTomorrow(Classes classes) {
        int year = DateUtils.getCurrentYear();
        int week = DateUtils.getCurrentWeek();
        int day = DateUtils.getCurrentDay();
        //根据班级id获取所有关联的考试时间表的id
        List<ExamTimeTable> examTimeTableTomorrow = examTimeTableMapper.selectExamTimeTableTomorrowByClassId(classes.getId());
        return examTimeTableTomorrow;
    }

    /**
     * 获取classes和scheduleTask的映射关系
     * @param students 学生信息列表
     * @param openids openid实体列表
     * @param scheduleTasks 定时任务实体列表
     * @return 映射关系
     */
    private Map<Classes, List<ScheduleTask>> getClassesMappingMap(List<Student> students, List<Openid> openids, List<ScheduleTask> scheduleTasks) {
        //如果当前班级没有订阅的学生，则返回null
        if (Objects.isNull(students))
            return null;
        Map<String, Student> openidToStudentMapping = getOpenIdMap(students, openids);
        Map<Classes, List<ScheduleTask>> classesMappingMap = Maps.newHashMap();
        openidToStudentMapping.forEach((openid, student) -> scheduleTasks.forEach(task -> {
            if (Objects.equals(openid, task.getOpenid())) {
                List<ScheduleTask> temp = classesMappingMap.get(student.getClasses());
                if (Objects.isNull(temp))
                    temp = Lists.newArrayList();
                temp.add(task);
                classesMappingMap.put(student.getClasses(), temp);
            }
        }));
        return classesMappingMap;
    }


    /**
     * 获取学生实体和openid的映射关系
     * @param students 学生信息列表
     * @param openIds openid列表
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
         * @param openIds openid列表
         * @param appid appid
         * @return openid实体列表
         */
    private List<Openid> getOpenIdList(List<String> openIds, String appid) {
        //如果openid为空，直接返回null
        if(CollectionUtils.isEmpty(openIds))
            return null;
        OpenidExample openidExample = new OpenidExample();
        openidExample.createCriteria().andOpenidIn(openIds);
        if(Objects.equals(wechatMpPlusProperties.getAppId(), appid))
            return openidPlusMapper.selectByExample(openidExample);
        return openidMapper.selectByExample(openidExample);
    }

    /**
     * 通过openid实体列表来获取相应的学生实体
     * @param openIds openid实体列表
     * @return 学生实体列表
     */
    private List<Student> getAllStudentsByOpenids(List<Openid> openIds) {
        if (CollectionUtils.isEmpty(openIds))
            return null;
        List<Integer> accounts = openIds.stream().map(Openid::getAccount).collect(Collectors.toList());
        StudentExample studentExample = new StudentExample();
        studentExample.createCriteria().andAccountIn(accounts);
        return studentMapper.selectByExample(studentExample);
    }


    public List<ExamTimeTable> getExamTimeTableByStudent(Student student) {
        int id = student.getClasses().getId();
        List<Integer> timeTableIdList = examTimeTableMapper.selectExamIdIdByClassId(id);
        if(timeTableIdList.size() == 0){
            List<ExamTimeTable> examList;
            try {
                examList = appSpiderService.getExamByAccount(student.getAccount());
            } catch (PasswordUncorrectException e) {
                return new ArrayList<>();
            }
            if(CollectionUtils.isEmpty(examList)){
                return examList;
            }

            ExecutorService executorService = Executors.newSingleThreadExecutor();
            List<ExamTimeTable> finalExamList = examList;
            executorService.execute(() -> saveExamTimeTask(id, finalExamList));
            return examList;
        }
        else {
            ExamTimeTableExample examTimeTableExample = new ExamTimeTableExample();
            examTimeTableExample.createCriteria()
                    .andIdIn(timeTableIdList);
            return examTimeTableMapper.selectByExample(examTimeTableExample);
        }
    }

    /**
     * 保存好考试时间数据之后，再保存班级和
     * @param classId
     * @param examTimeTableList
     */
    private void saveExamTimeTask(int classId, List<ExamTimeTable> examTimeTableList){
        log.info("save exam timetable task run， class id{}", classId);
        ArrayList<Integer> examIdList = new ArrayList<>();
        for (ExamTimeTable examTimeTable : examTimeTableList) {
            examTimeTableMapper.insert(examTimeTable);
            examIdList.add(examTimeTable.getId());
        }

        for (Integer examId : examIdList) {
            examTimeTableMapper.insertClassAndExamRelation(classId, examId);
        }


    }
}
