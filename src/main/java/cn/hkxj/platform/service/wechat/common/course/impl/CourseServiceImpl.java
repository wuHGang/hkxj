package cn.hkxj.platform.service.wechat.common.course.impl;

import cn.hkxj.platform.mapper.ClassesMapper;
import cn.hkxj.platform.mapper.CourseMapper;
import cn.hkxj.platform.mapper.CourseTimeTableMapper;
import cn.hkxj.platform.mapper.StudentMapper;
import cn.hkxj.platform.pojo.*;
import cn.hkxj.platform.service.wechat.common.course.CourseService;
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

    @Override
    public List<CourseTimeTable> getCoursesByAccount(Integer account) {
        List<Integer> courseIds = getCourseIds(account);
        if(courseIds.size() == 0){
            logger.error("没有相关的课程，查询失败");
            throw new RuntimeException("没有相关的课程，查询失败");
        }

        String ids = getIdsString(courseIds);
        List<Course> courses = courseMapper.getAllCourses(ids);
        List<CourseTimeTable> courseTimeTables = courseTimeTableMapper.getAllTimeTableByCourseIds(ids);

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
     * 判断是否拥有课程
     * @param account 序号
     * @return boolean
     */
    public boolean isHaveCourses(Integer account){
        List<Integer> courseIds = getCourseIds(account);
        return courseIds.size() != 0;
    }

    /**
     * 将包含课程编号的list转换成(数字,数字)的形式
     * @param courseIds 包含课程id的列表
     * @return (数字,数字);
     */
    private String getIdsString(List<Integer> courseIds){
        StringBuilder builder = new StringBuilder();
        builder.append("(" + courseIds.get(0) + ",");
        int size = courseIds.size();
        for(int i = 1; i < size; i++){
            builder.append(courseIds.get(i) + ",");
        }
        builder.append(courseIds.get(size - 1) + ")");
        return builder.toString();
    }

    /**
     * 返回一个包含对应学生信息的classes对象
     * @param strs 包含有专业名，年级，班级序号
     * @param academy 学院名
     * @return classes对象
     */
    private Classes getStudentClasses(String[] strs, String academy){
        ClassesExample example = new ClassesExample();
        ClassesExample.Criteria criteria = example.createCriteria();
        criteria.andNameEqualTo(strs[0]);
        criteria.andYearEqualTo(Integer.parseInt(strs[1]));
        criteria.andNumEqualTo(Integer.parseInt(strs[2]));
        criteria.andAcademyEqualTo(Academy.getAcademyCodeByName(academy));
        List<Classes> classesList = classesMapper.selectByExample(example);
        return classesList.get(0);
    }

    /**
     * 获取相关的课程id列表
     * @param account 学号
     * @return 课程id列表
     */
    private List<Integer> getCourseIds(Integer account){
        Student student = studentMapper.selectByAccount(account);
        String[] strs = getClassnameAndYearAndNum(student.getClassname());
        return courseMapper.getCourseIdsByClassId(getStudentClasses(strs, student.getAcademy()).getId());
    }
}
