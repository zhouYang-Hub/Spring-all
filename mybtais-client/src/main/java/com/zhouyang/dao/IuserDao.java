package com.zhouyang.dao;

import com.zhouyang.pojo.User;
import org.dom4j.DocumentException;

import java.beans.IntrospectionException;
import java.beans.PropertyVetoException;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.List;

/**
 * IuserDao:
 *
 * @author zhouYang
 * @date 2023/05/30
 */
public interface IuserDao {

    //查询所有用户信息
    public List<User> findAll() throws IOException, PropertyVetoException, DocumentException, SQLException, IntrospectionException, NoSuchFieldException, ClassNotFoundException, InvocationTargetException, IllegalAccessException, InstantiationException;

    //根据条件查询用户信息
    public User findByCondition(User user) throws IOException, PropertyVetoException, DocumentException, SQLException, IntrospectionException, NoSuchFieldException, ClassNotFoundException, InvocationTargetException, IllegalAccessException, InstantiationException;

}
