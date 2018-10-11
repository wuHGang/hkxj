package cn.hkxj.platform.service.wechat.common.course.impl;

import cn.hkxj.platform.mapper.CourseMapper;
import cn.hkxj.platform.mapper.StudentMapper;
import cn.hkxj.platform.pojo.Course;
import cn.hkxj.platform.pojo.Student;
import cn.hkxj.platform.service.wechat.common.course.CourseService;
import cn.hkxj.platform.utils.JsonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    private CourseMapper courseMapper;

    @Override
    public String getCoursesByAccount(Integer account) {
        Student student = studentMapper.selectByAccount(account);
        List<Integer> courseIds = courseMapper.getCourseIdsByStudentId(student.getId());
        StringBuilder builder = new StringBuilder();
        builder.append("(" + courseIds.get(0) + ",");
        int size = courseIds.size();
        for(int i = 1; i < size - 1; i++){
            builder.append(courseIds.get(i) + ",");
        }
        builder.append(courseIds.get(size - 1) + ")");
        List<Course> courses = courseMapper.getCoursesByIds(builder.toString());
        return JsonUtils.toJson(courses);
    }
}
