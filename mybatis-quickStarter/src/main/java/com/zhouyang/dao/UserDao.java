package com.zhouyang.dao;

import com.zhouyang.model.pojo.User;

import java.io.IOException;
import java.util.List;

/**
 * UserMapper:
 *
 * @author zhouYang
 * @date 2023/06/05
 */
public interface UserDao {

    List<User> findAll() throws IOException;
}
