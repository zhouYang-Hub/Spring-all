package com.zhouyang.model.pojo;

import lombok.Data;

import java.math.BigDecimal;

/**
 * Person:
 *
 * @author zhouYang
 * @date 2023/06/25
 */
@Data
public class Person {

    private Long id;

    private String name;

    private BigDecimal amount;

}
