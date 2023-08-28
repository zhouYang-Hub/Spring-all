package com.zhouyang.springcloud.ribbon.config;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * RibbonRandomConfig:
 *
 * @author zhouYang
 * @date 2023/08/28
 */
@Configuration
public class RibbonRandomRuleConfig {

    /**
     * 配置负载均衡策略
     *
     * @return
     */
    @Bean
    public IRule ribbonRule() {
        return new RandomRule();
    }
}
