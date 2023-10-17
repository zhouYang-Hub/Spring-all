package com.zhouyang.mq.work;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.DefaultConsumer;
import com.zhouyang.mq.constant.RabbitConstant;
import com.zhouyang.mq.util.RabbitMqUtil;

/**
 * @author: ZY
 * @description 消费消息
 * @date: 2023/10/17 15:50
 */
public class SmsSender3 {
    public static void main(String[] args) throws Exception {
        System.out.println("接受消息开始");

        Connection connection = RabbitMqUtil.getConnection();
        Channel channel = connection.createChannel();

        /**
         *绑定队列
         * 第一个参数：队列名称ID
         * 第二个参数：队列是否自动确认，设置为true表示消息接收到自动向mq回复接收到了，mq接收到回复会删除消息，设置为false则需要手动确认
         *  第三个参数：消费者的回调接口
         *  第四个参数：消费者取消消费的回调接口，比如队列删除了，就会触发这个回调
         *  可以使用channel.basicQos(1);来设置每次消费一个消息
         *  注意：如果要实现消息的自动确认，必须要关闭自动确认，即第二个参数设置为false，否则无效
         */
        channel.queueDeclare(RabbitConstant.QUEUE_SMS, true, false, false, null);

        //每次消费一条在取下一条
        channel.basicQos(1);

        /**
         * 消费消息
         */
        channel.basicConsume(RabbitConstant.QUEUE_SMS, false, new Reciver(channel));
    }

    static class Reciver extends DefaultConsumer {

        private Channel channel;

        public Reciver(Channel channel) {
            super(channel);
            this.channel = channel;
        }

        @Override
        public void handleDelivery(String consumerTag, com.rabbitmq.client.Envelope envelope, com.rabbitmq.client.AMQP.BasicProperties properties, byte[] body) throws java.io.IOException {
            System.out.println("接收到消息：" + new String(body));
            //false只确认签收当前的消息，设置为true的时候则代表签收该消费者所有未签收的消息
            channel.basicAck(envelope.getDeliveryTag(), false);
        }
    }
}
