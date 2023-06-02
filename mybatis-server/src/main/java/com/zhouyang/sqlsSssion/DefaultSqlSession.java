package com.zhouyang.sqlsSssion;

import com.zhouyang.pojo.Configuration;
import com.zhouyang.pojo.MappedStatement;

import java.beans.IntrospectionException;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Proxy;
import java.lang.reflect.Type;
import java.sql.SQLException;
import java.util.List;

/**
 * DefaultSqlSession:
 *
 * @author zhouYang
 * @date 2023/05/22
 */
public class DefaultSqlSession implements SqlSession {

    private Configuration configuration;

    public DefaultSqlSession(Configuration configuration) {
        this.configuration = configuration;
    }

    @Override
    public <E> List<E> selectList(String statementId, Object... params) throws SQLException, IntrospectionException, NoSuchFieldException, ClassNotFoundException, InvocationTargetException, IllegalAccessException, InstantiationException {
        //完成simpleExecutor 的query（）
        SimpleExecutor simpleExecutor = new SimpleExecutor();
        MappedStatement mappedStatement = configuration.getMap().get(statementId);
        List<Object> list = simpleExecutor.query(configuration, mappedStatement, params);
        return (List<E>) list;
    }


    @Override
    public <T> T selectOne(String statementId, Object... params) throws SQLException, IntrospectionException, NoSuchFieldException, ClassNotFoundException, InvocationTargetException, IllegalAccessException, InstantiationException {
        List<Object> objects = selectList(statementId, params);
        if (null != objects && 1 == objects.size()) {
            return (T) objects.get(0);
        } else {
            throw new RuntimeException("查询结果异常");
        }
    }

    @Override
    public <T> T getMapper(Class<?> mapperClass) {
        //使用jdk的动态代理为dao接口生成代理对象，并返回
        Object proxyInstance = Proxy.newProxyInstance(DefaultSqlSession.class.getClassLoader(), new Class[]{mapperClass}, new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                //底层都是执行jdbc 代码
                //1：准备参数； 一、 statementId ；sql 语句唯一标识，namespace.id =  接口全限定名和方法名称
                //获取方法名称
                String methodName = method.getName();
                //获取接口全限定名
                Class<?> className = method.getDeclaringClass();
                //statementId
                String statementId = className + "." + methodName;
                //准备参数二 params = args
                //获取被调用方法的返回值类型
                Type genericReturnType = method.getGenericReturnType();
                //判断是否进行了泛型类型参数化
                if (genericReturnType instanceof ParameterizedType) {
                    List<Object> selectList = selectList(statementId, args);
                    return selectList;
                }
                return selectOne(statementId, args);
            }
        });
        return (T) proxyInstance;
    }
}
