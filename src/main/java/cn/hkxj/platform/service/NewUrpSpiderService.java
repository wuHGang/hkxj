package cn.hkxj.platform.service;

import cn.hkxj.platform.pojo.Classes;
import cn.hkxj.platform.pojo.Student;
import cn.hkxj.platform.spider.NewUrpSpider;
import cn.hkxj.platform.spider.model.UrpStudentInfo;
import cn.hkxj.platform.spider.model.VerifyCode;
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


    /**
     * 获取验证码
     */
    public VerifyCode getVerifyCode(){
        NewUrpSpider spider = new NewUrpSpider();
        return spider.getCaptcha();
    }

    /**
     * 判断一个学号是否有可用的cookie
     */
    public boolean canUseCookie(String account){
        if(NewUrpSpider.canUseCookie(account)){
            NewUrpSpider spider = new NewUrpSpider(account);
            return !spider.isCookieExpire();
        }
        return false;
    }

    public Student getStudentInfo(String account, String password, String verifyCode){
        NewUrpSpider spider = new NewUrpSpider();
        spider.studentCheck(account, password, verifyCode);
        return getUserInfo(spider.getStudentInfo());
    }

    public Student getStudentInfo(String account){
        NewUrpSpider spider = new NewUrpSpider(account);
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
