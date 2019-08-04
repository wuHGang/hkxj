package cn.hkxj.platform.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
    @Resource
    private NewUrpSpiderService newUrpSpiderService;


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

    @RequestMapping("/kaptest")
    public String kaptcha(){
        String code = newUrpSpiderService.getCode();

        return code;
    }
}
