package cn.hkxj.platform.controller;

import cn.hkxj.platform.config.wechat.WechatMpProProperties;
import cn.hkxj.platform.dao.ClassDao;
import cn.hkxj.platform.dao.StudentDao;
import cn.hkxj.platform.exceptions.PasswordUnCorrectException;
import cn.hkxj.platform.pojo.Classes;
import cn.hkxj.platform.pojo.CourseTimeTableDetail;
import cn.hkxj.platform.pojo.Student;
import cn.hkxj.platform.service.CourseTimeTableService;
import cn.hkxj.platform.service.NewUrpSpiderService;
import cn.hkxj.platform.spider.NewUrpSpider;
import cn.hkxj.platform.task.GradeAutoUpdateTask;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.error.WxErrorException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.List;

@RestController
@Slf4j
public class TestController {

    @Resource
    private NewUrpSpiderService newUrpSpiderService;
    @Resource
    private CourseTimeTableService courseTimeTableService;
    @Resource
    private GradeAutoUpdateTask gradeAutoUpdateTask;

    @Resource
    private ClassDao classDao;
    @Resource
    private StudentDao studentDao;

    @GetMapping(value = "/getCourse")
    public void getClazz(){

        for (Classes classes : classDao.getAllClass()) {
            if(classes.getYear() > 15){
                for (Student student : studentDao.selectStudentByClassId(classes.getId())) {
                    try {
                        log.info("class {} student {} start",classes.getId(), student.getName());
                        courseTimeTableService.getAllCourseTimeTableDetails(student);
                        log.info("class {} student {} success",classes.getId(), student.getName());
                        break;
                    }catch (PasswordUnCorrectException e){
                        studentDao.updatePasswordUnCorrect(student.getAccount());
                        log.error("class {} student {} password not correct", classes.getId(), student.getName());
                    } catch (Exception e){
                        log.error("class {} student {} fail",classes.getId(), student.getName(), e);
                    }

                }

            }

        }


    }

    @GetMapping(value = "/testhtml")
    public String courseTimeTable(){
        return "LoginWeb/test";
    }

    @RequestMapping("/testGrade")
    public String testGrade(@RequestParam("account") int account,@RequestParam("password") String password){

        Student student = new Student();
        student.setAccount(account);
        student.setPassword(password);
        Classes classes = new Classes();
        classes.setId(316);
//        student.setClasses(classes);
//        GradeSearchResult gradeSearchResult = newGradeSearchService.getCurrentGrade(student);
//        log.info(NewGradeSearchService.gradeListToText(gradeSearchResult.getData()));
        return newUrpSpiderService.getUrpCourseTimeTable(student).toString();
    }

    @RequestMapping("/testctt")
    public void testCourseTimeTable() throws WxErrorException {
        Student student = new Student();
        Classes classes = new Classes();
        student.setAccount(2017026003);
        student.setPassword("1");
        classes.setId(503);
//        student.setClasses(classes);
        System.out.println(courseTimeTableService.convertToText(courseTimeTableService.getDetailsForCurrentDay(student)));
    }


    @RequestMapping("tests")
    public String test(String echostr){
        System.out.println("echostr:----------" + echostr);
        return echostr;
    }


    @RequestMapping("/exam")
    public String exam(){
        NewUrpSpider spider = new NewUrpSpider("2017021517", "1");
        return "ok";
    }


    @RequestMapping("/log")
    public String testLog(){
        throw new NullPointerException();
    }

    @RequestMapping("/update")
    public String testGrade(){
        gradeAutoUpdateTask.autoUpdateGrade();
        return "ok";
    }


    public static void main(String[] args) throws IOException {



    }
}
