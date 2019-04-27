package cn.hkxj.platform.interceptor;

import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.mp.bean.message.WxMpXmlMessage;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 为微信所有的请求线程生命周期内的日志植入openid
 *
 * @author JR Chan
 * @date 2019/4/26
 */
@Slf4j
@Component
public class WechatMDCInterceptor extends MDCInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        String body = "";
        try {
            StringBuilder stringBuilder = new StringBuilder();
            request.getReader().lines().forEach(stringBuilder::append);
            body = stringBuilder.toString();
        } catch (IOException e) {
            log.error("get input stream error {}", e);
        }
        if (StringUtils.isNotEmpty(body)) {
            WxMpXmlMessage inMessage = WxMpXmlMessage.fromXml(body);
            MDC.put("openid", inMessage.getOpenId());
        }

        return true;
    }

}
