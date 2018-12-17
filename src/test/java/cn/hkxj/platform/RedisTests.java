package cn.hkxj.platform;

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
    private RedisTemplate redisTemplate;

    @Test
    public void redisAdd() {

        // -----------------String类型数据操作 start--------------------
        ValueOperations<String, String> stringOperations = redisTemplate.opsForValue();
        // String类型数据存储，不设置过期时间，永久性保存
        stringOperations.set("string1", "fiala");
        // String类型数据存储，设置过期时间为80秒，采用TimeUnit控制时间单位
        stringOperations.set("string2", "fiala", 80, TimeUnit.SECONDS);
        // 判断key值是否存在，存在则不存储，不存在则存储
        stringOperations.setIfAbsent("string1", "my fiala");
        stringOperations.setIfAbsent("string3", "my fiala");
        String value1 = stringOperations.get("string1");
        String value2 = stringOperations.get("string3");
        log.info(value1);
        log.info(value2);

    }
}
