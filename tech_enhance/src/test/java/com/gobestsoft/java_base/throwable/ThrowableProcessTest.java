/*
 * Copyright 2020-2021, 木木996.
 * All Right Reserved.
 */

package com.gobestsoft.java_base.throwable;

/**
 * ThrowableProcessTest
 * 异常的产生过程解析
 * @author liuzhen
 * @version 1.0.0 2022/1/9 16:33
 */
public class ThrowableProcessTest {

    /**
     * 异常的产生过程解析
     * 1. 由于没有找到4索引，导致运行时异常。这个异常JVM认识。
     * 这个异常Java本身有描述：描述内容包括：异常的名称、异常的内容、异常的产生位置。
     * Java将这些信息直接封装到异常对象中。new ArrayIndexOutOfBoundsException(4)
     *
     * 2. throw new ArrayIndexOutOfBoundsException(4); 产生异常对象
     *
     * 3. JVM将异常的异常抛给调用者main()方法
     *
     * 4. main() 方法接收到了数组索引越界异常对象。
     * 由于main()方法没有进行处理异常，main()方法就会继续把异常抛给调用者JVM。
     * 当JVM收到异常后，将异常对象中的名称、异常内容、位置都显示在屏幕上。同时让程序立刻终止。
     *
     * 运行结果：
     *
     */


    /**
     *  对给定的数组通过给定的角标获取元素。
     */
    public static int getElement(int[] arr, int index) {
        int element = arr[index];
        return element;
    }

    public static void main(String[] args) {
        int[] arr = {34, 12, 67};
        int num = getElement(arr, 4);
        System.out.println("num=" + num);
        System.out.println("over");
    }

}
