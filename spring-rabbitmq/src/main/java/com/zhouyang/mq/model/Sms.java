package com.zhouyang.mq.model;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author: ZY
 * @description
 * @date: 2023/10/17 16:22
 */
@Accessors(chain = true)
@Data
public class Sms {

    private String name;

    private String mobile;

    private String content;
}
