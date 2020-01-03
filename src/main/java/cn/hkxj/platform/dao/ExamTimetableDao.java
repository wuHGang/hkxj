package cn.hkxj.platform.dao;

import cn.hkxj.platform.mapper.ExamTimetableMapper;
import cn.hkxj.platform.pojo.ExamTimetable;
import cn.hkxj.platform.pojo.ExamTimetableExample;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ExamTimetableDao {
    @Resource
    private ExamTimetableMapper examTimetableMapper;


    public void insertSelective(ExamTimetable examTimetable){
        examTimetableMapper.insertSelective(examTimetable);
    }

    public ExamTimetable selectByPrimaryKey(Integer id){
        return examTimetableMapper.selectByPrimaryKey(id);
    }

    public List<ExamTimetable> selectByPojo(ExamTimetable examTimetable){

        ExamTimetableExample example = new ExamTimetableExample();
        ExamTimetableExample.Criteria criteria = example.createCriteria();

        criteria.andCourseNumEqualTo(examTimetable.getCourseNum());
        criteria.andCourseOrderEqualTo(examTimetable.getCourseOrder());
        criteria.andTermYearEqualTo(examTimetable.getTermYear());
        criteria.andTermOrderEqualTo(examTimetable.getTermOrder());

        return examTimetableMapper.selectByExample(example);
    }

}
