package cn.hkxj.platform.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

/**
 * @author junrong.chen
 * @date 2018/10/22
 */
@Controller
@RequestMapping("/show")
@Slf4j
public class HtmlShowController {

	@GetMapping(value = "/timetable")
	public String courseTimeTable(){
		log.info("request timetable html");
		return "new";
	}

	@GetMapping(value = "/menu")
	public String menue() {
		return "main";
	}

}
