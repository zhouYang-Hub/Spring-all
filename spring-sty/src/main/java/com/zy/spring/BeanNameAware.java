package com.zy.spring;

/**
 * @author: ZY
 * @description Aware 回调
 * @date: 2023/8/30 16:48
 */
public interface BeanNameAware {

    void setBeanName(String name);
}
