package cn.hkxj.platform.interceptor;

import cn.hkxj.platform.pojo.Student;
import cn.hkxj.platform.service.OpenIdService;
import cn.hkxj.platform.service.RandomMatchService;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.common.session.WxSessionManager;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.message.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutMessage;


import javax.annotation.Resource;
import java.util.Map;
import java.util.Objects;

public class RandomMatchInterceptor implements WxMessageInterceptor {
    @Resource
    private OpenIdService openIdService;

    @Resource
    private RandomMatchService randomMatchService;

    @Override
    public boolean intercept(WxMpXmlMessage wxMessage, Map<String, Object> context, WxMpService wxMpService, WxSessionManager sessionManager) throws WxErrorException {
        String appid = wxMpService.getWxMpConfigStorage().getAppId();
        String openid = wxMessage.getFromUser();

        Student student = openIdService.getStudentByOpenId(openid, appid);

        if (randomMatchService.checkState(openid)) {
            String type = wxMessage.getMsgType();
            if(!type.equalsIgnoreCase("text")) {
                return false;
            }
            randomMatchService.forwardMessage(wxMessage, wxMpService);
            return false;
        } else {
            return true;
        }

    }

    @Override
    public WxMpXmlOutMessage handle(WxMpXmlMessage wxMessage, Map<String, Object> context, WxMpService
            wxMpService, WxSessionManager sessionManager) throws WxErrorException {
        return null;
    }
}
