package org.example.redisCache.service;

import org.example.po.UserEntityPO;
import org.example.redisCache.mapper.CacheMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author: ZY
 * @description
 * @date: 2023/7/10 11:47
 */
@Service
public class CacheService {

    @Autowired
    private CacheMapper cacheMapper;

    /**
     * value：缓存key的前缀。
     * key：缓存key的后缀。
     * sync：设置如果缓存过期是不是只放一个请求去请求数据库，其他请求阻塞，默认是 false（根据个人需求）。
     * unless：不缓存空值,这里不使用，会报错
     * 查询用户信息类
     * 如果需要加自定义字符串，需要用单引号
     * 如果查询为null，也会被缓存
     */
//    @Cacheable(cacheNames = "GET:USER", key = "'user' + #userId")
//    @CacheEvict
    public UserEntityPO getUserByUserId(Integer userId) {
        UserEntityPO userEntity = cacheMapper.getUserByUserId(userId);
        return userEntity;
    }
}
