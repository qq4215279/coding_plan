/*
 * Copyright 2020-2023, 木木996.
 * All Right Reserved.
 */

package com.mumu.java_base.ref;

import com.mumu.common.pojo.User;

/**
 * RefDemo02
 *
 * @author liuzhen
 * @version 1.0.0 2021/5/7 20:39
 */
public class RefDemo02 {

    // 在调用方法时，传递参数可以传递基本数据类型和引用类型
    public static void main(String[] args) {
        User user = new User("张三", 18);
        System.out.println("调用参数之前：" + user);
        call(user);
        System.out.println("调用参数之后：" + user);
    }

    public static void call(User person) {
        person.setUserName("李四");
        person.setAge(30);
    }


}
