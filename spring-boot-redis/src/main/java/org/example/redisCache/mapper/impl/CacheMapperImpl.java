package org.example.redisCache.mapper.impl;

import com.alibaba.fastjson.JSON;
import org.example.po.UserEntityPO;
import org.example.redisCache.mapper.CacheMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * @author: ZY
 * @description
 * @date: 2023/7/10 11:55
 */
@Component
public class CacheMapperImpl implements CacheMapper {

    private static final Logger log = LoggerFactory.getLogger(CacheMapperImpl.class);

    @Override
    public UserEntityPO getUserByUserId(Integer userId) {
        log.info("走数据库查询");
        UserEntityPO userEntity = new UserEntityPO();
        userEntity.setUserId(userId);
        userEntity.setUserName("userName".concat(":").concat(userId.toString()));
        log.info("GET_USER_RESUlt:{}", JSON.toJSONString(userEntity));
        return userEntity;
    }
}
