package cn.hkxj.platform.service.wechat.common.course.impl;

import cn.hkxj.platform.mapper.*;
import cn.hkxj.platform.pojo.*;
import cn.hkxj.platform.service.wechat.common.course.CourseService;
import org.checkerframework.checker.units.qual.A;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

/**
 * @author Yuki
 * @date 2018/10/10 23:35
 */
@Service
public class CourseServiceImpl implements CourseService{

    private static final Logger logger = LoggerFactory.getLogger(CourseServiceImpl.class);

    @Autowired
    private StudentMapper studentMapper;

    @Autowired
    private ClassesMapper classesMapper;

    @Autowired
    private  CourseMapper courseMapper;

    @Autowired
    private CourseTimeTableMapper courseTimeTableMapper;

    @Autowired
    private ClassTimeTableMapper classTimeTableMapper;

    @Override
    public List<CourseTimeTable> getCoursesByAccount(Integer account) {
        Student student = studentMapper.selectByAccount(account);
        String[] strs = getClassnameAndYearAndNum(student.getClassname());
        List<Classes> classesList = getStudentClassesList(strs, student.getAcademy());

        if(classesList.size() == 0){
            logger.error("学号为{}没有相关的班级信息", account);
            throw new RuntimeException("学号为" + account + "没有相关的班级信息");
        }
        List<Integer> courseIds = getCourseIds(classesList.get(0));
        if(courseIds.size() == 0){
            logger.error("学号为{}没有相关课程", account);
            throw new RuntimeException("学号为" + account + "没有相关的课程");
        }

        String ids = getIdsString(courseIds);
        Classes classes = classesList.get(0);
        List<Integer> classTimetables = classTimeTableMapper.getTimeTableIdByClassId(classes.getId());

        if(classTimetables.size() == 0){
            logger.error("学号为{}所在的对应的班级id为{},名称为{}的班级没有对应时间表信息", account, classes.getId(), classes.getName());
            throw new RuntimeException("学号为" + account + "所在的对应的班级id为" + classes.getId() + ",名称为" + classes.getName() + "的班级没有对应时间表信息");
        }
        List<Course> courses = courseMapper.getAllCourses(ids);
        String timeTableIds = getIdsString(classTimetables);
        List<CourseTimeTable> courseTimeTables = courseTimeTableMapper.getTimeTables(timeTableIds);

        courseTimeTables.forEach(courseTimeTable -> {
            courses.forEach(course -> {
                if(Objects.equals(course.getId(), courseTimeTable.getCourse())){
                    courseTimeTable.setCourseObject(course);
                }
            });
        });

        return courseTimeTables;
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
        targets[2] = strs[1];
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

    /**
     * 将包含课程编号的list转换成(数字,数字)的形式
     * @param origin 想要转话成的列表
     * @return (数字,数字);
     */
    private String getIdsString(List<Integer> origin){
        StringBuilder builder = new StringBuilder();
        builder.append("(" + origin.get(0) + ",");
        int size = origin.size();
        for(int i = 1; i < size; i++){
            builder.append(origin.get(i) + ",");
        }
        builder.append(origin.get(size - 1) + ")");
        return builder.toString();
    }

    /**
     * 返回一个包含对应学生信息的classes的List
     * @param strs 包含有专业名，年级，班级序号
     * @param academy 学院名
     * @return classesList
     */
    private List<Classes> getStudentClassesList(String[] strs, String academy){
        ClassesExample example = new ClassesExample();
        ClassesExample.Criteria criteria = example.createCriteria();
        criteria.andNameEqualTo(strs[0]);
        criteria.andYearEqualTo(Integer.parseInt(strs[1]));
        criteria.andNumEqualTo(Integer.parseInt(strs[2]));
        criteria.andAcademyEqualTo(Academy.getAcademyCodeByName(academy));
        return classesMapper.selectByExample(example);
    }

    /**
     * 获取相关的课程id列表
     * @param classes
     * @return 课程id列表
     */
    private List<Integer> getCourseIds(Classes classes){
        return courseMapper.getCourseIdsByClassId(classes.getId());
    }
}
