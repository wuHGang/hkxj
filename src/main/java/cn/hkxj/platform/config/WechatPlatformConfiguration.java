package cn.hkxj.platform.config;

import me.chanjar.weixin.mp.api.WxMpInMemoryConfigStorage;
import me.chanjar.weixin.mp.api.WxMpMessageRouter;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.api.impl.WxMpServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;

/**
 * @author JR Chan
 * @date 2018/6/7 15:16
 */
@Configurable
public class WechatPlatformConfiguration {
    @Value("${wechat.appid}")
    private String appID;
    @Value("${wechat.appSecret}")
    private String secret;
    @Value("${wechat.token}")
    private String token;

    @Bean(name = "wechatConfig")
    public WxMpInMemoryConfigStorage config(){
        WxMpInMemoryConfigStorage config = new WxMpInMemoryConfigStorage();
        config.setAppId(appID); // 设置微信公众号的appid
        config.setSecret(secret); // 设置微信公众号的app corpSecret
        config.setToken(token); // 设置微信公众号的token

        return config;
    }

    @Bean(name = "wechatService")
    public WxMpService serviceConfig(WxMpInMemoryConfigStorage wechatConfig){
        WxMpService wxMpService = new WxMpServiceImpl();
        wxMpService.setWxMpConfigStorage(wechatConfig);

        return wxMpService;
    }

    @Bean(name = "wechatRoute")
    public WxMpMessageRouter routeConfig(WxMpService wxMpService){
        WxMpMessageRouter wxMpMessageRouter = new WxMpMessageRouter(wxMpService);
        return wxMpMessageRouter;
    }


}
