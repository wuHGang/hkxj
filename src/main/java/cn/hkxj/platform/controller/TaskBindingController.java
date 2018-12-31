package cn.hkxj.platform.controller;

import cn.hkxj.platform.service.TaskBindingService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@Slf4j
@RestController

public class TaskBindingController {
    @Resource
    TaskBindingService taskBindingService;

    @RequestMapping("/taskBinding")
    public String taskBindingWeb(@RequestParam("openid") String openid,
                              @RequestParam("updateType") int updateType){
        taskBindingService.taskBinding(openid,updateType);
        return "绑定成功";
    }
}
