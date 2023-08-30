package com.zy.writeService;

import com.zy.spring.BeanPostProcessor;
import com.zy.spring.SpringComponent;

/**
 * @author: ZY
 * @description
 * @date: 2023/8/30 9:34
 */
@SpringComponent
public class ZhouYangValueBeanPostProcessor implements BeanPostProcessor {

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) {

        //初始化之前
        

        return bean;
    }
}

