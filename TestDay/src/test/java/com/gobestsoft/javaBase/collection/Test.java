/*
 * Copyright 2018-2020, 上海哈里奥科技有限公司
 * All Right Reserved.
 */

package com.gobestsoft.javaBase.collection;

/**
 * Test
 *
 * @author liuzhen
 * @version 1.0.0 2021/5/8 20:14
 */
public class Test {

    public static void main(String[] args) {
        String aa = "aa,bb,cc,, ";
        String[] split = aa.split(",");

        System.out.println("--->" + split[3]);
    }

}
