package cn.hkxj.platform.mapper;

import cn.hkxj.platform.pojo.Course;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

/**
 * @author junrong.chen
 * @date 2018/9/23
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class CourseMapperTest {
	@Resource
	private CourseMapper courseMapper;

	@Test
	public void insertStudentAndCourse() {
        courseMapper.selectByPrimaryKey(1259);
	}


	@Test
	public void selectByName() {
        for (Course course : courseMapper.selectCourseByName("材料力学")) {
			log.info(course.toString());
		}
	}
}