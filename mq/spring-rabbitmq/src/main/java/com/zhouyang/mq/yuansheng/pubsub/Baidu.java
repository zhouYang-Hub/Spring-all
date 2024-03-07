package com.zhouyang.mq.yuansheng.pubsub;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.DefaultConsumer;
import com.zhouyang.mq.constant.RabbitConstant;
import com.zhouyang.mq.util.RabbitMqUtil;

/**
 * @author: ZY
 * @description
 * @date: 2023/10/17 17:11
 */
public class Baidu {
    public static void main(String[] args) throws Exception {

        Connection connection = RabbitMqUtil.getConnection();
        Channel channel = connection.createChannel();

        //声明队列
        channel.queueDeclare(RabbitConstant.QUEUE_BAIDU, true, false, false, null);
        //队列绑定交换机 参数1：队列名称  参数2：交换机名称  参数3：路由key(暂时用不到)
        channel.queueBind(RabbitConstant.QUEUE_BAIDU, RabbitConstant.EXCHANGE_WEATHER, "");
        channel.basicQos(1);
        //消费消息
        channel.basicConsume(RabbitConstant.QUEUE_BAIDU, false, new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, com.rabbitmq.client.Envelope envelope, com.rabbitmq.client.AMQP.BasicProperties properties, byte[] body) throws java.io.IOException {
                System.out.println("百度收到气象信息：" + new String(body));
                channel.basicAck(envelope.getDeliveryTag(), false);
            }
        });
    }
}
