package cn.hkxj.platform.service;

import cn.hkxj.platform.exceptions.PasswordUncorrectException;
import cn.hkxj.platform.pojo.Classes;
import cn.hkxj.platform.pojo.Student;
import cn.hkxj.platform.pojo.StudentWrapper;
import cn.hkxj.platform.spider.UrpSpider;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;

/**
 * @author JR Chan
 * @date 2018/12/15
 */
@Slf4j
@Service("urpSpiderService")
public class UrpSpiderService {
    @Resource
    private ClazzService clazzService;

    public Student getInformation(int account, String password) throws PasswordUncorrectException {
        UrpSpider urpSpider = new UrpSpider(account, password);
        Map information = urpSpider.getInformation();

        StudentWrapper studentWrapper = new StudentWrapper();
        try {
            BeanUtils.populate(studentWrapper, (Map) information);
        } catch (IllegalAccessException | InvocationTargetException e) {
            log.error(e.getMessage());
            throw new RuntimeException("个人信息json解析出错", e);
        }

        Classes classes = clazzService.parseSpiderResult(studentWrapper);
        Student student = wrapperToStudent(studentWrapper);
        student.setClasses(classes);
        return student;
    }

    public void getCurrentGrade(Student student) throws PasswordUncorrectException {
        UrpSpider urpSpider = new UrpSpider(student.getAccount(), student.getPassword());
        Map resultMap = urpSpider.getGrade();
        Map gradeResultMap = (Map) resultMap.get("garde");

        List currentList = (List) gradeResultMap.get("current");
        for (Object grade : currentList) {
            Map gradeMap = (Map) grade;
            log.info(gradeMap.toString());
        }

    }

    private Student wrapperToStudent(StudentWrapper studentWrapper) {
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

