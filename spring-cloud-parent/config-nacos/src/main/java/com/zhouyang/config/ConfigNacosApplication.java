package com.zhouyang.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.concurrent.TimeUnit;

/**
 * ${NAME}:
 *
 * @author zhouYang
 * @date 2023/09/06
 */
@EnableDiscoveryClient
@SpringBootApplication
public class ConfigNacosApplication {
    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(ConfigNacosApplication.class, args);
        while (true) {
            try {
                String userName = context.getEnvironment().getProperty("user.name");
                String age = context.getEnvironment().getProperty("user.age");
                String config = context.getEnvironment().getProperty("comm.config");
                System.err.println("user name :" + userName + "; age: " + age + "; config: " + config);
                TimeUnit.SECONDS.sleep(1);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }
}