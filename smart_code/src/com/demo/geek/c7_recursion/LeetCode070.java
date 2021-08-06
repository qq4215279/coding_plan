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

    /**
     * 递归: f(n) = f(n-1) + f(n-2)  解释: f(n-1): 直接上1阶楼梯到f(n); f(n-2): 也可以直接上2阶楼梯到f(n); 两种选择。
     * 递归总结：对于每一次都有多种选择的情况，递归写法是自上而下写调用递归，有多少种可能，就调用多少次递归函数。如果是将多种可能的求和(eg: 此题)，则将多次
     *  递归的结果返回累加；如果是对每次递归比较出大小，则在两次递归后在继续写业务逻辑，比如比较大小，返回业务上需要的结果。
     * @param n
     * @return
     */
    public static int climbRecursion(int n) {
        int f1 = 1;
        int f2 = 2;

        if (n == 1) {
            return f1;
        }
        if (n == 2) {
            return f2;
        }

        return climbRecursion(n - 1) + climbRecursion(n - 2);
    }

    public static void main(String[] args) {
        int i = climbRecursion(5);
        System.out.println(i);
    }

}
