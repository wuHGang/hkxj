package cn.hkxj.platform.mapper;

import cn.hkxj.platform.pojo.Student;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class StudentMapperTest {
	@Autowired
	private StudentMapper studentMapper;

	@Test
	public void selectByAccount() {
		Student student = studentMapper.selectByAccount(2014025838);
		log.info(student.toString());
	}
}