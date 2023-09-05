package com.zhouyang.springcloud.order.config;

import feign.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * OpenFeignConfig:
 *
 * @author zhouYang
 * @date 2023/09/05
 * @description: OpenFeign的配置类  其中@Configuration 表示全局的配置类，
 * 如果想要针对某个服务的配置类，可以使用@FeignClient的configuration属性，然后指定配置类，在OpenFeignConfig 配置类中取消@Configuration注解
 */
@Configuration
public class OpenFeignConfig {

    /**
     * 配置OpenFeign的日志级别 有四种
     * NONE：默认的，不显示任何日志
     * BASIC：仅记录请求方法、URL、响应状态码及执行时间
     * HEADERS：除了BASIC中定义的信息之外，还有请求和响应的头信息
     * FULL：除了HEADERS中定义的信息之外，还有请求和响应的正文及元数据
     *
     * @return
     */
    @Bean
    public Logger.Level feignLoggerLevel() {
        return Logger.Level.FULL;
    }
}
