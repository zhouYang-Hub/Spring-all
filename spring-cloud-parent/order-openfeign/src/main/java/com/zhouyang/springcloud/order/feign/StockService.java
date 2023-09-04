package com.zhouyang.springcloud.order.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * StockService:
 *
 * @author zhouYang
 * @date 2023/09/04
 * @see FeignClient name: 服务消费者服务名称，path： 服务消费者服务接口路径
 */
@FeignClient(name = "stock-service", path = "/stock")
public interface StockService {

    /**
     * stock-service 服务远程接口
     *
     * @return
     */
    @GetMapping("/getStock")
    String getStock();
}
