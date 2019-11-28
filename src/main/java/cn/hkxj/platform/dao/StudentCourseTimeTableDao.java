package cn.hkxj.platform.dao;

import cn.hkxj.platform.mapper.StudentCourseTimeTableMapper;
import cn.hkxj.platform.pojo.StudentCourseTimeTable;
import cn.hkxj.platform.pojo.example.StudentCourseTimeTableExample;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class StudentCourseTimeTableDao {
    @Resource
    private StudentCourseTimeTableMapper studentCourseTimeTableMapper;

    public void insertSelective(StudentCourseTimeTable studentCourseTimeTable){
        studentCourseTimeTableMapper.insertSelective(studentCourseTimeTable);
    }

    public List<StudentCourseTimeTable> selectByExample(StudentCourseTimeTable studentCourseTimeTable){
        StudentCourseTimeTableExample example = new StudentCourseTimeTableExample();
        StudentCourseTimeTableExample.Criteria criteria = example.createCriteria();

        if(studentCourseTimeTable.getTermOrder() != null){
            criteria.andTermOrderEqualTo(studentCourseTimeTable.getTermOrder());
        }
        if(studentCourseTimeTable.getTermYear() != null){
            criteria.andTermYearEqualTo(studentCourseTimeTable.getTermYear());
        }
        if(studentCourseTimeTable.getStudentId() != null){
            criteria.andStudentIdEqualTo(studentCourseTimeTable.getStudentId());
        }
        if(studentCourseTimeTable.getCourseTimetableId() != null){
            criteria.andCourseTimetableIdEqualTo(studentCourseTimeTable.getCourseTimetableId());
        }

        return studentCourseTimeTableMapper.selectByExample(example);
    }

    public int deleteByAccount(Integer account){
        StudentCourseTimeTableExample example = new StudentCourseTimeTableExample();
        StudentCourseTimeTableExample.Criteria criteria = example.createCriteria();

        criteria.andStudentIdEqualTo(account);
        return studentCourseTimeTableMapper.deleteByExample(example);
    }
}
