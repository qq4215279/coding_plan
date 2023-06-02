/*
 * Copyright 2020-2021, 木木996.
 * All Right Reserved.
 */

package com.mumu.user;

/**
 * StringSizeDemo
 *
 * @author liuzhen
 * @version 1.0.0 2022/7/23 10:25
 */
public class StringSizeDemo {

    public static int stringSize(int x) {
        int d = 1;
        if (x >= 0) {
            d = 0;
            x = -x;
        }
        int p = -10;
        for (int i = 1; i < 10; i++) {
            if (x > p)
                return i + d;
            p = 10 * p;
        }
        return 10 + d;
    }

    public static void main(String[] args) {
        int x = -10;
        System.out.println(stringSize(x));
    }

}
