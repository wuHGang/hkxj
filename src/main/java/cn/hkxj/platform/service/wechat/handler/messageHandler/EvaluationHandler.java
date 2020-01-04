package cn.hkxj.platform.service.wechat.handler.messageHandler;


import cn.hkxj.platform.builder.TextBuilder;
import cn.hkxj.platform.pojo.Student;
import cn.hkxj.platform.pojo.constant.RedisKeys;
import cn.hkxj.platform.service.OpenIdService;
import cn.hkxj.platform.service.TeachingEvaluationService;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.common.session.WxSessionManager;
import me.chanjar.weixin.mp.api.WxMpMessageHandler;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.message.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutMessage;
import org.apache.commons.lang3.BooleanUtils;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Map;

@Slf4j
@Component
public class EvaluationHandler implements WxMpMessageHandler {
    @Resource
    private OpenIdService openIdService;
    @Resource
    private TeachingEvaluationService teachingEvaluationService;
    @Resource
    private StringRedisTemplate stringRedisTemplate;
    @Resource
    private TextBuilder textBuilder;

    @Override
    public WxMpXmlOutMessage handle(WxMpXmlMessage wxMpXmlMessage, Map<String, Object> context, WxMpService wxMpService, WxSessionManager sessionManager) {
        String appId = wxMpService.getWxMpConfigStorage().getAppId();
        Student student = openIdService.getStudentByOpenId(wxMpXmlMessage.getFromUser(), appId);

        String account = student.getAccount().toString();
        String key = RedisKeys.WAITING_EVALUATION_SET.getName();
        String content;

        if (BooleanUtils.toBoolean(stringRedisTemplate.opsForSet().isMember(RedisKeys.FINISH_EVALUATION_SET.getName(), account))) {
            content = "您的评估已经完成评估啦！~";
        } else {
            if (BooleanUtils.toBoolean(stringRedisTemplate.opsForSet().isMember(key, account))) {
                content = "稍待片刻，已经在队列中了，评估完成后立刻给你发通知提醒你";
            } else {
                content = "我们很快会为你完成评估，评估完成后立刻给你发通知提醒你";
                stringRedisTemplate.opsForSet().add(key, account);
            }
        }

        return textBuilder.build(content, wxMpXmlMessage, wxMpService);
    }
}
