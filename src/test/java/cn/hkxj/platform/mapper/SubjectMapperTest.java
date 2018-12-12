package cn.hkxj.platform.mapper;

import cn.hkxj.platform.pojo.Subject;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

/**
 * @author JR Chan
 * @date 2018/12/10
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class SubjectMapperTest {
    @Resource
    private SubjectMapper subjectMapper;


    @Test
    public void selectByPrimaryKey() {
        Subject subject = subjectMapper.selectByPrimaryKey(1);
        log.info(subject.toString());
    }
}