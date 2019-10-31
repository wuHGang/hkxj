package cn.hkxj.platform.mapper;

import cn.hkxj.platform.pojo.StudentCourseTable;
import cn.hkxj.platform.pojo.StudentCourseTimeTable;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.util.Lists;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class CourseTimeTableDetailMapperTest {
    @Resource
    private CourseTimeTableDetailMapper courseTimeTableDetailMapper;

    @Test
    public void insertStudentCourseTimeTableBatch() {

        ArrayList<Integer> list = Lists.newArrayList(1, 2, 3);
        courseTimeTableDetailMapper.insertStudentCourseTimeTableBatch(list, 2014025838, "2019-2020", 1);
    }

    @Test
    public void selectStudentCourseTimeTableRelative() {
        StudentCourseTable table = new StudentCourseTable()
                .setAccount(2017025299)
                .setTermOrder(1)
                .setTermYear("2019-2020");
        List<StudentCourseTable> tables = courseTimeTableDetailMapper.selectStudentCourseTimeTableRelative(table);
        System.out.println(tables);
    }
}