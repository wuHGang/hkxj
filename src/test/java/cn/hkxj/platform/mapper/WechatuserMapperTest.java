package cn.hkxj.platform.mapper;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author JR Chan
 * @date 2018/6/5 15:49
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class WechatuserMapperTest {
    @Autowired
    private WechatuserMapper mapper;

    @Test
    public void selectByPrimaryKey() {
        System.out.println(mapper.selectByPrimaryKey(2014025838));
    }
}