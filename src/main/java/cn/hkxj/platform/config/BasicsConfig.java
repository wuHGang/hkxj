
package cn.hkxj.platform.config;

import javax.servlet.Filter;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import cn.hkxj.platform.filter.InterfaceStatisticsFilter;

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
}