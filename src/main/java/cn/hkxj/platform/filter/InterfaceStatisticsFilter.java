
package cn.hkxj.platform.filter;

import cn.hkxj.platform.pojo.constant.StatisticsTypeEnum;
import cn.hkxj.platform.service.CacheService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * @author zhouqinglai
 * @version version
 * @title InterfaceStatisticsFilter
 * @desc 接口统计过滤器
 * @date: 2019年05月02日
 */
@Slf4j
public class InterfaceStatisticsFilter implements Filter {

    @Resource
    private CacheService cacheService;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        final String requestURI = httpServletRequest.getRequestURI();
        try {
            cacheService.increment(StatisticsTypeEnum.INTERFACE_STATISTICS.getDesc(),requestURI);
        } catch (Exception e) {
            log.error("doFilter,InterfaceStatisticsFilter error ! e:{}",e);
        }

        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {

    }


}
