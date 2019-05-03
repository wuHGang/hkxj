
package cn.hkxj.platform.config;

import cn.hkxj.platform.filter.InterfaceStatisticsFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;

/**
 * @author zhouqinglai
 * @version version
 * @title BasicsConfig
 * @desc
 * @date: 2019年05月03日
 */
@Configuration
public class BasicsConfig {

    @Bean
    public Filter interfaceStatisticsFilter(){
        return new InterfaceStatisticsFilter();
    }

    @Bean
    public FilterRegistrationBean authFilterRegistrationBean() {
        FilterRegistrationBean registrationBean = new FilterRegistrationBean();
        registrationBean.setFilter(interfaceStatisticsFilter());
        return registrationBean;
    }
}