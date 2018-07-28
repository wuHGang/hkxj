package cn.hkxj.platform.controller;


import cn.hkxj.platform.pojo.Wechatuser;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;



@Controller
public class UserBinding {

    @RequestMapping(value = "/Login")
    public String loginHtml() {
        return "LoginWeb/Login";
    }


    @RequestMapping(value = "/Login",method = RequestMethod.POST)
    public String loginHtmlPost(@RequestBody Wechatuser wechatuser ) {

        return "LoginWeb/Login";
    }
}
