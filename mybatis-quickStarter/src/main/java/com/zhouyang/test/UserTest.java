package com.zhouyang.test;

import com.alibaba.fastjson.JSON;
import com.zhouyang.dao.IUserDAO;
import com.zhouyang.dao.UserDao;
import com.zhouyang.dao.UserDaoImpl;
import com.zhouyang.model.pojo.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * UserTest:
 *
 * @author zhouYang
 * @date 2023/06/05
 */
public class UserTest {

    @Test
    public void findAllUserTest() throws IOException {
        InputStream resourceAsStream = Resources.getResourceAsStream("SqlMapConfig.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        List<User> users = sqlSession.selectList("user.findAll");
        users.forEach(user -> {
            System.out.println(user);
        });
        sqlSession.close();
    }

    @Test
    public void saveUserTest() throws IOException {
        InputStream resourceAsStream = Resources.getResourceAsStream("sqlMapConfig.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        User user = new User().setId(2).setUsername("tom").setPassword("111111");
        sqlSession.insert("user.saveUser", user);
        sqlSession.commit();
        sqlSession.close();
    }

    @Test
    public void updateUserTest() throws IOException {
        InputStream resourceAsStream = Resources.getResourceAsStream("sqlMapconfig.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        User user = new User().setId(2).setPassword("121212");
        sqlSession.update("user.updateUser", user);
        sqlSession.commit();
        sqlSession.close();
    }

    @Test
    public void deleteUserTest() throws IOException {
        InputStream resourceAsStream = Resources.getResourceAsStream("SqlMapConfig.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        User user = new User().setId(2);
        sqlSession.delete("user.deleteUser", user);
        sqlSession.commit();
        sqlSession.close();
    }

    //普通模式调用
    @Test
    public void findAllDAOTest() throws IOException {
        UserDao userDao = new UserDaoImpl();
        List<User> list = userDao.findAll();
        System.out.println(JSON.toJSONString(list));
    }

    //动态代理模式调用 jdk 动态代理模式
    @Test
    public void findByIdTest() throws IOException {
        InputStream resourceAsStream = Resources.getResourceAsStream("SqlMapConfig.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        IUserDAO userMappers = sqlSession.getMapper(IUserDAO.class);
        List<User> users = userMappers.userAll();
        System.out.println(JSON.toJSONString(users));
    }
}
