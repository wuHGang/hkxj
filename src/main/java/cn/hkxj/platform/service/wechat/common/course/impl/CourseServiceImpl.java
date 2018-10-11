package cn.hkxj.platform.service.wechat.common.course.impl;

import cn.hkxj.platform.mapper.ClassesMapper;
import cn.hkxj.platform.mapper.CourseMapper;
import cn.hkxj.platform.mapper.StudentMapper;
import cn.hkxj.platform.pojo.Classes;
import cn.hkxj.platform.pojo.ClassesExample;
import cn.hkxj.platform.pojo.Course;
import cn.hkxj.platform.pojo.Student;
import cn.hkxj.platform.service.wechat.common.course.CourseService;
import cn.hkxj.platform.utils.JsonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.PostConstruct;
import java.util.List;

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

    @Override
    public String getCoursesByAccount(Integer account) {
        Student student = studentMapper.selectByAccount(account);
        String classname = student.getClassname();
        String[] strs = classname.split("-");
        String year = null;
        int length = strs[0].length();
        for(int i = 0; i < length; i++){
            char c = strs[0].charAt(i);
            if(c >= '0' && c <= '9'){
                year = strs[0].substring(i, length);        //year代表第几级 如16级
                strs[0] = strs[0].substring(0, i);          //此时的strs[0]是专业名,strs[1]是班级在所在的序号
                break;
            }
        }
        ClassesExample classesExample = new ClassesExample();
        ClassesExample.Criteria criteria = classesExample.createCriteria();
        criteria.andNameEqualTo(strs[0]);
        criteria.andYearEqualTo(Integer.parseInt(year));
        criteria.andNumEqualTo(Integer.parseInt(strs[1]));
        List<Classes> classesList = classesMapper.selectByExample(classesExample);
        List<Integer> courseIds = courseMapper.getCourseIdsByClassId(classesList.get(0).getId());
        StringBuilder builder = new StringBuilder();
        builder.append("(" + courseIds.get(0) + ",");
        int size = courseIds.size();
        for(int i = 1; i < size; i++){
            builder.append(courseIds.get(i) + ",");
        }
        builder.append(courseIds.get(size - 1) + ")");
        List<Course> courses = courseMapper.getAllCourses(builder.toString());
        return JsonUtils.toJson(courses);
    }
}
