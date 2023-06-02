/*
 * Copyright 2020-2021, 木木996.
 * All Right Reserved.
 */

package com.mumu.java_base.func_interface;

/**
 * LoggerLambda
 * lambda表达式test
 * @author liuzhen
 * @version 1.0.0 2021/12/19 22:44
 */
public class LoggerLambda {

    /**
     * 未使用函数式编程
     * @param level
     * @param msg
     */
    public static void log1(int level, String msg) {
        if (level == 1) {
            System.out.println(msg);
        }
    }

    /**
     * 使用了函数式编程
     * 在不符合级别要求的情况下，Lambda将不会执行。从而达到节省性能的效果
     * @param level
     * @param myFunctionalInterface
     */
    public static void log2(int level, MyFunctionalInterface myFunctionalInterface) {
        if (level == 1) {
            System.out.println(myFunctionalInterface.buildMessage());
        }
    }

    public static void main(String[] args) {
        String msgA = "Hello";
        String msgB = "World";
        String msgC = "Java";

        /*
        * 存在问题：无论级别是否满足要求，作为 log 方法的第二个参数，三个字符串一定会首先被拼接并传入方 法内，然后才会进行级别判断。
        * 如果级别不符合要求，那么字符串的拼接操作就白做了，存在性能浪费。
        */
        log1(1, msgA + msgB + msgC);

        // 只有当级别满足要求的时候，才会进行三个字符串的拼接；否则三个字符串将不会进行拼接。
        log2(1, () -> {
            System.out.println("证明 lambda 表达式的延迟执行！（条件成立时，再进行字符串拼接。）");
            return msgA + msgB + msgC;
        });

    }

}
