package com.zhouyang;

import com.alibaba.fastjson.JSON;
import com.zhouyang.io.Resources;
import com.zhouyang.pojo.User;
import com.zhouyang.sqlsSssion.SqlSession;
import com.zhouyang.sqlsSssion.SqlSessionFactory;
import com.zhouyang.sqlsSssion.SqlSessionFactoryBuilder;

import java.io.InputStream;

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
        SqlSession sqlSession = sqlSessionFactory.openSqlSession();
        User user = new User();
        user.setId(1);
        user.setUsername("zhangsan");
        User users = sqlSession.selectOne("user.userOne", user);
        System.out.println("获取用户对象:{}" + JSON.toJSONString(users));
    }
}