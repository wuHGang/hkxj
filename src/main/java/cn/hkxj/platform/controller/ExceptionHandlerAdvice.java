package cn.hkxj.platform.controller;

import cn.hkxj.platform.exceptions.*;
import cn.hkxj.platform.pojo.WebResponse;
import cn.hkxj.platform.pojo.constant.ErrorCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author junrong.chen
 * @date 2018/10/10
 */
@Slf4j
@ControllerAdvice
public class ExceptionHandlerAdvice {

	@ExceptionHandler(value = Exception.class)
	@ResponseBody
	public WebResponse<Void> handleException(Exception e) {
		if (e instanceof PasswordUnCorrectException) {
			return WebResponse.fail(ErrorCode.ACCOUNT_OR_PASSWORD_INVALID.getErrorCode(), e.getMessage());
		} else if (e instanceof ReadTimeoutException) {
			return WebResponse.fail(ErrorCode.READ_TIMEOUT.getErrorCode(), e.getMessage());
		}else if(e instanceof UrpRequestException) {
			return WebResponse.fail(ErrorCode.LOGIN_ERROR.getErrorCode(), "can`t verify user");
		}else if(e instanceof UrpEvaluationException) {
			return WebResponse.fail(ErrorCode.Evaluation_ERROR.getErrorCode(), "未完成评估");
		}else if(e instanceof UrpException){
			return WebResponse.fail(ErrorCode.URP_EXCEPTION.getErrorCode(), "教务网异常，请重试");
		}
		log.error("request fail", e);
		return WebResponse.fail(ErrorCode.SYSTEM_ERROR.getErrorCode(), "服务器出了点小问题");
	}
}
