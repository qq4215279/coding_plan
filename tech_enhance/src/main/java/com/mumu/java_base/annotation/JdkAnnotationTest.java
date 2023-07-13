/*
 * Copyright 2020-2023, 木木996.
 * All Right Reserved.
 */

package com.mumu.java_base.annotation;

import java.util.Date;

/**
 * JdkAnnotationTest
 * Jdk自带注解 test
 * @author liuzhen
 * @version 1.0.0 2023/7/13 14:55
 */
@SuppressWarnings("all")
public class JdkAnnotationTest {

    /**
     * @Override：检测被该注解标注的方法是否是继承自父类(接口)的
     * @Deprecated：该注解标注的内容，表示已过时
     * @SuppressWarnings：压制警告
     */


    /**
     * @Override 标记重写方法
     * @return
     */
    @Override
    public String toString() {
        return super.toString();
    }

    /**
     * @Deprecated  标记过时方法
     */
    @Deprecated
    public void show1(){
        // 有缺陷
        System.out.println("这是一个过时方法");
    }

    public void show2(){
        // 替代show1方法
        System.out.println("这是一个替代过时方法的方法");
    }

    public void demo(){
        show1();
        Date date = new Date();
    }

    public static void main(String[] args) {
        JdkAnnotationTest test = new JdkAnnotationTest();

        test.show1();
        test.show2();

    }

}
