package com.zhouyang.sqlsSssion;

import com.zhouyang.config.XMLConfigBuilder;
import com.zhouyang.pojo.Configuration;
import org.dom4j.DocumentException;

import java.beans.PropertyVetoException;
import java.io.IOException;
import java.io.InputStream;

/**
 * SqlSeeesionFactoryBuilder:
 *
 * @author zhouYang
 * @date 2023/05/22
 */
public class SqlSessionFactoryBuilder {

    public SqlSessionFactory builder(InputStream in) throws DocumentException, PropertyVetoException, IOException {
        //1:使用dom4J解析配置文件，将解析出来的文件解析到Configuration 中
        XMLConfigBuilder xmlConfigBuilder = new XMLConfigBuilder();
        Configuration configuration = xmlConfigBuilder.parseConfig(in);

        //2:创建sqlsesionFactory对象
        DefaultSqlSessionFactory defaultSqlSessionFactory = new DefaultSqlSessionFactory(configuration);
        return defaultSqlSessionFactory;
    }
}
