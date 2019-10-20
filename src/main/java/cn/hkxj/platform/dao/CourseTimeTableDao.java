package cn.hkxj.platform.dao;

import cn.hkxj.platform.mapper.CourseTimetableMapper;
import cn.hkxj.platform.pojo.CourseTimetable;
import cn.hkxj.platform.pojo.example.CourseTimetableExample;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class CourseTimeTableDao {
    @Resource
    private CourseTimetableMapper courseTimetableMapper;



    public List<CourseTimetable> selectByCourseTimetable(CourseTimetable courseTimetable){
        CourseTimetableExample example = new CourseTimetableExample();
        CourseTimetableExample.Criteria criteria = example.createCriteria();
        if(courseTimetable.getCourseId() != null){
            criteria.andCourseIdEqualTo(courseTimetable.getCourseId());
        }
        if(courseTimetable.getCourseSequenceNumber() != null){
            criteria.andCourseSequenceNumberEqualTo(courseTimetable.getCourseSequenceNumber());
        }
        if(courseTimetable.getClassDay() != null){
            criteria.andClassDayEqualTo(courseTimetable.getClassDay());
        }
        if(courseTimetable.getClassOrder() != null){
            criteria.andClassOrderEqualTo(courseTimetable.getClassOrder());
        }
        if(courseTimetable.getTermYear() != null){
            criteria.andTermYearEqualTo(courseTimetable.getTermYear());
        }
        if(courseTimetable.getTermOrder() != null){
            criteria.andTermOrderEqualTo(courseTimetable.getTermOrder());
        }
        if(courseTimetable.getStartWeek() != null){
            criteria.andStartWeekEqualTo(courseTimetable.getStartWeek());
        }
        if(courseTimetable.getEndWeek() != null){
            criteria.andEndWeekEqualTo(courseTimetable.getEndWeek());
        }
        if(courseTimetable.getRoomNumber() != null){
            criteria.andRoomNumberEqualTo(courseTimetable.getRoomNumber());
        }

        return courseTimetableMapper.selectByExample(example);
    }

    public void insertSelective(CourseTimetable courseTimetable){
        courseTimetableMapper.insertSelective(courseTimetable);
    }

    public void updateByPrimaryKeySelective(CourseTimetable courseTimetable){
        courseTimetableMapper.updateByPrimaryKeySelective(courseTimetable);
    }
}
