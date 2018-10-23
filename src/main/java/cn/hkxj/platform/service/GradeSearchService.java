package cn.hkxj.platform.service;

import cn.hkxj.platform.mapper.CourseMapper;
import cn.hkxj.platform.mapper.GradeMapper;
import cn.hkxj.platform.pojo.AllGradeAndCourse;
import cn.hkxj.platform.pojo.Course;
import cn.hkxj.platform.pojo.Grade;
import cn.hkxj.platform.spider.AppSpider;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author junrong.chen
 * @date 2018/9/16
 */
@Slf4j
@Service("gradeSearchService")
public class GradeSearchService {
	@Resource
	private CourseMapper courseMapper;
	@Resource
	private GradeMapper gradeMapper;

	/**
	 * 只返回本学期的成绩，这个数据暂时不存在数据库
	 *
	 * @param account 教务网账号
	 * @param password 密码
	 */
	public void getCurrentGrade(int account, String password){
		//暂定只要是半学期的都应该直接查询最新的数据
		//先查询数据库中有没有这个数据，有就返回（如果要查本学期的数据，怎么判断知道数据有没有更新完）
		//如果没有从App中进行抓取，要先判断这个他的app账号是否正确，不正确从校务网抓
		//抓到的数据保存到数据并且返回结果（并行执行）在密集查成绩的期间要考虑是否需要存库这个功能
		AppSpider appSpider = new AppSpider(account);
		appSpider.getToken();
		AllGradeAndCourse gradeAndCourse = appSpider.getGradeAndCourse();
		for (AllGradeAndCourse.GradeAndCourse andCourse : gradeAndCourse.getCurrentTermGrade()) {
//			gradeMapper.insert(andCourse.getGrade());
			saveCourse(account, andCourse.getCourse());
		}


	}

	private void saveCourse(int account, Course course){
		courseMapper.insert(course);
		courseMapper.insertStudentAndCourse(account, course.getUid());
	}
}
