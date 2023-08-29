package com.zy.writeService;

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
public class WriteSpringUserService {
    public void test() {
        System.out.println("writeSpringUserService");
    }
}
