package cn.hkxj.platform.spider;

import cn.hkxj.platform.PlatformApplication;
import cn.hkxj.platform.pojo.constant.RedisKeys;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;
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
    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @Test
    public void select() {
        System.out.println(urpSpiderProxySelector.usePayProxy());
    }

    @Test
    public void setKey(){
        stringRedisTemplate.opsForValue().set(RedisKeys.PROXY_SELECT_SWITCH.getName(), "true");
    }
}