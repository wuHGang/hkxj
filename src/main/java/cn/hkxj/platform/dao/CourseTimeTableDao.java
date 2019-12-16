package cn.hkxj.platform.dao;

import cn.hkxj.platform.mapper.CourseTimetableMapper;
import cn.hkxj.platform.pojo.CourseTimetable;
import cn.hkxj.platform.pojo.example.CourseTimetableExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class CourseTimeTableDao {
    @Resource
    private CourseTimetableMapper courseTimetableMapper;


    public CourseTimetable selectByPrimaryKey(Integer id) {
        return courseTimetableMapper.selectByPrimaryKey(id);
    }

    public List<CourseTimetable> selectByCourseTimetable(CourseTimetable courseTimetable) {
        CourseTimetableExample example = getExample(courseTimetable);

        return courseTimetableMapper.selectByExample(example);
    }

    public CourseTimetable selectUniqueCourse(CourseTimetable courseTimetable) {
        CourseTimetableExample example = new CourseTimetableExample();
        CourseTimetableExample.Criteria criteria = example.createCriteria();

        getUniqueExample(courseTimetable, example, criteria);

        return courseTimetableMapper.selectByExample(example).stream().findFirst().orElse(null);

    }

    private CourseTimetableExample getUniqueExample(CourseTimetable courseTimetable, CourseTimetableExample example, CourseTimetableExample.Criteria criteria) {

        if (courseTimetable.getCourseId() != null) {
            criteria.andCourseIdEqualTo(courseTimetable.getCourseId());
        }
        if (courseTimetable.getCourseSequenceNumber() != null) {
            criteria.andCourseSequenceNumberEqualTo(courseTimetable.getCourseSequenceNumber());
        }
        if (courseTimetable.getClassDay() != null) {
            criteria.andClassDayEqualTo(courseTimetable.getClassDay());
        }
        if (courseTimetable.getClassOrder() != null) {
            criteria.andClassOrderEqualTo(courseTimetable.getClassOrder());
        }
        if (courseTimetable.getTermYear() != null) {
            criteria.andTermYearEqualTo(courseTimetable.getTermYear());
        }
        if (courseTimetable.getTermOrder() != null) {
            criteria.andTermOrderEqualTo(courseTimetable.getTermOrder());
        }
        if (courseTimetable.getStartWeek() != null) {
            criteria.andStartWeekEqualTo(courseTimetable.getStartWeek());
        }
        if (courseTimetable.getEndWeek() != null) {
            criteria.andEndWeekEqualTo(courseTimetable.getEndWeek());
        }

        return example;
    }

    private CourseTimetableExample getExample(CourseTimetable courseTimetable){
        CourseTimetableExample example = new CourseTimetableExample();
        CourseTimetableExample.Criteria criteria = example.createCriteria();
        getUniqueExample(courseTimetable, example, criteria);

        if (courseTimetable.getRoomNumber() != null) {
            criteria.andRoomNumberEqualTo(courseTimetable.getRoomNumber());
        }

        if (courseTimetable.getRoomName() != null) {
            criteria.andRoomNameEqualTo(courseTimetable.getRoomName());
        }

        return example;
    }

    public List<CourseTimetable> selectByIdList(List<Integer> idList) {
        CourseTimetableExample example = new CourseTimetableExample();
        CourseTimetableExample.Criteria criteria = example.createCriteria();
        criteria.andIdIn(idList);

        return courseTimetableMapper.selectByExample(example);
    }

    public void insertSelective(CourseTimetable courseTimetable) {
        courseTimetableMapper.insertSelective(courseTimetable);
    }

    public void updateByPrimaryKeySelective(CourseTimetable courseTimetable) {
        courseTimetableMapper.updateByPrimaryKeySelective(courseTimetable);
    }

    public void updateByUniqueKey(CourseTimetable courseTimetable) {
        CourseTimetableExample example = new CourseTimetableExample();
        CourseTimetableExample.Criteria criteria = example.createCriteria();
        getUniqueExample(courseTimetable, example, criteria);

        courseTimetableMapper.updateByExampleSelective(courseTimetable, example);
    }
}
