package com.demo.geek.c8_divide_backtrack;

/**
 * LeetCode050
 * Pow(x,n)
 * @author liuzhen
 * @version 1.0.0 2021/8/15 12:49
 */
public class LeetCode050 {

    /**
     * 实现 pow(x, n) ，即计算 x 的 n 次幂函数（即，xn）。
     *
     * 示例 1：
     * 输入：x = 2.00000, n = 10
     * 输出：1024.00000
     *
     * 示例 2：
     * 输入：x = 2.10000, n = 3
     * 输出：9.26100
     * 示例 3：
     *
     * 输入：x = 2.00000, n = -2
     * 输出：0.25000
     * 解释：2-2 = 1/22 = 1/4 = 0.25
     *  
     * 提示：
     * -100.0 < x < 100.0
     * -231 <= n <= 231-1
     * -104 <= xn <= 104
     */

    /**
     * 暴力解
     * @author liuzhen
     * @date 2021/8/15 12:54
     * @param x
     * @param n
     * @return double
     */
    public double myPow01(double x, int n) {
        if (n < 0) {
            return 1.0 / myPow01(x, -n);
        }

        double res = 1.0;
        for (int i = n; i != 0 ; i /= 2) {
            if (i % 2 != 0) {}
            res *= x;
        }
        x *= x;
        return n < 0 ? 1 / res : res;
    }

    /**
     * 分治回溯
     * 思路：x^n --> 2^10: 2^5 -> (2^2) *2
     *  pow(x,n):
     *      subProblem: subProblem = pow(x, n / 2)
     *  merge:
     *      if n % 2 == 1 {
     *          // odd
     *          result = subResult * subResult * x;
     *      } else {
     *          // even
     *          result = subResult * subResult;
     *      }
     * @author liuzhen
     * @date 2021/8/15 12:58
     * @param x
     * @param n
     * @return double
     */
    public double myPow02(double x, int n) {
        long N = n;
        if (N < 0) {
            x = 1 / x;
            N = -N;
        }
        return fastPow(x, N);
    }

    private double fastPow(double x, long n) {
        if (n == 0) {
            return 1.0;
        }
        double half = fastPow(x, n / 2);
        if (n % 2 == 0) {
            return half * half;
        } else {
            return half * half * x;
        }
    }

    /**
     * 牛顿迭代法...
     * @author liuzhen
     * @date 2021/8/15 12:58
     * @param x
     * @param n
     * @return double
     */
    public double myPow3(double x, int n) {
        return 1.0;
    }

}

