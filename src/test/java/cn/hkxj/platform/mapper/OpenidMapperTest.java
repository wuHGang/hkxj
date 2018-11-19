package cn.hkxj.platform.mapper;

import cn.hkxj.platform.pojo.Openid;
import cn.hkxj.platform.pojo.OpenidExample;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class OpenidMapperTest {
    @Autowired
    private OpenidMapper openidMapper;

    @Test
    public void insertSelective() {
        Openid openid = new Openid();
        openid.setAccount(2014025838);
        openid.setOpenid("o6393wqXpaxROMjiy8RAgPLqWFF8");
        openidMapper.insertSelective(openid);
    }

    @Test
    public void selectByPrimaryKey() {
        OpenidExample openidExample = new OpenidExample();
        OpenidExample.Criteria criteria = openidExample.
                createCriteria()
                .andOpenidEqualTo("o6393wqXpaxROMjiy8RAgPLqWFF8");

        List<Openid> openids = openidMapper.selectByExample(openidExample);
        log.info(openids.toString());
    }

    @Test
    public void updateByExampleSelective() {
    }
}