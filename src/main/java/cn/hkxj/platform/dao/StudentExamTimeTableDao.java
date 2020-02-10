package cn.hkxj.platform.dao;

import cn.hkxj.platform.mapper.StudentExamTimetableMapper;
import cn.hkxj.platform.pojo.StudentExamTimetable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.List;

@Service
public class StudentExamTimeTableDao {
    @Resource
    private StudentExamTimetableMapper studentExamTimetableMapper;


    public void insertSelective(StudentExamTimetable record){
        studentExamTimetableMapper.insertSelective(record);
    }


    public List<StudentExamTimetable> selectCurrentTermExam(String account){

        return Collections.emptyList();

    }


}
