package cn.hkxj.platform.controller;

import cn.hkxj.platform.pojo.GradeSearchResult;
import cn.hkxj.platform.pojo.Student;
import cn.hkxj.platform.service.NewGradeSearchService;
import cn.hkxj.platform.spider.NewUrpSpider;
import org.slf4j.MDC;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

@Controller
//@RestController
public class TestController {

    @Resource
    private NewGradeSearchService newGradeSearchService;
    @Resource
    private HttpSession httpSession;

    @GetMapping(value = "/testhtml")
    public String courseTimeTable(){
        return "LoginWeb/test";
    }

    @RequestMapping("/testGrade")
    public void testGrade(String account, String password, String verifyCode){
        NewUrpSpider spider = new NewUrpSpider();
        MDC.put("cookieTrace", (String) httpSession.getAttribute("cookieTrace"));
        spider.studentCheck("2016024170", "1", verifyCode);
        Student student = new Student();
        student.setAccount(2016024170);
        student.setPassword("1");
        GradeSearchResult gradeSearchResult = newGradeSearchService.getCurrentGrade(student);
        System.out.println(NewGradeSearchService.gradeListToText(gradeSearchResult.getData()));
    }

    @RequestMapping("tests")
    public String test(String echostr){
        System.out.println("echostr:----------" + echostr);
        return echostr;
    }
}
