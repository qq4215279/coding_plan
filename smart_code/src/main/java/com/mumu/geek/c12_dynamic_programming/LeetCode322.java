package com.mumu.geek.c12_dynamic_programming;

/**
 * LeetCode322
 * 零钱兑换
 * @author liuzhen
 * @version 1.0.0 2021/8/18 21:03
 */
public class LeetCode322 {

    /**
     * 给你一个整数数组 coins ，表示不同面额的硬币；以及一个整数 amount ，表示总金额。
     * 计算并返回可以凑成总金额所需的 最少的硬币个数 。如果没有任何一种硬币组合能组成总金额，返回 -1 。
     * 你可以认为每种硬币的数量是无限的。
     *
     * 示例 1：
     * 输入：coins = [1, 2, 5], amount = 11
     * 输出：3
     * 解释：11 = 5 + 5 + 1
     *
     * 示例 2：
     * 输入：coins = [2], amount = 3
     * 输出：-1
     *
     * 示例 3：
     * 输入：coins = [1], amount = 0
     * 输出：0
     *
     * 示例 4：
     * 输入：coins = [1], amount = 1
     * 输出：1
     *
     * 示例 5：
     * 输入：coins = [1], amount = 2
     * 输出：2
     *  
     * 提示：
     * 1 <= coins.length <= 12
     * 1 <= coins[i] <= 231 - 1
     * 0 <= amount <= 104
     *
     * 思路:
     * 1. 暴力：递归，指数级别
     * 2. BFS：如图零钱兑换递归状态树，层次遍历，当当前层结果为0时，层数既为结果
     * 3. DP：
     *     a. subProblems
     *     b. DP array: f(n) = min{f(n-k), for k in [1,2,5]}) + 1       => 这里的+1不理解？？？
     *     c. DP方程
     *
     */

    /**
     * 递归写法
     * @author liuzhen
     * @date 2021/8/19 23:02
     * @param coins
     * @param amount
     * @return int
     */
    public static int coinChange(int[] coins, int amount) {
        if (amount < 1) {
            return 0;
        }

        return helper(coins, amount, new int[amount]);
    }

    private static int helper(int[] coins, int rem, int[] count) {
        // not valid
        if (rem < 0) {
            return -1;
        }
        // completed
        if (rem == 0) {
            return 0;
        }
        // already computed, so reuse
        if (count[rem - 1] != 0) {
            return count[rem - 1];
        }

        int min = Integer.MAX_VALUE;
        for (int coin : coins) {
            int res = helper(coins, rem - coin, count);
            if (res >= 0 && res < min) {
                min = 1 + res;
            }
        }

        count[rem - 1] = (min == Integer.MAX_VALUE) ? -1 : min;
        return count[rem - 1];
    }

    public static int coinChange2(int[] coins, int amount) {
        if (amount < 1) {
            return 0;
        }

        int[] dp = new int[amount + 1];
        int sum = 0;

        while (++sum <= amount) {
            int min = -1;
            for (int coin : coins) {
                if (sum >= coin && dp[sum - coin] != -1) {
                    int temp = dp[sum - coin] + 1;
                    min = min < 0 ? temp : (temp < min ? temp : min);
                }
            }
            dp[sum] = min;
        }
        return dp[amount];
    }

    public static void main(String[] args) {
        int[] coins = {1, 2, 5};
        System.out.println(coinChange(coins, 11));
        System.out.println(coinChange2(coins, 11));
    }

}
