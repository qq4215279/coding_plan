package com.demo.geek.c11_binary_search;

/**
 * LeetCode069
 * x的平方根
 * @author liuzhen
 * @version 1.0.0 2021/8/17 21:25
 */
public class LeetCode069 {

    /**
     * 实现 int sqrt(int x) 函数。
     * 计算并返回 x 的平方根，其中 x 是非负整数。
     * 由于返回类型是整数，结果只保留整数的部分，小数部分将被舍去。
     *
     * 示例 1:
     * 输入: 4
     * 输出: 2
     *
     * 示例 2:
     * 输入: 8
     * 输出: 2
     * 说明: 8 的平方根是 2.82842...,
     *      由于返回类型是整数，小数部分将被舍去。
     */

    /**
     * 二分法
     * @author liuzhen
     * @date 2021/8/17 22:01
     * @param x
     * @return int
     */
    public static int mySqrt(int x) {
        if (x == 0 || x == 1) {
            return x;
        }

        long left = 1;
        long right = x;
        while (left <= right) {
//             int middle = (left + right) / 2;
            // 防止数组越界
            long middle = left + (right - left) / 2;
            if (middle * middle > x) {
                right = middle - 1;
            } else {
                left = middle + 1;
            }
        }
        return (int)right;
    }

    /**
     * 暴力解法
     * @author liuzhen
     * @date 2021/8/17 22:01
     * @param x
     * @return int
     */
    public static int mySqrt02(int x) {
        long res = x;
        while (res * res > x) {
            res = (res + x / res) / 2;
        }

        return (int)res;
    }

    /**
     * TODO 牛顿迭代法
     * @author liuzhen
     * @date 2021/8/17 22:01
     * @param x
     * @return int
     */
    public static int mySqrt03(int x) {

        return 1;
    }


    public static void main(String[] args) {
        System.out.println(mySqrt(8));
    }

}
