package cn.hkxj.platform.service;

import cn.hkxj.platform.dao.StudentDao;
import cn.hkxj.platform.exceptions.PasswordUncorrectException;
import cn.hkxj.platform.exceptions.UrpException;
import cn.hkxj.platform.pojo.Classes;
import cn.hkxj.platform.pojo.Student;
import cn.hkxj.platform.spider.NewUrpSpider;
import cn.hkxj.platform.spider.model.UrpStudentInfo;
import cn.hkxj.platform.spider.newmodel.course.UrpCourseForSpider;
import cn.hkxj.platform.spider.newmodel.coursetimetable.UrpCourseTimeTableForSpider;
import cn.hkxj.platform.spider.newmodel.examtime.UrpExamTime;
import cn.hkxj.platform.spider.newmodel.grade.CurrentGrade;
import cn.hkxj.platform.spider.newmodel.searchcourse.ClassCourseSearchResult;
import cn.hkxj.platform.spider.newmodel.searchcourse.ClassInfoSearchResult;
import cn.hkxj.platform.spider.newmodel.searchcourse.SearchClassInfoPost;
import lombok.extern.slf4j.Slf4j;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

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
    @Resource
    private StudentDao studentDao;

    @Retryable(value = UrpException.class, maxAttempts = 3)
    CurrentGrade getCurrentTermGrade(String account, String password){
        NewUrpSpider spider = getSpider(account, password);
        return spider.getCurrentGrade();
    }

    UrpCourseForSpider getCourseFromSpider(String account, String password, String uid){
        NewUrpSpider spider = getSpider(account, password);
        return spider.getUrpCourse(uid);
    }

    UrpCourseForSpider getCourseFromSpider(Student student, String uid){
        return getCourseFromSpider(student.getAccount().toString(), student.getPassword(), uid);
    }

    @Retryable(value = UrpException.class, maxAttempts = 3)
    public List<ClassInfoSearchResult> getClassInfoSearchResult(String account, String password, SearchClassInfoPost searchClassInfoPost){
        NewUrpSpider spider = getSpider(account, password);
        return spider.getClassInfoSearchResult(searchClassInfoPost);
    }

    @Retryable(value = UrpException.class, maxAttempts = 3)
    public List<List<ClassCourseSearchResult>> searchClassTimeTable(String account, String password, String classCode){
        NewUrpSpider spider = getSpider(account, password);
        return spider.getUrpCourseTimeTableByClassCode(classCode);
    }

    public void checkStudentPassword(String account, String password){
        NewUrpSpider spider = getSpider(account, password);
    }

    @Retryable(value = UrpException.class, maxAttempts = 3)
    public UrpCourseTimeTableForSpider getUrpCourseTimeTable(Student student){
        NewUrpSpider spider = getSpider(student.getAccount().toString(), student.getPassword());
        return spider.getUrpCourseTimeTable();
    }

    /**
     * 获取学生信息
     */
    public Student getStudentInfo(String account, String password){
        NewUrpSpider spider = getSpider(account, password);

        return getUserInfo(spider.getStudentInfo());
    }


    /**
     * 考试安排
     * @return
     */
    @Retryable(value = UrpException.class, maxAttempts = 3)
    public List<UrpExamTime> getExamTime(String account, String password){
        NewUrpSpider spider = getSpider(account, password);

        return spider.getExamTime();
    }
    private Student getUserInfo(UrpStudentInfo studentInfo){

        Classes classes = classService.parseSpiderResult(studentInfo);
        Student student = wrapperToStudent(studentInfo);
        student.setClasses(classes);

        return student;
    }

    private NewUrpSpider getSpider(String account, String password){
        try {
            return new NewUrpSpider(account, password);
        }catch (PasswordUncorrectException e){
            studentDao.updatePasswordUnCorrect(Integer.parseInt(account));
            throw e;
        }

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
