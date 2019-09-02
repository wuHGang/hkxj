package cn.hkxj.platform.dao;

import cn.hkxj.platform.mapper.CourseTimeTableDetailMapper;
import cn.hkxj.platform.pojo.CourseTimeTableDetail;
import cn.hkxj.platform.pojo.SchoolTime;
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

    public void insertClassesCourseTimeTable(int classesId, int courseTimeTableDetailId){
        courseTimeTableDetailMapper.insertClassesCourseTimeTable(classesId, courseTimeTableDetailId);
    }

    public void insertCourseTimeTableDetail(CourseTimeTableDetail detail){
        courseTimeTableDetailMapper.insertSelective(detail);
    }

    public List<CourseTimeTableDetail> getCourseTimeTableDetailForCurrentDay(int classesId, SchoolTime schoolTime){
        List<Integer> detailIds = courseTimeTableDetailMapper.getCourseTimeTableDetailIdsByClassId(classesId);
        if(detailIds.size() == 0) return Lists.newArrayList();
        CourseTimeTableDetailExample example = new CourseTimeTableDetailExample();
        example.createCriteria()
                .andIdIn(detailIds)
                .andTermYearEqualTo(schoolTime.getTerm().getTermYear())
                .andTermOrderEqualTo(schoolTime.getTerm().getOrder())
                .andDayEqualTo(schoolTime.getDay())
                .andStartWeekLessThanOrEqualTo(schoolTime.getWeek())
                .andEndWeekGreaterThanOrEqualTo(schoolTime.getWeek())
                .andDistinctNotEqualTo(DateUtils.getContraryDistinct());
        return courseTimeTableDetailMapper.selectByExample(example);
    }

    public List<CourseTimeTableDetail> getCourseTimeTableDetailForCurrentTerm(int classesId, SchoolTime schoolTime){
        List<Integer> detailIds = courseTimeTableDetailMapper.getCourseTimeTableDetailIdsByClassId(classesId);
        if(detailIds.size() == 0) return Lists.newArrayList();
        CourseTimeTableDetailExample example = new CourseTimeTableDetailExample();
        example.createCriteria()
                .andIdIn(detailIds)
                .andTermYearEqualTo(schoolTime.getTerm().getTermYear())
                .andTermOrderEqualTo(schoolTime.getTerm().getOrder());
        return courseTimeTableDetailMapper.selectByExample(example);
    }

    public List<CourseTimeTableDetail> getCourseTimeTableDetailForCurrentWeek(int classesId, SchoolTime schoolTime){
        List<Integer> detailIds = courseTimeTableDetailMapper.getCourseTimeTableDetailIdsByClassId(classesId);
        if(detailIds.size() == 0) return Lists.newArrayList();
        CourseTimeTableDetailExample example = new CourseTimeTableDetailExample();
        example.createCriteria()
                .andIdIn(detailIds)
                .andTermYearEqualTo(schoolTime.getTerm().getTermYear())
                .andTermOrderEqualTo(schoolTime.getTerm().getOrder())
                .andStartWeekLessThanOrEqualTo(schoolTime.getWeek())
                .andEndWeekGreaterThanOrEqualTo(schoolTime.getWeek())
                .andDistinctNotEqualTo(DateUtils.getContraryDistinct());
        return courseTimeTableDetailMapper.selectByExample(example);
    }

    public boolean ifExistCourseTimeTableDetail(CourseTimeTableDetail detail){
        CourseTimeTableDetailExample example = new CourseTimeTableDetailExample();
        Term term = detail.getTermForCourseTimeTableDetail();
        example.createCriteria()
                .andTermOrderEqualTo(term.getOrder())
                .andTermYearEqualTo(term.getTermYear())
                .andCourseIdEqualTo(detail.getCourseId())
                .andRoomNameEqualTo(detail.getRoomName())
                .andDayEqualTo(detail.getDay())
                .andWeekEqualTo(detail.getWeek());
        return courseTimeTableDetailMapper.countByExample(example) == 1;
    }

    public CourseTimeTableDetail getCourseTimeTableDetail(String courseId, String roomName, Term term){
        CourseTimeTableDetailExample example = new CourseTimeTableDetailExample();
        example.createCriteria()
                .andTermOrderEqualTo(term.getOrder())
                .andTermYearEqualTo(term.getTermYear())
                .andCourseIdEqualTo(courseId)
                .andRoomNameEqualTo(roomName);
        return courseTimeTableDetailMapper.selectByExample(example).stream().findFirst().orElse(null);
    }

    public CourseTimeTableDetail getCourseTimeTableDetailById(int id){
        return courseTimeTableDetailMapper.selectByPrimaryKey(id);
    }

    public void updateCourseTimeTableBasicInfoId(CourseTimeTableDetail detail){
        courseTimeTableDetailMapper.updateByPrimaryKeySelective(detail);
    }

    /**
     * 将从爬虫爬取到的数据判断是需要存入数据库还是从数据库中进行获取
     * @param convertFromSpider 从爬虫爬取的信息中转化的考试实体
     * @return 考试实体
     */
    public CourseTimeTableDetail saveOrGetCourseTimeTableDetailFromDb(CourseTimeTableDetail convertFromSpider){
        if(!ifExistCourseTimeTableDetail(convertFromSpider)){
            insertCourseTimeTableDetail(convertFromSpider);
        } else {
            convertFromSpider = getCourseTimeTableDetail(convertFromSpider.getCourseId(), convertFromSpider.getRoomName(),
                    convertFromSpider.getTermForCourseTimeTableDetail());
        }
        return convertFromSpider;
    }
}
