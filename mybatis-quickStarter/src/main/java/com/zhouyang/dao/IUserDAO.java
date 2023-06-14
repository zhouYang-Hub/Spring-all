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
}
