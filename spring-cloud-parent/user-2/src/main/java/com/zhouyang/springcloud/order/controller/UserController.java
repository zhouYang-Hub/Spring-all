package com.zhouyang.springcloud.order.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * OrderController:
 *
 * @author zhouYang
 * @date 2023/08/09
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @GetMapping("/getUser")
    public String getUser() {
        return "get user ...>" + "获取用户信息成功";
    }
}
