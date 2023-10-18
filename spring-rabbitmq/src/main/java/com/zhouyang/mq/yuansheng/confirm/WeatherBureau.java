package com.zhouyang.mq.yuansheng.confirm;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.ConfirmListener;
import com.rabbitmq.client.Connection;
import com.zhouyang.mq.constant.RabbitConstant;
import com.zhouyang.mq.util.RabbitMqUtil;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * @author: ZY
 * @description
 * @date: 2023/10/17 17:07
 */
public class WeatherBureau {

    public static void main(String[] args) throws Exception {
        HashMap<String, String> map = new HashMap<>();
        map.put("china.hebei.shijiazhuang.20991011", "中国河北石家庄20991011天气数据");
        map.put("china.hebei.handan.20991012", "中国河北邯郸20991012天气数据");
        map.put("china.hebei.chengde.20991013", "中国河北承德20991013天气数据");
        Connection connection = RabbitMqUtil.getConnection();
        Channel channel = connection.createChannel();
        //开启confirm 监听模式
        channel.confirmSelect();
        /**
         *
         *ack
         */
        channel.addConfirmListener(new ConfirmListener() {
            @Override
            public void handleAck(long deliveryTag, boolean multiple) {
                System.out.println("---------ack-消息已被接收!tag:" + deliveryTag);
            }

            @Override
            public void handleNack(long deliveryTag, boolean multiple) {
                System.out.println("---------noack - 消息被拒绝!tag:" + deliveryTag);
            }
        });

        Iterator<Map.Entry<String, String>> iterator = map.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<String, String> content = iterator.next();
            //RabbitConstant.EXCHANGE_WEATHER  声明交换机
            channel.basicPublish(RabbitConstant.EXCHANGE_WEATHER_ROUTING, content.getKey(), null, content.getValue().getBytes());
        }
    }
}
