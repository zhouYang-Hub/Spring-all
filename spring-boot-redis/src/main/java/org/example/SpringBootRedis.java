package org.example;

import org.redisson.Redisson;
import org.redisson.config.Config;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

/**
 * @author user
 */
@SpringBootApplication
public class SpringBootRedis {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootRedis.class, args);
    }

    @Bean
    public Redisson redisson() {
        //单机模式
        Config config = new Config();
        config.useSingleServer().setAddress("redis://localhost:6379").setDatabase(0);
        return (Redisson) Redisson.create(config);
    }
}
