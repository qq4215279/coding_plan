/*
 * Copyright 2020-2021, 木木996.
 * All Right Reserved.
 */

package com.mumu.java_base.keyword._default;

/**
 * A
 * JDK8开始，接口中科院定义不用被实现的方法，但改方法必须被“default”修饰
 * @author liuzhen
 * @version 1.0.0 2021/12/20 23:11
 */
public interface A {

    default void hello() {
        System.out.println("Hello from A");
    }

}
