package cn.hkxj.platform.controller;

import cn.hkxj.platform.service.WechatMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.*;
import org.springframework.boot.autoconfigure.*;
import org.springframework.web.bind.annotation.*;



@RestController
@EnableAutoConfiguration
public class Example {
    @Autowired
    WechatMessageService service;

    @RequestMapping("/")
    String home() {
        return "Hello World";
    }


    public static void main(String[] args) throws Exception {
        SpringApplication.run(Example.class, args);
    }
}
