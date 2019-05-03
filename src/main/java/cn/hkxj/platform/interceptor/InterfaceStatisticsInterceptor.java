
package cn.hkxj.platform.interceptor;


import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;

import cn.hkxj.platform.pojo.constant.StatisticsTypeEnum;
import cn.hkxj.platform.service.CacheService;
import lombok.extern.slf4j.Slf4j;

/**
 * @author zhouqinglai
 * @version version
 * @title InterfaceStatisticsInterceptor
 * @desc
 * @date: 2019年05月03日
 */
@Slf4j
public class InterfaceStatisticsInterceptor implements HandlerInterceptor {

    @Resource
    private CacheService cacheService;
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception{
        final String requestURI = request.getRequestURI();
        try {
            cacheService.increment(StatisticsTypeEnum.INTERFACE_STATISTICS.getDesc(), requestURI);
        } catch (Exception e) {
            log.error("doFilter,InterfaceStatisticsFilter error ! e:{}",e);
        }

        return true;
    }
}
