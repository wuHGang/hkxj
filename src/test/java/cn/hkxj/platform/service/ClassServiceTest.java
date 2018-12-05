package cn.hkxj.platform.service;

import cn.hkxj.platform.PlatformApplication;
import cn.hkxj.platform.mapper.StudentMapper;
import cn.hkxj.platform.pojo.Classes;
import cn.hkxj.platform.pojo.Student;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import javax.annotation.Resource;

/**
 * @author junrong.chen
 * @date 2018/11/28
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = PlatformApplication.class)
@WebAppConfiguration
public class ClassServiceTest {
    @Resource
    private StudentMapper studentMapper;
    @Resource
    private ClassService classService;

    @Test
    public void getClassByStudent() {
        Student student = studentMapper.selectByAccount(2017025971);
        Classes clazz = classService.getClassByStudent(student);
        System.out.println(clazz.toString());
    }
}