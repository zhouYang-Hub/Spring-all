package com.zhouyang.springcloud.ribbon.rule;

import com.netflix.client.config.IClientConfig;
import com.netflix.loadbalancer.AbstractLoadBalancerRule;
import com.netflix.loadbalancer.Server;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

/**
 * CustomerRule:    自定义负载均衡策略
 *
 * @author zhouYang
 * @date 2023/08/28
 */
public class CustomerRule extends AbstractLoadBalancerRule {
    @Override
    public void initWithNiwsConfig(IClientConfig iClientConfig) {

    }

    @Override
    public Server choose(Object key) {
        //获取所有可达的服务
        List<Server> reachableServers = this.getLoadBalancer().getReachableServers();
        //获取一个随机数
        int random = ThreadLocalRandom.current().nextInt(reachableServers.size());
        Server server = reachableServers.get(random);
        return server;
    }
}
