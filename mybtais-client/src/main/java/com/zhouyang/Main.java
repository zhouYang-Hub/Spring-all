package com.zhouyang;

import com.alibaba.fastjson.JSON;
import com.zhouyang.dao.IuserDao;
import com.zhouyang.io.Resources;
import com.zhouyang.pojo.User;
import com.zhouyang.sqlsSssion.SqlSession;
import com.zhouyang.sqlsSssion.SqlSessionFactory;
import com.zhouyang.sqlsSssion.SqlSessionFactoryBuilder;

import java.io.InputStream;
import java.util.List;

/**
 * ${NAME}:
 *
 * @author zhouYang
 * @date ${YEAR}/${MONTH}/${DAY}
 */
public class Main {
    public static void main(String[] args) throws Exception {
        System.out.println("Hello world!");
        InputStream resourceAsSteam = Resources.getResourceAsStream("SqlMapConfig.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().builder(resourceAsSteam);
        //selectOne
        SqlSession sqlSession = sqlSessionFactory.openSqlSession();
        User user = new User();
        user.setId(1);
        user.setUsername("zhangsan");
        User userRest = sqlSession.selectOne("user.userOne", user);
        System.out.println("获取用户对象:{}" + JSON.toJSONString(userRest));

        //selectList
        List<User> usersRest = sqlSession.selectList("user.userList");
        System.out.println("获取用户list集合信息:{}" + JSON.toJSONString(usersRest));

        //优化后的调用
        IuserDao userDao = sqlSession.getMapper(IuserDao.class);
        User byCondition = userDao.findByCondition(user);
        System.out.println("优化后的调用 =====>获取用户对象:{}" + JSON.toJSONString(byCondition));

    }
}