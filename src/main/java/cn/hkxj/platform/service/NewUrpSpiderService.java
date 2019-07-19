package cn.hkxj.platform.service;

import cn.hkxj.platform.pojo.Student;
import cn.hkxj.platform.spider.NewUrpSpider;
import cn.hkxj.platform.spider.model.VerifyCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author junrong.chen
 * @date 2019/7/18
 */
@Slf4j
@Service
public class NewUrpSpiderService {

    /**
     * 获取验证码
     */
    public VerifyCode getVerifyCode(){
        NewUrpSpider spider = new NewUrpSpider();
        return spider.getCaptcha();
    }


    public void login(String account, String password, String verifyCode){
        NewUrpSpider spider = new NewUrpSpider();
        spider.studentCheck(account, password, verifyCode);
        spider.getStudentInfo();
    }
}
