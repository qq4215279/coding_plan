/*
 * Copyright 2020-2021, 木木996.
 * All Right Reserved.
 */

package com.gobestsoft.java_base.throwable;

/**
 * Finally_Return_Test
 *
 * @author liuzhen
 * @version 1.0.0 2022/1/9 17:23
 */
public class Finally_Return_Test {

    public static void main(String[] args) {
        int a = getA();
        System.out.println(a);
    }

    // 定义一个方法,返回变量a的值
    public static int getA() {
        int a = 10;
        try {
            return a;
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            // 一定会执行的代码
            a = 100;
            return a;
        }

    }

}
