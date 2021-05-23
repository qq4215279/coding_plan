package com.gobestsoft.javaBase.keyword;

import java.util.HashMap;
import java.util.Map;

/**
 * FinalDemo
 * Final 关键字修饰常量时的使用
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
//        num = 220;
//        numInteger = 200;
//        str = "bb";
        final Object obj = new Object();
//        obj = new Object();
//
        map.put("a", 2);
    }

    public static void main(String[] args) {

    }

}
