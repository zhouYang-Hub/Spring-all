package com.zhouyang.model.pojo;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * Order:
 *
 * @author zhouYang
 * @date 2023/06/18
 */
@Data
@Accessors(chain = true)
public class Order {

    private Integer id;

    private String orderTime;

    private double total;

    /*表明该订单属于那个用户*/
    private User user;
}
