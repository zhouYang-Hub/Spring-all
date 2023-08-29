package com.zy.test;

import com.zy.WriteSpringAppConfig;
import com.zy.spring.ZhouYangClassPathXmlApplicationContext;

/**
 * @author: ZY
 * @description
 * @date: 2023/8/24 16:58
 */
public class SpringTest {
    public static void main(String[] args) {

        //扫描路径
        ZhouYangClassPathXmlApplicationContext context = new ZhouYangClassPathXmlApplicationContext(WriteSpringAppConfig.class);
        System.out.println(context.getBean("writeSpringUserService"));
        System.out.println(context.getBean("writeSpringUserService"));
        System.out.println(context.getBean("writeSpringUserService"));
    }
}
