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
    public static final String BOOT_EXCHANGE = "boot_exchange";
    public static final String BOOT_EXCHANGE_CONFIRM = "boot_exchange_confirm";

    //定义空交换机名称
    public static final String BOOT_EXCHANGE_CONFIRM_NULL = "";


    //队列名称
    public static final String BOOT_QUEUE = "boot_queue";
    public static final String BOOT_QUEUE_CONFIRM = "boot_queue_confirm";

    //理由Key
    public static final String ROUTING_KEY = "confirm";

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

    /**
     * 消息的可靠投递
     */
    //声明交换机
    @Bean("boot_exchange_confirm")
    public Exchange boot_exchange_confirm() {
        return ExchangeBuilder.directExchange(BOOT_EXCHANGE_CONFIRM).durable(true).build();
    }

    //声明队列
    @Bean("boot_queue_confirm")
    public Queue boot_queue_confirm() {
        return QueueBuilder.durable(BOOT_QUEUE_CONFIRM).build();
    }

    //交换机绑定队列  boot.# 表示boot开头的所有消息
    @Bean
    public Binding bingQueueExchangeConfirm(@Qualifier("boot_queue_confirm") Queue queue, @Qualifier("boot_exchange_confirm") Exchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with(ROUTING_KEY).noargs();
    }
}
