package cn.hkxj.platform.spider;

import lombok.extern.slf4j.Slf4j;
import okhttp3.Cookie;
import okhttp3.CookieJar;
import okhttp3.HttpUrl;
import okhttp3.internal.annotations.EverythingIsNonNull;
import okhttp3.internal.platform.Platform;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.MDC;

import java.io.IOException;
import java.net.CookieHandler;
import java.net.CookieManager;
import java.net.HttpCookie;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import static okhttp3.internal.Util.delimiterOffset;
import static okhttp3.internal.Util.trimSubstring;
import static okhttp3.internal.platform.Platform.WARN;

/**
 * @author junrong.chen
 * @date 2019/7/16
 */
@Slf4j
@EverythingIsNonNull
public class UrpCookieJar implements CookieJar {
    private final ConcurrentHashMap<String, CookieHandler> accountCookieHandler;
    private final ConcurrentHashMap<String, CookieHandler> traceCookieHandler;

    UrpCookieJar() {
        accountCookieHandler = new ConcurrentHashMap<>();
        traceCookieHandler = new ConcurrentHashMap<>();
    }

    @Override
    public void saveFromResponse(HttpUrl url, List<Cookie> cookies) {

        CookieHandler cookieHandler = selectCookieHandler();
        List<String> cookieStrings = new ArrayList<>();
        for (Cookie cookie : cookies) {
            cookieStrings.add(cookie.toString());
        }
        Map<String, List<String>> multimap = Collections.singletonMap("Set-Cookie", cookieStrings);
        try {
            cookieHandler.put(url.uri(), multimap);
        } catch (IOException e) {
            Platform.get().log(WARN, "Saving cookies failed for " + url.resolve("/..."), e);
        }
    }

    @Override
    public List<Cookie> loadForRequest(HttpUrl url) {
        CookieHandler cookieHandler = selectCookieHandler();
        // The RI passes all headers. We don't have 'em, so we don't pass 'em!
        Map<String, List<String>> headers = Collections.emptyMap();
        Map<String, List<String>> cookieHeaders;
        try {
            cookieHeaders = cookieHandler.get(url.uri(), headers);
        } catch (IOException e) {
            Platform.get().log(WARN, "Loading cookies failed for " + url.resolve("/..."), e);
            return Collections.emptyList();
        }

        List<Cookie> cookies = null;
        for (Map.Entry<String, List<String>> entry : cookieHeaders.entrySet()) {
            String key = entry.getKey();
            if (("Cookie".equalsIgnoreCase(key) || "Cookie2".equalsIgnoreCase(key)) && !entry.getValue().isEmpty()) {
                for (String header : entry.getValue()) {
                    if (cookies == null) cookies = new ArrayList<>();
                    cookies.addAll(decodeHeaderAsJavaNetCookies(url, header));
                }
            }
        }

        return cookies != null
                ? Collections.unmodifiableList(cookies)
                : Collections.emptyList();
    }


    private CookieHandler selectCookieHandler() {
        CookieManager cookieManager = new CookieManager();
        CookieHandler result = null;
        if (StringUtils.isNotEmpty(MDC.get("account"))) {
            result = accountCookieHandler.putIfAbsent(MDC.get("account"), cookieManager);
        } else if (StringUtils.isNotEmpty(MDC.get("cookieTrace"))) {
            result = traceCookieHandler.putIfAbsent(MDC.get("cookieTrace"), cookieManager);
        }

        log.error("no cookie jar to use");

        return result == null ? cookieManager : result;
    }

    /**
     * Convert a request header to OkHttp's cookies via {@link HttpCookie}. That extra step handles
     * multiple cookies in a single request header, which {@link Cookie#parse} doesn't support.
     */
    private List<Cookie> decodeHeaderAsJavaNetCookies(HttpUrl url, String header) {
        List<Cookie> result = new ArrayList<>();
        for (int pos = 0, limit = header.length(), pairEnd; pos < limit; pos = pairEnd + 1) {
            pairEnd = delimiterOffset(header, pos, limit, ";,");
            int equalsSign = delimiterOffset(header, pos, pairEnd, '=');
            String name = trimSubstring(header, pos, equalsSign);
            if (name.startsWith("$")) continue;

            // We have either name=value or just a name.
            String value = equalsSign < pairEnd
                    ? trimSubstring(header, equalsSign + 1, pairEnd)
                    : "";

            // If the value is "quoted", drop the quotes.
            if (value.startsWith("\"") && value.endsWith("\"")) {
                value = value.substring(1, value.length() - 1);
            }

            result.add(new Cookie.Builder()
                    .name(name)
                    .value(value)
                    .domain(url.host())
                    .build());
        }
        return result;
    }


    public void saveCookieByAccount(String account){
        if (StringUtils.isNotEmpty(MDC.get("cookieTrace"))) {
            CookieHandler cookieHandler = traceCookieHandler.get(MDC.get("cookieTrace"));
            accountCookieHandler.put(account, cookieHandler);
            traceCookieHandler.remove(MDC.get("cookieTrace"));
        }
    }

}
