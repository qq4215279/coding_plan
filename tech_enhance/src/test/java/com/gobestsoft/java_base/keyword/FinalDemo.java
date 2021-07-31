package com.gobestsoft.java_base.keyword;

import java.util.HashMap;
import java.util.Map;

/**
 * FinalDemo
 * final 关键字修饰常量时的使用
 * @author liuzhen
 * @version 1.0.0 2021/5/23 20:29
 */
public class FinalDemo {

    public static final int num = 100;

    public static final Integer numInteger = 100;

    public static final String str = "Final关键字的使用";

    public static final Map<String, Integer> map = new HashMap<>();

    static {
        map.put("a", 1);
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

    public class User {
        public String name;
        public int age;

        public User(String name, int age) {
            this.name = name;
            this.age = age;
        }

        @Override
        public String toString() {
            return "User{" + "name='" + name + '\'' + ", age=" + age + '}';
        }
    }

    /**
     * final修饰引用类型时，在对其初始化之后便不能再让其指向另一个对象。但被引用的对象的值是可以改变的（引用变量的值实际上是它所引用的对象的地址）。
     */
    public void referenceObjDemo() {
        final User user = new User("haha", 5);
        user.name = "李华";
        user.age = 10;
        System.out.println(user); // 证明了：它指向的对象的内容是可变的。

//        user = new User(); //  // 报错,不可重新赋值
    }

    public static void main(String[] args) {
        FinalDemo demo = new FinalDemo();
        demo.referenceObjDemo();
    }

}
