package com.zhouyang.test;

import cn.hutool.cache.CacheUtil;
import cn.hutool.cache.impl.TimedCache;
import cn.hutool.core.thread.ThreadUtil;

/**
 * @Author huyi @Date 2021/10/12 17:00 @Description:
 */
public class TimedCacheUtils {
    private static final TimedCache<String, String> TIMED_CACHE = CacheUtil.newTimedCache(5000);

    static {
        /** 每5ms检查一次过期 */
        TIMED_CACHE.schedulePrune(5);
    }

    /**
     * 存入键值对，提供消逝时间
     *
     * @param key
     * @param value
     * @param timeout
     */
    public static void put(String key, String value, Long timeout) {
        /** 设置消逝时间 */
        TIMED_CACHE.put(key, value, timeout);
    }

    /**
     * 每次重新get一次缓存，均会重新刷新消逝时间
     *
     * @param key
     * @return
     */
    public static String get(String key) {
        return TIMED_CACHE.get(key);
    }

    public static void main(String[] args) {
        put("haha", "1", 3000L);
        System.out.println("第1次结果:" + get("haha"));
        ThreadUtil.sleep(3000);
        System.out.println("第2次结果:" + get("haha"));
        System.out.println("第3次结果:" + get("haha"));
        // 取消定时清理
        TIMED_CACHE.cancelPruneSchedule();
    }

}
