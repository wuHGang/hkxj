package cn.hkxj.platform.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @author junrong.chen
 * @date 2018/10/29
 */
@RestController
@Slf4j
public class IndexController {
	@Autowired
	private HttpServletRequest request;

	@GetMapping("/")
	public String index(){
        log.info("boom");
		return "boom";
	}
}
