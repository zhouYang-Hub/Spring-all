package com.zhouyang.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Exchange;
import org.springframework.amqp.core.ExchangeBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.QueueBuilder;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * @author: ZY
 * @description
 * @date: 2023/10/24 16:26
 */
@Configuration
@Slf4j
public class RabbitMqConfig {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    //交换机名称
    public static final String BOOT_EXCHANGE = "boot_exchange";
    public static final String BOOT_EXCHANGE_CONFIRM = "boot_exchange_confirm";

    //ttl
    public static final String BOOT_EXCHANGE_TTL = "boot_exchange_ttl";

    //定义空交换机名称
    public static final String BOOT_EXCHANGE_CONFIRM_NULL = "";


    //队列名称
    public static final String BOOT_QUEUE = "boot_queue";
    public static final String BOOT_QUEUE_CONFIRM = "boot_queue_confirm";

    public static final String BOOT_QUEUE_TTL = "boot_queue_ttl";

    //路由Key
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

    //ttl
    @Bean("boot_exchange_ttl")
    public Exchange bootExchangeTtl() {
        return ExchangeBuilder.topicExchange(BOOT_EXCHANGE_TTL).durable(true).build();
    }

    @Bean("boot_queue_ttl")
    public Queue bootQueueTtl() {
        Map<String, Object> args = new HashMap<>(3);
        args.put("x-message-ttl", 10000);
        return QueueBuilder.durable(BOOT_QUEUE_TTL).withArguments(args).build();
    }

    @Bean
    public Binding bingQueueExchangeTtl(@Qualifier("boot_queue_ttl") Queue queue, @Qualifier("boot_exchange_ttl") Exchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with("boot-ttl.#").noargs();
    }

    //死信交换机
    public static final String BOOT_NOR_EXCHANGE = "boot_nor_exchange";
    public static final String BOOT_NOR_QUEUE = "boot_nor_queue";

    public static final String BOOT_EXCHANGE_DEAD = "boot_exchange_dead";
    public static final String BOOT_QUEUE_DEAD = "boot_queue_dead";


    //声明交换机
    @Bean("boot_normal_exchange")
    public DirectExchange bootNorExchange() {
        return (DirectExchange) ExchangeBuilder.directExchange(BOOT_NOR_EXCHANGE).build();
    }

    //声明队列
    @Bean("boot_normal_queue")
    public Queue bootNorQueue() {
        HashMap<String, Object> args = new HashMap<>();
        args.put("x-message-ttl", 10000);
        args.put("x-dead-letter-exchange", BOOT_EXCHANGE_DEAD);
        args.put("x-dead-letter-routing-key", "dead");
        return QueueBuilder.durable(BOOT_NOR_QUEUE).withArguments(args).build();
    }

    //交换机绑定队列  boot.# 表示boot开头的所有消息
    @Bean
    public Binding bingQueueExchangeNor(@Qualifier("boot_normal_queue") Queue queue, @Qualifier("boot_normal_exchange") Exchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with("dead").noargs();
    }

    //死信交换机
    @Bean("boot_exchange_dead")
    public DirectExchange bootExchangeDead() {
        return (DirectExchange) ExchangeBuilder.directExchange(BOOT_EXCHANGE_DEAD).build();
    }

    @Bean("boot_queue_dead")
    public Queue bootQueueDead() {
        return QueueBuilder.durable(BOOT_QUEUE_DEAD).build();
    }

    @Bean
    public Binding bingQueueExchangeDead(@Qualifier("boot_queue_dead") Queue queue, @Qualifier("boot_exchange_dead") DirectExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with("dead");
    }

}
