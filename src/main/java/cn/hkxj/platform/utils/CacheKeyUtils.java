
package cn.hkxj.platform.utils;

import java.time.LocalDateTime;

/**
 * @author zhouqinglai
 * @version version
 * @title CacheKeyUtils
 * @desc
 * @date: 2019年05月02日
 */
public class CacheKeyUtils {

    private static final String CACHE_PREFIX = "HeiKeXiaoJi:";

    private static final String HASH_KEY_PREFIX = "hash";

    /**
     * 需要统计的的业务key
     * 注意：key每天更新
     *
     * @param key 指定的业务key
     * @return key
     */
    public static String getStatisticsKeyForEveryMonth(String key) {
        return String.join(CACHE_PREFIX, key, DateUtils.getTimeOfPattern(LocalDateTime.now(), DateUtils.YYYY_MM_PATTERN));
    }

    public static String getStatisticsKeyForEveryDay(String key, LocalDateTime time) {
        return String.join(CACHE_PREFIX, key, DateUtils.getTimeOfPattern(time, DateUtils.YYYY_MM_DD_PATTERN));
    }


}
