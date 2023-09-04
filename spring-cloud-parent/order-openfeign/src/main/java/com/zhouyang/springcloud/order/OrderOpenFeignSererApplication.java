package com.zhouyang.springcloud.order;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * ${NAME}:
 *
 * @author zhouYang
 * @date ${YEAR}/${MONTH}/${DAY}
 */
@EnableFeignClients
@SpringBootApplication
public class OrderOpenFeignSererApplication {
    public static void main(String[] args) {
        SpringApplication.run(OrderOpenFeignSererApplication.class, args);
    }
}