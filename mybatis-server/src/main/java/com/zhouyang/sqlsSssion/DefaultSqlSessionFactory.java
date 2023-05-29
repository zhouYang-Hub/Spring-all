package com.zhouyang.sqlsSssion;

import com.zhouyang.pojo.Configuration;

/**
 * DefaultSqlSessionFactory:
 *
 * @author zhouYang
 * @date 2023/05/22
 */
public class DefaultSqlSessionFactory implements SqlSessionFactory {
    private Configuration configuration;

    public DefaultSqlSessionFactory(Configuration configuration) {
        this.configuration = configuration;
    }

    @Override
    public SqlSession openSqlSession() {
        return new DefaultSqlSession(configuration);
    }
}
