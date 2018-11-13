package cn.hkxj.platform.service.course.impl;

import cn.hkxj.platform.mapper.*;
import cn.hkxj.platform.pojo.*;
import cn.hkxj.platform.service.course.CourseService;
import cn.hkxj.platform.utils.DateUtils;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author Yuki
 * @date 2018/10/10 23:35
 */
@Service
@AllArgsConstructor
public class CourseServiceImpl implements CourseService{

    private static final Logger logger = LoggerFactory.getLogger(CourseServiceImpl.class);

    private StudentMapper studentMapper;
    private ClassesMapper classesMapper;
    private  CourseMapper courseMapper;
    private CourseTimeTableMapper courseTimeTableMapper;
    private ClassTimeTableMapper classTimeTableMapper;
    private OpenidMapper openidMapper;
    private SubscribeOpenidMapper subscribeOpenidMapper;

    @Override
    public List<CourseTimeTable> getCoursesCurrentDay(Integer account) {
        Integer[] currentTime = DateUtils.getCurrentTime();

        Student student = studentMapper.selectByAccount(account);
        String[] strs = getClassnameAndYearAndNum(student.getClassname());
        logger.info("学号{}, 学院为{}的学生在查询星期{}的课表", account, student.getAcademy(), currentTime[2]);

        Classes classes = getOppositeClasses(strs, student.getAcademy(), account);
        List<Integer> courseIds = getCourseIdList(classes, account);
        List<Course> courses = courseMapper.getAllCourses(courseIds);

        List<Integer> classTimetables = getOppositeClassTimetables(classes, account);
        logger.info("根据学号{}获取当天courseTimeTable列表时传入参数{},{},{}"
                ,account, currentTime[0], currentTime[1], currentTime[2]);
        List<CourseTimeTable> courseTimeTables =
                courseTimeTableMapper.getTimetablesByIdsForCurrentDay(currentTime[0], currentTime[1], currentTime[2], classTimetables);
        combineCourseAndCouseTimetable(courseTimeTables, courses);

        return courseTimeTables;
    }

    @Override
    public List<CourseGroupMsg> getCoursesSubscribeForCurrentDay() {
        if(!isVaildDay()) { return null; }
        Integer[] currentTime = DateUtils.getCurrentTime();
        //和课程相关的数据
        logger.info("获取获取{}年{}月{}日，星期{}的所有课表",currentTime[0], currentTime[1], currentTime[2], currentTime[2]);
        List<CourseTimeTable> courseTimeTables =
                courseTimeTableMapper.getTimetablesByTimeCondition(currentTime[0], currentTime[1], currentTime[2]);
        putCourseDataIntoCourseTimetable(courseTimeTables);
        //获取所有有课班级的信息
        List<Classes> classesList = getClassList(courseTimeTables);
        //装填要群发的消息的班级名称和课程信息
        List<CourseGroupMsg> courseGroupMsgs = getCourseGroupMsgs(courseTimeTables, classesList);
        List<String> classnames = courseGroupMsgs.stream().map(courseGroupMsg -> courseGroupMsg.getClassname()).collect(Collectors.toList());
        //和学生还有openId的数据
        Map<Student, String> openIdMap = getOpenIdMap(classnames);
        //将每一个班级对应的openid放进courseGroupMsgs中
        setIdsIntoCourseGroupMsgs(courseGroupMsgs, openIdMap);

        return courseGroupMsgs;
    }

    @Override
    public List<CourseTimeTable> getCoursesByAccount(Integer account) {
        Student student = studentMapper.selectByAccount(account);
        String[] strs = getClassnameAndYearAndNum(student.getClassname());
        logger.info("学号{},学院{}的学生查询当前周的课表", account, student.getAcademy());

        Classes classes = getOppositeClasses(strs, student.getAcademy(), account);
        List<Integer> courseIds = getCourseIdList(classes, account);
        List<Course> courses = courseMapper.getAllCourses(courseIds);

        List<Integer> classTimetables = getOppositeClassTimetables(classes, account);
        List<CourseTimeTable> courseTimeTables = courseTimeTableMapper.getTimeTables(classTimetables);
        combineCourseAndCouseTimetable(courseTimeTables, courses);

        return courseTimeTables;
    }

    private <T extends Object> String getIdsString(List<T> origin){
        StringBuilder builder = new StringBuilder();
        if(origin.size() == 1){
            return builder.append("(" + origin.get(0) +  ")").toString();
        }
        builder.append("(" + origin.get(0) + ",");
        int size = origin.size();
        for(int i = 1; i < size; i++){
            builder.append(origin.get(i) + ",");
        }
        builder.append(origin.get(size - 1) + ")");
        return builder.toString();
    }

