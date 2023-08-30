package com.zy.writeService;

import com.zy.spring.Autowired;
import com.zy.spring.Scope;
import com.zy.spring.SpringComponent;

/**
 * @author: ZY
 * @description
 * @date: 2023/8/30 11:40
 */
@SpringComponent("userServiceInterfaceImpl")
@Scope("prototype")
public class UserServiceInterfaceImpl implements UserServiceInterface {

    @Autowired
    private WriteSpringOrderService writeSpringOrderService;

    @Override
    public void test() {
        System.out.println(writeSpringOrderService);
    }
}
