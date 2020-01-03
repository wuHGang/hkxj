package cn.hkxj.platform.dao;

import cn.hkxj.platform.mapper.StudentExamTimetableMapper;
import cn.hkxj.platform.pojo.StudentExamTimetable;
import cn.hkxj.platform.pojo.StudentExamTimetableExample;
import cn.hkxj.platform.pojo.Term;
import cn.hkxj.platform.utils.DateUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class StudentExamTimeTableDao {
    @Resource
    private StudentExamTimetableMapper studentExamTimetableMapper;


    public void insertSelective(StudentExamTimetable record){
        studentExamTimetableMapper.insertSelective(record);
    }


    public List<StudentExamTimetable> selectCurrentTermExam(String account){
        StudentExamTimetableExample example = new StudentExamTimetableExample();
        StudentExamTimetableExample.Criteria criteria = example.createCriteria();

        Term term = DateUtils.getCurrentSchoolTime().getTerm();


        criteria.andAccountEqualTo(account);
        criteria.andTermYearEqualTo(term.getTermYear());
        criteria.andTermOrderEqualTo(term.getOrder());

        return studentExamTimetableMapper.selectByExample(example);

    }


}
