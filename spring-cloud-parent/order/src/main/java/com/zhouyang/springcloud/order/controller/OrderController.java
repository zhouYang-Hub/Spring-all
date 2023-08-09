package com.zhouyang.springcloud.order.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * OrderController:
 *
 * @author zhouYang
 * @date 2023/08/09
 */
@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/getOrder")
    public String getOrder() {
        String result = restTemplate.getForObject("http://localhost:8011/stock/getStock", String.class);
        return "get order ...>" + result;
    }
}
