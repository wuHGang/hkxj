package cn.hkxj.platform.dao;

import cn.hkxj.platform.PlatformApplication;
import cn.hkxj.platform.pojo.Grade;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

import java.util.List;

import static org.junit.Assert.*;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(classes = PlatformApplication.class)
public class GradeDaoTest {
    @Resource
    private GradeDao gradeDao;

    @Test
    public void getCurrentTermGradeByAccount() {
        List<Grade> account = gradeDao.getCurrentTermGradeByAccount(2016023344);
        System.out.println(account.size());
        for (Grade grade : account) {
            System.out.println(grade);
        }
    }

    @Test
    public void getEverTermGradeByAccount() {
        List<Grade> account = gradeDao.getEverTermGradeByAccount(2016023344);
        System.out.println(account.size());
        for (Grade grade : account) {
            System.out.println(grade);
        }
    }

    @Test
    public void getGradeByAccount() {
        List<Grade> account = gradeDao.getGradeByAccount(2016023344);
        System.out.println(account.size());
        for (Grade grade : account) {
            System.out.println(grade);
        }
    }


}