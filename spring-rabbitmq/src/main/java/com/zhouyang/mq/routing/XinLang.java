package com.zhouyang.mq.routing;

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
public class XinLang {
    public static void main(String[] args) throws Exception {

        Connection connection = RabbitMqUtil.getConnection();
        Channel channel = connection.createChannel();

        //声明队列
        channel.queueDeclare(RabbitConstant.QUEUE_XINLANG, true, false, false, null);
        //队列绑定交换机 参数1：队列名称  参数2：交换机名称  参数3：路由key
        channel.queueBind(RabbitConstant.QUEUE_XINLANG, RabbitConstant.EXCHANGE_WEATHER_ROUTING, "china.hebei.shijiazhuang.20991011");
        channel.queueBind(RabbitConstant.QUEUE_XINLANG, RabbitConstant.EXCHANGE_WEATHER_ROUTING, "china.hebei.handan.20991012");
        channel.queueBind(RabbitConstant.QUEUE_XINLANG, RabbitConstant.EXCHANGE_WEATHER_ROUTING, "china.hebei.chengde.20991013");

        channel.basicQos(1);
        //消费消息
        channel.basicConsume(RabbitConstant.QUEUE_XINLANG, false, new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, com.rabbitmq.client.Envelope envelope, com.rabbitmq.client.AMQP.BasicProperties properties, byte[] body) throws java.io.IOException {
                System.out.println("新浪收到handan气象信息：" + new String(body));
                channel.basicAck(envelope.getDeliveryTag(), false);
            }
        });
    }
}
