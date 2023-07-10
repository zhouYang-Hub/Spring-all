package org.example.redisCache.mapper;

import org.example.po.UserEntityPO;

/**
 * @author: ZY
 * @description
 * @date: 2023/7/10 11:54
 */
public interface CacheMapper {

    UserEntityPO getUserByUserId(Integer userId);
}
