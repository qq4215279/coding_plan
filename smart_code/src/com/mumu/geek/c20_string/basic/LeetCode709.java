package com.mumu.geek.c20_string.basic;

/**
 * LeetCode709
 * 转换成小写字母
 * @author liuzhen
 * @version 1.0.0 2021/8/30 22:34
 */
public class LeetCode709 {

    /**
     * 给你一个字符串 s ，将该字符串中的大写字母转换成相同的小写字母，返回新的字符串。
     *
     * 示例 1：
     * 输入：s = "Hello"
     * 输出："hello"
     *
     * 示例 2：
     * 输入：s = "here"
     * 输出："here"
     *
     * 示例 3：
     * 输入：s = "LOVELY"
     * 输出："lovely"
     *  
     * 提示：
     * 1 <= s.length <= 100
     * s 由 ASCII 字符集中的可打印字符组成
     */

    public static String toLowerCase(String s) {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < s.length(); i++) {
            char charAt = s.charAt(i);
            if (charAt >= 65 && charAt <= 90) {
                charAt = (char)(charAt + 32);
            }
            sb.append(charAt);
        }

        return sb.toString();
    }

    public static void main(String[] args) {
        System.out.println((int)'a');
        System.out.println((int)'Z');
        String s = "Hello";
        System.out.println(toLowerCase(s));
    }

}
