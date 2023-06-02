/*
 * Copyright 2020-2021, 木木996.
 * All Right Reserved.
 */

package com.mumu.java_base.keyword._default;

/**
 * B
 *
 * @author liuzhen
 * @version 1.0.0 2021/12/20 23:11
 */
public interface B extends A {

    default void hello() {
        System.out.println("Hello from B");
    }

}
