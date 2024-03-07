package com.zy.test;

import cn.hutool.http.HttpUtil;

import java.util.ArrayList;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.function.BiFunction;

/**
 * @author: ZY
 * @description
 * @date: 2023/10/24 15:13
 */
public class Test {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        long startTime = System.currentTimeMillis();
        ArrayList<Object> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            HttpUtil.get("https://www.baidu.com");
        }
        CompletableFuture<String> job1 = CompletableFuture.supplyAsync(() -> {
            for (int i = 0; i < 10; i++) {
                HttpUtil.get("https://www.baidu.com");
            }
            return "job1";
        });

        CompletableFuture<String> job2 = CompletableFuture.supplyAsync(() -> {
            for (int i = 0; i < 10; i++) {
                HttpUtil.get("https://www.baidu.com");
            }
            return "job2";
        });
        
        CompletableFuture<Object> future = job1.thenCombine(job2, new BiFunction<String, String, Object>() {
            @Override
            public Object apply(String s, String s2) {
                list.add(s);
                list.add(s2);
                return list;
            }
        });
        long endTime = System.currentTimeMillis();
        System.out.printf("耗时：%d ms", endTime - startTime);
        System.out.println(future.get());
    }
}
