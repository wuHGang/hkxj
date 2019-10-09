package cn.hkxj.platform.service;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class UrpCourseServiceTest {
    @Resource
    private UrpCourseService urpCourseService;

    @Test
    public void checkOrSaveUrpCourseToDb() {
    }

    @Test
    public void getUrpCourseByCourseId() {
        urpCourseService.getUrpCourseByCourseId("1702002");
    }
}