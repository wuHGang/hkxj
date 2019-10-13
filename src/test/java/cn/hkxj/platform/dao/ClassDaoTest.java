package cn.hkxj.platform.dao;

import cn.hkxj.platform.PlatformApplication;
import cn.hkxj.platform.pojo.Classes;
import cn.hkxj.platform.pojo.Student;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = PlatformApplication.class)
@Slf4j
public class ClassDaoTest {
    @Resource
    private ClassDao classDao;
    @Resource
    private StudentDao studentDao;

    @Test
    public void getClassByClassName() {
        Classes classes = new Classes().setName("采矿卓越").setYear(15).setNum(1);
        List<Classes> className = classDao.getClassByClassName(classes);
        for (Classes classes1 : className) {
            System.out.println(classes1);
            for (Student student : studentDao.selectStudentByClassId(classes1.getId())) {
                System.out.println(student);
            }

            System.out.println("\n\n");

        }

    }

    @Test
    public void insertClass() {
    }

    @Test
    public void getAllClass() {
    }
}