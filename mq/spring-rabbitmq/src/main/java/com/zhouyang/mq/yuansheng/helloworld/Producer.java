package com.zhouyang.mq.yuansheng.helloworld;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.zhouyang.mq.constant.RabbitConstant;
import com.zhouyang.mq.util.RabbitMqUtil;

/**
 * @author: ZY
 * @description 发送消息
 * @date: 2023/10/17 15:50
 */
public class Producer {

    public static void main(String[] args) throws Exception {

        //创建mq链接信息
        Connection connection = RabbitMqUtil.getConnection();
        Channel channel = connection.createChannel();
        /**
         *  创建队列, 声明并创建一个队列，如果队列已存在，则使用这个队列
         * 第一个参数：队列名称ID
         * 第二个参数：是否持久化，false对应不持久化数据，MQ停掉数据就会丢失
         * 第三个参数：是否队列私有化，false则代表所有消费者都可以访问，true代表只有第一次拥有它的消费者才能一直使用，其他消费者不让访问
         * 第四个：是否自动删除,false代表连接停掉后不自动删除掉这个队列
         * 其他额外的参数, null
         */
        channel.queueDeclare(RabbitConstant.QUEUE_HELLO_WORLD, true, false, false, null);
        String message = "Hello World! 你好，世界！";

        /**
         * 四个参数
         * exchange 交换机，暂时用不到，在后面进行发布订阅时才会用到
         * 队列名称
         * 额外的设置属性
         * 最后一个参数是要传递的消息字节数组
         */
        channel.basicPublish("", RabbitConstant.QUEUE_HELLO_WORLD, null, message.getBytes());
        channel.close();
        connection.close();
        System.out.println("消息发送完毕-success");
    }
}
