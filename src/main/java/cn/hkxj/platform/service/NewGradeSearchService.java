package cn.hkxj.platform.service;

import cn.hkxj.platform.dao.CourseDao;
import cn.hkxj.platform.dao.GradeDao;
import cn.hkxj.platform.mapper.CourseMapper;
import cn.hkxj.platform.mapper.GradeMapper;
import cn.hkxj.platform.pojo.*;
import cn.hkxj.platform.spider.NewUrpSpider;
import cn.hkxj.platform.spider.model.VerifyCode;
import cn.hkxj.platform.spider.newmodel.CurrentGrade;
import cn.hkxj.platform.spider.newmodel.UrpGrade;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.concurrent.*;
import java.util.stream.Collectors;

/**
 * @author Yuki
 * @date 2019/8/1 17:05
 */
@Slf4j
@Service
public class NewGradeSearchService {

    @Resource
    private NewUrpSpiderService newUrpSpiderService;
    @Resource
    private GradeMapper gradeMapper;
    @Resource
    private CourseMapper courseMapper;
    @Resource
    private GradeDao gradeDao;
    @Resource
    private CourseDao courseDao;

    private static final Term currentTerm = new Term(2018, 2019, 2);

    public GradeSearchResult getCurrentGrade(Student student){
        CurrentGrade currentGrade = getCurrentGradeFromSpider(student);
        GradeSearchResult result = new GradeSearchResult();
        if(currentGrade == null){
            result.setData(getGradeFromDB(student));
            result.setUpdate(false);
            return result;
        }
        boolean update = saveCurrentGradeToDb(student, currentGrade);
        result.setData(urpGradeConvertToGradeAndCourse(currentGrade));
        result.setUpdate(update);
        return result;
    }

    List<GradeAndCourse> getGradeFromDB(Student student){
        List<Grade> currentGrade = gradeDao.getCurrentGrade(student);
        currentGrade.removeIf(grade -> !grade.getYear().equals(2018) || !grade.getTerm().equals((byte) 2));

        if(currentGrade.isEmpty()){
            return Lists.newArrayListWithExpectedSize(0);
        }

        List<String> courseUidList = currentGrade.stream()
                .map(Grade::getCourseId)
                .collect(Collectors.toList());
        final List<Course> courseList = courseDao.selectCourseByUid(courseUidList);

        return currentGrade.stream()
                .flatMap(grade -> courseList.stream()
                        .filter(course -> grade.getCourseId().equals(course.getUid()))
                        .map(course -> new GradeAndCourse(grade, course, currentTerm)))
                .collect(Collectors.toList());
    }

    public CurrentGrade getCurrentGradeFromSpider(Student student){
        CurrentGrade currentGrade = newUrpSpiderService.getCurrentTermGrade(student.getAccount().toString(),
                student.getPassword());
        if(currentGrade.getList() == null){
            return null;
        }
        return currentGrade;
    }

    public boolean saveCurrentGradeToDb(Student student, CurrentGrade currentGrade){
        //返回是否有更新的标识位
        boolean haveUpdate = false;
        for(UrpGrade urpGrade : currentGrade.getList()){
            String uid = urpGrade.getId().getCourseNumber();
            if(!courseMapper.ifExistCourse(uid)){
                Course course = newUrpSpiderService.getCourseFromSpider(student, uid);
                courseMapper.insert(course);
            }
            if(gradeMapper.ifExistGrade(student.getAccount(), uid) == 0){
                haveUpdate = true;
                Grade grade = urpGrade.convertToGrade();
                gradeMapper.insert(grade);
            }
        }
        return haveUpdate;
    }

    private List<GradeAndCourse> urpGradeConvertToGradeAndCourse(CurrentGrade currentGrade){
        List<UrpGrade> urpGrades = currentGrade.getList();
        List<GradeAndCourse> result = Lists.newArrayList();
        for (UrpGrade urpGrade : urpGrades) {
            GradeAndCourse gradeAndCourse = new GradeAndCourse();
            Grade grade = urpGrade.convertToGrade();
            Course course = courseMapper.selectNameByUid(urpGrade.getId().getCourseNumber()).get(0);
            Term term = urpGrade.getTermForUrpGrade();
            gradeAndCourse.setGrade(grade);
            gradeAndCourse.setCourse(course);
            gradeAndCourse.setTerm(term);
            result.add(gradeAndCourse);
        }
        return result;
    }

    public static String gradeListToText(List<GradeAndCourse> studentGrades) {
        StringBuilder buffer = new StringBuilder();
        boolean i = true;
        if (studentGrades.size() == 0) {
            buffer.append("尚无本学期成绩");
        } else {
            AllGradeAndCourse allGradeAndCourse = new AllGradeAndCourse();
            allGradeAndCourse.addGradeAndCourse(studentGrades);
            for (GradeAndCourse gradeAndCourse : allGradeAndCourse.getCurrentTermGrade()) {
                if (i) {
                    i = false;
                    buffer.append("- - - - - - - - - - - - - -\n");
                    buffer.append("|").append(gradeAndCourse.getGrade().getYear()).append("学年，第").append(gradeAndCourse.getGrade().getTerm()).append("学期|\n");
                    buffer.append("- - - - - - - - - - - - - -\n\n");
                }
                int grade = gradeAndCourse.getGrade().getScore();
                buffer.append("考试名称：").append(gradeAndCourse.getCourse().getName()).append("\n")
                        .append("成绩：").append(grade == -1 ? "" : grade).append("   学分：")
                        .append(((float) gradeAndCourse.getGrade().getPoint())).append("\n\n");
            }
        }
        return buffer.toString();
    }

    public String getElectiveCourseText(List<GradeAndCourse> studentGrades) {
        StringBuffer buffer = new StringBuffer();
        if (studentGrades.size() == 0) {
            buffer.append("尚无选修课成绩");
        } else {
            int allPoint = 0;
            for (GradeAndCourse gradeAndCourse : studentGrades) {
                allPoint += gradeAndCourse.getCourse().getCredit();
                float grade = gradeAndCourse.getGrade().getScore();
                buffer.append("考试名称：").append(gradeAndCourse.getCourse().getName()).append("\n")
                        .append("成绩：").append(grade == -1 ? "" : grade / 100).append("   学分：")
                        .append(((float) gradeAndCourse.getGrade().getPoint()) / 10).append("\n\n");
            }
            allPoint /= 10;
            buffer.insert(0, "- - - - - - - - - - - - - - - \n");
            int num = 0;
            if (allPoint < 7) num = 7 - allPoint;
            buffer.insert(0, "你选修的公共选修课共" + allPoint + "学分\n还差" + num + "学分\n");
            buffer.insert(0, "- - - - - - - - - - - - - - - \n");
        }
        buffer.append("\n 查询仅供参考，以教务网为准，如有疑问微信联系：吴彦祖【hkdhd666】\n（有同学反映，大学英语提高班也是选修课）");
        return buffer.toString();
    }
}
