package cn.hkxj.platform.controller;


import cn.hkxj.platform.pojo.OpenId;
import cn.hkxj.platform.pojo.Wechatuser;
import cn.hkxj.platform.utils.HttpRequest;
import me.chanjar.weixin.common.session.WxSessionManager;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.message.WxMpXmlMessage;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@Controller
public class Example {


//    @RequestMapping(value = "/Login")
//    public String loginHtml() {
//        return "LoginWeb/Login";
//    }
//
//
//    @RequestMapping(value = "/Login",method = RequestMethod.POST)
//    public String loginHtmlPost(@RequestBody Wechatuser wechatuser , WxMpXmlMessage wxMpXmlMessage, Map<String, Object> map) {
//
//        System.out.println(wechatuser.test());
//        return "LoginWeb/Login";
//    }



    public static void main(String[] args) throws Exception {
        SpringApplication.run(Example.class, args);
    }
}


