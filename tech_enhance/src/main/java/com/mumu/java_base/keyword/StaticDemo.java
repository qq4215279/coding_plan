/*
 * Copyright 2020-2024, 木木996.
 * All Right Reserved.
 */

package com.mumu.java_base.keyword;

/**
 * StaticDemo
 * 静态变量引用赋值测试
 * 结论：没有区别！
 * @author liuzhen
 * @version 1.0.0 2024/6/18 16:25
 */
public class StaticDemo {

    public static void main(String[] args) {
        System.out.println("ClassA.a = " + ClassA.a); // 输出 ClassA.a = 30
        System.out.println("ClassB.b = " + ClassB.b); // 输出 ClassB.b = 20

        System.out.println("ClassC.c = " + ClassC.c); // 输出 ClassC.c = 30
        System.out.println("ClassD.d = " + ClassD.d); // 输出 ClassD.d = 20
    }

}

class ClassA {
    static int a = ClassB.b + 10;
}

class ClassB {
    static int b = 20;
}

class ClassC {
    static int c;

    static {
        c = ClassD.d + 10;
    }
}

class ClassD {
    static int d = 20;
}