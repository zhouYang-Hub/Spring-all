package com.zhouyang.springcloud.order.interceptor.feign;

import feign.RequestInterceptor;
import feign.RequestTemplate;

/**
 * AuthFeignIntrceptor:
 *
 * @author zhouYang
 * @date 2023/09/05
 */
public class AuthFeignInterceptor implements RequestInterceptor {
    @Override
    public void apply(RequestTemplate requestTemplate) {
        //todo 处理自己业务逻辑
    }
}
