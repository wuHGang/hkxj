package cn.hkxj.platform;

import cn.hkxj.platform.utils.ApplicationUtil;
import com.alibaba.fastjson.JSON;
import org.apache.commons.lang3.BooleanUtils;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SetOperations;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.util.AbstractQueue;
import java.util.Collection;
import java.util.Iterator;
import java.util.Objects;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

public class RedisSetBlockingQueue<E> extends AbstractQueue<E>
        implements BlockingQueue<E> {

    private RedisTemplate<String, E> redisTemplate;

    private String key;



    public RedisSetBlockingQueue(String key, RedisTemplate<String, E> redisTemplate){
        this.key = key;
        this.redisTemplate = redisTemplate;
    }

    @Override
    public Iterator<E> iterator() {
        return null;
    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public void put(E e) throws InterruptedException {
        SetOperations<String, E> opsForSet = redisTemplate.opsForSet();
        opsForSet.add(key, e);
    }

    @Override
    public boolean offer(E e, long timeout, TimeUnit unit) throws InterruptedException {
        SetOperations<String, E> opsForSet = redisTemplate.opsForSet();
        opsForSet.add(key, e);
        return true;
    }

    @Override
    public E take() throws InterruptedException {
        SetOperations<String, E> opsForSet = redisTemplate.opsForSet();
        return Objects.requireNonNull(opsForSet.pop(key));
    }

    @Override
    public E poll(long timeout, TimeUnit unit) throws InterruptedException {
        SetOperations<String, E> opsForSet = redisTemplate.opsForSet();
        return Objects.requireNonNull(opsForSet.pop(key));
    }

    @Override
    public int remainingCapacity() {
        return 0;
    }

    @Override
    public int drainTo(Collection<? super E> c) {
        return 0;
    }

    @Override
    public int drainTo(Collection<? super E> c, int maxElements) {
        return 0;
    }

    @Override
    public boolean offer(E e) {
        return false;
    }

    @Override
    public E poll() {
        return null;
    }

    @Override
    public E peek() {
        return null;
    }
}
