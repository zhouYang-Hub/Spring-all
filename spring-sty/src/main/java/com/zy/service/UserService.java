//package com.zy.service;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//
///**
// * @author: ZY
// * @description
// * @date: 2023/8/24 16:58
// */
//@Component
//public class UserService {
//
//    private OrderService orderService;
//
//    /**
//     * 对于一个类来说，默认的是在初始化的时候找的是无参的构造方法；
//     * 如果存在一个无参的构造方法和多个有参的构造方法。他默认处理的是无参的构造方法
//     * 如果没有无参的构造方法，但是存在多个有参的构造方法，这种的情况就会报错，spring 是实例化类(UserService)的时候
//     * 就不知道该去初始化那个了。
//     * 如果只存在一个有参的构造方法，这种情况下就直接使用这个构造方法初始化。
//     * <p>
//     * spring 怎么是去容器中找 OrderService bean 对象  spring容器就是一个Map集合
//     * 根据类型去spring容器中找，可能会找到那个 bean名字，所以应该先通过类型去找到所有的 bean ，然后在通过bean  name 匹配一个beanName
//     */
//    /*
//        public UserService() {
//            System.out.println("userService");
//        }
//    */
//
//    /**
//     * 我现在生成了 两个 OrderService 的bean ， 一个是orderService 、  orderService1
//     * 但是我在构造方法中的beanName 是 orderService11 ，导致我在找beanName的时候匹配不到 OrderService 对应的beanName ，所以报错了，
//     * 如果在 OrderService 通过类型找到的beanName是有一个的情况下，就直接使用，不在报错，
//     * <p>
//     * spring 找bean的过程
//     *
//     * @param orderService11
//     */
//    public UserService(OrderService orderService11) {
//        this.orderService = orderService11;
//        System.out.println("orderService");
//    }
//
//    @Autowired
//    public UserService(OrderService orderService, OrderService orderService1) {
//        System.out.println("catService");
//    }
//
//    public void test() {
//        System.out.println("11");
//    }
//
//}
