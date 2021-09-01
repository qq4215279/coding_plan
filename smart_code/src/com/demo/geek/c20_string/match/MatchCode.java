package com.demo.geek.c20_string.match;

/**
 * MatchCode
 * 字符串匹配算法
 * @author liuzhen
 * @version 1.0.0 2021/9/1 21:05
 */
public class MatchCode {

    // 暴力法
    public static int forceSearch(String txt, String pat) {
        int M = txt.length();
        int N = pat.length();

        for (int i = 0; i <= M- N; i++) {
            int j; // s1
            for (j = 0; j < N; j++) {
                if (txt.charAt(i + j) != pat.charAt(j)) {
                    break;
                }
            }
            if (j == N) {
                return i;
            } // e1

            /*
            * 从s1 到 e1 算法变得更加聪明？
            * 1. 预先判断 hash(txt.substring(i, M)) == hash(pat)
            * 2. KMP
            */
        }
        return -1;
    }

}
