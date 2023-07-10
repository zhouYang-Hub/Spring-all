package com.zhouyang.test;

import com.alibaba.fastjson.JSON;
import com.zhouyang.dao.IOrderDAO;
import com.zhouyang.dao.IUserDAO;
import com.zhouyang.dao.UserDao;
import com.zhouyang.dao.UserDaoImpl;
import com.zhouyang.model.pojo.Order;
import com.zhouyang.model.pojo.Person;
import com.zhouyang.model.pojo.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.LinkedList;
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

    //使用if标签动态sql 查询
    @Test
    public void findByConcation() throws IOException {
        InputStream resourceAsStream = Resources.getResourceAsStream("SqlMapConfig.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        IUserDAO userMappers = sqlSession.getMapper(IUserDAO.class);
        User user = new User();
        user.setUsername("zhangsan");
        List<User> users = userMappers.findByCondition(user);
        System.out.println(JSON.toJSONString(users));

    }

    //foreach 标签动态sql 查询
    @Test
    public void findByIds() throws IOException {
        InputStream resourceAsStream = Resources.getResourceAsStream("SqlMapConfig.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        IUserDAO userMappers = sqlSession.getMapper(IUserDAO.class);
        int[] ids = new int[]{1};
        List<User> users = userMappers.findByIds(ids);
        System.out.println(JSON.toJSONString(users));

    }

    // 1对1 mapper sql 查询
    @Test
    public void userAndOrder() throws IOException {
        InputStream resourceAsStream = Resources.getResourceAsStream("SqlMapConfig.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        IOrderDAO orderDAO = sqlSession.getMapper(IOrderDAO.class);
        List<Order> orders = orderDAO.userByOrder();
        System.out.println(JSON.toJSONString(orders));

    }

    @Test
    public void test01() throws IOException {
        List<Person> persons = new ArrayList<Person>();
        Person person = new Person();
        person.setId(111L);
        person.setName("zhangsan");
        person.setAmount(new BigDecimal("11"));

        Person person1 = new Person();
        person1.setId(222L);
        person1.setName("lisi");
        person1.setAmount(new BigDecimal("22"));

        Person person2 = new Person();
        person2.setId(333L);
        person2.setName("wangwu");
        person2.setAmount(new BigDecimal("33"));

        persons.add(person);
        persons.add(person1);
        persons.add(person2);

        BigDecimal bigDecimal = persons.stream().map(Person::getAmount).reduce(BigDecimal.ZERO, BigDecimal::add);
        System.out.println(bigDecimal);

        ArrayList<Object> arrayList = new ArrayList<>(1);
        LinkedList<Object> linkedList = new LinkedList<>();

    }
}