    /**
     * 判断对应的学号是否有课程信息
     * @param account 学号
     * @return boolean
     */
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
                targets[1] = strs[0].substring(i, length);        //year代表第几级 如16级
                targets[0] = strs[0].substring(0, i);          //此时的strs[0]是专业名,strs[1]是班级在所在的序号
                return targets;
            }
        }
        return targets;
    }

    private void combineCourseAndCouseTimetable(List<CourseTimeTable> courseTimeTables, List<Course> courses){
        courseTimeTables.forEach(courseTimeTable -> {
            courses.forEach(course -> {
                if(Objects.equals(course.getId(), courseTimeTable.getCourse())){
                    courseTimeTable.setCourseObject(course);
                }
            });
        });
    }

    private List<Integer> getOppositeClassTimetables(Classes classes, Integer account){
        List<Integer> classTimetables = classTimeTableMapper.getTimeTableIdByClassId(classes.getId());

        if(classTimetables.size() == 0){
            logger.error("学号为{}所在的对应的班级id为{},名称为{}的班级没有对应时间表信息", account, classes.getId(), classes.getName());
            throw new RuntimeException("学号为" + account + "所在的对应的班级id为" + classes.getId() + ",名称为" + classes.getName() + "的班级没有对应时间表信息");
        }
        return classTimetables;
    }

    private List<Integer> getCourseIdList(Classes classes, Integer account){
        List<Integer> courseIds = getCourseIds(classes);
        if(courseIds.size() == 0){
            logger.error("学号为{}没有相关课程", account);
            throw new RuntimeException("学号为" + account + "没有相关的课程");
        }
        return courseIds;
    }

    private Classes getOppositeClasses(String[] strs, String academy, Integer account){
        List<Classes> classesList = getStudentClassesList(strs, academy);
        if(classesList.size() == 0){
            logger.error("学号为{}没有相关的班级信息", account);
            throw new RuntimeException("学号为" + account + "没有相关的班级信息");
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

    /**
     * 将课程信息及其对应的时间信息，组成一个Map
     * @param courseTimeTables 时间信息
     * @return Map<CourseTimeTable, Course>
     */
    private Map<CourseTimeTable, Course> getCourseRelateInfoMap(List<CourseTimeTable> courseTimeTables){
        List<Course> courses = getCoursesList(courseTimeTables);
        Map<CourseTimeTable, Course> map = new LinkedHashMap<>();
        courseTimeTables.forEach(courseTimeTable -> {
            courses.forEach(course -> {
                if(Objects.equals(course.getId(), courseTimeTable.getCourse())){
                    map.put(courseTimeTable, course);
                }
            });
        });
        return map;
    }

    private void putCourseDataIntoCourseTimetable(List<CourseTimeTable> courseTimeTables){
        List<Course> courses = getCoursesList(courseTimeTables);
        combineCourseAndCouseTimetable(courseTimeTables, courses);
    }

    /**
     * 获取课程时间和班级id之间的对应关系
     * @param courseTimeTables 课程时间
     * @return ClassTimeTable
     */
    private List<ClassTimeTable> getClassTimeTables(List<CourseTimeTable> courseTimeTables){
        List<Integer> courseTimeTableIds =
                courseTimeTables.stream().map(courseTimeTable -> courseTimeTable.getId()).collect(Collectors.toList());
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
                classesMapper.getClassesByIds(classTimeTables.stream().map(classTimeTable -> classTimeTable.getClassId()).collect(Collectors.toList()));
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
            classes.getCourseTimeTableIds().forEach(timetableId -> {
                courseTimeTables.forEach(courseTimeTable -> {
                    if(Objects.equals(courseTimeTable.getId(), timetableId)){
                        targetList.add(courseTimeTable);
                    }
                });
            });
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
    private Map<Student, String> getOpenIdMap(List<String> classnames){
        List<Student> students = studentMapper.getStudentsByClassnames(classnames);
        List<Integer> accounts = students.stream().map(student -> student.getAccount()).collect(Collectors.toList());

        List<Openid> openIds = openidMapper.getOpenIdsByAccount(accounts);
        List<String> subscribeOpenids = subscribeOpenidMapper.getSubscribeOpenids(openIds);
        List<Openid> openidList = openIds.stream().filter(openid -> subscribeOpenids.contains(openid.getOpenid())).collect(Collectors.toList());

        Map<Student, String> openIdMap = new LinkedHashMap<>();
        students.forEach(student -> {
            openidList.forEach(openid -> {
                if(Objects.equals(student.getAccount(), openid.getAccount())){
                    openIdMap.put(student, openid.getOpenid());
                }
            });
        });
        return openIdMap;
    }

    /**
     * openIdMap中学生的对应的openid一一对应，将相同班级的学生的openId放进courseGroupMsgs
     * @param courseGroupMsgs 课程提醒
     * @param openIdMap 学生和openId一一对应的Map
     */
    private void setIdsIntoCourseGroupMsgs(List<CourseGroupMsg> courseGroupMsgs, Map<Student, String> openIdMap){
        courseGroupMsgs.forEach(courseGroupMsg -> {
            List<String> openIdList = new ArrayList<>(40);
            openIdMap.forEach((student, openid) -> {
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
        List<Integer> courseIds = courseTimeTables.stream().map(courseTimeTable -> courseTimeTable.getCourse()).collect(Collectors.toList());
        return courseMapper.getAllCourses(courseIds);
    }

    private boolean isVaildDay(){
        return DateUtils.getCurrentDay() >= 1 && DateUtils.getCurrentDay() <= 5;
    }

    public String toText(List<CourseTimeTable> courseTimeTables){
        if(courseTimeTables == null || courseTimeTables.size() == 0) return "课表空空如也";
        StringBuffer buffer = new StringBuffer();
        buffer.append("今日课表").append("\n");
        CourseTimeTable[] ctts = new CourseTimeTable[4];
        courseTimeTables.forEach(courseTimeTable -> {
            Course course = courseTimeTable.getCourseObject();
            ctts[courseTimeTable.getOrder()/2] = courseTimeTable;
        });
        for(int i = 0; i < 4; i++){
            buffer.append("第").append(ctts[i].getOrder()).append("节").append("\n")
                    .append(ctts[i].getCourseObject().getName()).append("  ")
                    .append(ctts[i].getPosition()).append("\n");
        }
        return buffer.toString();
    }
}
