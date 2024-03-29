package com.zhouyang.mq.constant;

/**
 * @author: ZY
 * @description
 * @date: 2023/10/17 15:49
 */
public class RabbitConstant {

    public static final String QUEUE_HELLO_WORLD = "hello_world";

    public static final String QUEUE_SMS = "sms";

    //声明交换机
    public static final String EXCHANGE_WEATHER = "exchange_weather";


    //声明交换机   routing Key
    public static final String EXCHANGE_WEATHER_ROUTING = "exchange_weather_routing";
    public static final String QUEUE_WANGYI = "queue_wangyi";
    public static final String QUEUE_XINLANG = "queue_xinlang";
    public static final String QUEUE_JD = "queue_jd";
    public static final String QUEUE_BAIDU = "queue_baidu";

    //声明交换机
    public static final String EXCHANGE_WEATHER_TOPIC = "exchange_weather_topic";

    //声明 routing_key  通配符
    public static final String TOPIC_ROUTING_KEY_BAIDU = "baidu.#";
    public static final String TOPIC_ROUTING_KEY_XINLANG = "*.hebei.#";
    public static final String TOPIC_ROUTING_KEY_JD = "jd.*";

}
