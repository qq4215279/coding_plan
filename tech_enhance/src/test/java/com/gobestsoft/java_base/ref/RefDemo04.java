/*
 * Copyright 2020-2021, 木木996.
 * All Right Reserved.
 */

package com.gobestsoft.java_base.ref;

/**
 * RefDemo04
 *
 * @author liuzhen
 * @version 1.0.0 2021/5/7 20:47
 */
public class RefDemo04 {

    public static void main(String[] args) {
        Person person = new Person("刘亦菲", 20);
        System.out.println("调用参数之前：" + person);
        call(person);
        // person没有变的原因：call方法传递的是引用的一个拷贝，只是p一个的引用地址没main方法里的person是同一个，但p在call里重新赋值新的引用
        // p的引用就不在跟person是同一个了。所以修改p引用对象里的属性值，不影响person对象的属性值。
        System.out.println("调用参数之后：" + person);
    }

    public static void call(Person p) {
        p = new Person("lihua", 20);
        System.out.println("call person : "  + p);
    }

}
