package cn.hkxj.platform.service;

import cn.hkxj.platform.dao.*;
import cn.hkxj.platform.mapper.CourseMapper;
import cn.hkxj.platform.pojo.*;
import cn.hkxj.platform.spider.newmodel.CurrentGrade;
import cn.hkxj.platform.spider.newmodel.UrpGeneralGradeForSpider;
import cn.hkxj.platform.spider.newmodel.UrpGradeForSpider;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
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
    private CourseMapper courseMapper;
    @Resource
    private GradeDao gradeDao;
    @Resource
    private CourseDao courseDao;
    @Resource
    private UrpCourseDao urpCourseDao;
    @Resource
    private UrpGradeDao urpGradeDao;
    @Resource
    private UrpGradeDetailDao urpGradeDetailDao;
    @Resource
    private UrpExamDao urpExamDao;

    private static final Term currentTerm = new Term(2018, 2019, 2);

    public GradeSearchResult getCurrentGrade(Student student) {
        CurrentGrade currentGrade = getCurrentGradeFromSpider(student);
        GradeSearchResult result = new GradeSearchResult();
        if (currentGrade == null) {
            result.setData(getUrpGradeAndUrpCourse(student));
            result.setUpdate(false);
            return result;
        }
        result = urpGradeDao.saveCurrentGradeToDb(student, currentGrade);
        return result;
    }

    public List<UrpGradeAndUrpCourse> getUrpGradeAndUrpCourse(Student student) {
        Map<String, NewGrade> courseAndGrade = getGradeFromDb(student);
        List<UrpGradeAndUrpCourse> urpGradeAndUrpCourses = Lists.newArrayList();
        courseAndGrade.forEach((courseId, newGrade) -> {
            UrpCourse urpCourse = urpCourseDao.getUrpCourseByUid(courseId);
            UrpGradeAndUrpCourse urpGradeAndUrpCourse = new UrpGradeAndUrpCourse();
            urpGradeAndUrpCourse.setNewGrade(newGrade);
            urpGradeAndUrpCourse.setUrpCourse(urpCourse);
            urpGradeAndUrpCourse.setTerm(currentTerm);
            urpGradeAndUrpCourses.add(urpGradeAndUrpCourse);
        });
        return urpGradeAndUrpCourses;
    }

    public Map<String, NewGrade> getGradeFromDb(Student student) {
        List<UrpExam> urpExamList = urpExamDao.getOneClassCurrentTermAllUrpExam(student.getClasses().getId(), currentTerm);
        List<UrpGrade> urpGradeList = urpGradeDao.getCurrentTermAllUrpGrade(student.getAccount(),
                urpExamList.stream().map(UrpExam::getId).collect(Collectors.toList()));
        Map<String, NewGrade> results = Maps.newHashMap();
        for (int i = 0, length = urpExamList.size(); i < length; i++) {
            UrpExam exam = urpExamList.get(i);
            UrpGrade grade = urpGradeList.get(i);
            NewGrade newGrade = new NewGrade();
            newGrade.setUrpGrade(grade);
            newGrade.setDetails(urpGradeDetailDao.getUrpGradeDetail(grade.getId()));
            results.put(exam.getCourseId(), newGrade);
        }
        return results;
    }

    public CurrentGrade getCurrentGradeFromSpider(Student student) {
        CurrentGrade currentGrade = newUrpSpiderService.getCurrentTermGrade(student.getAccount().toString(),
                student.getPassword());
        if (CollectionUtils.isEmpty(currentGrade.getList())) {
            return null;
        }
        return currentGrade;
    }

    public static String gradeListToText(List<UrpGradeAndUrpCourse> studentGrades) {
        StringBuilder buffer = new StringBuilder();
        if (studentGrades.size() == 0) {
            buffer.append("尚无本学期成绩");
        } else {
            //因为查询的都是同学期的，所以取第一个元素即可
            Term term = studentGrades.get(0).getTerm();
            buffer.append("- - - - - - - - - - - - - -\n");
            buffer.append("|").append(term.getTermCode()).append("学年，").append(term.getTermName()).append("|\n");
            for (UrpGradeAndUrpCourse urpGradeAndUrpCourse : studentGrades) {
                int grade = urpGradeAndUrpCourse.getNewGrade().getUrpGrade().getScore();
                buffer.append("考试名称：").append(urpGradeAndUrpCourse.getUrpCourse().getKcm()).append("\n")
                        .append("成绩：").append(grade == -1 ? "" : grade).append("   学分：")
                        .append((urpGradeAndUrpCourse.getUrpCourse().getXf())).append("\n\n");
            }
            buffer.append("- - - - - - - - - - - - - -\n\n");
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
