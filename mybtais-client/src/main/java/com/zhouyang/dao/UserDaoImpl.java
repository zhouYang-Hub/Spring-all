package com.zhouyang.dao;

import com.alibaba.fastjson.JSON;
import com.zhouyang.io.Resources;
import com.zhouyang.pojo.User;
import com.zhouyang.sqlsSssion.SqlSession;
import com.zhouyang.sqlsSssion.SqlSessionFactory;
import com.zhouyang.sqlsSssion.SqlSessionFactoryBuilder;
import org.dom4j.DocumentException;

import java.beans.IntrospectionException;
import java.beans.PropertyVetoException;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.List;

/**
 * UserDaoImpl:
 *
 * @author zhouYang
 * @date 2023/05/30
 */
public class UserDaoImpl implements IuserDao {

    @Override
    public List<User> findAll() throws IOException, PropertyVetoException, DocumentException, SQLException, IntrospectionException, NoSuchFieldException, ClassNotFoundException, InvocationTargetException, IllegalAccessException, InstantiationException {
        InputStream resourceAsSteam = Resources.getResourceAsStream("SqlMapConfig.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().builder(resourceAsSteam);
        SqlSession sqlSession = sqlSessionFactory.openSqlSession();

        //selectList
        List<User> usersRest = sqlSession.selectList("user.userList");
        System.out.println("获取用户list集合信息:{}" + JSON.toJSONString(usersRest));

        return usersRest;
    }


    @Override
    public User findByCondition(User user) throws IOException, PropertyVetoException, DocumentException, SQLException, IntrospectionException, NoSuchFieldException, ClassNotFoundException, InvocationTargetException, IllegalAccessException, InstantiationException {
        InputStream resourceAsSteam = Resources.getResourceAsStream("SqlMapConfig.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().builder(resourceAsSteam);
        //selectOne
        SqlSession sqlSession = sqlSessionFactory.openSqlSession();
        User userRest = sqlSession.selectOne("user.userOne", user);
        System.out.println("获取用户对象:{}" + JSON.toJSONString(userRest));
        return userRest;
    }
}
