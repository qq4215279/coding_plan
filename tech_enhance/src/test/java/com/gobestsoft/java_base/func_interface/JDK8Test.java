/*
 * Copyright 2020-2021, 木木996.
 * All Right Reserved.
 */

package com.gobestsoft.java_base.func_interface;

/**
 * JDK8Test
 *
 * @author liuzhen
 * @version 1.0.0 2021/12/20 23:16
 */
public class JDK8Test implements JDK8InterFaceTest {

    /**
     * 重写接口中中default修饰的方法
     * @return
     */
    @Override
    public int defaultMethodB() {
        return 555;
    }

    public static void main(String[] args) {
        // JDK1.8接口中年常量定义
        int constantsX = JDK8InterFaceTest.CONSTANTS_X;
        System.out.println("JDK1.8接口中年常量定义：");
        System.out.println(constantsX);

        // JDK1.8接口中静态方法调用
        int staticMethodA = JDK8InterFaceTest.staticMethodA();
        System.out.println("JDK1.8接口中静态方法调用：");
        System.out.println(staticMethodA);

        // JDK1.8接口中定义的default方法调用
        JDK8Test test = new JDK8Test();
        int methodB = test.defaultMethodB();
        System.out.println("JDK1.8接口中定义的default方法调用：");
        System.out.println(methodB);
    }

}
