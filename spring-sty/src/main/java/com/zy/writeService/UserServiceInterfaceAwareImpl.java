package com.zy.writeService;

import com.zy.spring.BeanNameAware;
import com.zy.spring.Scope;
import com.zy.spring.SpringComponent;

/**
 * @author: ZY
 * @description
 * @date: 2023/8/30 11:40
 */
@SpringComponent("userServiceInterfaceAwareImpl")
@Scope("prototype")
public class UserServiceInterfaceAwareImpl implements BeanNameAware {

    private String beanName;

    @Override
    public void setBeanName(String name) {
        this.beanName = name;
    }

    public void test() {
        System.out.println(beanName);
    }
}
