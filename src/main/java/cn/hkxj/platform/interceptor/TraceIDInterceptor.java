package cn.hkxj.platform.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

/**
 * @author JR Chan
 * @date 2019/4/27
 */
@Slf4j
@Component
public class TraceIDInterceptor extends MDCInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        UUID uuid = UUID.randomUUID();
        MDC.put("traceId", uuid.toString());
        return true;
    }


}
