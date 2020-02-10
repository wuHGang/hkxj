package cn.hkxj.platform.dao;

import cn.hkxj.platform.mapper.CourseMapper;
import cn.hkxj.platform.pojo.Course;
import lombok.Data;
import lombok.experimental.Accessors;
import org.mybatis.dynamic.sql.select.SelectDSLCompleter;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

import static cn.hkxj.platform.mapper.CourseDynamicSqlSupport.*;
import static org.mybatis.dynamic.sql.SqlBuilder.isEqualToWhenPresent;

/**
 * @author chenjuanrong
 */
@Service
public class CourseDao {
    @Resource
    private CourseMapper courseMapper;

    public List<Course> getAllCourse() {
        return courseMapper.select(SelectDSLCompleter.allRows());
    }

    public List<Course> selectCourseByPojo(Course course) {
        return courseMapper.select(c -> c.where(courseOrder, isEqualToWhenPresent(course.getCourseOrder()))
                .and(num, isEqualToWhenPresent(course.getNum()))
                .and(termOrder, isEqualToWhenPresent(course.getTermOrder()))
                .and(termYear, isEqualToWhenPresent(course.getTermYear())));


    }

    public void insertSelective(Course course) {
        courseMapper.insertSelective(course);
    }

    /**
     * 保证课程唯一性的约束
     */
    @Data
    @Accessors(chain = true)
    public static class CourseKey {
        private String courseOrder;
        private String num;
        private int termOrder;
        private String termYear;
    }
}
