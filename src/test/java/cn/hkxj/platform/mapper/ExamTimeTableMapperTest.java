package cn.hkxj.platform.mapper;

import cn.hkxj.platform.pojo.ExamTimeTable;
import cn.hkxj.platform.utils.ApplicationUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

/**
 * @author junrong.chen
 * @date 2018/11/29
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class ExamTimeTableMapperTest extends AbstractJUnit4SpringContextTests {
    @Resource
    private ExamTimeTableMapper examTimeTableMapper;


    @Before
    public void inject(){
        ApplicationUtil.setApplicationContext(applicationContext);
    }

    @Test
    public void selectByPrimaryKey() {
        ExamTimeTable examTimeTable = examTimeTableMapper.selectByPrimaryKey(1);
        System.out.println(examTimeTable.toString());

    }
}