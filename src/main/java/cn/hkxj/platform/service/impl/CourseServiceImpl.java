package cn.hkxj.platform.service.impl;

import cn.hkxj.platform.mapper.ClassTimeTableMapper;
import cn.hkxj.platform.mapper.ClassesMapper;
import cn.hkxj.platform.mapper.CourseMapper;
import cn.hkxj.platform.mapper.CourseTimeTableMapper;
import cn.hkxj.platform.mapper.OpenidMapper;
import cn.hkxj.platform.mapper.StudentMapper;
import cn.hkxj.platform.mapper.SubscribeOpenidMapper;
import cn.hkxj.platform.pojo.Academy;
import cn.hkxj.platform.pojo.ClassTimeTable;
import cn.hkxj.platform.pojo.Classes;
import cn.hkxj.platform.pojo.ClassesExample;
import cn.hkxj.platform.pojo.Course;
import cn.hkxj.platform.pojo.CourseGroupMsg;
import cn.hkxj.platform.pojo.CourseTimeTable;
import cn.hkxj.platform.pojo.Openid;
import cn.hkxj.platform.pojo.Student;
import cn.hkxj.platform.service.CourseService;
import cn.hkxj.platform.utils.DateUtils;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author Yuki
 * @date 2018/10/10 23:35
 */
@Slf4j
@Service
@AllArgsConstructor
public class CourseServiceImpl implements CourseService{

    private StudentMapper studentMapper;
    private ClassesMapper classesMapper;
    private CourseMapper courseMapper;
    private CourseTimeTableMapper courseTimeTableMapper;
    private ClassTimeTableMapper classTimeTableMapper;
    private OpenidMapper openidMapper;
    private SubscribeOpenidMapper subscribeOpenidMapper;

