/*
 * Copyright 2020-2021, 木木996.
 * All Right Reserved.
 */

package com.mumu.work.huawei;

/**
 * Solution01
 *
 * @author liuzhen
 * @version 1.0.0 2022/8/10 21:02
 */
public class Main2 {

    public static void demo01(String s) {
        int sum = 0;

        char[] charArray = s.toCharArray();

        boolean hasFuHao = false;
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < charArray.length; i++) {
            char c = charArray[i];

            if (c == '-') {
                hasFuHao = true;
                continue;
            }

            if ((c >= 65 && c <= 90) || c >= 90 && c <= 122) {
                if (hasFuHao) {
                    sum -= calcFuhao(sb.toString());

                    hasFuHao = false;
                }

                continue;
            }

            if (hasFuHao) {
                sb.append(c);
            } else {
                c -= 48;
                sum += c;
            }
        }

        sum = sum < 0 ? -sum : sum;

        System.out.println(sum);
    }

    private static int calcFuhao(String s) {
        int sum = 0;
        int x = 1;
        char[] charArr = s.toCharArray();
        for (int i = charArr.length - 1; i >= 0; i--) {
            char c = (char)(charArr[i] - 48);
            sum += c * x;
            x *= 10;
        }

        return sum;
    }

    public static void main(String[] args) {
        char c = '-'; //45
        char a = 'a'; // 97
        char z = 'z'; // 122
        char A = 'A'; // 65
        char Z = 'Z'; // 90
        char aa = '0'; // 48
        char bb = '9'; // 57
        System.out.println((int)c);
        System.out.println((int)a);
        System.out.println((int)z);
        System.out.println((int)A);
        System.out.println((int)Z);
        System.out.println((int)aa);
        System.out.println((int)bb);

        // String s = "bb1234aa";
        String s = "bb12-34aa";
        demo01(s);
    }

}
