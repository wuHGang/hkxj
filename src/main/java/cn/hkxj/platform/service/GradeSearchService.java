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

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
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


	/**
	 * 通过appspider返回学生本学期的全部成绩
     * @param student 学生账户
	 * @return gradeAndCoourseList 学生的全部成绩
	 */
    public List<GradeAndCourse> getAllGradeList(Student student) {
        // 先查这个学生有美誉成绩 有的话返回  没有的话走爬虫;

        List<GradeAndCourse> gradeFromSpider = getGradeFromSpider(student);
        ExecutorService singleThreadPool = Executors.newSingleThreadExecutor();
        singleThreadPool.execute(() -> saveGradeAndCourse(student, gradeFromSpider));
        return gradeFromSpider;
	}

	/**
	 * 将本学期的成绩数据存储于数据库，同时适用于自动更新
     * @param student 学生账户
     * @param gradeAndCourseList 学生的全部成绩
	 */
    public void saveGradeAndCourse(Student student, List<GradeAndCourse> gradeAndCourseList) {
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
            }
		}
	}

	/**
	 * 获取本学期的成绩用于回复
	 * 同时启用一个新线程进行成绩保存
     * @param student 学生的全部成绩与课程
	 */
    public List<GradeAndCourse> getCurrentTermGrade(Student student) {
        List<GradeAndCourse> allGradeList = getAllGradeList(student);
        List<GradeAndCourse> studentGrades = new ArrayList<>();
        for (GradeAndCourse gradeAndCourse : allGradeList) {
			if(gradeAndCourse.getGrade().getYear()==2018){
				studentGrades.add(gradeAndCourse);
			}
		}
		return studentGrades;
	}

    private List<GradeAndCourse> getGradeFromSpider(Student student) {
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

        HashSet<GradeAndCourse> resulSet = Sets.newHashSet(currentFromApp);
        resulSet.addAll(currentFromUrp);
        for (GradeAndCourse fromApp : currentFromApp) {
            for (GradeAndCourse fromUrp : currentFromUrp) {
                if (fromApp.equals(fromUrp)) {
                    resulSet.remove(fromUrp);
                    resulSet.add(fromUrp.getGrade().getScore() == -1 ? fromApp : fromUrp);
                }
            }
        }


        return Lists.newArrayList(resulSet);
    }

}
