package com.zhouyang.springcloud.order;

import com.zhouyang.springcloud.ribbon.config.RibbonRandomRuleConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.netflix.ribbon.RibbonClients;

/**
 * ${NAME}:
 *
 * @author zhouYang
 * @date ${YEAR}/${MONTH}/${DAY}
 */
@RibbonClients(value = {@RibbonClient(name = "stock-service", configuration = RibbonRandomRuleConfig.class)})
@SpringBootApplication
public class OrderSererApplication {
    public static void main(String[] args) {
        SpringApplication.run(OrderSererApplication.class, args);
    }
}