package cn.hkxj.platform.config;

import cn.hkxj.platform.interceptor.InterfaceStatisticsInterceptor;
import cn.hkxj.platform.interceptor.LoginInterceptor;
import cn.hkxj.platform.interceptor.TraceIDInterceptor;
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
	public InterfaceStatisticsInterceptor interfaceStatisticsInterceptor () {
		return new InterfaceStatisticsInterceptor();
	}

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new TraceIDInterceptor()).addPathPatterns("/**");
//		registry.addInterceptor(interfaceStatisticsInterceptor ()).addPathPatterns("/**");
//        registry.addInterceptor(loginInterceptor()).addPathPatterns("/course.json/timetable");

	}

}
