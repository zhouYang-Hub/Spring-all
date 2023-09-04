package com.zhouyang.springcloud.order.controller;

import com.zhouyang.springcloud.order.feign.StockService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

/**
 * OrderController:
 *
 * @author zhouYang
 * @date 2023/08/09
 */
@Slf4j
@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private StockService stockService;

    @GetMapping("/getOrder")
    public String getOrder() {
        log.info("下单成功-----------");
        String result = stockService.getStock();
        return "用户在" + LocalDateTime.now() + "下单完成；获取订单" + "get order ...>" + result;
    }
}
