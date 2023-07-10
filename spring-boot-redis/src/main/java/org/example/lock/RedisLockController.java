package org.example.lock;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: ZY
 * @description
 * @date: 2023/7/10 16:45
 */
@RestController
@RequestMapping(value = "/lock")
public class RedisLockController {

    private static final Logger log = LoggerFactory.getLogger(RedisLockController.class);

    @Autowired
    private StringRedisTemplate redisTemplate;

    @GetMapping(value = "/lockStock")
    public String getLockStock() {
        //单机下使用 synchronized jvm锁即可
        String stockKey = "stock";
        synchronized (this) {
            Integer stock = Integer.parseInt(redisTemplate.opsForValue().get(stockKey));
            log.info("getLockStock-start：{}", stock);
            if (stock > 0) {
                redisTemplate.opsForValue().set(stockKey, String.valueOf(stock - 1));
                log.info("扣减库存成功；剩余库stock:{}", stock - 1);
            } else {
                log.info("扣减库存失败；库存不足。。。。。。");
            }
        }
        return "end";
    }
}
