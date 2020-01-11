package cn.hkxj.platform.service.wechat;

import cn.hkxj.platform.config.wechat.WechatMpConfiguration;
import cn.hkxj.platform.config.wechat.WechatMpPlusProperties;
import cn.hkxj.platform.mapper.MiniProgramOpenidMapper;
import cn.hkxj.platform.mapper.OpenidPlusMapper;
import cn.hkxj.platform.pojo.MiniProgramOpenid;
import cn.hkxj.platform.pojo.MiniProgramOpenidExample;
import cn.hkxj.platform.pojo.example.OpenidExample;
import cn.hkxj.platform.pojo.wechat.Openid;
import cn.hkxj.platform.pojo.wechat.miniprogram.SubscribeMessage;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.template.WxMpTemplateMessage;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class SendMessageService {
    @Resource
    private WechatMpPlusProperties wechatMpPlusProperties;
    @Resource
    private OpenidPlusMapper openidPlusMapper;
    @Resource
    private MiniProgramService miniProgramService;
    @Resource
    private MiniProgramOpenidMapper miniProgramOpenidMapper;

    public void sendTemplateMessage(String appId, WxMpTemplateMessage templateMessage){
        WxMpService wxMpService = WechatMpConfiguration.getMpServices().get(appId);
        try {
            log.info("send template message {}", templateMessage.getData());
            wxMpService.getTemplateMsgService().sendTemplateMsg(templateMessage);
        } catch (WxErrorException e) {
            log.error("send template message error", e);
        }
    }


    public void sendPlusTemplateMessage(WxMpTemplateMessage templateMessage){
        sendTemplateMessage(wechatMpPlusProperties.getAppId(), templateMessage);
    }

    public void sendPlusTemplateMessageByAccount(WxMpTemplateMessage templateMessage, int account){
        for (String s : getPlusOpenIdByAccount(account)) {
            templateMessage.setToUser(s);
            sendTemplateMessage(wechatMpPlusProperties.getAppId(), templateMessage);
        }
    }

    public <T> void sendAppTemplateMessage(SubscribeMessage<T> subscribeMessage, int account){
        for (String s : getMiniAppOpenIdByAccount(account)) {
            subscribeMessage.setToUser(s);
            miniProgramService.sendSubscribeMessage(subscribeMessage);
        }

    }


    private List<String> getPlusOpenIdByAccount(int account){
        OpenidExample example = new OpenidExample();
        OpenidExample.Criteria criteria = example.createCriteria();
        criteria.andAccountEqualTo(account);
        return openidPlusMapper.selectByExample(example).stream().map(Openid::getOpenid).collect(Collectors.toList());
    }

    private List<String> getMiniAppOpenIdByAccount(int account){
        MiniProgramOpenidExample example = new MiniProgramOpenidExample();
        MiniProgramOpenidExample.Criteria criteria = example.createCriteria();
        criteria.andAccountEqualTo(account);
        return miniProgramOpenidMapper.selectByExample(example)
                .stream()
                .map(MiniProgramOpenid::getOpenid)
                .collect(Collectors.toList());
    }

}
