package com.zhouyang.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Exchange;
import org.springframework.amqp.core.ExchangeBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.QueueBuilder;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author: ZY
 * @description
 * @date: 2023/10/24 16:26
 */
@Configuration
public class RabbitMqConfig {

    //交换机名称
    private static final String BOOT_EXCHANGE = "boot_exchange";

    //队列名称
    private static final String BOOT_QUEUE = "boot_queue";

    //声明交换机
    @Bean("bootExchange")
    public Exchange bootExchange() {

        return ExchangeBuilder.topicExchange(BOOT_EXCHANGE).durable(true).build();
    }

    //声明队列
    @Bean("bootQueue")
    public Queue bootQueue() {
        
        return QueueBuilder.durable(BOOT_QUEUE).build();
    }

    //交换机绑定队列  boot.# 表示boot开头的所有消息
    @Bean
    public Binding bingQueueExchange(@Qualifier("bootQueue") Queue queue, @Qualifier("bootExchange") Exchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with("boot.#").noargs();
    }

}
