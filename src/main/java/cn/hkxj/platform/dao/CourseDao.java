package cn.hkxj.platform.dao;

import cn.hkxj.platform.mapper.CourseMapper;
import cn.hkxj.platform.pojo.Course;
import cn.hkxj.platform.pojo.example.CourseExample;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class CourseDao {
    @Resource
    private CourseMapper courseMapper;

    public List<Course> getAllCourse(){
        CourseExample example = new CourseExample();
        return courseMapper.selectByExample(example);
    }
}
