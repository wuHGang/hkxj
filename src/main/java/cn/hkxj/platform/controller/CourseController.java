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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Objects;

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
	@Resource
	private HttpSession httpSession;
	private static final int ACCOUNT_LENGTH = 10;
	private static final String ACCOUNT_PREFIX = "201";

	@GetMapping("/timetable")
	public WebResponse getTimeTable(@RequestParam(value = "account", required = false) String account){
		log.info("course timetable start-- account:{}", account);

		if (Objects.isNull(account)){
			account = (String) httpSession.getAttribute("account");
		}

		if (Objects.isNull(account)){
			log.info("course timetable fail-- 用户未绑定");
			return WebResponse.fail(ErrorCode.USER_UNAUTHORIZED.getErrorCode(), "用户未绑定");
		}

		if (!isAccountValid(account)){
			log.info("student login fail--invalid account:{}", account);
			return WebResponse.fail(ErrorCode.ACCOUNT_OR_PASSWORD_INVALID.getErrorCode(), "账号无效");
		}

		int accountInt = Integer.parseInt(account);

		if (courseService.isHaveCourses(accountInt)){
			List<CourseTimeTable> courseTimeTables = courseService.getCoursesByAccount(accountInt);
			log.info("course timetable success-- account:{}", account);
			return WebResponse.success(courseTimeTables);
		}

		log.info("course timetable fail-- account:{}没有数据", account);
		return WebResponse.fail(ErrorCode.NO_DATA.getErrorCode(), "该学号没有对应数据");
	}

	private boolean isAccountValid(String account){
		if(Objects.isNull(account) || account.length() != ACCOUNT_LENGTH || !account.startsWith(ACCOUNT_PREFIX))
			return false;
		return true;
	}

}
