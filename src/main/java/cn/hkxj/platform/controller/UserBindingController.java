package cn.hkxj.platform.controller;


import cn.hkxj.platform.exceptions.OpenidExistException;
import cn.hkxj.platform.exceptions.PasswordUncorrectException;
import cn.hkxj.platform.exceptions.UrpVerifyCodeException;
import cn.hkxj.platform.pojo.WebResponse;
import cn.hkxj.platform.pojo.constant.ErrorCode;
import cn.hkxj.platform.service.wechat.StudentBindService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.Objects;

@Slf4j
@Controller
public class UserBindingController {
	@Resource
	private HttpSession httpSession;
	@Resource
	private StudentBindService studentBindService;

	private static final int ACCOUNT_LENGTH = 10;
    private static final String ACCOUNT_PREFIX = "20";

	@RequestMapping(value = "/bind", method = RequestMethod.GET)
	public String loginHtml(@RequestParam(value = "openid", required = false) String openid,
							@RequestParam(value = "appid", required = false) String appid) {
		httpSession.setAttribute("openid", openid);
		httpSession.setAttribute("appid", appid);
		return "LoginWeb/Login";
	}


	@RequestMapping(value = "/bind/wechat", method = RequestMethod.POST, produces="application/json;charset=UTF-8")
	@ResponseBody
	public WebResponse loginHtmlPost(@RequestParam("account") String account,
									 @RequestParam("password") String password) {
		log.info("student bind start account:{} password:{}", account, password);

        if (!isAccountValid(account)){
            log.info("student getStudentInfo fail--invalid account:{}", account);
            return WebResponse.fail(ErrorCode.ACCOUNT_OR_PASSWORD_INVALID.getErrorCode(), "账号无效");
        }

		String openid = (String) httpSession.getAttribute("openid");
		String appid = (String) httpSession.getAttribute("appid");
		try {
			if (Objects.isNull(openid)){
				WebResponse.success(studentBindService.studentLogin(account, password));
			}
			else {
				WebResponse.success(studentBindService.studentBind(openid, account, password, appid));
			}
			httpSession.setAttribute("account", account);
		}catch (UrpVerifyCodeException e){
            log.info("student bind fail verify code error account:{} password:{} openid:{}", account, password,
                    openid);
            return WebResponse.fail(ErrorCode.VERIFY_CODE_ERROR.getErrorCode(), "验证码错误");
        } catch (PasswordUncorrectException e) {
			log.info("student bind fail Password not correct account:{} password:{} openid:{}", account, password, openid);
			return WebResponse.fail(ErrorCode.ACCOUNT_OR_PASSWORD_INVALID.getErrorCode(), "账号或者密码错误");
		} catch (OpenidExistException e) {
			log.info("student bind fail openid is exist account:{} password:{}", account, password);
			return WebResponse.fail(ErrorCode.OPENID_EXIST.getErrorCode(), "该账号已经绑定");
		}

		log.info("student bind success account:{} password:{} openid{}", account, password, openid);
		return WebResponse.success();
	}


    private boolean isAccountValid(String account){
        return !Objects.isNull(account) && account.length() == ACCOUNT_LENGTH && account.startsWith(ACCOUNT_PREFIX);
    }

}
