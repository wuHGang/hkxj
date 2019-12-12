package cn.hkxj.platform.mapper;

import cn.hkxj.platform.exceptions.PasswordUnCorrectException;
import cn.hkxj.platform.exceptions.ReadTimeoutException;
import cn.hkxj.platform.pojo.Student;
import cn.hkxj.platform.spider.UrpSpider;
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
        Student student = studentMapper.selectByAccount(2017025971);
		log.info(student.toString());
	}

	@Test
	public void insertByStudent() throws PasswordUnCorrectException, ReadTimeoutException {
        UrpSpider urpSpider = new UrpSpider(2017025971, "1");

//        studentMapper.insert(urpSpider.getInformation());
	}

	@Test
	public void updateByStudent() {
//		Student student = Student.builder()
//				.account(2014025839)
//				.isCorrect(false)
//				.build();

//		studentMapper.updateByPrimaryKey(student);
	}
}