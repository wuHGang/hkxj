package cn.hkxj.platform.dao;

import cn.hkxj.platform.mapper.ClassCourseTimetableMapper;
import cn.hkxj.platform.pojo.ClassCourseTimetable;
import cn.hkxj.platform.pojo.example.ClassCourseTimetableExample;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ClassCourseTimetableDao {
    @Resource
    private ClassCourseTimetableMapper classCourseTimetableMapper;

    public List<ClassCourseTimetable> selectByPojo(ClassCourseTimetable classCourseTimetable){
        ClassCourseTimetableExample example = new ClassCourseTimetableExample();
        ClassCourseTimetableExample.Criteria criteria = example.createCriteria();

        if(classCourseTimetable.getCourseTimetableId() != null){
            criteria.andCourseTimetableIdEqualTo(classCourseTimetable.getCourseTimetableId());
        }
        if(classCourseTimetable.getClassId() != null){
            criteria.andClassIdEqualTo(classCourseTimetable.getClassId());
        }

        if(classCourseTimetable.getTermOrder() != null){
            criteria.andTermOrderEqualTo(classCourseTimetable.getTermOrder());
        }
        if(classCourseTimetable.getTermYear() != null){
            criteria.andTermYearEqualTo(classCourseTimetable.getTermYear());
        }

        return classCourseTimetableMapper.selectByExample(example);


    }

}
