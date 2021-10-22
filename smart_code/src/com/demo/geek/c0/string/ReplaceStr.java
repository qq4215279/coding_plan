/*
 * Copyright 2018-2020, 上海哈里奥科技有限公司
 * All Right Reserved.
 */

package com.demo.geek.c0.string;

/**
 * 请实现一个函数，将一个字符串中的每个空格替换成“%20”。
 * 例如，当字符串为We Are Happy.则经过替换之后的字符串为We%20Are%20Happy。
 */
public class ReplaceStr {

    public void test() {
        String a = "We Are Happy";
        char[] chars = a.toCharArray();
        String[] b = new String[16];
        for (int i = 0; i < chars.length; i++) {
            if (' ' == chars[i]) {

            }
        }

        System.out.println(a);
        String replace = a.replace(" ", "%20");
        System.out.println(replace);

    }

    public static void main(String[] args) {
        new ReplaceStr().test();
    }

}
