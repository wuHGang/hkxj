package cn.hkxj.platform.config.wechat;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class WechatMpConfigurationTest {
    @Resource
    private WechatMpConfiguration wechatMpConfiguration;


    @Test
    public void setPlusMenu() {
        wechatMpConfiguration.setPlusMenu();
    }
}