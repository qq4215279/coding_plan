/*
 * Copyright 2020-2021, 木木996.
 * All Right Reserved.
 */

package com.mumu.java_base.generic;

/**
 * MyGenericInterface
 * 含有泛型的接口
 * @author liuzhen
 * @version 1.0.0 2022/1/4 22:41
 */
public interface MyGenericInterface<E> {

    /**
     * 3. 含有泛型的接口
     * 定义格式：
     *      修饰符 interface接口名<代表泛型的变量> {
     *
     *       }
     *
     * 使用格式：
     *      1. 定义类时确定泛型的类型
     *      2. 始终不确定泛型的类型，直到创建对象时，确定泛型的类型
     */

    void add(E e);

    E getE();

}

/**
 * 使用1. 定义类时确定泛型的类型
 */
class MyImp1 implements MyGenericInterface<String> {
    @Override
    public void add(String e) {
        // 省略...
    }

    @Override
    public String getE() {
        return null;
    }
}

/**
 * 使用2. 始终不确定泛型的类型，直到创建对象时，确定泛型的类型
 */
class MyImp2<E> implements MyGenericInterface<E> {
    @Override
    public void add(E e) {
        // 省略...
    }

    @Override
    public E getE() {
        return null;
    }
}

/**
 * 使用
 */
class GenericInterface {
    public static void main(String[] args) {
        MyImp2<String> my = new MyImp2<>();
        my.add("aa");
    }
}