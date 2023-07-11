package org.example.lock;

import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

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
    @Autowired
    private RedissonClient redissonClient;

    /**
     * Jvm 进程锁
     *
     * @return
     * @throws InterruptedException
     */
    @GetMapping(value = "/lockStock")
    public String getLockStock() throws InterruptedException {

        String lockKey = "stockKey";
        //单机下使用 synchronized jvm进程锁即可
        synchronized (this) {
            Integer stock = Integer.parseInt(redisTemplate.opsForValue().get(lockKey));
            log.info("getLockStock-start：{}", stock);
            if (stock > 0) {
                redisTemplate.opsForValue().set(lockKey, String.valueOf(stock - 1));
                log.info("扣减库存成功；剩余库stock:{}", stock - 1);
            } else {
                log.error("扣减库存失败；库存不足。。。。。。");
            }
        }
        return "end";
    }

    /**
     * 分布式redis 简单版本的锁 setNx 实现
     *
     * @return
     */
    @GetMapping(value = "/lockStock_setNx")
    public String getLockStockSetNx() {
        String redis_lock_Key = "product_stock_lock_name";
        String lockKey = "stockKey";
        //解决锁实效的问题 只能释放自己加的锁
        String client_id = UUID.randomUUID().toString();
        //setNX  lock key
        try {
            Boolean absent = redisTemplate.opsForValue().setIfAbsent(redis_lock_Key, client_id, 10, TimeUnit.SECONDS);
            if (!absent) {
                return "errorCode:1001,message:获取redis Key 异常";
            }
            //执行减库存的代码
            //获取库存
            int stock = Integer.parseInt(redisTemplate.opsForValue().get(lockKey));
            if (stock > 0) {
                redisTemplate.opsForValue().set(lockKey, String.valueOf(stock - 1));
                log.info("扣减库存成功;剩余库存数量：{}", stock - 1);
            } else {
                log.error("库存不足；扣减库存失败");
            }
        } finally {
            if (client_id.equals(redisTemplate.opsForValue().get(redis_lock_Key))) {
                //删除redis的标记key
                redisTemplate.delete(redis_lock_Key);
            }
        }
        log.info("end");
        return "end";
    }

    /**
     * 基于redisson 框架实现的分布式锁
     *
     * @return
     */
    public String redissonLock() {
        String lockKey = "stockKey";
        String redis_lock_Key = "product_stock_lock_name";
        //获取锁对象
        RLock redissonClientLock = redissonClient.getLock(redis_lock_Key);
        //上锁
        redissonClientLock.lock();
        int stock = Integer.parseInt(redisTemplate.opsForValue().get(lockKey));
        if (stock > 0) {
            redisTemplate.opsForValue().set(lockKey, String.valueOf(stock - 1));
        } else {
            log.error("库存不足；扣减库存失败");
        }
        //手动释放锁
        redissonClientLock.unlock();
        return "end";
    }
}
