package cn.hkxj.platform.service;

import cn.hkxj.platform.config.wechat.WechatMpPlusProperties;
import cn.hkxj.platform.mapper.*;
import cn.hkxj.platform.pojo.*;
import cn.hkxj.platform.pojo.example.CourseTimeTableExample;
import cn.hkxj.platform.pojo.example.OpenidExample;
import cn.hkxj.platform.pojo.example.StudentExample;
import cn.hkxj.platform.pojo.timetable.ClassTimeTable;
import cn.hkxj.platform.pojo.timetable.CourseTimeTable;
import cn.hkxj.platform.pojo.wechat.CourseGroupMsg;
import cn.hkxj.platform.pojo.wechat.Openid;
import cn.hkxj.platform.utils.DateUtils;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    private ClassesMapper classesMapper;
    @Resource
    private CourseTimeTableMapper courseTimeTableMapper;
    @Resource
    private OpenidMapper openidMapper;
    @Resource
    private SubscribeOpenidMapper subscribeOpenidMapper;
    @Resource
    private ScheduleTaskService scheduleTaskService;
    @Resource
    private WechatMpPlusProperties wechatMpPlusProperties;
    @Resource
    private OpenidPlusMapper openidPlusMapper;


    public Map<String, Set<CourseGroupMsg>> getCoursesSubscribeForCurrentDay() {
        if(!isValidDay()) {
            log.error("calling function on an illegal date");
            return null;
        }
        List<CourseTimeTable> courseTimeTables = getCourseTimeTables();
        //获取所有有课班级的信息
        List<Classes> classesList = getClassList(courseTimeTables);
        //装填要群发的消息的班级名称和课程信息
        Map<Integer, CourseGroupMsg> courseGroupMsgMap = getCourseGroupMsgs(courseTimeTables, classesList);
        //和学生还有openId的数据
        Map<String, Student> openIdMap = getOpenIdMap();
        Map<String, List<Map<ScheduleTask, Student>>> scheduleTaskStudentMap = getStudentMappingMap();
//        if(Objects.isNull(openIdMap)){ return null; }
        //将每一个班级对应的openid放进courseGroupMsgs中
        return setTaskIntoCourseGroupMsgs(courseGroupMsgMap, scheduleTaskStudentMap);
    }

    /**
     *
     * @param courseTimeTables 课程时间
     * @param classesList 班级列表
     * @return 课程推送信息
     */
    private Map<Integer, CourseGroupMsg> getCourseGroupMsgs(List<CourseTimeTable> courseTimeTables, List<Classes> classesList){
//        List<CourseGroupMsg> courseGroupMsgs = new ArrayList<>(600);
        Map<Integer, CourseGroupMsg> courseGroupMsgMap = new HashMap<>(600);
        classesList.forEach(classes -> {
            CourseGroupMsg msg = new CourseGroupMsg();
            List<CourseTimeTable> targetList = new ArrayList<>();
            classes.getCourseTimeTableIds().forEach(timetableId ->
                    courseTimeTables.stream().filter(courseTimeTable -> Objects.equals(courseTimeTable.getId(), timetableId))
                            .forEach(targetList::add)
            );
            msg.setClasses(classes);
            msg.setCourseTimeTables(targetList);
//            courseGroupMsgs.add(msg);
            courseGroupMsgMap.put(classes.getId(), msg);
        });
        return courseGroupMsgMap;
    }

    private Map<String, List<Map<ScheduleTask, Student>>> getStudentMappingMap(){
        Map<String, List<ScheduleTask>> scheduleMap =
                scheduleTaskService.getSubscribeData(1005, ScheduleTaskService.FUNCTION_ENABLE);
        Map<String, List<Map<ScheduleTask, Student>>>  mappingMap = Maps.newHashMap();
        scheduleMap.forEach((appid, taskList) ->{
            if(taskList.size() == 0){
                mappingMap.put(appid, null);
            } else {
                List<String> openIds = taskList.stream().map(ScheduleTask::getOpenid).collect(Collectors.toList());
                List<Openid> openidList = getOpenIdList(openIds, appid);
                Map<Integer, Student> studentMap = getAllStudentByOpenids(openidList);
                List<Map<ScheduleTask, Student>> mappingList = Lists.newArrayList();
                taskList.forEach(task -> {
                    Map<ScheduleTask, Student> scheduleTaskStudentMap = Maps.newHashMap();
                    openidList.forEach(openid -> {
                        if(Objects.equals(task.getOpenid(), openid.getOpenid())){
                            scheduleTaskStudentMap.put(task, studentMap.get(openid.getAccount()));
                            mappingList.add(scheduleTaskStudentMap);
                        }
                    });
                });
                mappingMap.put(appid, mappingList);
            }
        });

        return mappingMap;
    }

    private List<Openid> getOpenIdList(List<String> openIds, String appid){
        OpenidExample openidExample = new OpenidExample();
        openidExample.createCriteria()
                .andOpenidIn(openIds);
        if(Objects.equals(wechatMpPlusProperties.getAppId(), appid)){
            return openidPlusMapper.selectByExample(openidExample);
        }
        return openidMapper.selectByExample(openidExample);
    }

    /**
     * 获取一个学生和所对应openId一一映射的Map
     * @return 学生和所对应openId一一映射的Map
     */
    private Map<String, Student> getOpenIdMap(){
        List<String> subscribeOpenids = subscribeOpenidMapper.getOnlySubcribeOpenids();
        if(Objects.isNull(subscribeOpenids) || subscribeOpenids.size() == 0){
            log.info("don't have data in subscribe_openid");
            return null;
        }
        List<Openid> openIds = getOpenIdBySubscribedOpneid(subscribeOpenids);
        List<Student> students = getAllStudentByAccounts(openIds);
        Map<String, Student> openIdMap = new HashMap<>(16);
        students.forEach(student ->
                openIds.stream().filter(openid -> Objects.equals(student.getAccount(), openid.getAccount()))
                        .forEach(openid -> openIdMap.put(openid.getOpenid(), student))
        );
        return openIdMap;
    }


    private Map<String, Set<CourseGroupMsg>> setTaskIntoCourseGroupMsgs(Map<Integer, CourseGroupMsg> courseGroupMsgMap, Map<String, List<Map<ScheduleTask, Student>>> mappingMap){
        Map<String, Set<CourseGroupMsg>> coursePushMap = Maps.newHashMap();
        mappingMap.forEach((appid, mappingList) -> {
            Set<CourseGroupMsg> courseGroupMsgs = Sets.newHashSet();
            if(Objects.isNull(mappingList)){
                coursePushMap.put(appid, courseGroupMsgs);
            } else {
                mappingList.stream().filter(taskMap -> !Objects.isNull(taskMap)).forEach(taskMap -> {
                    taskMap.forEach((task, student) -> {
                        CourseGroupMsg courseGroupMsg = courseGroupMsgMap.get(student.getClasses().getId());
                        Set<ScheduleTask> scheduleTasks = courseGroupMsg.getScheduleTasks();
                        if(scheduleTasks == null){
                            scheduleTasks = Sets.newHashSet();
                            courseGroupMsg.setScheduleTasks(scheduleTasks);
                        }
                        scheduleTasks.add(task);
                        courseGroupMsgs.add(courseGroupMsg);
                    });
                });
                coursePushMap.put(appid, courseGroupMsgs);
            }
        });
        return coursePushMap;
    }

    private void setIdsIntoCourseGroupMsgs(List<CourseGroupMsg> courseGroupMsgs, Map<String, Student> openIdMap){
        courseGroupMsgs.forEach(courseGroupMsg -> {
            List<String> openIdList = new ArrayList<>(40);
            openIdMap.forEach((openid, student) -> {
                if(Objects.equals(student.getClasses().getId(), courseGroupMsg.getClasses().getId())){
                    openIdList.add(openid);
                }
            });
//            courseGroupMsg.setOpenIds(openIdList);
        });
    }

    /**
     * 通过课程时间获取对应的班级信息
     * @param courseTimeTables 课程时间
     * @return 班级信息
     */
    private List<Classes> getClassList(List<CourseTimeTable> courseTimeTables){
        List<ClassTimeTable> classTimeTables = getClassTimeTables(courseTimeTables);
        List<Classes> classesList =
                classesMapper.getClassesByIds(classTimeTables.stream().map(ClassTimeTable::getClassId).collect(Collectors.toList()));
        classesList.forEach(classes -> {
            List<Integer> timetableIds = new ArrayList<>(20);
            classTimeTables.forEach(classTimeTable -> {
                if(Objects.equals(classTimeTable.getClassId(), classes.getId())){
                    timetableIds.add(classTimeTable.getTimetableId());
                }
            });
            classes.setCourseTimeTableIds(timetableIds);
        });
        return classesList;
    }

    /**
     * 获取课程时间和班级id之间的对应关系
     * @param courseTimeTables 课程时间
     * @return ClassTimeTable
     */
    private List<ClassTimeTable> getClassTimeTables(List<CourseTimeTable> courseTimeTables){
        List<Integer> courseTimeTableIds =
                courseTimeTables.stream().map(CourseTimeTable::getId).collect(Collectors.toList());
        //和班级相关的数据
        return classesMapper.getClassesByTimetableIds(courseTimeTableIds);
    }

    private List<Openid> getOpenIdBySubscribedOpneid(List<String> subscribeOpenids){
        OpenidExample openidExample = new OpenidExample();
        openidExample.createCriteria()
                .andOpenidIn(subscribeOpenids);
        return openidMapper.selectByExample(openidExample);
    }

    private List<Student> getAllStudentByAccounts(List<Openid> openIds){
        List<Integer> accounts = openIds.stream().map(Openid::getAccount).collect(Collectors.toList());
        StudentExample studentExample = new StudentExample();
        studentExample.createCriteria()
                .andAccountIn(accounts);
        return studentMapper.selectByExample(studentExample);

    }

    private Map<Integer, Student> getAllStudentByOpenids(List<Openid> openIds){
        if(openIds.size() == 0){
            return null;
        }
        List<Integer> accounts = openIds.stream().map(Openid::getAccount).collect(Collectors.toList());
        StudentExample studentExample = new StudentExample();
        studentExample.createCriteria()
                .andAccountIn(accounts);
        List<Student> students = studentMapper.selectByExample(studentExample);
        return students.stream().collect(Collectors.toMap(Student::getAccount, student -> student));
    }

    public List<CourseTimeTable> getCourseTimeTables(){
        int year = DateUtils.getCurrentYear();
        int week = DateUtils.getCurrentWeek();
        int day = DateUtils.getCurrentDay();
        log.info("get all courses of --year{} week{} day{}",year, week, day);
        CourseTimeTableExample example = new CourseTimeTableExample();
        example.createCriteria()
                .andYearEqualTo(year)
                .andStartLessThanOrEqualTo(week)
                .andEndGreaterThanOrEqualTo(week)
                .andTermEqualTo(2)
                .andWeekEqualTo(day);
        return courseTimeTableMapper.selectByExample(example);
    }



    private boolean isValidDay(){
        int day = DateUtils.getCurrentDay();
        return day >= 1 && day <= 5;
    }
}
