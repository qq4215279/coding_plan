/*
 * Copyright 2018-2020, 上海哈里奥科技有限公司
 * All Right Reserved.
 */

package com.gobestsoft.java_base.string;

/**
 * Test split()方法
 *
 * @author liuzhen
 * @version 1.0.0 2021/5/8 20:14
 */
public class splitTest {

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

}
