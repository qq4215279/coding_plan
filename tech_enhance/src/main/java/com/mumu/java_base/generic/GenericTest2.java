/*
 * Copyright 2020-2023, 木木996.
 * All Right Reserved.
 */

package com.mumu.java_base.generic;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * GenericTest2
 *
 * @author liuzhen
 * @version 1.0.0 2022/1/4 23:03
 */
public class GenericTest2 {

    /**
     * 泛型的通配符:
     *      ?: 代表任意的数据类型
     * 使用方式:
     *    不能创建对象使用
     *    只能作为方法的参数使用
     */

    /**
     * 定义一个方法,能遍历所有类型的ArrayList集合
     * 这时候我们不知道ArrayList集合使用什么数据类型,可以泛型的通配符?来接收数据类型
     *  注意: 泛型没有继承概念的
     * @param list
     */
    public static void printArray(ArrayList<?> list) {
        // 使用迭代器遍历集合
        Iterator<?> it = list.iterator();
        while(it.hasNext()){
            // it.next()方法,取出的元素是Object,可以接收任意的数据类型
            Object o = it.next();
            System.out.println(o);
        }
    }

    /**
     * 如上泛型使用 ? 通配符，等同于 使用 <T>
     * @date 2023/7/11 14:09
     * @param list
     * @return void
     */
    public static <T> void printArray2(ArrayList<T> list) {
        // 使用迭代器遍历集合
        Iterator<T> it = list.iterator();
        while(it.hasNext()){
            // it.next()方法,取出的元素是Object,可以接收任意的数据类型
            Object o = it.next();
            System.out.println(o);
        }
    }

    public static void main(String[] args) {
        ArrayList<Integer> list01 = new ArrayList<>();
        list01.add(1);
        list01.add(2);

        ArrayList<String> list02 = new ArrayList<>();
        list02.add("a");
        list02.add("b");

        printArray(list01);
        printArray(list02);

        // ArrayList<?> list03 = new ArrayList<?>();
    }

}
