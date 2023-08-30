package com.zy.test;

import com.zy.WriteSpringAppConfig;
import com.zy.spring.ZhouYangClassPathXmlApplicationContext;
import com.zy.writeService.UserServiceInterface;

/**
 * @author: ZY
 * @description
 * @date: 2023/8/24 16:58
 */
public class SpringTest {
    public static void main(String[] args) {

        //扫描路径
        ZhouYangClassPathXmlApplicationContext context = new ZhouYangClassPathXmlApplicationContext(WriteSpringAppConfig.class);

        /*  测试创建 单例bean 和多例bean的过程和结果
            System.out.println(context.getBean("writeSpringUserService"));
            System.out.println(context.getBean("writeSpringUserService"));
            System.out.println(context.getBean("writeSpringUserService"));
        */
        /**
         * 测试依赖注入
         */
        UserServiceInterface writeSpringUserService = (UserServiceInterface) context.getBean("writeSpringUserService");
        writeSpringUserService.test();
    }
}
