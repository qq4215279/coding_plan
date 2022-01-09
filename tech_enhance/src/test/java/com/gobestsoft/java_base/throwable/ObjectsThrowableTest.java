/*
 * Copyright 2020-2021, 木木996.
 * All Right Reserved.
 */

package com.gobestsoft.java_base.throwable;

import java.util.Objects;

/**
 * ObjectsThrowableTest
 *
 * @author liuzhen
 * @version 1.0.0 2022/1/9 17:30
 */
public class ObjectsThrowableTest {

    /**
     * Objects类中的静态方法
     *     public static <T> T requireNonNull(T obj):查看指定引用对象不是null。
     *     源码:
     *         public static <T> T requireNonNull(T obj) {
     *             if (obj == null)
     *                 throw new NullPointerException();
     *             return obj;
     *         }
     */

    public static void main(String[] args) {
        method(null);
    }

    public static void method(Object obj){
        //对传递过来的参数进行合法性判断,判断是否为null
        /*if(obj == null){
            throw new NullPointerException("传递的对象的值是null");
        }*/

        //Objects.requireNonNull(obj);
        Objects.requireNonNull(obj, "传递的对象的值是null");
    }

}
