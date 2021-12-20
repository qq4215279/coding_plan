/*
 * Copyright 2020-2021, 木木996.
 * All Right Reserved.
 */

package com.gobestsoft.java_base.func_interface.test;

/**
 * A
 *
 * @author liuzhen
 * @version 1.0.0 2021/12/20 23:11
 */
public interface A {

    default void hello() {
        System.out.println("Hello from A");
    }

}
