package cn.hkxj.platform.spider;

import cn.hkxj.platform.PlatformApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import javax.annotation.Resource;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = PlatformApplication.class)
@WebAppConfiguration
public class UrpSpiderProxySelectorTest {
    @Resource
    private UrpSpiderProxySelector urpSpiderProxySelector;


    @Test
    public void select() {

        urpSpiderProxySelector.select(null);
    }
}