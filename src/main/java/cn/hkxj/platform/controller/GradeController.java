package cn.hkxj.platform.controller;

import cn.hkxj.platform.exceptions.PasswordUncorrectException;
import cn.hkxj.platform.exceptions.SpiderException;
import cn.hkxj.platform.pojo.GradeAndCourse;
import cn.hkxj.platform.pojo.Student;
import cn.hkxj.platform.pojo.WebResponse;
import cn.hkxj.platform.service.GradeSearchService;
import cn.hkxj.platform.service.wechat.StudentBindService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Comparator;
import java.util.List;

/**
 * @author JR Chan
 * @date 2019/6/10
 */
@Slf4j
@RestController
public class GradeController {
    @Resource
    private GradeSearchService gradeSearchService;
    @Resource(name = "studentBindService")
    private StudentBindService studentBindService;

    @RequestMapping("/student/grade")
    public WebResponse<List<GradeAndCourse>> getStudentGrade(@RequestParam("account") String account,
                                                             @RequestParam("password") String password) {

        Student student;
        try {
            student = studentBindService.studentLogin(account, password);

        } catch (PasswordUncorrectException e) {
            log.error("student grade query Password not correct account:{} password:{}", account, password);
            throw e;
        } catch (SpiderException e) {
            log.error("student grade query error account:{} password:{}", account, password);
            throw e;
        }
        List<GradeAndCourse> currentGradeFromSpider = gradeSearchService.getCurrentGradeFromSpider(student);
        currentGradeFromSpider.sort(Comparator.comparing(o -> o.getCourse().getType()));
        return WebResponse.success(currentGradeFromSpider);
    }
}
