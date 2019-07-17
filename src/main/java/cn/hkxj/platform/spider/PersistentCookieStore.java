package cn.hkxj.platform.spider;

import cn.hkxj.platform.pojo.constant.RedisKeys;
import lombok.extern.slf4j.Slf4j;
import okhttp3.Cookie;
import okhttp3.HttpUrl;
import org.apache.http.util.TextUtils;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.StringUtils;

import javax.naming.Context;
import java.net.CookieStore;
import java.net.HttpCookie;
import java.net.URI;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
public class PersistentCookieStore implements CookieStore {

    private static final String HOST_NAME_PREFIX = "host_";
    private static HashMap uriIndex = new HashMap<URI, List<HttpCookie>>();
    /**
     * 用户前置访问验证码的session的保存
     */
    private static ConcurrentHashMap verifyCodeIndex = new ConcurrentHashMap<String, List<HttpCookie>>();
    /**
     * 用户验证码验证成功后的session保存
     */
    private static ConcurrentHashMap accountIndex = new ConcurrentHashMap<String, List<HttpCookie>>();
    private final HashMap<String, ConcurrentHashMap<String, Cookie>> cookies;
    private RedisTemplate<String, String> redisTemplate;

    /** Construct a persistent cookie store.  */
    public PersistentCookieStore(Context context) {

        this.cookies = new HashMap<>();
        HashOperations<String, String, String> opsForHash = redisTemplate.opsForHash();

        Map<String, String> tempCookieMap = new HashMap<>(opsForHash.entries(RedisKeys.URP_SPIDER_COOKIE.getName()));
        for (String key : tempCookieMap.keySet()) {
            if (StringUtils.isEmpty(key) || !key.contains(HOST_NAME_PREFIX)) {
                continue;
            }

            String cookieNames = tempCookieMap.get(key);
            if (TextUtils.isEmpty(cookieNames)) {
                continue;
            }

            if (!this.cookies.containsKey(key)) {
                this.cookies.put(key, new ConcurrentHashMap<>());
            }

        }
        tempCookieMap.clear();

    }


    @Override
    public void add(URI uri, HttpCookie cookie) {
        if (cookie == null) {
            throw new NullPointerException("cookie is null");
        }

//        try {
//            if (cookie.getMaxAge() != 0) {
//                // and add it to domain index
//                if (cookie.getDomain() != null) {
//                    addIndex(domainIndex, cookie.getDomain(), cookie);
//                }
//                if (uri != null) {
//                    // add it to uri index, too
//                    addIndex(uriIndex, getEffectiveURI(uri), cookie);
//                }
//            }
//        } finally {
//            lock.unlock();
//        }
    }

    @Override
    public List<HttpCookie> get(URI uri) {
        return null;
    }

    @Override
    public List<HttpCookie> getCookies() {
        return null;
    }

    @Override
    public List<URI> getURIs() {
        return null;
    }

    @Override
    public boolean remove(URI uri, HttpCookie cookie) {
        return false;
    }

    @Override
    public boolean removeAll() {
        return false;
    }

    // add 'cookie' indexed by 'index' into 'indexStore'
    private <T> void addIndex(Map<T, List<HttpCookie>> indexStore, T index, HttpCookie cookie)
    {
        if (index != null) {
            List<HttpCookie> cookies = indexStore.get(index);
            if (cookies != null) {
                // there may already have the same cookie, so remove it first
                cookies.remove(cookie);

                cookies.add(cookie);
            } else {
                cookies = new ArrayList<HttpCookie>();
                cookies.add(cookie);
                indexStore.put(index, cookies);
            }
        }
    }
}