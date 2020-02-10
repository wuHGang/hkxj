package cn.hkxj.platform.dao;

import cn.hkxj.platform.PlatformApplication;
import cn.hkxj.platform.pojo.ExamTimetable;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author JR Chan
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = PlatformApplication.class)
public class ExamTimetableDaoTest {
    @Resource
    private ExamTimetableDao examTimetableDao;

    @Test
    public void selectByPrimaryKey() {
    }

    @Test
    public void selectByPojo() {
    }

    @Test
    public void selectCurrentExamByAccount() {
        List<ExamTimetable> examTimetables = examTimetableDao.selectCurrentExamByAccount("2017025838");
        System.out.println(examTimetables);

    }
}