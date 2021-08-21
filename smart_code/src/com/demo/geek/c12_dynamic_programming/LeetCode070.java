package com.demo.geek.c12_dynamic_programming;

/**
 * LeetCode070
 * 爬楼梯 -- Fibonacci
 * @author liuzhen
 * @version 1.0.0 2021/8/18 20:57
 */
public class LeetCode070 {

    /**
     * 假设你正在爬楼梯。需要 n 阶你才能到达楼顶。
     * 每次你可以爬 1 或 2 个台阶。你有多少种不同的方法可以爬到楼顶呢？
     * 注意：给定 n 是一个正整数。
     *
     * 示例 1：
     * 输入： 2
     * 输出： 2
     * 解释： 有两种方法可以爬到楼顶。
     * 1.  1 阶 + 1 阶
     * 2.  2 阶
     *
     * 示例 2：
     * 输入： 3
     * 输出： 3
     * 解释： 有三种方法可以爬到楼顶。
     * 1.  1 阶 + 1 阶 + 1 阶
     * 2.  1 阶 + 2 阶
     * 3.  2 阶 + 1 阶
     *
     * 思路：
     *  DP方程：F(n) = F(n-1) + F(n-2)
     *
     * 扩展题：
     * 扩展1：1,2,3  =>   F(n) = F(n-1) + F(n-2) + F(n-3)
     * 扩展2：相邻两部的步伐不能相同
     */

    /**
     * 同Fibonacci题
     * @author liuzhen
     * @date 2021/8/21 22:18
     * @param n
     * @return int
     */
    public static int climbStairs(int n) {
        int f1 = 1;
        int f2 = 2;

        if (n == 1) {
            return f1;
        }
        if (n == 2) {
            return f2;
        }

        int f3 = 0;
        for (int i = 3; i <= n; i++) {
            f3 = f1 + f2;
            f1 = f2;
            f2 = f3;
        }

        return f3;
    }

    public static void main(String[] args) {
        System.out.println(climbStairs(5));
    }

}
