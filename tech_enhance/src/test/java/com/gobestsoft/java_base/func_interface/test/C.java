/*
 * Copyright 2020-2021, 木木996.
 * All Right Reserved.
 */

package com.gobestsoft.java_base.func_interface.test;

/**
 * C
 *
 * @author liuzhen
 * @version 1.0.0 2021/12/20 23:12
 */
public class C implements A, B {

    /**
     * 前言：jdk1.8中新增加了default关键字,就是在接口中可以增加默认实现.
     * 因为改动接口所有的实现类都要改动,所以增加了default关键字后不需要修改其他类,默认给所有实现类增加了方法.
     *
     * 提问：一个类实现了两个接口,这两个接口都有默认的default关键字,那么程序会先执行哪一个呢?
     * 答案是先执行B
     *
     * 因为这里有三个规则
     * 1. 类中的方法优先级最高. 类或者父类声明的方法的优先级高于任何声明为默认方法的优先级.
     * 2. 子接口优先级最高.
     * 3. 1  2 无法判断的情况下,只能显示覆盖.
     *
     * 因为这里C有实现了两个接口,所以无法通过1来判断.
     * 之所以B的优先级比A高,是因为B继承了A,B比A更具体,所以按照规则2执行B.
     * 假如B不继承A的话,那么会编译不通过,编译器无法确定是hello是哪一个,只能按照规则3来在C中显示的实现hello()方法.
     */
    public static void main(String[] args) {
        new C().hello(); // Hello from B
    }
}


