package cn.hkxj.platform.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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
		return "classTable";
	}

	@GetMapping(value = "/menu")
	public String menue() {
		return "main";
	}

}
