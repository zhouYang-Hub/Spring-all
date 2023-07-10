package org.example.constant;

/**
 * @param
 * @author zhouYang
 */
public class CacheConstants {

    /**
     * 缓存常量CacheConstants
     * 默认过期时间（配置类中我使用的时间单位是秒，所以这里如 3*60 为3分钟）
     */
    public static final int DEFAULT_EXPIRES = 3 * 60;

    public static final int EXPIRES_5_MIN = 5 * 60;

    public static final int EXPIRES_10_MIN = 10 * 60;

    public static final String GET_USER = "GET:USER";

    public static final String GET_DYNAMIC = "GET:DYNAMIC";
}