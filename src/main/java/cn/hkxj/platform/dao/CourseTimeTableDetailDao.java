package cn.hkxj.platform.dao;

import cn.hkxj.platform.mapper.CourseTimeTableDetailMapper;
import cn.hkxj.platform.pojo.CourseTimeTableDetail;
import cn.hkxj.platform.pojo.SchoolTime;
import cn.hkxj.platform.pojo.StudentCourseTimeTable;
import cn.hkxj.platform.pojo.Term;
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
        Term term = detail.getTermForCourseTimeTableDetail();
        example.createCriteria()
                .andTermOrderEqualTo(detail.getTermOrder())
                .andTermYearEqualTo(term.getTermYear())
                .andCourseIdEqualTo(detail.getCourseId())
                .andRoomNameEqualTo(detail.getRoomName())
                .andStartWeekEqualTo(detail.getStartWeek())
                .andEndWeekEqualTo(detail.getEndWeek())
                .andDayEqualTo(detail.getDay())
                .andWeekEqualTo(detail.getWeek())
                .andOrderEqualTo(detail.getOrder());
        return courseTimeTableDetailMapper.selectByExample(example);
    }

    public void insertStudentCourseTimeTableBatch(List<Integer> courseTimeTableIdList, int account,  String termYear,  int termOrder){
        courseTimeTableDetailMapper.insertStudentCourseTimeTableBatch(courseTimeTableIdList, account ,termYear ,termOrder);
    }

    public List<StudentCourseTimeTable> selectStudentCourseTimeTableRelative(StudentCourseTimeTable studentCourseTimeTable){
        return courseTimeTableDetailMapper.selectStudentCourseTimeTableRelative(studentCourseTimeTable);
    }

}
