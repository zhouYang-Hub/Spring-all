package com.zhouyang.springcloud.order.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
@RequestMapping(value = "/user")
public class UserController {

    private final static Logger logger = LoggerFactory.getLogger(UserController.class);

    @GetMapping("/getUser")
    public String getUser() {
        logger.info("我是user-1服务的getUser方法");
        return "get user ...>" + "获取用户信息成功";
    }
}
