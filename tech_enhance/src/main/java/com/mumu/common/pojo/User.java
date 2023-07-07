/*
 * Copyright 2020-2023, 木木996.
 * All Right Reserved.
 */

package com.mumu.common.pojo;

/**
 * User
 *
 * @author liuzhen
 * @version 1.0.0 2023/7/7 11:05
 */
public class User {
    private String userName;
    private String password;
    private int age;
    private int sex;

    public User() {
    }

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

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
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
}
