package cn.hkxj.platform.controller.wechat;

import cn.hkxj.platform.pojo.WebResponse;
import cn.hkxj.platform.service.wechat.MiniProgramService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@Slf4j
@RestController
@RequestMapping("/mini")
public class MiniProgramController {
    @Resource
    private MiniProgramService miniProgramService;

    @RequestMapping("/auth")
    public WebResponse auth(@RequestParam(value = "code") String code){

        return WebResponse.success(miniProgramService.auth(code));
    }


    @RequestMapping("/subscribe")
    public WebResponse subscribe(@RequestParam(value = "openid") String openid,
                                 @RequestParam(value = "templateId") String templateId){

        miniProgramService.subscribe(templateId, openid);
        return WebResponse.success("success");
    }
}
