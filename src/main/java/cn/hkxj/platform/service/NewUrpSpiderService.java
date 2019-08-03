package cn.hkxj.platform.service;

import cn.hkxj.platform.pojo.Classes;
import cn.hkxj.platform.pojo.Student;
import cn.hkxj.platform.pojo.constant.RedisKeys;
import cn.hkxj.platform.spider.NewUrpSpider;
import cn.hkxj.platform.spider.model.UrpStudentInfo;
import cn.hkxj.platform.spider.model.VerifyCode;
import com.google.common.collect.Maps;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.UUID;
import java.util.concurrent.ConcurrentMap;
import sun.misc.BASE64Encoder;

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
    private StringRedisTemplate stringRedisTemplate;

    private static final ConcurrentMap<String, byte[]> cache = Maps.newConcurrentMap();
    /**
     * 获取验证码
     */
    public VerifyCode getVerifyCode(String key){
        return new VerifyCode(cache.get(key));
    }


    /**
     * 获取验证码
     * 这个方法仅测试使用
     */
    public String getCode(){
        NewUrpSpider spider = new NewUrpSpider("xxx", "xxx");
        VerifyCode verifyCode = spider.getCaptcha();

        UUID uuid = UUID.randomUUID();
        System.out.println(uuid.toString());
        cache.put(uuid.toString(), verifyCode.getData().clone());
        return uuid.toString();
    }



    /**
     * 获取学生信息
     * @return
     */
    public Student getStudentInfo(String account, String password){
        NewUrpSpider spider = new NewUrpSpider(account, password);

        VerifyCode verifyCode = spider.getCaptcha();
        BASE64Encoder encoder = new BASE64Encoder();
        UUID uuid = UUID.randomUUID();
        HashOperations<String, String, String> opsForHash = stringRedisTemplate.opsForHash();

        opsForHash.put(RedisKeys.KAPTCHA.getName(), uuid.toString(), encoder.encode(verifyCode.getData().clone()));
        String code = spider.getCode(uuid.toString());

        spider.studentCheck(account, password, code);
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