    @Override
    public Course getCourseById(Integer id) {
        return courseMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<CourseTimeTable> getCoursesCurrentDay(Integer account) {
        int year = DateUtils.getCurrentYear();
        int week = DateUtils.getCurrentWeek();
        int day = DateUtils.getCurrentDay();

        Student student = studentMapper.selectByAccount(account);
        if(Objects.equals(student, null)) { return null; }
        log.info("query currentday course schedule --account {}, academy {} day{}", account, student.getAcademy(), day);

        Classes classes = getOppositeClasses(student);
        if(classes == null){ return null; }
        List<Course> courses = getAllCourses(classes, account);

        List<Integer> classTimetables = getOppositeClassTimetables(classes, account);
        log.info("query courseTimetable list --parameters {},{},{}"
                , year, week, day);
        List<CourseTimeTable> courseTimeTables =
                courseTimeTableMapper.getTimetablesByIdsForCurrentDay(year, week, day, classTimetables);
        combineCourseAndCourseTimetable(courseTimeTables, courses);

        return courseTimeTables;
    }

    @Override
    public List<CourseGroupMsg> getCoursesSubscribeForCurrentDay() {
        if(!isVaildDay()) { return null; }
        int year = DateUtils.getCurrentYear();
        int week = DateUtils.getCurrentWeek();
        int day = DateUtils.getCurrentDay();
        //和课程相关的数据
        log.info("get all courses of --year{} week{} day{}",year, week, day);
        List<CourseTimeTable> courseTimeTables =
                courseTimeTableMapper.getTimetablesByTimeCondition(year, week, day);
        putCourseDataIntoCourseTimetable(courseTimeTables);
        //获取所有有课班级的信息
        List<Classes> classesList = getClassList(courseTimeTables);
        //装填要群发的消息的班级名称和课程信息
        List<CourseGroupMsg> courseGroupMsgs = getCourseGroupMsgs(courseTimeTables, classesList);
        List<String> classnames = courseGroupMsgs.stream().map(CourseGroupMsg::getClassname).collect(Collectors.toList());
        //和学生还有openId的数据
        Map<String, Student> openIdMap = getOpenIdMap(classnames);
        //将每一个班级对应的openid放进courseGroupMsgs中
        setIdsIntoCourseGroupMsgs(courseGroupMsgs, openIdMap);

        return courseGroupMsgs;
    }

    @Override
    public List<CourseTimeTable> getCoursesByAccount(Integer account) {
        Student student = studentMapper.selectByAccount(account);
        if(Objects.equals(student, null)){ return null; }
        log.info("query this week course schedule --account {},academy {}", account, student.getAcademy());

        Classes classes = getOppositeClasses(student);
        if(classes == null){ return null; }
        List<Course> courses = getAllCourses(classes, account);
        if(courses == null){ return null; }

        List<CourseTimeTable> courseTimeTables = getCourseTimeTables(classes, account);
        if(courseTimeTables == null){ return null; }
        combineCourseAndCourseTimetable(courseTimeTables, courses);

        return courseTimeTables;
    }

    /**
     * 判断对应的学号是否有课程信息
     * @param account 学号
     * @return boolean
     */
    @Override
    public boolean isHaveCourses(Integer account){
        Student student = studentMapper.selectByAccount(account);
        String[] strs = getClassnameAndYearAndNum(student.getClassname());
        List<Classes> classesList = getStudentClassesList(strs, student.getAcademy());
        if(classesList.size() != 0){
            List<Integer> courseIds = getCourseIds(classesList.get(0));
            return courseIds.size() != 0;
        }
        return false;
    }

    public List<Course> getCourseByName(String name){
//        courseMapper
        return null;
    }

    @Override
    public String toText(List<CourseTimeTable> courseTimeTables){
        if(courseTimeTables == null || courseTimeTables.size() == 0) return "课表空空如也";
        StringBuilder builder = new StringBuilder();
        CourseTimeTable[] ctts = new CourseTimeTable[4];
        courseTimeTables.forEach(courseTimeTable -> ctts[courseTimeTable.getOrder()/2] = courseTimeTable );
        int count = 0;
        int length = courseTimeTables.size();
        for(int i = 0; i < 4; i++){
            if(ctts[i] == null) continue;
            count++;
            builder.append("第").append(ctts[i].getOrder()).append("节").append("\\n")
                    .append(ctts[i].getCourseObject().getName()).append("  ")
                    .append(ctts[i].getPosition());
            if(count != length){
                builder.append("\\n\\n");
            }
        }
            builder.append("点击查看更多");
        return builder.toString();
    }

    /**
     * 将传入的字符串切割成班级名 年级 班级序号
     * @param classname student表中字段classname的值
     * @return new String[] {班级名, 年级, 班级序号}
     */
    private String[] getClassnameAndYearAndNum(String classname){
        String[] strs = classname.split("-");
        String[] targets = new String[3];
        if(strs[1].length() > 1){
            targets[2] = strs[1].substring(0, 1);
        } else {
            targets[2] = strs[1];
        }
        int length = strs[0].length();
        for(int i = 0; i < length; i++){
            char c = strs[0].charAt(i);
            if(c >= '0' && c <= '9'){
                //year代表第几级 如16级
                targets[1] = strs[0].substring(i, length);
                //此时的targets[0]是专业名,targets[1]是班级在所在的序号
                targets[0] = strs[0].substring(0, i);
                return targets;
            }
        }
        return targets;
    }

    private void combineCourseAndCourseTimetable(List<CourseTimeTable> courseTimeTables, List<Course> courses){
        courseTimeTables.forEach(courseTimeTable ->
            courses.stream().filter(course -> Objects.equals(course.getId(), courseTimeTable.getCourse()))
                    .forEach(courseTimeTable::setCourseObject)
            );
    }

    private List<CourseTimeTable> getCourseTimeTables(Classes classes, Integer account){
        List<Integer> classTimetables = getOppositeClassTimetables(classes, account);
        if(classTimetables == null) return null;
        return courseTimeTableMapper.getTimeTables(classTimetables);
    }

    private List<Course> getAllCourses(Classes classes, Integer account){
        List<Integer> courseIds = getCourseIdList(classes, account);
        if(courseIds == null) return null;
        return courseMapper.getAllCourses(courseIds);
    }

    private List<Integer> getOppositeClassTimetables(Classes classes, Integer account){
        List<Integer> classTimetables = classTimeTableMapper.getTimeTableIdByClassId(classes.getId());

        if(classTimetables.size() == 0){
            log.info("no relevant courseTimetable information -- account {} classid {}", account, classes.getId());
            return null;
        }
        return classTimetables;
    }

    private List<Integer> getCourseIdList(Classes classes, Integer account){
        List<Integer> courseIds = getCourseIds(classes);
        if(courseIds.size() == 0){
            log.info("no relevant course information --account {} ", account);
            return null;
        }
        return courseIds;
    }

    private Classes getOppositeClasses(Student student){
        String[] strs = getClassnameAndYearAndNum(student.getClassname());
        List<Classes> classesList = getStudentClassesList(strs, student.getAcademy());
        if(classesList.size() == 0){
            log.info("account {} no relevant class information.", student.getAccount());
            return null;
        }
        return classesList.get(0);
    }

    /**
     * 返回一个包含对应学生信息的classes的List
     * @param strs 包含有专业名，年级，班级序号
     * @param academy 学院名
     * @return classesList
     */
    private List<Classes> getStudentClassesList(String[] strs, String academy){
        ClassesExample example = new ClassesExample();
        example.createCriteria()
            .andNameEqualTo(strs[0])
            .andYearEqualTo(Integer.parseInt(strs[1]))
            .andNumEqualTo(Integer.parseInt(strs[2]))
            .andAcademyEqualTo(Academy.getAcademyCodeByName(academy));
        return classesMapper.selectByExample(example);
    }

    /**
     * 获取相关的课程id列表
     * @param classes 班级
     * @return 课程id列表
     */
    private List<Integer> getCourseIds(Classes classes){
        return courseMapper.getCourseIdsByClassId(classes.getId());
    }

    private void putCourseDataIntoCourseTimetable(List<CourseTimeTable> courseTimeTables){
        List<Course> courses = getCoursesList(courseTimeTables);
        combineCourseAndCourseTimetable(courseTimeTables, courses);
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
            msg.setClassname(classes.getName() + classes.getYear() + "-" + classes.getNum());
            msg.setCourseTimeTables(targetList);
            courseGroupMsgs.add(msg);
        });
        return courseGroupMsgs;
    }

    /**
     * 获取一个学生和所对应openId一一映射的Map
     * @param classnames 班级名称
     * @return 学生和所对应openId一一映射的Map
     */
    private Map<String, Student> getOpenIdMap(List<String> classnames){
        List<Student> students = studentMapper.getStudentsByClassnames(classnames);
        List<Integer> accounts = students.stream().map(Student::getAccount).collect(Collectors.toList());

        List<Openid> openIds = openidMapper.getOpenIdsByAccount(accounts);
        List<String> subscribeOpenids = subscribeOpenidMapper.getSubscribeOpenids(openIds);
        List<Openid> openidList = openIds.stream().filter(openid -> subscribeOpenids.contains(openid.getOpenid())).collect(Collectors.toList());

        Map<String, Student> openIdMap = new LinkedHashMap<>();
        students.forEach(student ->
            openidList.stream().filter(openid -> Objects.equals(student.getAccount(), openid.getAccount()))
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
                if(Objects.equals(student.getClassname(), courseGroupMsg.getClassname())){
                    openIdList.add(openid);
                }
            });
            courseGroupMsg.setOpenIds(openIdList);
        });
    }

    /**
     * 通过courseTimeTables获取所有对应的课程信息
     * @param courseTimeTables 时间表集合
     * @return 课程信息
     */
    private List<Course> getCoursesList(List<CourseTimeTable> courseTimeTables){
        List<Integer> courseIds = courseTimeTables.stream().map(CourseTimeTable::getCourse).collect(Collectors.toList());
        return courseMapper.getAllCourses(courseIds);
    }

    private boolean isVaildDay(){
        return DateUtils.getCurrentDay() >= 1 && DateUtils.getCurrentDay() <= 5;
    }

}
