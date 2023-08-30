package com.zy.writeService;

import com.zy.spring.BeanPostProcessor;
import com.zy.spring.SpringComponent;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author: ZY
 * @description
 * @date: 2023/8/30 9:34
 */
@SpringComponent
public class ZhouYangBeanPostProcessor implements BeanPostProcessor {

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) {

        //初始化之后
        System.out.println("初始化后 beanName:" + beanName);
        /**
         * 控制指定beanName走这个初始化后的逻辑
         */
        if (beanName.equals("userServiceInterfaceImpl")) {

            //invoke
            // 生成jdk动态代理 接口代理类
            Object proxyInstance = Proxy.newProxyInstance(ZhouYangBeanPostProcessor.class.getClassLoader(), bean.getClass().getInterfaces(), new InvocationHandler() {
                @Override
                public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                    //执行自定义的切面逻辑
                    System.out.println("执行自定义的切面逻辑");
                    //这个 bean 是普通对象
                    return method.invoke(bean, args);
                }
            });
            return proxyInstance;
        }
        return bean;
    }
}

