package com.zhouyang.mq.util;

import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

/**
 * @author: ZY
 * @description 创建mq链接信息
 * @date: 2023/10/11 9:42
 */
public class RabbitMqUtil {

    private static Connection connection;

    private static final String HOST_NAME = "39.104.94.151";

    private static final int HOST_PORT = 5675;

    public RabbitMqUtil() {
    }

    public static Connection getConnection() throws Exception {
        if (null == connection) {
            new ConnectionFactory();
            ConnectionFactory factory = new ConnectionFactory();
            factory.setHost(HOST_NAME);
            factory.setPort(HOST_PORT);
            factory.setUsername("zhouyang");
            factory.setPassword("zhouyang");
            factory.setVirtualHost("/zhouyang");
            connection = factory.newConnection();
        }
        return connection;
    }
}
