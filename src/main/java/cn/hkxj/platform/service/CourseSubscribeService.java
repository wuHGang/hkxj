package cn.hkxj.platform.service;

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
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author Yuki
 * @date 2018/12/5 19:26
 */
@Slf4j
@Service
public class CourseSubscribeService {

    private StudentMapper studentMapper;
    private ClassesMapper classesMapper;
    private CourseTimeTableMapper courseTimeTableMapper;
    private OpenidMapper openidMapper;
    private SubscribeOpenidMapper subscribeOpenidMapper;

    @Autowired
    private CourseSubscribeService(StudentMapper studentMapper, ClassesMapper classesMapper, CourseTimeTableMapper courseTimeTableMapper,
                                   OpenidMapper openidMapper, SubscribeOpenidMapper subscribeOpenidMapper){
        this.studentMapper = studentMapper;
        this.classesMapper = classesMapper;
        this.courseTimeTableMapper = courseTimeTableMapper;
        this.openidMapper = openidMapper;
        this.subscribeOpenidMapper = subscribeOpenidMapper;
    }

    public List<CourseGroupMsg> getCoursesSubscribeForCurrentDay() {

        if(!isValidDay()) {
            log.error("calling function on an illegal date");
            return null;
        }
        List<CourseTimeTable> courseTimeTables = getCourseTimeTables();
        //获取所有有课班级的信息
        List<Classes> classesList = getClassList(courseTimeTables);
        //装填要群发的消息的班级名称和课程信息
        List<CourseGroupMsg> courseGroupMsgs = getCourseGroupMsgs(courseTimeTables, classesList);
        //和学生还有openId的数据
        Map<String, Student> openIdMap = getOpenIdMap();
        if(Objects.isNull(openIdMap)){ return null; }
        //将每一个班级对应的openid放进courseGroupMsgs中
        setIdsIntoCourseGroupMsgs(courseGroupMsgs, openIdMap);

        return courseGroupMsgs;
    }

    /**
     *
     * @param courseTimeTables 课程时间
     * @param classesList 班级列表
     * @return 课程推送信息
     */
    private List<CourseGroupMsg> getCourseGroupMsgs(List<CourseTimeTable> courseTimeTables, List<Classes> classesList){
        List<CourseGroupMsg> courseGroupMsgs = new ArrayList<>(600);
        classesList.forEach(classes -> {
            CourseGroupMsg msg = new CourseGroupMsg();
            List<CourseTimeTable> targetList = new ArrayList<>();
            classes.getCourseTimeTableIds().forEach(timetableId ->
                    courseTimeTables.stream().filter(courseTimeTable -> Objects.equals(courseTimeTable.getId(), timetableId))
                            .forEach(targetList::add)
            );
            msg.setClasses(classes);
            msg.setCourseTimeTables(targetList);
            courseGroupMsgs.add(msg);
        });
        return courseGroupMsgs;
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

    /**
     * openIdMap中学生的对应的openid一一对应，将相同班级的学生的openId放进courseGroupMsgs
     * @param courseGroupMsgs 课程提醒
     * @param openIdMap 学生和openId一一对应的Map
     */
    private void setIdsIntoCourseGroupMsgs(List<CourseGroupMsg> courseGroupMsgs, Map<String, Student> openIdMap){
        courseGroupMsgs.forEach(courseGroupMsg -> {
            List<String> openIdList = new ArrayList<>(40);
            openIdMap.forEach((openid, student) -> {
                if(Objects.equals(student.getClasses().getId(), courseGroupMsg.getClasses().getId())){
                    openIdList.add(openid);
                }
            });
            courseGroupMsg.setOpenIds(openIdList);
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
