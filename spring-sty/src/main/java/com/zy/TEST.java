package com.zy;

import java.time.LocalDate;

/**
 * @author: ZY
 * @description
 * @date: 2023/9/22 16:35
 */
public class TEST {
    public static void main(String[] args) {
        LocalDate end = LocalDate.now();
        LocalDate start = end.minusDays(2L);
        System.out.println(end);
        System.out.println(start);
    }
}
