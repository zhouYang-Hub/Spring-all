package com.zhouyang.dao;

import com.zhouyang.model.pojo.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * UserDaoImpl:
 *
 * @author zhouYang
 * @date 2023/06/14
 */
public class UserDaoImpl implements UserDao {

    @Override
    public List<User> findAll() throws IOException {
        //加载配置文件
        InputStream resourceAsStream = Resources.getResourceAsStream("sqlMapConfig.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
        SqlSession sqlSession = sqlSessionFactory.openSession(true);
        List<User> users = sqlSession.selectList("user.findAll");
        sqlSession.close();
        return users;
    }
}
