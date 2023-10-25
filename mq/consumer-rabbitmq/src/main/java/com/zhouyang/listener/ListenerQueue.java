package com.zhouyang.listener;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @author: ZY
 * @description
 * @date: 2023/10/24 16:57
 */
@Component
public class ListenerQueue {
    
    @RabbitListener(queues = "boot_queue")
    public void listenerQueue(Message message) {
        System.out.println("boot_queue接收到消息：" + message.getBody());
        long deliveryTag = message.getMessageProperties().getDeliveryTag();

        System.out.println("deliveryTag = " + deliveryTag);

    }

}
