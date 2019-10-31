package cn.hkxj.platform.dao;

import cn.hkxj.platform.mapper.CourseTimeTableDetailMapper;
import cn.hkxj.platform.pojo.*;
import cn.hkxj.platform.pojo.example.CourseTimeTableDetailExample;
import cn.hkxj.platform.utils.DateUtils;
import com.google.common.collect.Lists;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author Yuki
 * @date 2019/8/30 10:12
 */
@Service
public class CourseTimeTableDetailDao {

    @Resource
    private CourseTimeTableDetailMapper courseTimeTableDetailMapper;

    public void insertClassesCourseTimeTableBatch(List<Integer> ids, int classesId){
        courseTimeTableDetailMapper.insertClassesCourseTimeTableBatch(ids, classesId);
    }

    public void insertCourseTimeTableDetail(CourseTimeTableDetail detail){
        courseTimeTableDetailMapper.insertSelective(detail);
    }

    public List<CourseTimeTableDetail> getCourseTimeTableDetailForSection(List<Integer> detailIdList, SchoolTime schoolTime, int section){
        CourseTimeTableDetailExample example = new CourseTimeTableDetailExample();
        example.createCriteria()
                .andIdIn(detailIdList)
                .andTermYearEqualTo(schoolTime.getTerm().getTermYear())
                .andTermOrderEqualTo(schoolTime.getTerm().getOrder())
                .andDayEqualTo(schoolTime.getDay())
                .andStartWeekLessThanOrEqualTo(schoolTime.getWeek())
                .andEndWeekGreaterThanOrEqualTo(schoolTime.getWeek())
                .andDistinctNotEqualTo(DateUtils.getContraryDistinct())
                .andOrderEqualTo(section);
        return courseTimeTableDetailMapper.selectByExample(example);
    }

    public List<CourseTimeTableDetail> getCourseTimeTableDetailForCurrentDay(List<Integer> detailIdList, SchoolTime schoolTime){
        CourseTimeTableDetailExample example = new CourseTimeTableDetailExample();
        example.createCriteria()
                .andIdIn(detailIdList)
                .andTermYearEqualTo(schoolTime.getTerm().getTermYear())
                .andTermOrderEqualTo(schoolTime.getTerm().getOrder())
                .andDayEqualTo(schoolTime.getDay())
                .andStartWeekLessThanOrEqualTo(schoolTime.getWeek())
                .andEndWeekGreaterThanOrEqualTo(schoolTime.getWeek())
                .andDistinctNotEqualTo(DateUtils.getContraryDistinct());
        return courseTimeTableDetailMapper.selectByExample(example);
    }

    public List<Integer> getCourseTimeTableDetailIdsByClassId(int classesId){
        return courseTimeTableDetailMapper.getCourseTimeTableDetailIdsByClassId(classesId);
    }

    public List<CourseTimeTableDetail> getByClassroomName(String classroomName){
        CourseTimeTableDetailExample example = new CourseTimeTableDetailExample();
        example.createCriteria().andRoomNameEqualTo(classroomName);
        return courseTimeTableDetailMapper.selectByExample(example);
    }

    public List<CourseTimeTableDetail> getCourseTimeTableDetailForCurrentTerm(List<Integer> detailIdList, SchoolTime schoolTime){
        CourseTimeTableDetailExample example = new CourseTimeTableDetailExample();
        example.createCriteria()
                .andIdIn(detailIdList)
                .andTermYearEqualTo(schoolTime.getTerm().getTermYear())
                .andTermOrderEqualTo(schoolTime.getTerm().getOrder());
        return courseTimeTableDetailMapper.selectByExample(example);
    }

    public List<CourseTimeTableDetail> getCourseTimeTableDetailForCurrentWeek(List<Integer> detailIdList,
                                                                              SchoolTime schoolTime){

        CourseTimeTableDetailExample example = new CourseTimeTableDetailExample();
        example.createCriteria()
                .andIdIn(detailIdList)
                .andTermYearEqualTo(schoolTime.getTerm().getTermYear())
                .andTermOrderEqualTo(schoolTime.getTerm().getOrder())
                .andStartWeekLessThanOrEqualTo(schoolTime.getWeek())
                .andEndWeekGreaterThanOrEqualTo(schoolTime.getWeek())
                .andDistinctNotEqualTo(DateUtils.getContraryDistinct());
        return courseTimeTableDetailMapper.selectByExample(example);
    }

    public List<CourseTimeTableDetail> selectByDetail(CourseTimeTableDetail detail){
        CourseTimeTableDetailExample example = new CourseTimeTableDetailExample();


        CourseTimeTableDetailExample.Criteria criteria = example.createCriteria()
                .andDayEqualTo(detail.getDay())
                .andOrderEqualTo(detail.getOrder());

        if(detail.getTermYear() != null){
            Term term = detail.getTermForCourseTimeTableDetail();
            criteria.andTermYearEqualTo(term.getTermYear());
        }
        if(detail.getTermOrder() != null){
            criteria.andTermOrderEqualTo(detail.getTermOrder());
        }
        if(detail.getRoomName() != null){
            criteria.andRoomNameEqualTo(detail.getRoomName());
        }

        if(detail.getCourseId() != null){
            criteria.andCourseIdEqualTo(detail.getCourseId());
        }
        if(detail.getStartWeek() != null){
            criteria.andStartWeekEqualTo(detail.getStartWeek());
        }
        if(detail.getEndWeek() != null){
            criteria.andEndWeekEqualTo(detail.getEndWeek());
        }
        if(detail.getWeek() != null){
            criteria.andWeekEqualTo(detail.getWeek());
        }

        return courseTimeTableDetailMapper.selectByExample(example);
    }

    public void insertStudentCourseTimeTableBatch(List<Integer> courseTimeTableIdList, int account,  String termYear,  int termOrder){
        courseTimeTableDetailMapper.insertStudentCourseTimeTableBatch(courseTimeTableIdList, account ,termYear ,termOrder);
    }

    public List<StudentCourseTable> selectStudentCourseTimeTableRelative(StudentCourseTable studentCourseTimeTable){
        return courseTimeTableDetailMapper.selectStudentCourseTimeTableRelative(studentCourseTimeTable);
    }

}
