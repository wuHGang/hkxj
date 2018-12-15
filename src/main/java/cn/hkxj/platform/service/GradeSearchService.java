package cn.hkxj.platform.service;

import cn.hkxj.platform.exceptions.PasswordUncorrectException;
import cn.hkxj.platform.mapper.CourseMapper;
import cn.hkxj.platform.mapper.GradeMapper;
import cn.hkxj.platform.pojo.Academy;
import cn.hkxj.platform.pojo.AllGradeAndCourse;
import cn.hkxj.platform.pojo.Course;
import cn.hkxj.platform.pojo.Grade;
import cn.hkxj.platform.pojo.Student;
import cn.hkxj.platform.spider.AppSpider;
import cn.hkxj.platform.spider.UrpCourseSpider;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
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


	/**
	 * 通过appspider返回学生本学期的全部成绩
     * @param student 学生账户
	 * @return gradeAndCoourseList 学生的全部成绩
	 */
    public AllGradeAndCourse getAllGradeList(Student student) {
        // 先查这个学生有美誉成绩 有的话返回  没有的话走爬虫;

        AllGradeAndCourse gradeAndCourseList = getGradeFromSpider(student);
        ExecutorService singleThreadPool = Executors.newSingleThreadExecutor();
        singleThreadPool.execute(() -> saveGradeAndCourse(student, gradeAndCourseList));
        return gradeAndCourseList;
	}

	/**
	 * 将本学期的成绩数据存储于数据库，同时适用于自动更新
     * @param student 学生账户
     * @param gradeAndCourseList 学生的全部成绩
	 */
    public void saveGradeAndCourse(Student student, AllGradeAndCourse gradeAndCourseList) {
        UrpCourseSpider urpCourseSpider = new UrpCourseSpider(student.getAccount(), student.getPassword());
        for (AllGradeAndCourse.GradeAndCourse gradeAndCourse : gradeAndCourseList.getCurrentTermGrade()) {
            Course course = gradeAndCourse.getCourse();
            Grade grade = gradeAndCourse.getGrade();
            String uid = course.getUid();
            if (!courseMapper.ifExistCourse(uid)) {
                course.setAcademy(Academy.getAcademyByCode(urpCourseSpider.getAcademyId(uid)));
                courseMapper.insert(course);
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
    public List<AllGradeAndCourse.GradeAndCourse> getCurrentTermGrade(Student student) {
        AllGradeAndCourse allGradeList = getAllGradeList(student);
		List<AllGradeAndCourse.GradeAndCourse> studentGrades=new ArrayList<>();
        for (AllGradeAndCourse.GradeAndCourse gradeAndCourse : allGradeList.getCurrentTermGrade()) {
			if(gradeAndCourse.getGrade().getYear()==2018){
				studentGrades.add(gradeAndCourse);
			}
		}
		return studentGrades;
	}

    private AllGradeAndCourse getGradeFromSpider(Student student) {
        AppSpider appSpider = new AppSpider(student.getAccount());
        try {
            appSpider.getToken();
            return appSpider.getGradeAndCourse();
        } catch (PasswordUncorrectException e) {
            log.error("error password");
        } catch (IllegalArgumentException e) {
            log.error(e.getMessage(), e);
        }
        return new AllGradeAndCourse();
    }

}
