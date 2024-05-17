package com.mumu.dp2_7_proxy.cjlibDynamic;

/**
 * UserOb
 *
 * @author liuzhen
 * @version 1.0.0 2024/4/11 17:11
 */
public class UserOb {

    private int age = 100;

    public UserOb(int age) {
        this.age = age;
    }

    public int getAge() {
        System.out.println("age: " + age);

        return age;
    }



}
