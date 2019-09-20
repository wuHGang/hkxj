package cn.hkxj.platform.spider;

import cn.hkxj.platform.spider.persistentcookiejar.ClearableCookieJar;
import cn.hkxj.platform.spider.persistentcookiejar.cache.CookieCache;
import cn.hkxj.platform.spider.persistentcookiejar.cache.SetCookieCache;
import cn.hkxj.platform.spider.persistentcookiejar.persistence.RedisCookiePersistor;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
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
import java.util.concurrent.TimeUnit;

/**
 * 实现okHttp的cookieJar接口需要实现的两个方法saveFromResponse和loadForRequest
 * 这样的cookieJar只能维护一个client的cookie，如果需要维护多个用户的cookieJar就必须要创建多个client
 * 由于需要实现的两个方法不能添加参数，所以使用线程变量来传递需要的参数
 *
 * @author junrong.chen
 * @date 2019/7/16
 */
@Slf4j
@EverythingIsNonNull
public class UrpCookieJar implements ClearableCookieJar {

    private final Cache<String,CookieCache> accountCookieCache;


    private final RedisCookiePersistor persistor;

    UrpCookieJar() {
        persistor = new RedisCookiePersistor();
        accountCookieCache = CacheBuilder.newBuilder()
                .maximumSize(100)
                .expireAfterAccess(20L, TimeUnit.MINUTES)
                .build();
        try{
            Map<String, List<Cookie>> accountCookieMap = persistor.loadAll();
            for (Map.Entry<String, List<Cookie>> entry : accountCookieMap.entrySet()) {
                CookieCache cookieCache = new SetCookieCache();
                cookieCache.addAll(entry.getValue());
                accountCookieCache.put(entry.getKey(), cookieCache);
            }
        }catch (Exception e){
            log.error("UrpCookieJar init error ", e);
        }


    }

    @Override
    public void saveFromResponse(HttpUrl url, List<Cookie> cookies) {

        CookieCache cookieCache = selectCookieCache();
        cookieCache.addAll(cookies);
        persistor.saveByAccount(cookies, MDC.get("account"));
    }

    @Override
    public List<Cookie> loadForRequest(HttpUrl url) {
        CookieCache cookieCache = selectCookieCache();
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
     */
    private CookieCache selectCookieCache() {
        CookieCache result;
        if(StringUtils.isNotEmpty(MDC.get("preLoad"))){
            result = accountCookieCache.getIfPresent(MDC.get("preLoad"));
            accountCookieCache.put(MDC.get("account"), result);
        }

        if (StringUtils.isNotEmpty(MDC.get("account"))) {
            result = accountCookieCache.getIfPresent(MDC.get("account"));
            if(result == null){
                CookieCache cookieCache = new SetCookieCache();
                accountCookieCache.put(MDC.get("account"), cookieCache);
                return cookieCache;
            }
            return result;
        } else {
            throw new RuntimeException("no cookie jar can use");
        }
    }


    private static boolean isCookieExpired(Cookie cookie) {
        return cookie.expiresAt() < System.currentTimeMillis();
    }

    @Override
    public void clearSession() {
        String account = MDC.get("account");
        if(StringUtils.isNotEmpty(account)){
            accountCookieCache.invalidate(account);
            persistor.clearByAccount(account);
        }
    }

    boolean isCookieExpiredByAccount(String account){
        return accountCookieCache.getIfPresent(account) == null;
    }

    @Override
    public void clear() {
        accountCookieCache.cleanUp();
    }


}
