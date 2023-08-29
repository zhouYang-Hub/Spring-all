package com.zhouyang.springcloud.order;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

/**
 * ${NAME}:
 *
 * @author zhouYang
 * @date ${YEAR}/${MONTH}/${DAY}
 */
@SpringBootApplication
public class LoadBalancerSererApplication {
    public static void main(String[] args) {
        SpringApplication.run(LoadBalancerSererApplication.class, args);
    }

    @Bean
    @LoadBalanced
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}