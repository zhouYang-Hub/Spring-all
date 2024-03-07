package com.spring.zhouyang.xiancheng.example1;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static java.lang.System.out;

/**
 * Example1:
 *
 * @author zhouYang
 * @ date 2024/01/02
 */
public class ExampleTest {

    private static final Logger log = LoggerFactory.getLogger(ExampleTest.class);

    private volatile boolean flag = true;

    public void refresh() {
        this.flag = false;
        log.debug("refresh flag :{};线程名称:{};线程ID:{} ", flag, Thread.currentThread().getName(), Thread.currentThread().getId());
    }

    public void load() {
        log.debug("线程名称:{};线程ID:{} ", Thread.currentThread().getName(), Thread.currentThread().getId());

        int i = 0;
        while (flag) {
            i++;
            out.println("i :{}" + i);
        }
        log.debug("线程名称:{};线程ID:{};i:{} ", Thread.currentThread().getName(), Thread.currentThread().getId(), i);
    }

    public static void main(String[] args) {
        ExampleTest exampleTest = new ExampleTest();
        new Thread(exampleTest::load, "t1").start();
//        try {
//            Thread.sleep(1);
//        } catch (InterruptedException e) {
//            log.error("线程异常:{}", e.getMessage(), e);
//        }
        new Thread(exampleTest::refresh, "t2").start();
    }
}
