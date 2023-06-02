/*
 * Copyright 2020-2021, 木木996.
 * All Right Reserved.
 */

package com.mumu.java_base.generic;

/**
 * MyGenericClass
 * 定义和使用含有泛型的类
 * @author liuzhen
 * @version 1.0.0 2022/1/4 22:36
 */
public class MyGenericClass<MVP> {

    /**
     * 1. 定义和使用含有泛型的类
     * 定义格式:
     *  修饰符 class 类名<代表泛型的变量> {
     *  }
     */

    // 没有MVP类型，在这里代表 未知的一种数据类型 未来传递什么就是什么类型
    private MVP mvp;

    public void setMVP(MVP mvp) {
        this.mvp = mvp;
    }

    public MVP getMVP() {
        return mvp;
    }

}

class GenericClassDemo {
    public static void main(String[] args) {
        // 创建一个泛型为String的类
        MyGenericClass<String> my = new MyGenericClass<>();
        // 调用setMVP
        my.setMVP("大胡子登登");
        // 调用getMVP
        String mvp = my.getMVP();
        System.out.println(mvp);

        // 创建一个泛型为Integer的类
        MyGenericClass<Integer> my2 = new MyGenericClass<>();
        my2.setMVP(123);
        Integer mvp2 = my2.getMVP();
    }
}
