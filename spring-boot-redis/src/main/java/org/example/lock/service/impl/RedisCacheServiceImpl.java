package org.example.lock.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.alibaba.fastjson.JSON;
import org.example.lock.service.RedisCacheService;
import org.example.po.UserEntityPO;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * @author: ZY
 * @description
 * @date: 2023/7/13 15:01
 */
@Service
public class RedisCacheServiceImpl implements RedisCacheService {

    private static final Logger LOG = LoggerFactory.getLogger(RedisCacheServiceImpl.class);

    private static final String PRODUCT_CACHE_KEY = "PRODUCT_CACHE";
    private static final String LOCK_PRODUCT_HOT_CACHE_CREATE_PREFIX = "lock:product:Hot_cache_create:";

    private static final Integer PRODUCT_CACHE_TIMEOUT = 60 * 60 * 24;

    private static final String PRODUCT_EMPTY_CACHE = "{}";

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Autowired
    private RedissonClient redissonClient;

    @Override
    public String create() {
        UserEntityPO userEntityPO = new UserEntityPO();
        userEntityPO.setUserId("10001-".concat(String.valueOf(1)));
        userEntityPO.setUserName("username" + 1);
        redisTemplate.opsForValue().set(PRODUCT_CACHE_KEY, JSON.toJSONString(userEntityPO), PRODUCT_CACHE_TIMEOUT, TimeUnit.SECONDS);
        return "保存商品成功";
    }

    @Override
    public String update() {
        return "更新商品缓存成功";
    }

    /**
     * redis  加分布式锁解决热点缓存在并发场景重建问题 缓存双写一致性问题 分布式的读写锁 读 可重入  写互斥
     * 双重检测机制，防止击穿数据库
     *
     * @param productId
     * @return
     */
    @Override
    public String getProduction(Integer productId) {
        UserEntityPO userEntityPO = null;
        //查询缓存中是否存在
        //查询数据库是否存在
        //如果都不存在设置一个空字符串
        String productStr = redisTemplate.opsForValue().get(PRODUCT_CACHE_KEY);
        if (!ObjectUtil.isEmpty(productStr)) {
            if (PRODUCT_EMPTY_CACHE.equals(productStr)) {
                redisTemplate.expire(PRODUCT_CACHE_KEY, genProductEmptyCacheTimeOut(), TimeUnit.SECONDS);
                return new UserEntityPO().toString();
            }
            userEntityPO = JSON.parseObject(productStr, UserEntityPO.class);
            //续期
            redisTemplate.expire(PRODUCT_CACHE_KEY, genProductCacheTimeOut(), TimeUnit.SECONDS);
            return userEntityPO.toString();
        }
        //双层检测机制
        RLock lock = redissonClient.getLock(LOCK_PRODUCT_HOT_CACHE_CREATE_PREFIX + productId);
        lock.lock(10L, TimeUnit.SECONDS);
        try {
            productStr = redisTemplate.opsForValue().get(PRODUCT_CACHE_KEY);
            if (!ObjectUtil.isEmpty(productStr)) {
                if (PRODUCT_EMPTY_CACHE.equals(productStr)) {
                    redisTemplate.expire(PRODUCT_CACHE_KEY, genProductEmptyCacheTimeOut(), TimeUnit.SECONDS);
                    return null;
                }
                userEntityPO = JSON.parseObject(productStr, UserEntityPO.class);
                //续期
                redisTemplate.expire(PRODUCT_CACHE_KEY, genProductCacheTimeOut(), TimeUnit.SECONDS);
                //数据库读取
                UserEntityPO userEntityPO23 = userEntityPO;
                if (!ObjectUtil.isEmpty(userEntityPO23)) {
                    redisTemplate.opsForValue().set(PRODUCT_CACHE_KEY, JSON.toJSONString(userEntityPO), genProductCacheTimeOut(), TimeUnit.SECONDS);
                } else {
                    redisTemplate.opsForValue().set(PRODUCT_CACHE_KEY, PRODUCT_EMPTY_CACHE, genProductEmptyCacheTimeOut(), TimeUnit.SECONDS);
                }
            }
        } finally {
            lock.unlock();
        }
        return null;
    }

    public Integer genProductCacheTimeOut() {
        return PRODUCT_CACHE_TIMEOUT + new Random().nextInt();
    }

    public Integer genProductEmptyCacheTimeOut() {
        return 60 + new Random().nextInt();
    }
}
