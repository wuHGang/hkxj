package cn.hkxj.platform.controller;


import cn.hkxj.platform.config.wechat.WechatMpConfiguration;
import cn.hkxj.platform.config.wechat.WechatMpPlusProperties;
import cn.hkxj.platform.exceptions.OpenidExistException;
import cn.hkxj.platform.exceptions.PasswordUncorrectException;
import cn.hkxj.platform.exceptions.UrpVerifyCodeException;
import cn.hkxj.platform.pojo.constant.ErrorCode;
import cn.hkxj.platform.pojo.WebResponse;
import cn.hkxj.platform.pojo.timetable.CourseTimeTable;
import cn.hkxj.platform.service.CourseService;
import cn.hkxj.platform.service.NewUrpSpiderService;
import cn.hkxj.platform.service.wechat.StudentBindService;
import cn.hkxj.platform.spider.model.VerifyCode;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.kefu.WxMpKefuMessage;
import org.slf4j.MDC;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.annotation.Resources;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;
import java.util.Objects;

@Slf4j
@Controller
public class UserBindingController {
	@Resource
	private HttpSession httpSession;
	@Resource
	private StudentBindService studentBindService;
    @Resource
    private NewUrpSpiderService newUrpSpiderService;

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
									 @RequestParam("password") String password,
									 @RequestParam("verifyCode") String verifyCode) {
		log.info("student bind start account:{} password:{} verifyCode:{}", account, password, verifyCode);
        MDC.put("cookieTrace", getCookieTrace());
        if (!isAccountValid(account)){
            log.info("student getStudentInfo fail--invalid account:{}", account);
            return WebResponse.fail(ErrorCode.ACCOUNT_OR_PASSWORD_INVALID.getErrorCode(), "账号无效");
        }

		String openid = (String) httpSession.getAttribute("openid");
		String appid = (String) httpSession.getAttribute("appid");
		try {
			if (Objects.isNull(openid)){
				studentBindService.studentLogin(account, password, verifyCode);
			}
			else {
				studentBindService.studentBind(openid, account, password, appid, verifyCode);
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


    @GetMapping("/verifyCode.jpg")
    public void getVerifyCode(HttpServletRequest request, HttpServletResponse response){

        MDC.put("cookieTrace", getCookieTrace());

        VerifyCode verifyCode = newUrpSpiderService.getVerifyCode();
        response.setContentType("image/jpeg");
        try {
            response.getOutputStream().write(verifyCode.getData());
        } catch (IOException e) {
            log.error("get outputStream error", e);
        }
    }

    private String getCookieTrace(){
        if(httpSession.getAttribute("cookieTrace") == null){
            String traceId = MDC.get("traceId");
            httpSession.setAttribute("cookieTrace", traceId);
            return traceId;
        }else {
            return httpSession.getAttribute("cookieTrace").toString();
        }
    }

    private boolean isAccountValid(String account){
        return !Objects.isNull(account) && account.length() == ACCOUNT_LENGTH && account.startsWith(ACCOUNT_PREFIX);
    }

}
