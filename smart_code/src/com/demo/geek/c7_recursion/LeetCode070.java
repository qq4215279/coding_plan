/*
 * Copyright 2018-2020, 上海哈里奥科技有限公司
 * All Right Reserved.
 */

package com.demo.geek.c7_recursion;

public class LeetCode070 { // T70

    /**
     * 爬楼梯问题：   即 Fibonacci数列问题
     * 假设你正在爬楼梯。需要 n 阶你才能到达楼顶。
     * 每次你可以爬 1 或 2 个台阶。你有多少种不同的方法可以爬到楼顶呢？
     * 注意：给定 n 是一个正整数。
     * <p>
     * 输入： 2
     * 输出： 2
     * 解释： 有两种方法可以爬到楼顶。
     * 1.  1 阶 + 1 阶
     * 2.  2 阶
     * 3. f(1) + f(2)
     * 4. f(2) + f(3)
     * n. f(n-1) + f(n-2)   : Fibonacci
     */

    public int climbStairs(int n) {
        int f1 = 1;
        int f2 = 2;
        int f3 = 0;

        if (n == 1) {
            return f1;
        }
        if (n == 2) {
            return f2;
        }

        for (int i = 2; i < n; i++) {
            f3 = f1 + f2;
            f1 = f2;
            f2 = f3;
        }
        return f3;
    }

    public int climbStairs00(int n) { // 递归
        int f1 = 1;
        int f2 = 2;

        if (n == 1) {
            return f1;
        }
        if (n == 2) {
            return f2;
        }

        return climbStairs00(n - 1) + climbStairs00(n - 2);
    }

}
