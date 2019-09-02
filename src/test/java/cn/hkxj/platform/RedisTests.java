package cn.hkxj.platform;

import cn.hkxj.platform.pojo.constant.RedisKeys;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.TimeUnit;

/**
 * @author JR Chan
 * @date 2018/12/9
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class RedisTests {

    @SuppressWarnings("rawtypes")
    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Test
    public void redisAdd() {
        redisTemplate.opsForValue().set(RedisKeys.OPENID_TO_ACCOUNT + "11", "2014025838");
        String account = redisTemplate.opsForValue().get(RedisKeys.OPENID_TO_ACCOUNT + "11");

    }

    @Test
    public void expire() {
        boolean key = redisTemplate.hasKey(RedisKeys.URP_COOKIE.genKey("2017021458"))
                && redisTemplate.hasKey(RedisKeys.URP_LOGIN_COOKIE.genKey("2017021458"));
        System.out.println(key);

    }

}
