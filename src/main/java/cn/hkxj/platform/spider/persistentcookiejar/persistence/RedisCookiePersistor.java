package cn.hkxj.platform.spider.persistentcookiejar.persistence;

import cn.hkxj.platform.pojo.constant.RedisKeys;
import cn.hkxj.platform.utils.ApplicationUtil;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import okhttp3.Cookie;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * @author junrong.chen
 */
@Slf4j
public class RedisCookiePersistor implements AccountCookiePersistor{

    private static RedisTemplate<String, String> redisTemplate;

    static {
        try{
            redisTemplate = ApplicationUtil.getBean("redisTemplate");
        }catch (Exception e){
            log.error("inject error", e);
        }

    }

    @Override
    synchronized public Map<String, List<Cookie>> loadAll() {
        Set<String> accountSet =
                Optional.ofNullable(redisTemplate.opsForSet().members(RedisKeys.URP_COOKIE_ACCOUNT.getName())).orElse(new HashSet<>());

        HashMap<String, List<Cookie>> accountCookieMap = Maps.newHashMapWithExpectedSize(accountSet.size());
        HashOperations<String, String, String> opsForHash = redisTemplate.opsForHash();
        for (String account : accountSet) {
            List<Cookie> cookies = Lists.newArrayList();

            for (Map.Entry<String, String> entry : opsForHash.entries(RedisKeys.URP_COOKIE.genKey(account)).entrySet()) {
                String serializedCookie = entry.getValue();
                Cookie cookie = new SerializableCookie().decode(serializedCookie);
                if (cookie != null) {
                    cookies.add(cookie);
                }
            }
            accountCookieMap.put(account, cookies);
        }

        return accountCookieMap;
    }

    @Override
    synchronized public void saveByAccount(Collection<Cookie> cookies, String account) {
        redisTemplate.opsForSet().add(RedisKeys.URP_COOKIE_ACCOUNT.getName(), account);

        HashMap<String, String> cookieMap = Maps.newHashMapWithExpectedSize(cookies.size());
        for (Cookie cookie : cookies) {
            cookieMap.put(createCookieKey(cookie), new SerializableCookie().encode(cookie));
        }
        String key = RedisKeys.URP_COOKIE.genKey(account);
        HashOperations<String, String, String> opsForHash = redisTemplate.opsForHash();
        opsForHash.putAll(key, cookieMap);
        redisTemplate.expire(key, 25L, TimeUnit.MINUTES);
    }

    @Override
    synchronized public void removeByAccount(Collection<Cookie> cookies, String account){
        HashOperations<String, String, String> opsForHash = redisTemplate.opsForHash();

        for (Cookie cookie : cookies) {
            opsForHash.delete(RedisKeys.URP_COOKIE.genKey(account), createCookieKey(cookie));
        }

    }


    @Override
    synchronized public void clearByAccount(String account) {
        redisTemplate.opsForSet().remove(RedisKeys.URP_COOKIE_ACCOUNT.getName(), account);
        redisTemplate.delete(RedisKeys.URP_COOKIE.genKey(account));
    }


    private static String createCookieKey(Cookie cookie) {
        return (cookie.secure() ? "https" : "http") + "://" + cookie.domain() + cookie.path() + "|" + cookie.name();
    }
}
