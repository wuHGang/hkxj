package cn.hkxj.platform.dao;

import cn.hkxj.platform.mapper.CourseMapper;
import cn.hkxj.platform.pojo.Course;
import cn.hkxj.platform.pojo.example.CourseExample;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Optional;

@Service
public class CourseDao {
    @Resource
    private CourseMapper courseMapper;

    public List<Course> getAllCourse(){
        CourseExample example = new CourseExample();
        return courseMapper.selectByExample(example);
    }

    public List<Course> selectCourseByPojo(Course course){
        CourseExample example = new CourseExample();
        CourseExample.Criteria criteria = example.createCriteria();
        if(course.getCourseOrder() != null){
            criteria.andCourseOrderEqualTo(course.getCourseOrder());
        }
        if(course.getNum() != null){
            criteria.andNumEqualTo(course.getNum());
        }
        if(course.getTermYear() != null){
            criteria.andTermYearEqualTo(course.getTermYear());
        }
        if(course.getTermOrder() != null){
            criteria.andTermOrderEqualTo(course.getTermOrder());
        }

        return courseMapper.selectByExample(example);
    }

    public Course selectByNumAndOrder(String number, String order){

        return selectCourseByPojo(new Course().setNum(number).setCourseOrder(order)).stream().findFirst().orElse(null);
    }

    public void insertSelective(Course course){
        courseMapper.insertSelective(course);
    }
}
