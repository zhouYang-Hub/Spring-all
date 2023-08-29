package com.zhouyang.springcloud.stock.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * StockController:
 *
 * @author zhouYang
 * @date 2023/08/09
 */
@RestController
@RequestMapping("/stock")
public class StockController {

    @GetMapping("/getStock")
    public String getStock() {
        return "扣减库存成功！" + "port: 8012";
    }
}
