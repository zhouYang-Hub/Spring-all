package com.zhouyang.springcloud.order.feign;

import com.zhouyang.springcloud.order.config.OpenFeignConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * StockService:
 *
 * @author zhouYang
 * @date 2023/09/04
 * @see FeignClient name: 服务消费者服务名称，path： 服务消费者服务接口路径
 * 单独配置其中一个feign的日志，可以在配置类中配置，也可以在配置文件中配置 configuration = OpenFeignConfig.class
 */
@FeignClient(name = "stock-service", path = "/stock", configuration = OpenFeignConfig.class)
public interface StockService {

    /**
     * stock-service 服务远程接口
     *
     * @return
     */
    @GetMapping("/getStock")
    String getStock();
}
