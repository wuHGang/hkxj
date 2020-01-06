package cn.hkxj.platform.service;

import cn.hkxj.platform.PlatformApplication;
import cn.hkxj.platform.dao.StudentDao;
import cn.hkxj.platform.pojo.Student;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import javax.annotation.Resource;

import static org.junit.Assert.*;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(classes = PlatformApplication.class)
@WebAppConfiguration
public class OpenIdServiceTest {
    @Resource
    private OpenIdService openIdService;
    @Resource
    private StudentDao studentDao;

    @Test
    public void unBind(){
        openIdService.openIdUnbind("o6393wqXpaxROMjiy8RAgPLqWFF8", "wx2212ea680ca5c05d");
    }


    @Test
    public void openIdUnbindAllPlatform(){
        for (Student student : studentDao.selectAllStudent()) {
            if(!student.getIsCorrect()){
                System.out.println(student.getAccount());
                try {
                    openIdService.openIdUnbindAllPlatform(student.getAccount());
                }catch (Exception e){
                    e.printStackTrace();
                }


            }
        }


    }
}