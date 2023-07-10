package org.example.redisCache.Controller;

import org.example.redisCache.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: ZY
 * @description
 * @date: 2023/7/8 15:30
 */
@RestController
@RequestMapping(value = "/redis")
public class RedisController {


    @Autowired
    private RedisService redisService;

    @RequestMapping(value = "/test")
    public String testRedis() {
        return redisService.redisService();
    }

}
