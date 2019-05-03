package cn.hkxj.platform.controller;

import cn.hkxj.platform.exceptions.PasswordUncorrectException;
import cn.hkxj.platform.pojo.Student;
import cn.hkxj.platform.service.SubjectService;
import cn.hkxj.platform.service.wechat.StudentBindService;
import cn.hkxj.platform.spider.UrpCourseSpider;
import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author xie
 * 微信小程序的相关接口
 *
 * appLoginHtmlPost 小程序登陆接口
 *
 * appKebiao 小程序课表功能接口（未完成）
 */
@RestController
@Slf4j
public class AppController {
    private final static Gson GSON = new Gson();
    @Resource
    private SubjectService subjectService;
    @Resource
    private StudentBindService studentBindService;


    @RequestMapping(value = "/app/bind", method = RequestMethod.POST, produces="application/json;charset=UTF-8")
    @ResponseBody
    public Map appLoginHtmlPost(@RequestBody Map loginInfo) throws IOException {

        Map json=new HashMap();

        String account=(String) loginInfo.get("account");
        String password=(String)loginInfo.get("password") ;
        String logincode=(String)loginInfo.get("logincode") ;
        try {
            Student student=studentBindService.studentLogin(account,password);
            Map studentMap=GSON.fromJson(GSON.toJson(student,student.getClass()),Map.class);
            Map appLoginInfo=new UrpCourseSpider(student.getAccount(),student.getPassword()).getAppJson(logincode);

            Map classes=(Map) studentMap.get("classes");
            int class_year=(int)(double)classes.get("year");
            int class_num=(int)(double)classes.get("num");
            int subject_num=(int)(double)classes.get("subject");
            String subject_name=subjectService.getSubjectById(subject_num).getName();

            loginInfo.put("className",(String)classes.get("name")+class_year+"-"+class_num);
            loginInfo.put("num",appLoginInfo.get("openid"));
            loginInfo.put("name",studentMap.get("name"));
            loginInfo.put("major",subject_name);

            json.put("stuinfo",loginInfo);
            json.put("userinfo",loginInfo);
            json.put("status" ,200);
            return json;
        } catch (PasswordUncorrectException e) {
            log.info("student bind fail Password not correct account:{} password:{} openid:{}", account, password);
            json.put("status" ,400);
            return json;
        }

    }

    @RequestMapping(value = "app/kebiao")
    @ResponseBody
    public Map appKebiao(@RequestBody Map stuInfo){


        return new HashMap();
    }
}
