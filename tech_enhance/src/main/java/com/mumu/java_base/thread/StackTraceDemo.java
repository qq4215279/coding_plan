/*
 * Copyright 2020-2023, 木木996.
 * All Right Reserved.
 */

package com.mumu.java_base.thread;

/**
 * StackTraceDemo
 * 获取当前被调用函数的函数路径(stack trace)
 * 可以利用这个功能生成整个程序的调用树，方便分析程序结构。
 * @author liuzhen
 * @version 1.0.0 2024/1/10 10:42
 */
public class StackTraceDemo {

    public static void main(String[] args) {
        parent();
    }

    private static void parent() {
        sub();
    }

    private static void sub() {
        for (StackTraceElement i : Thread.currentThread().getStackTrace()) {
            System.out.println(i);
        }
    }
}
