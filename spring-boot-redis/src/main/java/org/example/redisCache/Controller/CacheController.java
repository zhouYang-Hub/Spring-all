package org.example.redisCache.Controller;

import org.example.po.UserEntityPO;
import org.example.redisCache.service.CacheService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: ZY
 * @description
 * @date: 2023/7/10 11:42
 */
@RestController
@RequestMapping(value = "/cache")
public class CacheController {

    @Autowired
    private CacheService cacheService;

    @GetMapping(value = "/getUsers/{userId}")
    public UserEntityPO getUserByUserId(@PathVariable("userId") Integer userId) {
        return cacheService.getUserByUserId(userId);
    }


}
