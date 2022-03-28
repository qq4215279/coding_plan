/*
 * Copyright 2020-2021, 木木996.
 * All Right Reserved.
 */

package com.mumu.geek.c11_binary_search;

/**
 * LeetCode367
 * 有效的完全平方数
 * @author liuzhen
 * @version 1.0.0 2021/8/17 21:27
 */
public class LeetCode367 {

    /**
     * 给定一个正整数 num ，编写一个函数，如果 num 是一个完全平方数，则返回 true ，否则返回 false 。
     * 进阶：不要 使用任何内置的库函数，如  sqrt 。
     *
     * 示例 1：
     * 输入：num = 16
     * 输出：true
     *
     * 示例 2：
     * 输入：num = 14
     * 输出：false
     *  
     * 提示：
     * 1 <= num <= 2^31 - 1
     */

    /**
     * 二分法：时间复杂度：O(logN)。 空间复杂度：O(1)。
     * @author liuzhen
     * @date 2021/8/23 17:10
     * @param num
     * @return boolean
     */
    public static boolean isPerfectSquare(int num) {
        if (num == 1) {
            return true;
        }

        int left = 2;
        int right = num / 2;
        while (left <= right) {
            int middle = left + ((right - left) >> 1);

            // 注意：middle * middle 是int类型计算的话，可能会存在精度缺失情况，注意将结果转为long类型！！
            long chengji = (long)middle * middle;
            if (chengji == num) {
                System.out.println("middle: " + middle);
                return true;
            }

            if (chengji < num) {
                left = (middle + 1);
            } else {
                right = (middle - 1);
            }
        }

        return false;
    }

    /**
     * 牛顿迭代法： 时间复杂度：O(logN)   空间复杂度：O(1)。
     * @author liuzhen
     * @date 2021/8/23 17:12
     * @param num
     * @return boolean
     */
    public static boolean isPerfectSquare2(int num) {
        if (num < 2) {
            return true;
        }

        long x = num / 2;
        while (x * x > num) {
            x = (x + num / x) / 2;
        }

        return (x * x == num);
    }

    public static void main(String[] args) {
        int num = 808201;
        System.out.println(isPerfectSquare(num)); // 899

        System.out.println("---------------->");

        System.out.println(isPerfectSquare2(num));
    }

}
