package cn.hkxj.platform.service.wechat;

import cn.hkxj.platform.service.wechat.handler.messageHandler.ExampleHandler;
import me.chanjar.weixin.mp.api.WxMpMessageRouter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author JR Chan
 * @date 2018/6/11 17:19
 * <p>
 * 所有handle的路由规则都在这个service里配置
 */
@Service
public class HandlerRouteService {
    @Autowired
    WxMpMessageRouter router;

    public void handlerRegister() {
        System.out.println("register");

        router
                .rule()
                .async(false)
                .content("haha")
                .handler(new ExampleHandler())
                .end();

    }
}
