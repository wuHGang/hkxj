package cn.hkxj.platform.spider;

import cn.hkxj.platform.spider.persistentcookiejar.ClearableCookieJar;
import cn.hkxj.platform.spider.persistentcookiejar.cache.CookieCache;
import cn.hkxj.platform.spider.persistentcookiejar.cache.SetCookieCache;
import cn.hkxj.platform.spider.persistentcookiejar.persistence.RedisCookiePersistor;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import okhttp3.Cookie;
import okhttp3.HttpUrl;
import okhttp3.internal.annotations.EverythingIsNonNull;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.MDC;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author junrong.chen
 * @date 2019/7/16
 */
@Slf4j
@EverythingIsNonNull
public class UrpCookieJar implements ClearableCookieJar {
    private final ConcurrentHashMap<String, CookieCache> accountCookieHandler;
    private final ConcurrentHashMap<String, CookieCache> traceCookieHandler;

    private RedisCookiePersistor persistor = new RedisCookiePersistor();

    UrpCookieJar() {
        accountCookieHandler = new ConcurrentHashMap<>();
        traceCookieHandler = new ConcurrentHashMap<>();

        Map<String, List<Cookie>> accountCookieMap = persistor.loadAll();
        for (Map.Entry<String, List<Cookie>> entry : accountCookieMap.entrySet()) {
            CookieCache cookieCache = new SetCookieCache();
            cookieCache.addAll(entry.getValue());
            accountCookieHandler.put(entry.getKey(), cookieCache);
        }

    }

    @Override
    public void saveFromResponse(HttpUrl url, List<Cookie> cookies) {

        CookieCache cookieCache = selectCookieHandler();
        cookieCache.addAll(cookies);
    }

    @Override
    public List<Cookie> loadForRequest(HttpUrl url) {
        CookieCache cookieCache = selectCookieHandler();
        // The RI passes all headers. We don't have 'em, so we don't pass 'em!
        List<Cookie> cookiesToRemove = new ArrayList<>();
        List<Cookie> validCookies = new ArrayList<>();

        for (Iterator<Cookie> it = cookieCache.iterator(); it.hasNext(); ) {
            Cookie currentCookie = it.next();

            if (isCookieExpired(currentCookie)) {
                cookiesToRemove.add(currentCookie);
                it.remove();

            } else if (currentCookie.matches(url)) {
                validCookies.add(currentCookie);
            }
        }

        return validCookies;
    }


    /**
     * 这里是concurrentHashMap的一个特性使然
     * 为了保证putIfAbsent这个操作的原子性，当put的key不存在的时候，该方法会返回null
     * @return
     */
    private CookieCache selectCookieHandler() {
        CookieCache cookieCache = new SetCookieCache();
        CookieCache result = null;
        if (StringUtils.isNotEmpty(MDC.get("account"))) {
            result = accountCookieHandler.putIfAbsent(MDC.get("account"), cookieCache);
        } else if (StringUtils.isNotEmpty(MDC.get("cookieTrace"))) {
            result = traceCookieHandler.putIfAbsent(MDC.get("cookieTrace"), cookieCache);
        }else {
            log.error("no cookie jar to use");
        }

        return result == null ? cookieCache : result;
    }


    public void saveCookieByAccount(String account){
        if (StringUtils.isNotEmpty(MDC.get("cookieTrace"))) {
            CookieCache cookieCache = traceCookieHandler.get(MDC.get("cookieTrace"));
            accountCookieHandler.put(account, cookieCache);
            persistor.saveByAccount(Lists.newArrayList(cookieCache.iterator()), account);

            traceCookieHandler.remove(MDC.get("cookieTrace"));
        }
    }

    private static boolean isCookieExpired(Cookie cookie) {
        return cookie.expiresAt() < System.currentTimeMillis();
    }

    @Override
    public void clearSession() {

    }

    @Override
    public void clear() {
        accountCookieHandler.clear();
        traceCookieHandler.clear();
    }


    public boolean canUseCookie(String account){
        return accountCookieHandler.containsKey(account);
    }
}
