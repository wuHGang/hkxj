package cn.hkxj.platform.spider.persistentcookiejar.persistence;

import okhttp3.Cookie;

import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * @author junrong.chen
 */
public interface AccountCookiePersistor {
    /**
     * 返回所有未过期的cookie
     * @return 账号与缓存的cookie的应
     */
    Map<String, List<Cookie>> loadAll();

    /**
     * 为对应账号保存给定的cookie
     * @param cookies 需要保存的cookie
     * @param account cookie对应的学号
     */
    void saveByAccount(Collection<Cookie> cookies, String account);

    /**
     * 删除对应账号指定的cookie
     * @param cookies 需要删除的cookie
     * @param account 对应的账号
     */
    void removeByAccount(Collection<Cookie> cookies, String account);

    /**
     * 删除对应学号所有的cookie
     * @param account 对用账号
     */
    void clearByAccount(String account);
}
