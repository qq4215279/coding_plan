/*
 * Copyright 2018-2020, 上海哈里奥科技有限公司
 * All Right Reserved.
 */

package com.mumu.geek.c0.string;

public class LeetCode125 { // LeetCode T125
    /**
     * 验证回文串
     * 给定一个字符串，验证它是否是回文串，只考虑字母和数字字符，可以忽略字母的大小写。
     * 说明：本题中，我们将空字符串定义为有效的回文串。
     *
     * 输入: "A man, a plan, a canal: Panama"
     * 输出: true
     *
     * 输入: "race a car"
     * 输出: false
     * *
     **/

    public static boolean isPalindrome01(String s) { // 双指针
        if (s == null) return true;

        char[] chars = s.toCharArray();
        int p = 0;
        int q = s.length() - 1;
        while (p < q) {
            while (!Character.isLetterOrDigit(chars[p]) ){ // 判断是否是数字或字母
                p++;
            }
            while (!Character.isLetterOrDigit(chars[q])){
                q--;
            }

            if (chars[p] != chars[q] ){
                return false;
            }
            p++;
            q--;

        }

        return true;
    }


    public static boolean isPalindrome02(String s) {
        if (s == null) return true;

        s = s.toLowerCase();
        int l = s.length();
        StringBuilder sb = new StringBuilder(l);
        for (char c : s.toCharArray()) {
            if ((c >= '0' && c <= '9') || (c >= 'a' && c <= 'z')) {
                System.out.println("char: " + c);
                sb.append(c);
            }
        }
        System.out.println("s:" + s);
        System.out.println("sb:" + sb.toString());
        System.out.println("reverseStr:" + sb.reverse().toString());
        return sb.toString().equals(sb.reverse().toString());
    }

    public static boolean isPalindrome03(String s) { // 验证不能用字节反转字符串操作 eg: 转成字节后：95 7 =>  7 95
        if (s == null) {
            return true;
        }
        s = s.toLowerCase();
        int l = s.length();

        StringBuilder str = new StringBuilder(l);
        for (byte c : s.getBytes()) {
            if ((c >= 48 && c <= 57) || (c >= 97 && c <= 122)) {
                System.out.println("byte: " + c);
                str.append(c);
            }
        }
        System.out.println("s:" + s);
        System.out.println("str:" + str.toString());
        System.out.println("reverseStr:" + str.reverse().toString());
        return str.toString().equals(str.reverse().toString());
    }


    public static boolean isPalindrome04(String s) {
        if (s == null || s.length() == 0)
            return true;

        s = s.toLowerCase();
        int l = s.length();
        StringBuilder sb = new StringBuilder(l);
        for(char c : s.toCharArray()) {
            if ((c >= '0' && c <= '9') || (c >='a' && c <= 'z')){
                sb.append(c);
            }
        }
        StringBuilder resSb = sb.reverse();
        String a = sb.toString();
        String b = resSb.toString();
        System.out.println("a: " + sb.toString() + " 连续b: " + sb.reverse().toString() + " b: " + b);


        /**
         * str:raceacar
         * reverseStr:racaecar
         */
        return sb.toString().equals(sb.reverse().toString());
//        return a.equals(b);


    }

    public static void main(String[] args) {
        String s = "Race a car";

        System.out.println("方法1---双指针：" + isPalindrome01(s));
        System.out.println("---------------------------->");

        System.out.println("方法2：" + isPalindrome02(s));
        System.out.println("---------------------------->");

        System.out.println("方法3：" + isPalindrome03(s));
        System.out.println("---------------------------->");

        char a = 'a';
        char c = 'c';
        System.out.println("字符比较大小eg：'c' > 'a'?  " + (c>a));

        System.out.println("test04:  " + isPalindrome04(s));

    }


}
