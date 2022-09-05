/*
 * Copyright 2020-2021, 木木996.
 * All Right Reserved.
 */

package com.mumu.work.huawei;

import java.util.Scanner;

/**
 * Main
 *
 * @author liuzhen
 * @version 1.0.0 2022/8/10 21:28
 */
public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String s = sc.next();

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
                    sb = new StringBuilder();
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
}
