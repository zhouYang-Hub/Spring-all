package org.example.lock.service;

/**
 * @author: ZY
 * @description
 * @date: 2023/7/13 15:00
 */
public interface RedisCacheService {
    String create();

    String update();

    String getProduction(Integer productId);
}
