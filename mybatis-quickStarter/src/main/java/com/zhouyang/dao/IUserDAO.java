package com.zhouyang.dao;

import com.zhouyang.model.pojo.User;

import java.util.List;

/**
 * IUserDAO:
 *
 * @author zhouYang
 * @date 2023/06/14
 */
public interface IUserDAO {

    List<User> userAll();

    /*多条件查询 ，使用<if> 标签*/
    List<User> findByCondition(User user);

    /*数组形式接收参数*/
    List<User> findByIds(int[] ids);
}
