/*
 * Copyright 2020-2021, 木木996.
 * All Right Reserved.
 */

package com.gobestsoft.java_base.reflect.junit;

public class CalculatorTest {

    public static void main(String[] args) {

        // 创建对象
        Calculator c = new Calculator();
        // 调用
       /* int result = c.add(1, 2);
        System.out.println(result);*/

        int result = c.sub(1, 1);
        System.out.println(result);

        String str = "abc";
    }
}
