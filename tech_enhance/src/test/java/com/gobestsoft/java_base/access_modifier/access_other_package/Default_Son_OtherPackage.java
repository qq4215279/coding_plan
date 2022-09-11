/*
 * Copyright 2020-2021, 木木996.
 * All Right Reserved.
 */

package com.gobestsoft.java_base.access_modifier.access_other_package;

import com.gobestsoft.java_base.access_modifier.default_.DefaultDemo;

/**
 * Default_Son_OtherPackage
 * 不能访问父类default修饰的变量；不能访问其他包定义的default变量；
 * @author liuzhen
 * @version 1.0.0 2021/10/8 9:06
 */
public class Default_Son_OtherPackage extends DefaultDemo {

    public Default_Son_OtherPackage(String name) {
        super(name);
    }

    public void getNameSon() {
        // 1. 不能访问父类default修饰的变量
//        String name = super.name;
    }

    public void getNameSon2() {
        // 2. 不能访问其他包定义的default变量；
        DefaultDemo demo = new DefaultDemo("不能访问其他包定义的default变量");
//        String name = demo.name;
    }

}