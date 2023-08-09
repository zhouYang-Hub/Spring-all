package com.zhouyang.springcloud.order;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

/**
 * ${NAME}:
 *
 * @author zhouYang
 * @date ${YEAR}/${MONTH}/${DAY}
 */
@SpringBootApplication
public class OrderSererApplication {
    public static void main(String[] args) {
        SpringApplication.run(OrderSererApplication.class, args);
    }

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        return builder.build();
    }
}