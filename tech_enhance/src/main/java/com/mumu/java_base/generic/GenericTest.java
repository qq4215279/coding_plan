/*
 * Copyright 2020-2023, 木木996.
 * All Right Reserved.
 */

package com.mumu.java_base.generic;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * GenericDemo01
 *
 * @author liuzhen
 * @version 1.0.0 2022/1/4 22:31
 */
public class GenericTest {

    /**
     * 泛型通配符
     * 当使用泛型类或者接口时，传递的数据中，泛型类型不确定，可以通过通配符<?>表示。但是一旦使用泛型的通配符后，只能使用Object类中的共性方法，集合中元素自身方法无法使用。
     * 通配符基本使用:
     * 泛型的通配符: 不知道使用什么类型来接收的时候,此时可以使用?,?表示未知通配符。此时只能接受数据,不能往该集合中存储数据。
     * tips: 泛型不存在继承关系 Collection<Object> list = new ArrayList<String>(); 这种是错误的。
     *
     * 通配符高级使用----受限泛型
     * 之前设置泛型的时候，实际上是可以任意设置的，只要是类就可以设置。但是在JAVA的泛型中可以指定一个泛型的 上限 和 下限。
     *
     * 泛型的上限：
     *      格式：类型名称 <? extends 类 > 对象名称
     *      意义：只能接收该类型及其子类
     * 泛型的下限：
     *      格式：类型名称 <? super 类 > 对象名称
     *      意义：只能接收该类型及其父类型
     * 比如：现已知Object类，String 类，Number类，Integer类，其中Number是Integer的父类
     *
     *
     * 泛型的上限限定: ? extends E  代表使用的泛型只能是E类型的子类/本身
     * 泛型的下限限定: ? super E    代表使用的泛型只能是E类型的父类/本身
     */

    /**
     *  泛型的上限：此时的泛型?，必须是Number类型或者Number类型的子类
     * @param coll
     */
    public static void getElement_Son_Number(Collection<? extends Number> coll){
    }

    /**
     * 泛型的下限：此时的泛型?，必须是Number类型或者Number类型的父类
     * @param coll
     */
    public static void getElement_Fu_Number(Collection<? super Number> coll){
    }

    /**
     * 父 -> 子:
     *     Object  ->  Number  ->  Integer
     *     Object  ->  String
     *
     * @param args
     */
    public static void main(String[] args) {
        List<Integer> integerList = new ArrayList<>();
        List<String> stringList = new ArrayList<>();
        List<Number> numberList = new ArrayList<>();
        List<Object> objectList = new ArrayList<>();

        getElement_Son_Number(integerList);
        // getElement_Son_Number(stringList); // 报错
        getElement_Son_Number(numberList);
        // getElement_Son_Number(objectList); // 报错

        // getElement_Fu_Number(integerList); // 报错
        // getElement_Fu_Number(stringList); // 报错
        getElement_Fu_Number(numberList);
        getElement_Fu_Number(objectList);

    }

}
