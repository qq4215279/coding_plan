/*
 * Copyright 2020-2021, 木木996.
 * All Right Reserved.
 */

package com.gobestsoft.java_base.generic;

/**
 * MyGenericMethod
 * 含有泛型的方法
 * @author liuzhen
 * @version 1.0.0 2022/1/4 22:39
 */
public class MyGenericMethod {

    /**
     * 2. 含有泛型的方法
     * 定义格式：
     *      修饰符 <代表泛型的变量> 返回值类型 方法名(参数){
     *
     *      }
     *
     * 使用格式：调用方法时，确定泛型的类型
     *
     *
     *
     * 定义含有泛型的方法: 泛型定义在方法的修饰符和返回值类型之间
     *
     *     格式:
     *         修饰符 <泛型> 返回值类型 方法名(参数列表(使用泛型)) {
     *             方法体;
     *         }
     *
     *     含有泛型的方法,在调用方法的时候确定泛型的数据类型
     *     传递什么类型的参数,泛型就是什么类型
     */


    public <MVP> void show(MVP mvp) {
        System.out.println(mvp.getClass());
    }

    public <MVP> MVP show2(MVP mvp) {
        return mvp;
    }

    // 定义一个含有泛型的方法
    public <M extends Number> void method01(M m) {
        System.out.println(m);
    }

    // 定义一个含有泛型的静态方法
    public static <S> void method02(S s) {
        System.out.println(s);
    }

}

class GenericMethodDemo {
    public static void main(String[] args) {
        // 创建对象
        MyGenericMethod mm = new MyGenericMethod();
        // 演示看方法提示
        mm.show("aaa");
        mm.show(123);
        mm.show(12.45);
    }
}
