package cn.hkxj.platform.service;

import cn.hkxj.platform.PlatformApplication;
import cn.hkxj.platform.exceptions.PasswordUncorrectException;
import cn.hkxj.platform.mapper.StudentMapper;
import cn.hkxj.platform.pojo.ExamTimeTable;
import cn.hkxj.platform.pojo.Student;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import javax.annotation.Resource;

import java.util.List;

import static org.junit.Assert.*;

/**
 * @author junrong.chen
 * @date 2018/11/28
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = PlatformApplication.class)
@WebAppConfiguration
public class ExamTimeTableServiceTest {
    @Resource
    private ExamTimeTableService examTimeTableService;
    @Resource
    private StudentMapper studentMapper;

    @Test
    public void getExamTimeTableByStudent() throws PasswordUncorrectException {
        Student student = studentMapper.selectByAccount(2017025971);
        List<ExamTimeTable> table = examTimeTableService.getExamTimeTableByStudent(student);
        System.out.println(table.toString());
    }
}