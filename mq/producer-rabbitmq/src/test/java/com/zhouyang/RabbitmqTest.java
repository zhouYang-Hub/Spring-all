package com.zhouyang;

/**
 * @author: ZY
 * @description
 * @date: 2023/10/24 16:41
 */

import com.zhouyang.config.RabbitMqConfig;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.CompletableFuture;

@Slf4j
@SpringBootTest
@RunWith(SpringRunner.class)
public class RabbitmqTest {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Test
    public void testSend() {
        rabbitTemplate.convertAndSend("boot_exchange", "boot.hello", "hello world".getBytes());
    }

    @Test
    public void testSend1() {
        rabbitTemplate.convertAndSend("boot_exchange", "zy.boot.hello", "hello world");
    }

    @Test
    public void testSendConfirm() {
        CompletableFuture<Boolean> confirm = sendWithConfirm();
        log.info("confirm: {}", confirm);
    }

    public CompletableFuture<Boolean> sendWithConfirm() {
        CompletableFuture<Boolean> future = new CompletableFuture<>();
        rabbitTemplate.setConfirmCallback((correlationData, ack, cause) -> {
            log.info("回调方法已被执行。。。。。。。");
            log.info("消息id:{}", correlationData);
            log.info("确认结果:{}", ack);
            log.info("失败原因:{}", cause);


            /**
             * ack   == true  就是表示消息已经正常发送到交换机
             */
            if (ack) {
                future.complete(true); // 消息发送成功
            } else {
                future.completeExceptionally(new RuntimeException("消息发送失败: " + cause));
            }
        });

        rabbitTemplate.convertAndSend(RabbitMqConfig.BOOT_EXCHANGE_CONFIRM, RabbitMqConfig.ROUTING_KEY, "hello world- routing_key  Confirm .... 消息");
        return future;

    }


    /**
     * 发送消息到ttl队列
     */
    @Test
    public void testSendMessageTTL() {
        rabbitTemplate.convertAndSend(RabbitMqConfig.BOOT_EXCHANGE_TTL, "boot-ttl.hello", "hello world ttl......");
        log.info("ttl消息发送成功");
    }

    /**
     * 死信队列
     */
    @Test
    public void testDLXMessage() {
        rabbitTemplate.convertAndSend(RabbitMqConfig.BOOT_NOR_EXCHANGE, "dead", "hello world dead DLX......");
        log.info(" ttl 消息发送成功");
    }

}

