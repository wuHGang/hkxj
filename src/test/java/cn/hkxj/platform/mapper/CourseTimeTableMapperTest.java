package cn.hkxj.platform.mapper;

import cn.hkxj.platform.pojo.CourseTimeTable;
import cn.hkxj.platform.pojo.CourseTimeTableExample;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

/**
 * @author JR Chan
 * @date 2018/12/18
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class CourseTimeTableMapperTest {
    @Resource
    private CourseTimeTableMapper courseTimeTableMapper;

    @Test
    public void selectByExample() {
        CourseTimeTableExample courseTimeTableExample = new CourseTimeTableExample();
        courseTimeTableExample.createCriteria()
                .andYearEqualTo(2018)
                .andStartLessThanOrEqualTo(13)
                .andEndGreaterThanOrEqualTo(12)
                .andWeekEqualTo(1);

        for (CourseTimeTable timeTable : courseTimeTableMapper.selectByExample(courseTimeTableExample)) {
            System.out.println(timeTable.toString());
        }
        ;
    }
}