package com.zhouyang.dao;

import com.zhouyang.model.pojo.Order;

import java.util.List;

/**
 * IOrderDAO:
 *
 * @author zhouYang
 * @date 2023/06/18
 */
public interface IOrderDAO {

    List<Order> userByOrder();
}
