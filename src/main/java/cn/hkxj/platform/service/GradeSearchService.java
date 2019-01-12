package cn.hkxj.platform.service;

import cn.hkxj.platform.exceptions.PasswordUncorrectException;
import cn.hkxj.platform.exceptions.SpiderException;
import cn.hkxj.platform.mapper.CourseMapper;
import cn.hkxj.platform.mapper.GradeMapper;
import cn.hkxj.platform.pojo.AllGradeAndCourse;
import cn.hkxj.platform.pojo.Course;
import cn.hkxj.platform.pojo.Grade;
import cn.hkxj.platform.pojo.GradeAndCourse;
import cn.hkxj.platform.pojo.Student;
import cn.hkxj.platform.spider.UrpCourseSpider;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

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
    private static ExecutorService executorService = Executors.newFixedThreadPool(10);

	/**
	 * 通过appspider返回学生本学期的全部成绩
     * @param student 学生账户
	 * @return gradeAndCoourseList 学生的全部成绩
	 */
    public List<GradeAndCourse> getAllGradeList(Student student) {
        // 先查这个学生有美誉成绩 有的话返回  没有的话走爬虫;

        List<GradeAndCourse> gradeFromSpider = getGradeFromSpiderAsync(student);
        ExecutorService singleThreadPool = Executors.newSingleThreadExecutor();
        singleThreadPool.execute(() -> saveGradeAndCourse(student, gradeFromSpider));
        return gradeFromSpider;
	}

	/**
	 * 将本学期的成绩数据存储于数据库，同时适用于自动更新，返回最新爬取更新的成绩集合用于自动更新的回复功能
     * @param student 学生账户
     * @param gradeAndCourseList 学生的全部成绩
     * @return 返回最新爬取更新的成绩集合
	 */
    public List<GradeAndCourse> saveGradeAndCourse(Student student, List<GradeAndCourse> gradeAndCourseList) {
        List<GradeAndCourse> studentGrades=new ArrayList<>();
        UrpCourseSpider urpCourseSpider = new UrpCourseSpider(student.getAccount(), student.getPassword());
        for (GradeAndCourse gradeAndCourse : gradeAndCourseList) {
            Course course = gradeAndCourse.getCourse();
            Grade grade = gradeAndCourse.getGrade();
            String uid = course.getUid();
            if (!courseMapper.ifExistCourse(uid)) {
                course.setAcademy(urpCourseSpider.getAcademyId(uid));
                courseMapper.insert(course);
            }
            if (grade.getScore() == -1) {
                continue;
            }
            if (gradeMapper.ifExistGrade(student.getAccount(), grade.getCourseId()) == 0) {
                courseMapper.insertStudentAndCourse(student.getAccount(), uid);
                gradeMapper.insert(grade);
                studentGrades.add(gradeAndCourse);
            }
		}
		return studentGrades;
	}

	/**
	 * 获取本学期的成绩用于回复
	 * 同时启用一个新线程进行成绩保存
     * @param student 学生的全部成绩与课程
	 */
    public List<GradeAndCourse> getCurrentTermGradeAsync(Student student) {
        List<GradeAndCourse> allGradeList = getAllGradeList(student);
        List<GradeAndCourse> studentGrades = new ArrayList<>();
        for (GradeAndCourse gradeAndCourse : allGradeList) {
			if(gradeAndCourse.getGrade().getYear()==2018){
				studentGrades.add(gradeAndCourse);
			}
		}
		return studentGrades;
	}

    public List<GradeAndCourse> getCurrentTermGradeSync(Student student) {
        List<GradeAndCourse> allGradeList = getGradeFromSpiderSync(student);
        List<GradeAndCourse> studentGrades = new ArrayList<>();
        for (GradeAndCourse gradeAndCourse : allGradeList) {
            if (gradeAndCourse.getGrade().getYear() == 2018) {
                studentGrades.add(gradeAndCourse);
            }
        }
        return studentGrades;
    }

    public List<GradeAndCourse> getGradeFromSpiderAsync(Student student) {
        CompletionService<List<GradeAndCourse>> spiderExecutorService = new ExecutorCompletionService<>(executorService);

        spiderExecutorService.submit(appSpiderTask(student));
        spiderExecutorService.submit(urpSpiderTask(student));
        HashSet<GradeAndCourse> resultSet = Sets.newHashSet();
        ArrayList<GradeAndCourse> prepare = Lists.newArrayList();
        try {
            for (int x = 0; x < 2; x++) {
                // 结果不为空的时候  如果result已经记录全部插入到
                List<GradeAndCourse> gradeAndCourses = spiderExecutorService.take().get();
                if (!CollectionUtils.isEmpty(gradeAndCourses)) {
                    prepare.addAll(gradeAndCourses);
                }
            }
            for (GradeAndCourse gradeAndCourse : prepare) {
                gradeAndCourse.getCourse().setAcademy(null);
                if (resultSet.contains(gradeAndCourse)) {
                    if (gradeAndCourse.getGrade().getScore() != -1) {
                        //这里排除app有成绩但是教务网没成绩  app成绩被顶掉的情况
                        resultSet.remove(gradeAndCourse);
                        resultSet.add(gradeAndCourse);
                    }
                } else {
                    resultSet.add(gradeAndCourse);
                }
            }


        } catch (InterruptedException | ExecutionException e) {
            log.error("app spider execute error", e);
        }
        return Lists.newArrayList(resultSet);
    }


    public List<GradeAndCourse> getGradeFromSpiderSync(Student student) {
        List<GradeAndCourse> currentFromApp = new ArrayList<>();
        try {
            AllGradeAndCourse gradeAndCourseByAccount = appSpiderService.getGradeAndCourseByAccount(student.getAccount());
            currentFromApp = gradeAndCourseByAccount.getCurrentTermGrade();
        } catch (PasswordUncorrectException | SpiderException e) {
            log.error("account {} app spider error {}", student.getAccount(), e.getMessage());

        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        //将app和教务网数据整合到一起

        ArrayList<GradeAndCourse> currentFromUrp = urpSpiderService.getCurrentGrade(student);

        ArrayList<GradeAndCourse> result = Lists.newArrayList();
        if (CollectionUtils.isEmpty(currentFromApp)) {
            return currentFromUrp;
        }

        if (CollectionUtils.isEmpty(currentFromUrp)) {
            return currentFromApp;
        }

        for (GradeAndCourse fromApp : currentFromApp) {
            for (GradeAndCourse fromUrp : currentFromUrp) {
                if (fromApp.equals(fromUrp)) {
                    result.add(fromUrp.getGrade().getScore() == -1 ? fromApp : fromUrp);
                }
            }

        }


        return result;
    }


    private Callable<List<GradeAndCourse>> appSpiderTask(Student student) {
        return () -> {
            try {
                AllGradeAndCourse gradeAndCourseByAccount = appSpiderService.getGradeAndCourseByAccount(student.getAccount());
                return gradeAndCourseByAccount.getCurrentTermGrade();
            } catch (PasswordUncorrectException | SpiderException e) {
                log.error("account {} app spider error {}", student.getAccount(), e.getMessage());

            } catch (Exception e) {
                log.error(e.getMessage(), e);
            }
            return Lists.newArrayList();
        };
    }

    private Callable<List<GradeAndCourse>> urpSpiderTask(Student student) {
        return () -> urpSpiderService.getCurrentGrade(student);
    }


}
