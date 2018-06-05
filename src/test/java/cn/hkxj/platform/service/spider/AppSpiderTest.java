package cn.hkxj.platform.service.spider;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;

/**
 * @author JR Chan
 * @date 2018/6/5 9:57
 */

@RunWith(SpringRunner.class)
@SpringBootTest
public class AppSpiderTest {
    @Autowired
    AppSpider spider;

    @Before
    public void setUp() throws Exception {
        spider.setAccount(2015025838);
        spider.getToken();
    }

    @Test
    public void getToken() {
        try {
            System.out.println(spider.getToken());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void getGrade() {
        try {
            System.out.println(spider.getGrade());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void getLesson() {
        try {
            System.out.println(spider.getLesson());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void getSchedule() throws IOException {
        System.out.println(spider.getSchedule());
    }

}