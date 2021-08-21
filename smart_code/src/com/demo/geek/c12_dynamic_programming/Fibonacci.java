package com.demo.geek.c12_dynamic_programming;

/**
 * Fibonacci
 * 斐波拉契数列
 * @author liuzhen
 * @version 1.0.0 2021/8/20 20:35
 */
public class Fibonacci {

    /**
     * 思路：
     * DP: 自底向上
     * 1. F(n) = F(n-1) + F(n-2)
     * 2. a[0]=0,a[1]=1;
     *    for(int i=2;i<=n;++i) {
     *      a[i]=a[i-1]+a[i-2];
     *    }
     */

    /**
     * db
     * @author liuzhen
     * @date 2021/8/20 22:58
     * @param n
     * @return int
     */
    public static int fib(int n) {
        if (n <= 0) {
            return 0;
        }
        if (n == 1) {
            return 1;
        }

        return fib(n - 1) + fib(n - 2);
    }

    /**
     * 递归优化后：增加缓存
     * @author liuzhen
     * @date 2021/8/20 22:57
     * @param n
     * @param memo
     * @return int
     */
    public static int fib2(int n, int[] memo) {
        if (n <= 1) {
            return n;
        }

        if (memo[n] == 0) {
            memo[n] = fib2(n - 1, memo) + fib2(n - 2, memo);
        }
        return memo[n];
    }

    /**
     * FIb DP
     * @author liuzhen
     * @date 2021/8/21 22:15
     * @param n
     * @return int
     */
    public static int fibDP(int n) {
        int f1 = 1;
        int f2 = 1;

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
        int n = 10;
        System.out.println(fib(n));

        System.out.println("----------------------------->");

        int[] memo = new int[n + 1];
        System.out.println(fib2(n, memo));

        System.out.println("----------------------------->");
        System.out.println(fibDP(n));
    }

}
