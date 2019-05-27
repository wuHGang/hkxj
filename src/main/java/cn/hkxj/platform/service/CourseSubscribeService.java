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

    public Map<String, Set<CourseGroupMsg>> getCoursesSubscribeForCurrentDay() {
        Map<String, List<ScheduleTask>> scheduleTaskMap =
                scheduleTaskService.getSubscribeData(1005, ScheduleTaskService.FUNCTION_ENABLE);
        Map<String, Set<CourseGroupMsg>> courseGroupMsgMap = Maps.newHashMap();
        scheduleTaskMap.forEach((appid, scheduleTasks) -> {
            List<String> openids = scheduleTasks.stream().map(ScheduleTask::getOpenid).collect(Collectors.toList());
            List<Openid> openidObjects = getOpenIdList(openids, appid);
            List<Student> students = getAllStudentsByOpenids(openidObjects);
            Map<Classes, List<ScheduleTask>> classesMapping = getClassesMappingMap(students, openidObjects, scheduleTasks);
            Set<CourseGroupMsg> courseGroupMsgSet = getCourseGroupMsgs(classesMapping);
            courseGroupMsgMap.put(appid, courseGroupMsgSet);
        });
        return courseGroupMsgMap;
    }

    private Map<String, Student> getOpenIdMap(List<Student> students, List<Openid> openIds) {
        Map<String, Student> openIdMap = new HashMap<>(16);
        students.forEach(student ->
                openIds.stream().filter(openid -> Objects.equals(student.getAccount(), openid.getAccount()))
                        .forEach(openid -> openIdMap.put(openid.getOpenid(), student))
        );
        return openIdMap;
    }

    private Set<CourseGroupMsg> getCourseGroupMsgs(Map<Classes, List<ScheduleTask>> classesMappingMap) {
        if (classesMappingMap == null) {
            return null;
        }
        Set<CourseGroupMsg> courseGroupMsgs = Sets.newHashSet();
        classesMappingMap.forEach((classes, scheduleTasks) -> {
            CourseGroupMsg courseGroupMsg = new CourseGroupMsg();
            List<CourseTimeTable> courseTimeTables = getCourseTimeTables(classes);
            courseGroupMsg.setClasses(classes);
            courseGroupMsg.setScheduleTasks(scheduleTasks);
            courseGroupMsg.setCourseTimeTables(courseTimeTables);
            courseGroupMsgs.add(courseGroupMsg);
        });
        return courseGroupMsgs;
    }

    private List<CourseTimeTable> getCourseTimeTables(Classes classes) {
        List<Integer> timeTableIds = getClassTimeTables(classes);
        CourseTimeTableExample example = new CourseTimeTableExample();
        int year = DateUtils.getCurrentYear();
        int week = DateUtils.getCurrentWeek();
        int day = DateUtils.getCurrentDay();
        example.createCriteria()
                .andIdIn(timeTableIds);
        List<CourseTimeTable> courseTimeTables = courseTimeTableMapper.selectByExample(example);
        return courseTimeTables.stream().filter(timetable -> isValidCourseTimeTable(timetable, year, week, day)).collect(Collectors.toList());
    }

    private List<Integer> getClassTimeTables(Classes classes) {
        return classTimeTableMapper.getTimeTableIdByClassId(classes.getId());
    }

    private Map<Classes, List<ScheduleTask>> getClassesMappingMap(List<Student> students, List<Openid> openids, List<ScheduleTask> scheduleTasks) {
        if (Objects.isNull(students)) {
            return null;
        }
        Map<String, Student> openidToStudentMapping = getOpenIdMap(students, openids);
        Map<Classes, List<ScheduleTask>> classesMappingMap = Maps.newHashMap();
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

    private List<Openid> getOpenIdList(List<String> openIds, String appid) {
        if(CollectionUtils.isEmpty(openIds)){
            return null;
        }
        OpenidExample openidExample = new OpenidExample();
        openidExample.createCriteria()
                .andOpenidIn(openIds);
        if (Objects.equals(wechatMpPlusProperties.getAppId(), appid)) {
            return openidPlusMapper.selectByExample(openidExample);
        }
        return openidMapper.selectByExample(openidExample);
    }

    private List<Student> getAllStudentsByOpenids(List<Openid> openIds) {
        if (CollectionUtils.isEmpty(openIds)) {
            return null;
        }
        List<Integer> accounts = openIds.stream().map(Openid::getAccount).collect(Collectors.toList());
        StudentExample studentExample = new StudentExample();
        studentExample.createCriteria()
                .andAccountIn(accounts);
        return studentMapper.selectByExample(studentExample);
    }

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

    private boolean isValidCourseTimeTable(CourseTimeTable courseTimeTable, int year, int week, int day) {
        return courseTimeTable.getYear() == year
                && courseTimeTable.getTerm() == 2
                && courseTimeTable.getWeek() == day
                && courseTimeTable.getStart() <= week
                && courseTimeTable.getEnd() >= week;
    }

}
