package cn.hkxj.platform.interceptor;

import cn.hkxj.platform.pojo.constant.ErrorCode;
import cn.hkxj.platform.pojo.WebResponse;
import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Objects;

/**
 * @author junrong.chen
 * @date 2018/10/13
 * 需要登录信息的接口检测用户是否已经登录
 */
@Slf4j
@Component("loginInterceptor")
public class LoginInterceptor implements HandlerInterceptor {
	@Autowired
	private HttpSession session;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception{
		Object student = session.getAttribute("student");
		if (Objects.isNull(student)){
			returnJson(response, WebResponse.fail(ErrorCode.USER_UNAUTHORIZED.getErrorCode(), "用户未登录"));
			return false;
		}

		return true;
	}

	private void returnJson(HttpServletResponse response, Object object) throws IOException{
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json; charset=utf-8");
		String json = new Gson().toJson(object);
		try (PrintWriter writer = response.getWriter()) {
			writer.print(json);
		} catch (IOException e) {
			log.error("Interceptor response error", e);
			throw e;
		}
	}


}
