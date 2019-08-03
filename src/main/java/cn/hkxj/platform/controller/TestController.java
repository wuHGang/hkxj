package cn.hkxj.platform.controller;

import cn.hkxj.platform.service.NewUrpSpiderService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class TestController {
    @Resource
    private NewUrpSpiderService newUrpSpiderService;


    @RequestMapping("tests")
    public String test(String echostr){
        System.out.println("echostr:----------" + echostr);
        return echostr;
    }

    @RequestMapping("/kaptest")
    public String kaptcha(){
        String code = newUrpSpiderService.getCode();

        return code;
    }
}
