package org.example.redisCache.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

/**
 * @author: ZY
 * @description
 * @date: 2023/7/8 15:47
 */
@Component
public class RedisService {

    private static final Logger log = LoggerFactory.getLogger(RedisService.class);


    @Autowired
    private StringRedisTemplate redisTemplate;

    public String redisService() {
        redisTemplate.opsForValue().set("name", "zhangSan");
        String str = redisTemplate.opsForValue().get("name");
        log.info("Test:{}", str);
        return str;
    }
}
