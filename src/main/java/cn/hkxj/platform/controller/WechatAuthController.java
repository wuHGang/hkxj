package cn.hkxj.platform.controller;

import cn.hkxj.platform.config.WechatPlatformConfiguration;
import cn.hkxj.platform.service.WechatMessageService;
import me.chanjar.weixin.mp.api.WxMpMessageRouter;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.message.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutMessage;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Import;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author JR Chan
 * @date 2018/6/6 19:16
 */

@Controller
@Import({WechatMessageService.class, WechatPlatformConfiguration.class})
@EnableAutoConfiguration
public class WechatAuthController {
    private final WxMpService wxMpService;
    private final WechatMessageService service;
    private final WxMpMessageRouter wxMpMessageRouter;

    @Autowired
    public WechatAuthController(WechatMessageService service, WxMpService wxMpService, WxMpMessageRouter wxMpMessageRouter) {
        this.service = service;
        this.wxMpService = wxMpService;
        this.wxMpMessageRouter = wxMpMessageRouter;
    }


    @RequestMapping(
            value = "/auth",
            method = {RequestMethod.GET}
    )
    void auth(HttpServletRequest request, HttpServletResponse response) throws IOException {

        String signature = request.getParameter("signature");
        String nonce = request.getParameter("nonce");
        String timestamp = request.getParameter("timestamp");
        System.out.println(signature);
        PrintWriter writer = response.getWriter();

        if ((signature == null) | (nonce == null) | (timestamp == null)){
//            responsse.reset();
            writer.println("invalid request");
            return;
        }

        if (!wxMpService.checkSignature(timestamp, nonce, signature)) {
            // 消息签名不正确，说明不是公众平台发过来的消息
            response.setStatus(400);
            writer.println("invalid request");
            return;
        }

        String echostr = request.getParameter("echostr");
        if (StringUtils.isNotBlank(echostr)) {
            // 说明是一个仅仅用来验证的请求，回显echostr
            writer.println(echostr);
        }

    }
    @RequestMapping(
            value = "/auth",
            method = {RequestMethod.POST},
            produces = "text/xml;charset=UTF-8"
    )
    void handleMessage(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String encryptType = StringUtils.isBlank(request.getParameter("encrypt_type")) ?
                "raw" :
                request.getParameter("encrypt_type");
        service.handlerRegister();
        if ("raw".equals(encryptType)) {
            // 明文传输的消息
            WxMpXmlMessage inMessage = WxMpXmlMessage.fromXml(request.getInputStream());
            WxMpXmlOutMessage outMessage = wxMpMessageRouter.route(inMessage);
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(outMessage.toXml());

        }
    }

    public static void main(String[] args) {
        SpringApplication.run(WechatAuthController.class, args);
    }
}
