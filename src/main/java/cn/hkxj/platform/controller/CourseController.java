package cn.hkxj.platform.controller;

import cn.hkxj.platform.pojo.CourseTimeTable;
import cn.hkxj.platform.pojo.ErrorCode;
import cn.hkxj.platform.pojo.Student;
import cn.hkxj.platform.pojo.WebResponse;
import cn.hkxj.platform.service.wechat.common.course.CourseService;
import cn.hkxj.platform.service.wechat.common.course.impl.CourseServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @author junrong.chen
 * @date 2018/10/13
 */
@Slf4j
@RestController
@RequestMapping("/course")
public class CourseController {
	@Autowired
	private CourseService courseService;
	@Autowired
	private HttpSession session;

	@GetMapping("/timetable")
	public WebResponse getTimeTable(){

		Student student = (Student) session.getAttribute("student");
		Integer account = student.getAccount();
		log.info("course timetable start-- account:{}", account);
		if (courseService.isHaveCourses(account)){
			List<CourseTimeTable> courseTimeTables = courseService.getCoursesByAccount(account);
			log.info("course timetable success-- account:{}没有数据", account);
			return WebResponse.success(courseTimeTables);
		}

		log.info("course timetable success-- account:{}没有数据", account);
		return WebResponse.fail(ErrorCode.NO_DATA.getErrorCode(), "该学号没有对应数据");
	}

}
