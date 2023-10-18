package com.zhouyang.mq.yuansheng.topic;

import com.rabbitmq.client.Channel;
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
        map.put("baidu.hebei.shijiazhuang.20991011", "baidu-中国河北石家庄20991011天气数据");
        map.put("baidu.hebei.handan.20991012", "baidu-中国河北邯郸20991012天气数据");
        map.put("jd.hebei.chengde.20991013", "jd-中国河北承德20991013天气数据");
        map.put("jd.hebei.baoding.20991013", "jd-中国河北保定20991013天气数据");
        map.put("xinlang.hebei.chengde.20991013", "xinlang-中国陕西西安20991013天气数据");
        map.put("xinlang.hebei.baoding.20991013", "xinlang-中国陕西汉中20991013天气数据");
        Connection connection = RabbitMqUtil.getConnection();
        Channel channel = connection.createChannel();
        Iterator<Map.Entry<String, String>> iterator = map.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<String, String> content = iterator.next();
            //RabbitConstant.EXCHANGE_WEATHER  声明交换机
            channel.basicPublish(RabbitConstant.EXCHANGE_WEATHER_TOPIC, content.getKey(), null, content.getValue().getBytes());
        }
        channel.close();
        connection.close();
    }
}