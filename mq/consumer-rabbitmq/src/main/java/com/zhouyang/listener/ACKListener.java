package com.zhouyang.listener;

import com.rabbitmq.client.Channel;
import com.zhouyang.config.RabbitMqConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @author: ZY
 * @description
 * @date: 2023/10/25 13:55
 */
@SuppressWarnings("ALL")
@Slf4j
@Component
public class ACKListener {


    /**
     * @param message
     * @param channel
     * @throws Exception
     */
    @RabbitListener(queues = RabbitMqConfig.BOOT_QUEUE_CONFIRM)
    public void ACKConfirmListener(Message message, Channel channel) throws Exception {
        log.info("ACKListener接收到消息：{}; 消息ID:{}", new String(message.getBody()), message.getMessageProperties().getDeliveryTag());
        //获取消息ID

        long deliveryTag = message.getMessageProperties().getDeliveryTag();
        try {
            //只确认当前消息
            channel.basicAck(deliveryTag, false);

        } catch (Exception e) {
            log.error("ACKListener接收到消息失败：{}", e.getMessage());
            //拒绝当前消息
            /**
             * deliveryTag:消息ID
             * multiple：是否批量 true：将一次性拒绝所有小于deliveryTag的消息
             * requeue：是否重新入队列
             */
            channel.basicNack(deliveryTag, true, true);
        }
    }

}
