package cn.hkxj.platform;

import cn.hkxj.platform.pojo.constant.RedisKeys;
import com.google.common.io.Files;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Set;
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
        Set<String> members = redisTemplate.opsForSet().members(RedisKeys.WAITING_EVALUATION_SET.getName());
        System.out.println(members);
    }


}
