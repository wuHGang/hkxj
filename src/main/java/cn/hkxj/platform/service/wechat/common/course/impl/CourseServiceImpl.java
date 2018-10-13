package cn.hkxj.platform.service.wechat.common.course.impl;

import cn.hkxj.platform.mapper.ClassesMapper;
import cn.hkxj.platform.mapper.CourseMapper;
import cn.hkxj.platform.mapper.CourseTimeTableMapper;
import cn.hkxj.platform.mapper.StudentMapper;
import cn.hkxj.platform.pojo.*;
import cn.hkxj.platform.service.wechat.common.course.CourseService;
import cn.hkxj.platform.utils.JsonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author Yuki
 * @date 2018/10/10 23:35
 */
@Service
public class CourseServiceImpl implements CourseService{

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
        Student student = studentMapper.selectByAccount(account);
        String[] targets = getClassnameAndYearAndNum(student.getClassname());
        Integer academyCode = Academy.getAcademyCodeByName(student.getAcademy());

        ClassesExample classesExample = new ClassesExample();
        ClassesExample.Criteria criteria = classesExample.createCriteria();
        criteria.andNameEqualTo(targets[0]);
        criteria.andYearEqualTo(Integer.parseInt(targets[1]));
        criteria.andNumEqualTo(Integer.parseInt(targets[2]));
        criteria.andAcademyEqualTo(academyCode);

        List<Classes> classesList = classesMapper.selectByExample(classesExample);
        List<Integer> courseIds = courseMapper.getCourseIdsByClassId(classesList.get(0).getId());

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

        System.out.println("在这停顿");

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
}
