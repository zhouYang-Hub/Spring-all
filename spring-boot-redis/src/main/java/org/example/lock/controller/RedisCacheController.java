package org.example.lock.controller;

import org.example.lock.service.RedisCacheService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: ZY
 * @description
 * @date: 2023/7/13 14:57
 */
@RestController
@RequestMapping(value = "/redisCache")
public class RedisCacheController {
    @Autowired
    private RedisCacheService redisCacheService;

    @PostMapping(value = "/create")
    public String createCache() {
        return redisCacheService.create();
    }

    @PostMapping(value = "/update")
    public String updateCache() {
        return redisCacheService.update();
    }

    @GetMapping(value = "/getProduction/{productId}")
    public String getProduction(Integer productId) {
        return redisCacheService.getProduction(productId);
    }
}
