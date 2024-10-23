/*
 * Copyright 2020-2023, 木木996.
 * All Right Reserved.
 */

package com.mumu.common.pojo;

import lombok.Data;

/**
 * User
 *
 * @author liuzhen
 * @version 1.0.0 2023/7/7 11:05
 */
@Data
public class User extends Person {
    private String userName;
    private String password;
    private int age;
    private int sex;
    private String text;

    public User(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    public User(String userName, int age) {
        this.userName = userName;
        this.age = age;
    }

    public User(String userName, int age, int sex) {
        this.userName = userName;
        this.age = age;
        this.sex = sex;
    }

    public User(String userName, String password, int age, int sex) {
        this.userName = userName;
        this.password = password;
        this.age = age;
        this.sex = sex;
    }

    @Override
    public String toString() {
        return "User{" +
                "userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", age=" + age +
                ", sex=" + sex +
                '}';
    }

    @Override
    public void eat() {
        System.out.println("用户吃肉");
    }
}
