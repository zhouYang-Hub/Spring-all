package com.zy.spring;

/**
 * @author: ZY
 * @description
 * @date: 2023/8/30 9:38
 */
public interface BeanPostProcessor {

    default Object postProcessBeforeInitialization(Object bean, String beanName) {
        return bean;
    }

    default Object postProcessAfterInitialization(Object bean, String beanName) {
        return bean;
    }
}
