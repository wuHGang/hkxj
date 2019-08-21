package cn.hkxj.platform.spider;

import cn.hkxj.platform.PlatformApplication;
import cn.hkxj.platform.pojo.constant.RedisKeys;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import javax.annotation.Resource;

import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = PlatformApplication.class)
@WebAppConfiguration
@Slf4j
public class CaptchaBreakerTest {
    @Resource
    private StringRedisTemplate stringRedisTemplate;
    private static LinkedBlockingQueue<Runnable> queue =  new LinkedBlockingQueue<>();
    private static final ExecutorService pool = new ThreadPoolExecutor(2, 2, 0L, TimeUnit.SECONDS, queue);

    @Test
    public void getCode() {
        AtomicInteger sum = new AtomicInteger();
        AtomicInteger success = new AtomicInteger();
        AtomicInteger fail = new AtomicInteger();
        HashOperations<String, String, String> hash = stringRedisTemplate.opsForHash();
        Set<String> keys = hash.keys(RedisKeys.KAPTCHA.getName());
        for (String key : keys) {
            pool.execute(() -> {
                try {
                    CaptchaBreaker.getCode(key);
                    log.info("key {} success", key);
                    success.getAndIncrement();
                }catch (Exception e){
                    log.info("key {} fail", key);
                    fail.getAndIncrement();
                }finally {
                    sum.getAndIncrement();
                    log.info("sum {} success {} fail {}", sum.get(), success.get(), fail.get());
                }

            });

        }
        while (true){

        }


    }
}