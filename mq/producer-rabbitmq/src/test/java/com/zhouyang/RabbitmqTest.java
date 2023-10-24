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
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

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

        //自定义消息回调
        rabbitTemplate.setConfirmCallback(new RabbitTemplate.ConfirmCallback() {
            @Override
            public void confirm(CorrelationData correlationData, boolean ack, String cause) {
                log.info("消息id:{}" + correlationData);
                System.out.println("消息id:" + correlationData);
                System.out.println("确认结果:" + ack);
                System.out.println("失败原因:" + cause);
                if (ack) {
                    System.out.println("消息接收成功：" + cause);
                } else {
                    System.out.println("消息接收失败：" + cause);
                }
            }
        });
        rabbitTemplate.convertAndSend(RabbitMqConfig.BOOT_EXCHANGE_CONFIRM, RabbitMqConfig.ROUTING_KEY, "hello world- routing_key  Confirm .... 消息");
    }
}
