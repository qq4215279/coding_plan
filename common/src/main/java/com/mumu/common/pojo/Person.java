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
public class Person extends AbstractPerson {
    public String a;
    protected String b;
    String c;
    private String d;


    @Override
    public void eat() {
        System.out.println("人吃饭");
    }

    public void eat(String food) {
        System.out.println("eat..." + food);
    }
}
