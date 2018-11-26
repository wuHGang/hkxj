package cn.hkxj.platform.service;

import cn.hkxj.platform.exceptions.PasswordUncorrectException;
import cn.hkxj.platform.mapper.*;
import cn.hkxj.platform.pojo.*;
import cn.hkxj.platform.spider.AppSpider;
import cn.hkxj.platform.spider.UrpCourseSpider;
import cn.hkxj.platform.spider.UrpSpider;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutMessage;
import me.chanjar.weixin.mp.bean.template.WxMpTemplateMessage;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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

	private WxMpService wxMpService;


	/**
	 * 只返回本学期的成绩，这个数据存在数据库，用于自动更新
	 * @param account 学生账户
	 * @param password 学生密码
	 */
	public void getCurrentGrade(int account,String password)throws IOException {
		//暂定只要是半学期的都应该直接查询最新的数据
		//先查询数据库中有没有这个数据，有就返回（如果要查本学期的数据，怎么判断知道数据有没有更新完）
		//如果没有从App中进行抓取，要先判断这个他的app账号是否正确，不正确从校务网抓
		//抓到的数据保存到数据并且返回结果（并行执行）在密集查成绩的期间要考虑是否需要存库这个功能
		UrpCourseSpider urpCourseSpider=new UrpCourseSpider(account,password);
		AppSpider appSpider = new AppSpider(account);
		try {
			appSpider.getToken();
			AllGradeAndCourse gradeAndCourse = appSpider.getGradeAndCourse();
			for (AllGradeAndCourse.GradeAndCourse andCourse : gradeAndCourse.getCurrentTermGrade()) {
				if(andCourse.getGrade().getYear()==2018){
					if (!courseMapper.ifExistCourse(andCourse.getCourse().getUid())) {
						andCourse.getCourse().setAcademy(urpCourseSpider.getAcademyId(andCourse.getCourse().getUid()));
						saveCourse(andCourse.getCourse());
					}
					if (gradeMapper.ifExistGrade(andCourse.getGrade().getAccount(),andCourse.getGrade().getCourseId())==0){
						saveGrade(account,andCourse.getGrade(),andCourse.getCourse());
					}
				}
			}
		} catch (PasswordUncorrectException e) {
			log.error("error password");
		}catch (IllegalArgumentException e){
			log.error(e.getMessage());
		}
	}

	/**
	 * 直接返回本学期的成绩，这个数据暂时不存在数据库，便于回复
	 * @param account 学生账户
	 * @param password 学生密码
	 */
	public List<Grade> returnGrade(int account,String password)throws IOException {
		List<Grade> studentGrades=new ArrayList<>();
		AppSpider appSpider = new AppSpider(account);
		try {
			appSpider.getToken();
			AllGradeAndCourse gradeAndCourse = appSpider.getGradeAndCourse();
			for (AllGradeAndCourse.GradeAndCourse andCourse : gradeAndCourse.getCurrentTermGrade()) {
				if(andCourse.getGrade().getYear()==2018){
						studentGrades.add(andCourse.getGrade());
				}
			}
		} catch (PasswordUncorrectException e) {
			log.error("error password");
		}catch (IllegalArgumentException e){
			log.error(e.getMessage());
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
 * 将学生成绩转化成文本化
 * @param studentGrades 学生成绩总数
 */
	public String toText(List<Grade> studentGrades){
		StringBuffer buffer = new StringBuffer();
		boolean i=true;
		if(studentGrades.size()==0){
			buffer.append("尚无本学期成绩");
		}
		else {
			for (Grade grade:studentGrades){
				if(i){
					i=false;
					buffer.append("- - - - - - - - - - - - - -\n");
					buffer.append("|"+grade.getYear()+"学年，第"+grade.getTerm()+"学期|\n");
					buffer.append("- - - - - - - - - - - - - -\n\n");
				}
				buffer.append("考试名称："+courseMapper.selectNameByUid(grade.getCourseId())+"\n")
						.append("成绩："+grade.getScore()/10).append("   学分："+grade.getPoint()/10+"\n\n");
			}
		}
		return buffer.toString();
	}

}
