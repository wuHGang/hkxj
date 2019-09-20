package cn.hkxj.platform.interceptor;

import cn.hkxj.platform.pojo.Student;
import cn.hkxj.platform.pojo.constant.RedisKeys;
import cn.hkxj.platform.service.OpenIdService;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.common.session.WxSessionManager;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.message.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutMessage;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

@Component
@Slf4j
public class StudentInfoInterceptor implements WxMessageInterceptor{

    @Resource
    private OpenIdService openIdService;
    @Resource
    private RedisTemplate<String, String> redisTemplate;

    private static final ExecutorService executor = Executors.newSingleThreadExecutor();

    @Override
    public boolean intercept(WxMpXmlMessage wxMessage, Map<String, Object> context, WxMpService wxMpService, WxSessionManager sessionManager) {
        String appid = wxMpService.getWxMpConfigStorage().getAppId();
        String openid = wxMessage.getFromUser();
        String key = RedisKeys.OPENID_TO_ACCOUNT.getName() + openid;

        String account = redisTemplate.opsForValue().get(key);

        //TODO 这里不能排除掉用户绑定了新账号的情况  除非每次都查一下库  但是这样就失去了缓存的意义  最好有解绑事件来触发缓存的更新
        if(StringUtils.isEmpty(account)){
            Student student = openIdService.getStudentByOpenId(openid, appid);
            String mark = student.getAccount().toString() + " " + student.getName();
            executor.submit(() ->{
                try {
                    redisTemplate.opsForValue().set(key,
                            student.getAccount().toString(), 12L, TimeUnit.HOURS);
                    wxMpService.getUserService().userUpdateRemark(openid, mark);
                } catch (WxErrorException e) {
                    log.error("set student mark error", e);
                }
            });
        }




        return true;
    }

    @Override
    public WxMpXmlOutMessage handle(WxMpXmlMessage wxMessage, Map<String, Object> context, WxMpService wxMpService, WxSessionManager sessionManager) throws WxErrorException {
        return null;
    }
}
