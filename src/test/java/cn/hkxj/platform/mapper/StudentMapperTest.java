package cn.hkxj.platform.mapper;

import cn.hkxj.platform.exceptions.PasswordUncorrectException;
import cn.hkxj.platform.exceptions.ReadTimeoutException;
import cn.hkxj.platform.pojo.Student;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class StudentMapperTest {
	@Resource
	private StudentMapper studentMapper;


	@Test
	public void selectByAccount() {
		Student student = studentMapper.selectByAccount(2014025838);
		log.info(student.toString());
	}

	@Test
	public void insertByStudent() throws PasswordUncorrectException, ReadTimeoutException {
		Student student = Student.builder()
				.academy("计算机")
				.account(2014025839)
				.classname("物联")
				.ethnic("回")
				.isCorrect(true)
				.major("物联")
				.name("et")
				.password("1")
				.sex("男")
				.build();
		studentMapper.insertByStudent(student);
	}

	@Test
	public void updateByStudent() {
		Student student = Student.builder()
				.account(2014025839)
				.isCorrect(false)
				.build();

		studentMapper.updateByStudent(student);
	}
}