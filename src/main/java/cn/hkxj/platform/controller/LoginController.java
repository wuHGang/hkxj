package cn.hkxj.platform.controller;

import cn.hkxj.platform.exceptions.PasswordUncorrectException;
import cn.hkxj.platform.exceptions.SpiderException;
import cn.hkxj.platform.pojo.constant.ErrorCode;
import cn.hkxj.platform.pojo.Student;
import cn.hkxj.platform.pojo.WebResponse;
import cn.hkxj.platform.service.NewUrpSpiderService;
import cn.hkxj.platform.service.wechat.StudentBindService;
import cn.hkxj.platform.spider.model.VerifyCode;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Objects;

/**
 * @author junrong.chen
 * @date 2018/10/13
 * 提供登录API
 */
@RestController
@RequestMapping("/login")
@Slf4j
public class LoginController {
	@Resource(name = "studentBindService")
	private StudentBindService studentBindService;
	@Resource
    private NewUrpSpiderService newUrpSpiderService;
	@Autowired
	private HttpSession session;
	private static final int ACCOUNT_LENGTH = 10;
	private static final String ACCOUNT_PREFIX = "201";

	@PostMapping("/student")
	public WebResponse studentLogin(@RequestParam("account")String account,
	                                @RequestParam("password")String password) throws PasswordUncorrectException {
		log.info("student getStudentInfo--account:{} password:{}", account, password);
		if (!isAccountValid(account)){
			log.info("student getStudentInfo fail--invalid account:{}", account, password);
			return WebResponse.fail(ErrorCode.ACCOUNT_OR_PASSWORD_INVALID.getErrorCode(), "账号无效");
		}
        Student student;
		try{
		    student = studentBindService.studentLogin(account, password);
		    
        }catch (PasswordUncorrectException e) {
            log.info("student bind fail Password not correct account:{} password:{}", account, password);
            return WebResponse.fail(ErrorCode.ACCOUNT_OR_PASSWORD_INVALID.getErrorCode(), "账号或者密码错误");
        }
        catch (SpiderException e){
		    return WebResponse.fail(ErrorCode.ACCOUNT_OR_PASSWORD_INVALID.getErrorCode(), "账号无效");
        }
		session.setAttribute("student", student);
		log.info("student getStudentInfo success--account:{} password:{}", account, password);

		return WebResponse.success(student);
	}

	private boolean isAccountValid(String account){
        return !Objects.isNull(account) && account.length() == ACCOUNT_LENGTH && account.startsWith(ACCOUNT_PREFIX);
    }

    @GetMapping("/verifyCode.jpg")
    public void getVerifyCode(HttpServletRequest request, HttpServletResponse response){

        MDC.put("cookieTrace", getCookieTrace());

        VerifyCode verifyCode = newUrpSpiderService.getVerifyCode();
        response.setContentType("image/jpeg");
        try {
            response.getOutputStream().write(verifyCode.getData());
        } catch (IOException e) {
            log.error("get outputStream error");
        }
    }

    private String getCookieTrace(){
        if(session.getAttribute("cookieTrace") == null){
            String traceId = MDC.get("traceId");
            session.setAttribute("cookieTrace", traceId);
            return traceId;
        }else {
            return session.getAttribute("cookieTrace").toString();
        }
    }

}
