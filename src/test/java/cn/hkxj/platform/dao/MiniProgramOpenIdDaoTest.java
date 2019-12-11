package cn.hkxj.platform.dao;

import cn.hkxj.platform.PlatformApplication;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = PlatformApplication.class)
@Slf4j
public class MiniProgramOpenIdDaoTest {
    @Resource
    private MiniProgramOpenIdDao miniProgramOpenIdDao;

    @Test
    public void insertSelective() {
    }

    @Test
    public void selectByPojo() {
    }

    @Test
    public void isOpenidExist() {
    }

    @Test
    public void selectByOpenId() {
    }

    @Test
    public void updateByPrimaryKey() {
    }
}