package com.mumu.geek.c12_dynamic_programming;

/**
 * LeetCode122
 * 买卖股票的最佳时机2
 * @author liuzhen
 * @version 1.0.0 2021/8/18 21:10
 */
public class LeetCode122 {

    /**
     * 给定一个数组 prices ，其中 prices[i] 是一支给定股票第 i 天的价格。
     * 设计一个算法来计算你所能获取的最大利润。你可以尽可能地完成更多的交易（多次买卖一支股票）。
     * 注意：你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。
     *
     * 示例 1:
     * 输入: prices = [7,1,5,3,6,4]
     * 输出: 7
     * 解释: 在第 2 天（股票价格 = 1）的时候买入，在第 3 天（股票价格 = 5）的时候卖出, 这笔交易所能获得利润 = 5-1 = 4 。
     *      随后，在第 4 天（股票价格 = 3）的时候买入，在第 5 天（股票价格 = 6）的时候卖出, 这笔交易所能获得利润 = 6-3 = 3 。
     *
     * 示例 2:
     * 输入: prices = [1,2,3,4,5]
     * 输出: 4
     * 解释: 在第 1 天（股票价格 = 1）的时候买入，在第 5 天 （股票价格 = 5）的时候卖出, 这笔交易所能获得利润 = 5-1 = 4 。
     *      注意你不能在第 1 天和第 2 天接连购买股票，之后再将它们卖出。因为这样属于同时参与了多笔交易，你必须在再次购买前出售掉之前的股票。
     *
     * 示例 3:
     * 输入: prices = [7,6,4,3,1]
     * 输出: 0
     * 解释: 在这种情况下, 没有交易完成, 所以最大利润为 0。
     *
     * 提示：
     * 1 <= prices.length <= 3 * 104
     * 0 <= prices[i] <= 104
     */

    /**
     * DP方程
     * 时间复杂度：O(n)
     * 空间复杂度：O(n)
     * @author liuzhen
     * @date 2022/3/29 17:19
     * @param prices
     * @return int
     */
    public static int maxProfitDP(int[] prices) {
        int len = prices.length;
        // 特殊判断
        if (len < 2) {
            return 0;
        }

        int[][] dp = new int[len][2];

        // dp[i][0] 下标为 i 这天结束的时候，不持股，手上拥有的现金数
        // dp[i][1] 下标为 i 这天结束的时候，持股，手上拥有的现金数

        // 初始化：不持股显然为 0，持股就需要减去第 1 天（下标为 0）的股价
        dp[0][0] = 0;
        dp[0][1] = -prices[0];

        // 从第 2 天开始遍历
        for (int i = 1; i < len; i++) {
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] + prices[i]);
            // 和 LeetCode121的区别仅下面一行！！！
            dp[i][1] = Math.max(dp[i - 1][1], dp[i - 1][0] - prices[i]);
        }

        return dp[len - 1][0];
    }

    /**
     * 贪心
     * 时间复杂度：O(n)，其中 nn 为数组的长度。我们只需要遍历一次数组即可。
     * 空间复杂度：O(1)。只需要常数空间存放若干变量。
     * @author liuzhen
     * @date 2022/3/29 17:18
     * @param prices
     * @return int
     */
    public int maxProfitByGreedy(int[] prices) {
        int maxProfit = 0;
        int len = prices.length;

        for (int i = 1; i < len; i++) {
            maxProfit += Math.max(0, prices[i] - prices[i - 1]);
        }

        return maxProfit;
    }


    public static void main(String[] args) {
        int[] prices = {7, 1, 5, 3, 6, 4};

        int maxProfit = maxProfitDP(prices);

        System.out.println(maxProfit);
    }

}
