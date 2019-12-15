package cn.hkxj.platform.aop;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * @author junrong.chen
 */
@Slf4j
@Aspect
@Component
public class ControllerLogger {

    @Pointcut("execution(public * cn.hkxj.platform.controller..*Controller.*(..))")
    public void addAdvice() {
    }

    @Around("addAdvice()")
    public Object Interceptor(ProceedingJoinPoint pjp) throws Throwable {
        long start = System.currentTimeMillis();
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        log.info("request start path:{} method:{} parameter{}"
                , request.getRequestURI(), request.getMethod(), JSONObject.toJSONString(request.getParameterMap()));
        try {

            Object[] args = pjp.getArgs();
            Object result = pjp.proceed(args);

            log.info("request success in {}ms path:{} response:{}",
                    System.currentTimeMillis() - start,
                    request.getRequestURI(),
                    JSONObject.toJSONString(result));

            return result;
        } catch (Throwable throwable) {

            log.error("request fail in {}ms path:{} method:{} parameter{}",
                    System.currentTimeMillis() - start
                    , request.getRequestURI(), request.getMethod(), JSONObject.toJSONString(request.getParameterMap()), throwable);

            throw throwable;
        }


    }

}
