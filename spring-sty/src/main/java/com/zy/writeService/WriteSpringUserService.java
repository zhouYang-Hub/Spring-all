package com.zy.writeService;

import com.zy.spring.Autowired;
import com.zy.spring.InitializingBean;
import com.zy.spring.Scope;
import com.zy.spring.SpringComponent;

/**
 * @author: ZY
 * @description
 * @date: 2023/8/28 11:23
 */
@SpringComponent("writeSpringUserService")
//单例bean
//@Scope("singleton")
//多例bean
@Scope("prototype")
public class WriteSpringUserService implements InitializingBean {

    @Autowired
    private WriteSpringOrderService writeSpringOrderService;

    public void test() {
        System.out.println(writeSpringOrderService);
    }

    @Override
    public void afterPropertiesSet() {
        System.out.println("初始化bean");
    }
}
