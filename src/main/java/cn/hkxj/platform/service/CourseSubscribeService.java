package cn.hkxj.platform.service;

import cn.hkxj.platform.config.wechat.WechatMpPlusProperties;
import cn.hkxj.platform.mapper.*;
import cn.hkxj.platform.pojo.*;
import cn.hkxj.platform.pojo.example.CourseTimeTableExample;
import cn.hkxj.platform.pojo.example.OpenidExample;
import cn.hkxj.platform.pojo.example.StudentExample;
import cn.hkxj.platform.pojo.timetable.CourseTimeTable;
import cn.hkxj.platform.pojo.wechat.CourseGroupMsg;
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
import java.util.stream.Collectors;

/**
 * @author Yuki
 * @date 2018/12/5 19:26
 */
@Slf4j
@Service
public class CourseSubscribeService {

    @Resource
    private StudentMapper studentMapper;
    @Resource
    private CourseTimeTableMapper courseTimeTableMapper;
    @Resource
    private OpenidMapper openidMapper;
    @Resource
    private ScheduleTaskService scheduleTaskService;
    @Resource
    private WechatMpPlusProperties wechatMpPlusProperties;
    @Resource
    private OpenidPlusMapper openidPlusMapper;
    @Resource
    private ClassTimeTableMapper classTimeTableMapper;

    /**
     * 获取一个appid和CourseGroupMsg的映射关系
     * @return 映射关系
     */
    public Map<String, Set<CourseGroupMsg>> getCoursesSubscribeForCurrentDay() {
        //获取appid和scheduleTask的映射关系
        Map<String, List<ScheduleTask>> scheduleTaskMap = scheduleTaskService.getSubscribeData(1005);
        Map<String, Set<CourseGroupMsg>> courseGroupMsgMap = Maps.newHashMap();
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
            //组装appid和CourseGroupMsg的映射关系
            Set<CourseGroupMsg> courseGroupMsgSet = getCourseGroupMsgs(classesMapping);
            courseGroupMsgMap.put(appid, courseGroupMsgSet);
        });
        return courseGroupMsgMap;
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
     * 通过classes和scheduleTask的映射关系来创建CourseGroupMsg的集合
     * @param classesMappingMap classes和scheduleTask的映射关系
     * @return CourseGroupMsg的集合
     */
    private Set<CourseGroupMsg> getCourseGroupMsgs(Map<Classes, List<ScheduleTask>> classesMappingMap) {
        if (classesMappingMap == null) {
            return null;
        }
        Set<CourseGroupMsg> courseGroupMsgs = Sets.newHashSet();
        //每一个CourseGroupMsg都对应着一个班级的课程和订阅者的信息
        classesMappingMap.forEach((classes, scheduleTasks) -> {
            CourseGroupMsg courseGroupMsg = new CourseGroupMsg();
            //根据班级来获取当天的课程信息
            List<CourseTimeTable> courseTimeTables = getCourseTimeTablesCurrentDay(classes);
            //设置班级信息
            courseGroupMsg.setClasses(classes);
            //设置关联的定时任务信息
            courseGroupMsg.setScheduleTasks(scheduleTasks);
            //设置课程信息
            courseGroupMsg.setCourseTimeTables(courseTimeTables);
            courseGroupMsgs.add(courseGroupMsg);
        });
        return courseGroupMsgs;
    }

    /**
     * 根据班级信息获取当天的课程时间表
     * @param classes 班级信息
     * @return 课表时间表实体列表
     */
    private List<CourseTimeTable> getCourseTimeTablesCurrentDay(Classes classes) {
        int year = DateUtils.getCurrentYear();
        int week = DateUtils.getCurrentWeek();
        int day = DateUtils.getCurrentDay();
        //根据班级id获取所有关联的课程时间表的id
        List<Integer> timeTableIds = getClassTimeTables(classes);
        CourseTimeTableExample example = new CourseTimeTableExample();
        example.createCriteria()
                .andIdIn(timeTableIds);
        //获取所有关联的课程时间表的实体
        List<CourseTimeTable> courseTimeTables = courseTimeTableMapper.selectByExample(example);
        //筛选出所有符合条件的课程时间表实体，并组成列表返回
        return courseTimeTables.stream().filter(timetable -> isValidCourseTimeTable(timetable, year, week, day)).collect(Collectors.toList());
    }

    /**
     * 根据班级id来获取关联的课程时间表的id
     * @param classes 班级信息
     * @return 课程时间表id列表
     */
    private List<Integer> getClassTimeTables(Classes classes) {
        return classTimeTableMapper.getTimeTableIdByClassId(classes.getId());
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
        if (Objects.isNull(students)) {
            return null;
        }
        //获取openid和student之前的对应关系
        Map<String, Student> openidToStudentMapping = getOpenIdMap(students, openids);
        Map<Classes, List<ScheduleTask>> classesMappingMap = Maps.newHashMap();
        //这里其实是一个双层循环
        openidToStudentMapping.forEach((openid, student) -> scheduleTasks.forEach(task -> {
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
     * 根据openid列表获取相应的openid实体
     * @param openIds openid列表
     * @param appid appid
     * @return openid实体列表
     */
    private List<Openid> getOpenIdList(List<String> openIds, String appid) {
        //如果openid列表为空，直接返回null
        if(CollectionUtils.isEmpty(openIds)){
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
        studentExample.createCriteria()
                .andAccountIn(accounts);
        return studentMapper.selectByExample(studentExample);
    }

    /**
     * 获取当天的课程时间表
     * @return 课程时间表列表
     */
    public List<CourseTimeTable> getCourseTimeTables() {
        int year = DateUtils.getCurrentYear();
        int week = DateUtils.getCurrentWeek();
        int day = DateUtils.getCurrentDay();
        log.info("get all courses of --year{} week{} day{}", year, week, day);
        CourseTimeTableExample example = new CourseTimeTableExample();
        example.createCriteria()
                .andYearEqualTo(year)
                .andStartLessThanOrEqualTo(week)
                .andEndGreaterThanOrEqualTo(week)
                .andTermEqualTo(2)
                .andWeekEqualTo(day);
        return courseTimeTableMapper.selectByExample(example);
    }

    /**
     * 是否是当天的课程时间表
     * @param courseTimeTable 课程时间表实体
     * @param year 年份
     * @param week 周数
     * @param day 星期
     * @return true为合法，false为不合法
     */
    private boolean isValidCourseTimeTable(CourseTimeTable courseTimeTable, int year, int week, int day) {
        return courseTimeTable.getYear() == year
                && courseTimeTable.getTerm() == 2
                && courseTimeTable.getWeek() == day
                && courseTimeTable.getStart() <= week
                && courseTimeTable.getEnd() >= week;
    }

}
