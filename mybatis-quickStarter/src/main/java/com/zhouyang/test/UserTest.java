package com.zhouyang.test;

import cn.hutool.bloomfilter.BitMapBloomFilter;
import cn.hutool.core.collection.CollStreamUtil;
import cn.hutool.core.lang.Dict;
import cn.hutool.core.lang.Snowflake;
import cn.hutool.core.util.IdUtil;
import com.alibaba.fastjson.JSON;
import com.zhouyang.dao.IUserDAO;
import com.zhouyang.dao.UserDao;
import com.zhouyang.dao.UserDaoImpl;
import com.zhouyang.model.pojo.Student;
import com.zhouyang.model.pojo.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
        users.forEach(System.out::println);
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
    public void findAllDapTest() throws IOException {
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
    public void findByConcatenation() throws IOException {
        InputStream resourceAsStream = Resources.getResourceAsStream("SqlMapConfig.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        IUserDAO userMappers = sqlSession.getMapper(IUserDAO.class);
        User user = new User();
        user.setUsername("hangman");
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

    /**
     * 测试集合查找
     */
    @Test
    public void testToMap() {
        List<Student> list = new ArrayList<>();
        list.add(new Student(1, 1, 1, "张三"));
        list.add(new Student(1, 1, 2, "李四"));
        list.add(new Student(1, 1, 3, "王五"));
        Map<Long, Student> studentMap = CollStreamUtil.toIdentityMap(list, Student::getStudentId);
        System.out.println("studentMap:{}" + JSON.toJSONString(studentMap));
        List<String> listName = CollStreamUtil.toList(list, Student::getName);
        System.out.println("listName：{}" + listName);


        //布隆过滤器  初始化
        BitMapBloomFilter filter = new BitMapBloomFilter(10);
        filter.add("123");
        filter.add("abc");
        filter.add("ddd");
        // 查找
        boolean abc = filter.contains("abc");
        System.out.println(abc); //ture 1

        Dict dict = Dict.create().set("abc", "abc").set("ddd", "ddd");
        Object abc1 = dict.get("abc");
        System.out.println(abc1);
    }

    /**
     * @param
     * @date
     */
    @Test
    public void testFilter() {
        Snowflake snowflake = IdUtil.getSnowflake(1, 1);
        long centerId = snowflake.nextId();
        System.out.println(centerId);
    }

    @Test
    public void test() {

    }

}
