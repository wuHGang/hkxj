package cn.hkxj.platform.controller;

import cn.hkxj.platform.pojo.Classes;
import cn.hkxj.platform.pojo.GradeSearchResult;
import cn.hkxj.platform.pojo.Student;
import cn.hkxj.platform.service.NewGradeSearchService;
import cn.hkxj.platform.service.NewUrpSpiderService;
import cn.hkxj.platform.spider.NewUrpSpider;
import lombok.extern.slf4j.Slf4j;
import org.jboss.logging.MDC;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

//@RestController
@Slf4j
@Controller
public class TestController {

    @Resource
    private NewGradeSearchService newGradeSearchService;

    public TestController() {
    }

    @GetMapping(value = "/testhtml")
    public String courseTimeTable(){
        return "LoginWeb/test";
    }

    @RequestMapping("/testGrade")
    public String testGrade(){

        Student student = new Student();
        student.setAccount(2016024170);
        student.setPassword("1");
        Classes classes = new Classes();
        classes.setId(316);
        student.setClasses(classes);
        GradeSearchResult gradeSearchResult = newGradeSearchService.getCurrentGrade(student);
        log.info(NewGradeSearchService.gradeListToText(gradeSearchResult.getData()));
        return NewGradeSearchService.gradeListToText(gradeSearchResult.getData());
    }

    @RequestMapping("tests")
    public String test(String echostr){
        System.out.println("echostr:----------" + echostr);
        return echostr;
    }

}
