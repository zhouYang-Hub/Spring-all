package com.zy.spring;

/**
 * @author: ZY
 * @description
 * @date: 2023/8/29 16:16
 */
public class BeanDefinition {

    private Class type;
    private String scope;
    private boolean lazy;

    public BeanDefinition(Class type, String scope, boolean lazy) {
        this.type = type;
        this.scope = scope;
        this.lazy = lazy;
    }

    public BeanDefinition() {
    }

    public Class getType() {
        return type;
    }

    public void setType(Class type) {
        this.type = type;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    public boolean isLazy() {
        return lazy;
    }

    public void setLazy(boolean lazy) {
        this.lazy = lazy;
    }
}
