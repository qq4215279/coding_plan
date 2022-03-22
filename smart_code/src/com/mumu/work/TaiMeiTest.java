/*
 * Copyright 2018-2020, 上海哈里奥科技有限公司
 * All Right Reserved.
 */

package com.mumu.work;

/**
 * TaiMeiTest
 *
 * @author liuzhen
 * @version 1.0.0 2020/11/26 23:25
 */
public class TaiMeiTest {

    /**
     * 实现按单词反转输出
     * @param source  原句子，例如：“I LOVE SHANGHAI”
     * @return 结果，例如：“SHANGHAI LOVE I”
     */
    public static String reverse(String source) {
        String[] rev = source.split(" ");
        StringBuilder sb = new StringBuilder();
        for (int i = rev.length - 1; i >= 0; i--) {
            sb.append(rev[i]).append(" ");
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        String source = "I LOVE SHANGHAI";
        System.out.println(TaiMeiTest.reverse(source));
    }

}
