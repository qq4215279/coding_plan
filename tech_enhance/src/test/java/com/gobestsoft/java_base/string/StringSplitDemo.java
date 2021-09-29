package com.gobestsoft.java_base.string;

import org.junit.Test;

/**
 * StringSplitDemo
 * 字符串拼接详解
 * @author liuzhen
 * @version 1.0.0 2021/9/29 17:39
 */
public class StringSplitDemo {

    /**
     * 技巧：
     * +运算符拼接字符串：
     * 1. 如果一边是变量的话：就会先查找常量池中有没有拼接完的，如果有，常量池就不会在新建；否则会新建一个常量，并返回常量池地址。最后会在堆中创建一个新的String对象！
     * 2. 如果二端都是常量：先查找常量池中有没有拼接完的，如果有，常量池就不会在新建；否则会新建一个常量，返回常量池地址。但不会在堆中创建新的对象！
     * （注：== 间的比较，比较的是内存地址）
     */

    /**
     * 字符串变量拼接
     */
    @Test
    public void test01() {
        String a = "abc";
        String b = "ab";
        String c = "c";
        System.out.println(a == b + c); // false
    }

    /**
     * 字符串常量拼接
     */
    @Test
    public void test02() {
        String a = "abc";
        System.out.println(a == "ab"+"c"); // true
    }

}
