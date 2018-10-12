package cn.hkxj.platform.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author junrong.chen
 * @date 2018/10/10
 */
@Slf4j
public class ExceptionHandlerAdvice {
	@ExceptionHandler
	@ResponseBody
	public String handlerException(Exception e) {
		return "";
	}
}
