package com.zhouyang.model.pojo;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * User: mybatis 入门 配置
 *
 * @author zhouYang
 * @date 2023/06/05
 */
@Accessors(chain = true)
@Data
public class User {

    private Integer id;

    private String username;

    private String password;

    private String deleted;

    private String createTime;

    private String updateTime;

}
