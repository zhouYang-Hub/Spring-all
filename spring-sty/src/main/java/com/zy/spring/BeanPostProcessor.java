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

    /**
     * 自定义方法
     *
     * @param bean
     * @param beanName
     * @return
     */
    default Object postProcessBeforeInstantiationOn(Object bean, String beanName) {
        return bean;
    }

}
