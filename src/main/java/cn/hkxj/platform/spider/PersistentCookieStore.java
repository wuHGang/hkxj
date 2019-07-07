package cn.hkxj.platform.spider;

import cn.hkxj.platform.pojo.constant.RedisKeys;
import lombok.extern.slf4j.Slf4j;
import okhttp3.Cookie;
import org.apache.http.util.TextUtils;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.StringUtils;

import javax.naming.Context;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.CookieStore;
import java.net.HttpCookie;
import java.net.URI;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import static ch.qos.logback.core.encoder.ByteArrayUtil.hexStringToByteArray;

@Slf4j
public class PersistentCookieStore implements CookieStore {

    private static final String LOG_TAG = "PersistentCookieStore";
    private static final String COOKIE_PREFS = "CookiePrefsFile";
    private static final String HOST_NAME_PREFIX = "host_";
    private static final String COOKIE_NAME_PREFIX = "cookie_";
    private final HashMap<String, ConcurrentHashMap<String, Cookie>> cookies;
    private boolean omitNonPersistentCookies = false;
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

            String[] cookieNameArr = cookieNames.split(",");
            for (String name : cookieNameArr) {
                String encodedCookie = this.cookiePrefs.getString("cookie_" + name, null);
                if (encodedCookie == null) {
                    continue;
                }

                Cookie decodedCookie = this.decodeCookie(encodedCookie);
                if (decodedCookie != null) {
                    this.cookies.get(key).put(name, decodedCookie);
                }
            }
        }
        tempCookieMap.clear();

    }


    @Override
    public void add(URI uri, HttpCookie cookie) {

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

    protected Cookie decodeCookie(String cookieString) {
        byte[] bytes = hexStringToByteArray(cookieString);
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bytes);
        Cookie cookie = null;
        try {
            ObjectInputStream objectInputStream = new ObjectInputStream(byteArrayInputStream);
            cookie = ((SerializableCookie) objectInputStream.readObject()).getCookie();
        } catch (IOException e) {
            log.error("IOException in decodeCookie", e);
        } catch (ClassNotFoundException e) {
            log.error("ClassNotFoundException in decodeCookie", e);
        }
        return cookie;
    }
}