/*
 * Copyright 2020-2023, 木木996.
 * All Right Reserved.
 */

package com.mumu.java_base.string;

import org.junit.Test;

import java.util.Arrays;

/**
 * Test split()方法
 *
 * @author liuzhen
 * @version 1.0.0 2021/5/8 20:14
 */
public class SplitMethodDemo {

    /**
     * String a = new String(“1”+”2”)最终创建了几个对象: 2
     * @param args
     */
    public static void main(String[] args) {
        String aa = "aa,bb,cc,, ";
        String[] split = aa.split(",");

        System.out.println("长度： " + split.length); // 长度： 5
        System.out.println("split[3]--->" + split[3] + "!"); // split[3]--->?
        System.out.println("split[4]--->" + split[4] + "!"); // split[4]---> ?

        System.out.println("----------------------------------->");

        String bb = "aa,bb,cc,,"; // cc后面的逗号的字符都为空""， split()方法后会舍去cc后面的""内容！！！所以长度变为3
        String[] bbSplit = bb.split(",");

        System.out.println("长度： " + bbSplit.length); // 长度： 3
        System.out.println("bbSplit[3]--->" + bbSplit[3]); // 报错: java.lang.ArrayIndexOutOfBoundsException: 3
    }

    /**
     * limit > 0 ，则pattern（模式）应⽤ n - 1 次
     * @date 2022/7/23 16:06
     * @param
     * @return void
     */
    @Test
    public void splitDemo1() {
        // String str = "a,b,c";
        // String str = "a,b,c,,";
        String str = "a,b,c,,d,,";
        // String[] c1 = str.split(",", 2);
        String[] c1 = str.split(",", 5);
        System.out.println(c1.length); // 2
        System.out.println(Arrays.toString(c1)); // {"a","b,c"}
    }

    /** 
     * limit = 0 ，则pattern（模式）应⽤⽆限次并且省略末尾的空字串
     * @date 2022/7/23 16:06 
     * @param  
     * @return void
     */
    @Test
    public void splitDemo2() {
        String str2 = "a,b,c,,";
        String[] c2 = str2.split(",", 0);
        System.out.println(c2.length); // 3
        System.out.println(Arrays.toString(c2)); // [a, b, c]
    }

    /** 
     * limit < 0 ，则pattern（模式）应⽤⽆限次
     * @date 2022/7/23 16:06
     * @param  
     * @return void
     */
    @Test
    public void splitDemo3() {
        String str2 = "a,b,c,,";
        String[] c2 = str2.split(",", -1);
        System.out.println(c2.length); // 5
        System.out.println(Arrays.toString(c2)); // [a, b, c, , ]
    }

    @Test
    public void splitDemo4() {
        String str2 = "";
        String[] c2 = str2.split(",");
        System.out.println(c2.length); // 1
        System.out.println(Arrays.toString(c2)); // []

        for (String s : c2) {
            System.out.println(s + ":::");
        }
    }

    @Test
    public void splitDemo5() {
        String str2 = "";
        String[] c2 = str2.split(",");
        System.out.println(c2.length); // 1
        System.out.println(Arrays.toString(c2)); // []

        for (String s : c2) {
            System.out.println(s + ":::");
        }
    }

}
