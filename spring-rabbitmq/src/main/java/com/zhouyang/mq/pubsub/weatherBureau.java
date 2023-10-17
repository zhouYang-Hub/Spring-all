package com.zhouyang.mq.pubsub;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.zhouyang.mq.constant.RabbitConstant;
import com.zhouyang.mq.util.RabbitMqUtil;

import java.util.Scanner;

/**
 * @author: ZY
 * @description
 * @date: 2023/10/17 17:07
 */
public class weatherBureau {

    public static void main(String[] args) throws Exception {

        Connection connection = RabbitMqUtil.getConnection();
        String message = new Scanner(System.in).next();

        Channel channel = connection.createChannel();

        //RabbitConstant.EXCHANGE_WEATHER  声明交换机
        channel.basicPublish(RabbitConstant.EXCHANGE_WEATHER, "", null, message.getBytes());

        channel.close();
        connection.close();
    }
}
