package cn.hkxj.platform.service.impl;

import cn.hkxj.platform.mapper.*;
import cn.hkxj.platform.pojo.*;
import cn.hkxj.platform.service.CourseService;
import cn.hkxj.platform.utils.DateUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Objects;

/**
 * @author Yuki
 * @date 2018/10/10 23:35
 */
@Slf4j
@Service
public class CourseServiceImpl implements CourseService{

    private StudentMapper studentMapper;
    private ClassesMapper classesMapper;
    private CourseMapper courseMapper;
    private CourseTimeTableMapper courseTimeTableMapper;


    @Autowired
    private CourseServiceImpl(StudentMapper studentMapper, ClassesMapper classesMapper, CourseMapper courseMapper,
                              CourseTimeTableMapper courseTimeTableMapper){
        this.studentMapper = studentMapper;
        this.classesMapper = classesMapper;
        this.courseMapper = courseMapper;
        this.courseTimeTableMapper = courseTimeTableMapper;
    }

    @Override
    public Course getCourseById(Integer id) {
        return courseMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<CourseTimeTable> getCoursesCurrentDay(Integer account) {
        int year = DateUtils.getCurrentYear();
        int week = DateUtils.getCurrentWeek();
        int day = DateUtils.getCurrentDay();

        Student student = studentMapper.selectByAccount(account);
        if(checkStudentIsNull(student)){ return null; }
        List<Integer> courseTimetableIds = classesMapper.getAllCourseTimetableIds(student.getClasses().getId());
        if(Objects.isNull(courseTimetableIds) || courseTimetableIds.size() == 0){
            return null;
        }
        log.info("query currentday course schedule --account {}, academy {} day{}", account, student.getClasses().getAcademy(), day);

        CourseTimeTableExample example = new CourseTimeTableExample();
        example.createCriteria()
                .andYearEqualTo(year)
                .andEndGreaterThanOrEqualTo(week)
                .andStartLessThanOrEqualTo(week)
                .andWeekEqualTo(day)
                .andTermEqualTo(2)
                .andIdIn(courseTimetableIds);
        return courseTimeTableMapper.selectByExample(example);
    }

    @Override
    public List<CourseTimeTable> getCoursesByAccount(Integer account) {
        int year = DateUtils.getCurrentYear();
        int week = DateUtils.getCurrentWeek();
        Student student = studentMapper.selectByAccount(account);
        if(checkStudentIsNull(student)){ return null; }
        log.info("query this week course schedule --account {},academy {} week{}", account, student.getClasses().getAcademy(), week);
        List<CourseTimeTable> courseTimeTables = getCourseTimeTables(student.getClasses(), year, week);
        if(courseTimeTables == null){ return null; }

        return courseTimeTables;
    }

    /**
     * 判断对应的学号是否有课程信息
     * @param account 学号
     * @return boolean
     */
    @Override
    public boolean isHaveCourses(Integer account){
        Student student = studentMapper.selectByAccount(account);
        if(!Objects.isNull(student.getClasses())){
            List<Integer> courseIds = courseMapper.getCourseIdsByClassId(student.getClasses().getId());
            return courseIds.size() != 0;
        }
        return false;
    }

    @Override
    public String toText(List<CourseTimeTable> courseTimeTables){
        if(courseTimeTables == null || courseTimeTables.size() == 0) return "课表空空如也";
        StringBuilder builder = new StringBuilder();
        courseTimeTables.sort(Comparator.comparing(CourseTimeTable::getOrder));
        int count = 0;
        int length = courseTimeTables.size();
        for(CourseTimeTable courseTimeTable : courseTimeTables){
            if(courseTimeTable == null) continue;
            count++;
            builder.append("第").append(courseTimeTable.getOrder()).append("节").append("\\n")
                    .append(courseTimeTable.getCourse().getName()).append("  ")
                    .append(courseTimeTable.getRoom().getName());
            if(count != length){
                builder.append("\\n\\n");
            }
        }
        builder.append("点击查看更多");
        return builder.toString();
    }

    private List<CourseTimeTable> getCourseTimeTables(Classes classes, Integer year, Integer week){
        CourseTimeTableExample example = new CourseTimeTableExample();
        List<Integer> courseTimetableIds = classesMapper.getAllCourseTimetableIds(classes.getId());
        if(courseTimetableIds == null || courseTimetableIds.size() == 0) return null;
        example.createCriteria()
                .andIdIn(courseTimetableIds)
                .andStartLessThanOrEqualTo(week)
                .andEndGreaterThanOrEqualTo(week)
                .andTermEqualTo(2)
                .andYearEqualTo(year);
        return courseTimeTableMapper.selectByExample(example);
    }

    private boolean checkStudentIsNull(Student student){
        if(Objects.isNull(student)){ return true; }
        if(Objects.isNull(student.getClasses())){
            log.info("no relative class information for this student --account {}", student.getAccount());
            return true;
        }
        return false;
    }
}
