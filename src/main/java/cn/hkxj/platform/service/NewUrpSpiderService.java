package cn.hkxj.platform.service;

import cn.hkxj.platform.exceptions.UrpException;
import cn.hkxj.platform.pojo.Classes;
import cn.hkxj.platform.pojo.Student;
import cn.hkxj.platform.spider.NewUrpSpider;
import cn.hkxj.platform.spider.model.UrpStudentInfo;
import cn.hkxj.platform.spider.newmodel.CurrentGrade;
import cn.hkxj.platform.spider.newmodel.UrpCourseForSpider;
import lombok.extern.slf4j.Slf4j;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 第一次登录成功后，将学号对应session的cookie持久化
 * 如果需要不使用验证码登录，使用之前需要校验该账号是否有可用的cookie
 *
 *
 *
 * @author junrong.chen
 * @date 2019/7/18
 */
@Slf4j
@Service
public class NewUrpSpiderService {
    @Resource
    private ClassService classService;

    @Retryable(value = UrpException.class, maxAttempts = 3)
    CurrentGrade getCurrentTermGrade(String account, String password){
        NewUrpSpider spider = new NewUrpSpider(account, password);
        return spider.getCurrentGrade();
    }

    public UrpCourseForSpider getCourseFromSpider(Student student, String uid){
        NewUrpSpider spider = new NewUrpSpider(student.getAccount().toString(), student.getPassword());
        return spider.getUrpCourse(uid);
    }


    /**
     * 获取学生信息
     * @return
     */
    public Student getStudentInfo(String account, String password){
        NewUrpSpider spider = new NewUrpSpider(account, password);

        return getUserInfo(spider.getStudentInfo());
    }

    private Student getUserInfo(UrpStudentInfo studentInfo){

        Classes classes = classService.parseSpiderResult(studentInfo);
        Student student = wrapperToStudent(studentInfo);
        student.setClasses(classes);

        return student;
    }

    private Student wrapperToStudent(UrpStudentInfo studentWrapper) {
        Student student = new Student();
        student.setAccount(studentWrapper.getAccount());
        student.setPassword(studentWrapper.getPassword());
        student.setEthnic(studentWrapper.getEthnic());
        student.setSex(studentWrapper.getSex());
        student.setName(studentWrapper.getName());
        student.setIsCorrect(true);
        return student;
    }
}
