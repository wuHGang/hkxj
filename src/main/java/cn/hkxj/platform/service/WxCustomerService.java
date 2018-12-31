package cn.hkxj.platform.service;

import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.kefu.WxMpKefuMessage;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 微信客服消息服务
 *
 * @author JR Chan
 * @date 2018/12/29
 */
@Service
public class WxCustomerService {
    @Resource
    private WxMpService wxMpService;

    /**
     * 现有两个主动向用户发送消息的渠道，由于客服消息有时间限制，所以优先使用客服消息
     * 客服消息：
     * 条件：48小时内向公众号发送过消息
     * 一次性订阅消息
     * 点击了公众号提供的订阅url
     * <p>
     * 这个服务应该处理发送消息的逻辑 判断能否发送消息  以及什么时间发送
     */

    public void sendKefuMessage() throws WxErrorException {
        WxMpKefuMessage wxMpKefuMessage = new WxMpKefuMessage();
        wxMpKefuMessage.setContent("test");
        wxMpKefuMessage.setToUser("o6393wqXpaxROMjiy8RAgPLqWFF8");
        wxMpKefuMessage.setMsgType("text");
        wxMpService.getKefuService().sendKefuMessage(wxMpKefuMessage);
    }
}
