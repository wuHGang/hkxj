package cn.hkxj.platform.controller;

import cn.hkxj.platform.pojo.Student;
import cn.hkxj.platform.pojo.WebResponse;
import cn.hkxj.platform.service.GradeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequestMapping("/grade")
public class GradeController {
    @Autowired
    private GradeService gradeService;
    @RequestMapping("/findGradeByUser")
    public WebResponse findGradeByUser(Student student) {
        if (student.getAccount() == null || student.getPassword() == null ||
                StringUtils.isEmpty(student.getAccount()) || StringUtils.isEmpty(student.getPassword())) {
            return WebResponse.fail(0,"查询成绩参数错误");
        }
        return gradeService.findGradeByUser(student.getAccount(),student.getPassword());
    }
}
