package com.zhouyang.listener;

import com.zhouyang.config.RabbitMqConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @author: ZY
 * @description
 * @date: 2023/10/25 16:06
 */
@Slf4j
@Component
public class DLXListener {

    @RabbitListener(queues = RabbitMqConfig.BOOT_QUEUE_DEAD)
    public void dlxListener(String msg) {
        log.info("DLXListener接收到消息：{}", msg);
    }
}
