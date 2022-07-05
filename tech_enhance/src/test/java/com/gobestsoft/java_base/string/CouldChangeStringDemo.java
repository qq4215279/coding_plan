/*
 * Copyright 2020-2021, 木木996.
 * All Right Reserved.
 */

package com.gobestsoft.java_base.string;

import org.junit.Test;

import java.lang.reflect.Field;

/**
 * CouldChangeStringDemo
 * 可以改变的字符串
 * @author liuzhen
 * @version 1.0.0 2022/4/9 18:13
 */
public class CouldChangeStringDemo {

    /**
     * 前⾯我们介绍了，String 类是⽤ final 关键字修饰的，所以我们认为其是不可变对象。但是真的不可变吗？
     * 每个字符串都是由许多单个字符组成的，我们知道其源码是由 char[] value 字符数组构成。
     *
     * value 被 final 修饰，只能保证引⽤不被改变，但是 value 所指向的堆中的数组，才是真实的数据，只要能够操作堆中的数组，依旧能改变数据。
     * ⽽且 value 是基本类型构成，那么⼀定是可变的，即使被声明为 private，我们也可以通过反射来改变。
     * @author liuzhen
     * @date 2022/4/9 18:14
     * @return
     */
    @Test
    public void test() throws NoSuchFieldException, IllegalAccessException {
        String str = "vae";
        // 打印原字符串
        System.out.println(str); // vae
        // 获取String类中的value字段
        Field fieldStr = String.class.getDeclaredField("value");
        // 因为value是private声明的，这⾥修改其访问权限
        fieldStr.setAccessible(true);
        // 获取str对象上的value属性的值
        char[] value = (char[]) fieldStr.get(str);
        // 将第⼀个字符修改为 V(⼩写改⼤写)
        value[0] = 'V';
        // 打印修改之后的字符串
        System.out.println(str); // Vae
    }

    /**
     * 通过前后两次打印的结果，我们可以看到 String 被改变了，但是在代码⾥，⼏乎不会使⽤反射的机制去操作 String 字符串，所以，我们会认为 String 类型是不可变的。
     * 那么，String 类为什么要这样设计成不可变呢？我们可以从性能以及安全⽅⾯来考虑：
     *  安全
     *     - 引发安全问题，譬如，数据库的⽤户名、密码都是以字符串的形式传⼊来获得数据库的连接，或者在socket编程中，主机名和端⼝都是以字符串的形式传⼊。
     *       因为字符串是不可变的，所以它的值是不可改变的，否则⿊客们可以钻到空⼦，改变字符串指向的对象的值，造成安全漏洞。
     *     - 保证线程安全，在并发场景下，多个线程同时读写资源时，会引竞态条件，由于 String 是不可变的，不会引发线程的问题⽽保证了线程。
     *       HashCode，当 String 被创建出来的时候，hashcode也会随之被缓存，hashcode的计算与value有关，若 String 可变，那么 hashcode 也会随之变化，
     *       针对于 Map、Set 等容器，他们的键值需要保证唯⼀性和⼀致性，因此，String 的不可变性使其⽐其他对象更适合当容器的键值。
     * 性能
     *      当字符串是不可变时，字符串常量池才有意义。字符串常量池的出现，可以减少创建相同字⾯量的字符串，让不同的引⽤指向池中同⼀个字符串，为运⾏时节约很多的堆内存。
     *      若字符串可变，字符串常量池失去意义，基于常量池的String.intern()⽅法也失效，每次创建新的 String 将在堆内开辟出新的空间，占据更多的内存
     */

}
