/*
 * Copyright 2020-2023, 木木996.
 * All Right Reserved.
 */

package com.mumu.java_base.keyword;

import com.mumu.common.pojo.User;

import java.util.HashMap;
import java.util.Map;

/**
 * FinalDemo
 * final 关键字修饰常量时的使用
 * @author liuzhen
 * @version 1.0.0 2021/5/23 20:29
 */
public class FinalDemo {

    /**
     * final
     * 修饰变量：
     * 修饰基本数据类型指值不能更改
     * 修饰引用类型是指其引用地址不能更改
     *
     * 修饰方法：
     * final 关键字修饰的⽅法不可被覆盖。在《Java编程思想》第 4 版 7.8.2 章节 final ⽅法p176 ⻚这样描述：使⽤ final ⽅法原因有两个：
     * 1. 第⼀个原因是把⽅法锁定，以防⽌任何继承类修改它的含义，这是出于设计的考虑：想要确保在继承中使⽅法的⾏为保持不变，并且不会被覆盖。
     * 2. 第⼆个原因是效率，在 Java 的早期实现中，如果将⼀个⽅法声明为 final，就是同意编译器将针对该⽅法的所有调⽤都转为内嵌调⽤，内嵌调⽤能够提⾼⽅法调⽤效率，
     * 但是如果⽅法很⼤，内嵌调⽤不会提⾼性能。⽽在⽬前的Java版本中（JDK1.5以后），虚拟机可以⾃动进⾏优化了，⽽不需要使⽤final ⽅法。
     * 所以final 关键字只有明确禁⽌覆盖⽅法时，才使⽤其修饰⽅法。
     *
     * 修饰类
     * final 修饰类表示该类不可被继承。也就是说不希望某个类有⼦类的时候，⽤ final 关键字来修饰。并且由于是⽤ final 修饰的类，其类中所有的⽅法也被隐式的指为 final ⽅法。
     * 在 JDK 中有个最明显的类 String ，就是⽤ final 修饰的，将 String 类⽤ final 修饰很重要的⼀个原因是常量池。
     */


    public final int num00;

    public static final int num = 100;

    public static final Integer numInteger = 100;

    public static final String str = "Final关键字的使用";

    public static final Map<String, Integer> map = new HashMap<>();

    static {
        map.put("a", 1);
    }

    public FinalDemo() {
        this.num00 = 999;
    }

    /**
     * 对于一个final变量，如果是基本数据类型的变量，则其数值一旦在初始化之后便不能更改；如果是引用类型的变量，则在对其初始化之后便不能再让其指向另一个对象。
     */
    public void useFinal() {
//        num = 220;  // 报错,不可重新赋值
//        numInteger = 200;  // 报错,不可重新赋值
//        str = "bb";  // 报错,不可重新赋值
        final Object obj = new Object();
//        obj = new Object();  // 报错,不可重新赋值
//
        map.put("a", 2);
    }

    /**
     * final修饰引用类型时，在对其初始化之后便不能再让其指向另一个对象。但被引用的对象的值是可以改变的（引用变量的值实际上是它所引用的对象的地址）。
     */
    public void referenceObjDemo() {
        final User user = new User("haha", 5);
        user.setUserName("李华");
        user.setAge(10);
        System.out.println(user); // 证明了：它指向的对象的内容是可变的。

//        user = new User(); //  // 报错,不可重新赋值
    }

    public static void main(String[] args) {
        FinalDemo demo = new FinalDemo();
        demo.referenceObjDemo();
        System.out.println(demo.num00);
    }

}
