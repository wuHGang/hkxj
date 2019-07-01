package cn.hkxj.platform.service;

import cn.hkxj.platform.dao.CourseDao;
import cn.hkxj.platform.dao.GradeDao;
import cn.hkxj.platform.mapper.CourseMapper;
import cn.hkxj.platform.mapper.GradeMapper;
import cn.hkxj.platform.pojo.*;
import cn.hkxj.platform.pojo.constant.Academy;
import cn.hkxj.platform.spider.UrpCourse;
import cn.hkxj.platform.spider.UrpCourseSpider;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;
import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author xie
 * @date 2018/11/10
 */
@Slf4j
@Service("gradeSearchService")
public class GradeSearchService {
    @Resource
    private CourseMapper courseMapper;
    @Resource
    private GradeMapper gradeMapper;
    @Resource
    private UrpSpiderService urpSpiderService;
    @Resource
    private AppSpiderService appSpiderService;
    @Resource
    private GradeDao gradeDao;
    @Resource
    private CourseDao courseDao;

    private static ExecutorService executorService = Executors.newFixedThreadPool(10);
    private static ExecutorService saveDBexecutorService = Executors.newSingleThreadExecutor();
    private static final Term currentTerm = new Term(2018, 2019, 2);

    public List<GradeAndCourse> getEverGradeFromSpider(Student student) {
        CompletionService<List<GradeAndCourse>> spiderExecutorService = new ExecutorCompletionService<>(executorService);

        spiderExecutorService.submit(() -> appSpiderService.getGradeAndCourseByAccount(student.getAccount()).getEverTermGrade());
        spiderExecutorService.submit(() -> urpSpiderService.getEverGrade(student));
        List<GradeAndCourse> gradeAndCourses = mergeResult(spiderExecutorService);
        saveDBexecutorService.submit(() -> saveGradeAndCourse(student, gradeAndCourses));
        return gradeAndCourses;
    }


    public List<GradeAndCourse> getCurrentGradeFromSpider(Student student) {
        CompletionService<List<GradeAndCourse>> spiderExecutorService = new ExecutorCompletionService<>(executorService);

        spiderExecutorService.submit(() -> appSpiderService.getGradeAndCourseByAccount(student.getAccount()).getCurrentTermGrade());
        spiderExecutorService.submit(() -> urpSpiderService.getCurrentGrade(student));
        final List<GradeAndCourse> gradeAndCourses = mergeResult(spiderExecutorService);

        saveDBexecutorService.submit(() -> saveGradeAndCourse(student, gradeAndCourses));

        filterMergeResultForCurrentTerm(gradeAndCourses);
        if(gradeAndCourses.isEmpty()){
            return getGradeFromDB(student);
        }

        filterMergeResultForCurrentTerm(gradeAndCourses);

        return gradeAndCourses;
    }

    List<GradeAndCourse> getGradeFromDB(Student student){
        List<Grade> currentGrade = gradeDao.getCurrentGrade(student);
        currentGrade.removeIf(grade -> !grade.getYear().equals(2018) && !grade.getTerm().equals((byte) 2));
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

    /**
     * 将本学期的成绩数据存储于数据库，同时适用于自动更新，返回最新爬取更新的成绩集合用于自动更新的回复功能
     *
     * @param student            学生账户
     * @param gradeAndCourseList 学生的全部成绩
     * @return 返回最新爬取更新的成绩集合
     */
    public List<GradeAndCourse> saveGradeAndCourse(Student student, List<GradeAndCourse> gradeAndCourseList) {
        List<GradeAndCourse> studentGrades = new ArrayList<>();
        UrpCourseSpider urpCourseSpider = new UrpCourseSpider(student.getAccount(), student.getPassword());
        for (GradeAndCourse gradeAndCourse : gradeAndCourseList) {
            Course course = gradeAndCourse.getCourse();
            Grade grade = gradeAndCourse.getGrade();
            String uid = course.getUid();
            if (!courseMapper.ifExistCourse(uid)) {
                UrpCourse urpCourse = urpCourseSpider.getUrpCourse(uid);
                course.setAcademy(Academy.getAcademyByName(urpCourse.getAcademyName()));
                courseMapper.insert(course);
            }

            if (gradeMapper.ifExistGrade(student.getAccount(), grade.getCourseId()) == 0) {
                courseMapper.insertStudentAndCourse(student.getAccount(), uid);
                gradeMapper.insert(grade);
                studentGrades.add(gradeAndCourse);
            }
        }
        return studentGrades;
    }

    private List<GradeAndCourse> mergeResult(CompletionService<List<GradeAndCourse>> spiderExecutorService) {
        Set<GradeAndCourse> resultSet = Sets.newHashSet();
        List<List<GradeAndCourse>> resultList = Lists.newArrayList();
        try {
            for (int x = 0; x < 2; x++) {
                // 结果不为空的时候  如果result已经记录全部插入到
                resultList.add(spiderExecutorService.take().get());
            }

            for (List<GradeAndCourse> gradeAndCourses : resultList) {
                for (GradeAndCourse gradeAndCourse : gradeAndCourses) {
                    if (resultSet.contains(gradeAndCourse) && gradeAndCourse.getGrade().getScore() != -1) {
                        //这里排除app有成绩但是教务网没成绩  app成绩被顶掉的情况
                        resultSet.remove(gradeAndCourse);
                        resultSet.add(gradeAndCourse);
                    } else {
                        resultSet.add(gradeAndCourse);
                    }
                }
            }

        } catch (InterruptedException | ExecutionException e) {
            log.error("app spider execute error", e);
        }

        return Lists.newArrayList(resultSet);
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
                        .append("成绩：").append(grade == -1 ? "" : grade / 10).append("   学分：")
                        .append(((float) gradeAndCourse.getGrade().getPoint()) / 10).append("\n\n");
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

    private void filterMergeResultForCurrentTerm(List<GradeAndCourse> mergeResult) {
        //这里硬编码了一个Term对象，数据是当前学期的信息

        //因为app爬虫的数据可能有问题，处理合并过后错误的数据
        mergeResult.removeIf(gradeAndCourse -> !Objects.deepEquals(currentTerm, gradeAndCourse.getTerm()));
    }
}
