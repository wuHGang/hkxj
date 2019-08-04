package cn.hkxj.platform.service;

import cn.hkxj.platform.pojo.Classes;
import cn.hkxj.platform.pojo.Course;
import cn.hkxj.platform.pojo.Student;
import cn.hkxj.platform.pojo.constant.Academy;
import cn.hkxj.platform.pojo.constant.CourseType;
import cn.hkxj.platform.spider.NewUrpSpider;
import cn.hkxj.platform.spider.model.UrpStudentInfo;
import cn.hkxj.platform.spider.newmodel.CurrentGrade;
import cn.hkxj.platform.spider.newmodel.UrpCourse;
import lombok.extern.slf4j.Slf4j;
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


    public CurrentGrade getCurrentTermGrade(String account, String password){
        NewUrpSpider spider = new NewUrpSpider(account, password);
        return spider.getCurrentGrade();
    }

    public Course getCourseFromSpider(Student student, String uid){
        NewUrpSpider spider = new NewUrpSpider(student.getAccount().toString(), student.getPassword());
        UrpCourse urpCourse = spider.getUrpCourse(uid);
        Course course = new Course();
        course.setName(urpCourse.getKcm());
        course.setUid(urpCourse.getKch());
        course.setCredit((int) (urpCourse.getXf() * 10));
        course.setAcademy(Academy.getAcademyByCode(urpCourse.getXsh()));
        //因为接口返回的数据里面没有课程类型，所以设置成未知。
        //在成绩接口中有
        course.setType(CourseType.UNKNOWN);
        return course;
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
