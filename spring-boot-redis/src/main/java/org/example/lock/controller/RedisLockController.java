package org.example.lock.controller;

import org.redisson.RedissonRedLock;
import org.redisson.api.RLock;
import org.redisson.api.RScript;
import org.redisson.api.RedissonClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
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
        String redisLockKey = "product_stock_lock_name";
        String lockKey = "stockKey";
        //解决锁实效的问题 只能释放自己加的锁
        String clientId = UUID.randomUUID().toString();
        //setNX  lock key
        try {
            Boolean absent = redisTemplate.opsForValue().setIfAbsent(redisLockKey, clientId, 10, TimeUnit.SECONDS);
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
            if (clientId.equals(redisTemplate.opsForValue().get(redisLockKey))) {
                //删除redis的标记key
                redisTemplate.delete(redisLockKey);
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
    @GetMapping(value = "redissonLock")
    public String redissonLock() {
        String lockKey = "stockKey";
        String redisLockKey = "product_stock_lock_name";
        //获取锁对象
        RLock redissonClientLock = redissonClient.getLock(redisLockKey);
        //上锁 实现锁续命功能
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


    /**
     * 通过lua  脚本实现分布式锁 执行指令的原子操作
     * 减少网络IO操作
     * 替代redis 事务
     *
     * @return
     */
    @GetMapping(value = "lua-lock-redis")
    public String redisLockLua() {
        //设置商品redis kye
        String redisLockLuaKey = "product:1001_stock";
        try {
            //设置商品的库存
            // redisTemplate.opsForValue().set(redis_lock_lua_key, "50");
            //执行减库存操作 lua  脚本 原子操作
            String scriptLua = "local stock_count = redis.call('GET', KEYS[1]) "
                    + " local stock_a = tonumber(stock_count) "
                    + " local stock_b = tonumber(ARGV[1]) "
                    + " if stock_a >= stock_b then "
                    + " redis.call('set',KEYS[1],stock_count -stock_b) "
                    + " return 1 "
                    + " end "
                    + "return 0 ";
            /**
             * RScript.Mode.READ_WRITE：指定执行模式为读写模式，表示执行 Lua 脚本期间可以对 Redis 进行读写操作。
             * RScript.Mode.READ_ONLY :只读模式，不能写操作
             * RScript.ReturnType.INTEGER：指定返回值类型为整数类型，表示期望脚本返回一个整数结果。
             * KEYS[1] 参数key
             * ARGV[1] 参数value
             */
            Object result = redissonClient.getScript().eval(RScript.Mode.READ_ONLY, scriptLua, RScript.ReturnType.INTEGER,
                    Arrays.asList(redisLockLuaKey), 1);
            log.info("result:{}", result);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            redissonClient.shutdown();
        }
        return "end";
    }

    /**
     * 半数以上节点加锁成功
     *
     * @return
     */
    public void readLock() {
        String lockKey = "product_001";
        RLock lock1 = redissonClient.getLock(lockKey);
        RLock lock2 = redissonClient.getLock(lockKey);
        RLock lock3 = redissonClient.getLock(lockKey);
        RedissonRedLock redissonRedLock = new RedissonRedLock(lock1, lock2, lock3);
        try {
            /**
             * 尝试获取锁，
             * waitTimeout 尝试获取锁的最大等待时间，超过这个值，则认为获取锁失败
             * leaseTime 锁的持有时间，超过这个时间锁会自动失效
             * 强一致性：RedissonRedLock 使用 RedLock 算法，通过在多个 Redis 节点上获取锁，并在大多数节点上成功获取锁时才认为获取到锁，确保锁的强一致性。
             * 容错性：RedissonRedLock 在获取锁时，即使有部分 Redis 节点不可用或出现网络分区等问题，只要大多数节点可用，仍然可以成功获取锁，确保锁的容错性。
             * 自动释放：RedissonRedLock 提供自动释放锁的机制，避免锁被长时间占用，即使在锁的持有者出现故障或异常情况时，也可以确保锁的自动释放，避免死锁。
             * 高性能：RedissonRedLock 在锁的获取和释放过程中，通过 Redis 的高性能和高吞吐量来实现快速响应和高效的锁管理。
             */
            boolean b = redissonRedLock.tryLock(10, 30, TimeUnit.SECONDS);
            if (b) {
                log.info("加锁成功");
            }
        } catch (Exception e) {
            throw new IllegalStateException(e.getMessage());
        } finally {
            redissonRedLock.unlock();
        }
    }


}
