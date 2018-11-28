package cn.hkxj.platform.service;

import cn.hkxj.platform.exceptions.PasswordUncorrectException;
import cn.hkxj.platform.mapper.*;
import cn.hkxj.platform.pojo.*;
import cn.hkxj.platform.spider.AppSpider;
import cn.hkxj.platform.spider.UrpCourseSpider;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.io.IOException;
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
	 * @param account 学生账户
	 * @return gradeAndCoourseList 学生的全部成绩
	 */
	public AllGradeAndCourse getGradeList(int account){
		AppSpider appSpider = new AppSpider(account);
		AllGradeAndCourse gradeAndCourseList=new AllGradeAndCourse();
		try {
			appSpider.getToken();
			gradeAndCourseList = appSpider.getGradeAndCourse();
		} catch (PasswordUncorrectException e) {
			log.error("error password");
		}catch (IllegalArgumentException e){
			log.error(e.getMessage(), e);
		}
			return gradeAndCourseList;
	}

	/**
	 * 将本学期的成绩数据存储于数据库，同时适用于自动更新
	 * @param account 学生账户
	 * @param password 学生密码
	 * @param gradeAndCoourseList 学生的全部成绩
	 */
	public void saveGradeAndCourse(int account,String password,AllGradeAndCourse gradeAndCoourseList )throws IOException {
		//暂定只要是半学期的都应该直接查询最新的数据
		//先查询数据库中有没有这个数据，有就返回（如果要查本学期的数据，怎么判断知道数据有没有更新完）
		//如果没有从App中进行抓取，要先判断这个他的app账号是否正确，不正确从校务网抓
		//抓到的数据保存到数据并且返回结果（并行执行）在密集查成绩的期间要考虑是否需要存库这个功能
		UrpCourseSpider urpCourseSpider=new UrpCourseSpider(account,password);
		for (AllGradeAndCourse.GradeAndCourse gradeAndCourse : gradeAndCoourseList.getCurrentTermGrade()) {
			if(gradeAndCourse.getGrade().getYear()==2018){
				if (!courseMapper.ifExistCourse(gradeAndCourse.getCourse().getUid())) {
					gradeAndCourse.getCourse().setAcademy(urpCourseSpider.getAcademyId(gradeAndCourse.getCourse().getUid()));
					saveCourse(gradeAndCourse.getCourse());
				}
				if (gradeMapper.ifExistGrade(gradeAndCourse.getGrade().getAccount(),gradeAndCourse.getGrade().getCourseId())==0){
					saveGrade(account,gradeAndCourse.getGrade(),gradeAndCourse.getCourse());
				}
			}
		}
	}

	/**
	 * 获取本学期的成绩用于回复
	 * 同时启用一个新线程进行成绩保存
	 * @param gradeAndCoourseList 学生的全部成绩与课程
	 */
	public List<AllGradeAndCourse.GradeAndCourse>  returnGrade(int account,String password,AllGradeAndCourse gradeAndCoourseList) {
		ExecutorService singleThreadPool = Executors.newSingleThreadExecutor();
		singleThreadPool.execute(()-> {
				try {
					saveGradeAndCourse(account,password,gradeAndCoourseList);
				}catch (IOException e){
					log.error(e.getMessage());
				}
		});
		List<AllGradeAndCourse.GradeAndCourse> studentGrades=new ArrayList<>();
		for (AllGradeAndCourse.GradeAndCourse gradeAndCourse : gradeAndCoourseList.getCurrentTermGrade()) {
			if(gradeAndCourse.getGrade().getYear()==2018){
				studentGrades.add(gradeAndCourse);
			}
		}
		return studentGrades;
	}

	/**
	 * @param student 学生信息
	 * @return studentGrades 学生成绩
	 */
	public List<Grade> getStudentGrades(Student student)throws IOException{
//		getCurrentGrade(student.getAccount(),student.getPassword());
		List<Grade> studentGrades=gradeMapper.selectByAccount(student.getAccount());
		return studentGrades;
	}

	/**
	 * 保存未存储过的课程
	 * @param course 课程
	 */
	private void saveCourse( Course course){
		courseMapper.insert(course);
	}

	/**
	 * 保存学生成绩
	 * @param account 学生账号
	 * @param grade 学生成绩
	 * @param course 课程
	 */
	private void saveGrade(int account,Grade grade,Course course){
		courseMapper.insertStudentAndCourse(account, course.getUid());
		gradeMapper.insert(grade);
	}

	private void updateGrade(int gradeId,Grade grade){
	    grade.setId(gradeId);
	    gradeMapper.updateByPrimaryKey(grade);
    }
/**
 * 将学生成绩文本化
 * @param studentGrades 学生全部成绩
 */
	public String toText(List<AllGradeAndCourse.GradeAndCourse> studentGrades){
		StringBuffer buffer = new StringBuffer();
		boolean i=true;
		if(studentGrades.size()==0){
			buffer.append("尚无本学期成绩");
		}
		else {
			AllGradeAndCourse allGradeAndCourse=new AllGradeAndCourse();
			allGradeAndCourse.addGradeAndCourse(studentGrades);
			for (AllGradeAndCourse.GradeAndCourse gradeAndCourse:allGradeAndCourse.getCurrentTermGrade()){
				if(i){
					i=false;
					buffer.append("- - - - - - - - - - - - - -\n");
					buffer.append("|"+gradeAndCourse.getGrade().getYear()+"学年，第"+gradeAndCourse.getGrade().getTerm()+"学期|\n");
					buffer.append("- - - - - - - - - - - - - -\n\n");
				}
				buffer.append("考试名称："+gradeAndCourse.getCourse().getName()+"\n")
						.append("成绩："+gradeAndCourse.getGrade().getScore()/10).append("   学分："+gradeAndCourse.getGrade().getPoint()/10+"\n\n");
			}
		}
		return buffer.toString();
	}

}
