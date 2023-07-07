/*
 * Copyright 2020-2023, 木木996.
 * All Right Reserved.
 */

package com.mumu.common.pojo;

/**
 * Person
 * 人
 * @author liuzhen
 * @version 1.0.0 2023/7/7 15:51
 */
public class Person implements IPerson {
    @Override
    public void eat() {
        System.out.println("人吃饭");
    }
}
