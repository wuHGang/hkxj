package cn.hkxj.platform.config;

import cn.hkxj.platform.interceptor.LoginInterceptor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author junrong.chen
 * @date 2018/10/13
 */
@Configuration
@Slf4j
public class WebAppConfigurer implements WebMvcConfigurer {

	@Bean
	public LoginInterceptor loginInterceptor() {
		return new LoginInterceptor();
	}

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
//		registry.addInterceptor(loginInterceptor()).addPathPatterns("/course/timetable");
	}

}
