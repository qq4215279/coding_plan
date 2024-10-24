package com.mumu.geek.c12_dynamic_programming;

/**
 * LeetCode121
 * 买卖股票的最佳时机
 * @author liuzhen
 * @version 1.0.0 2021/8/18 21:08
 */
public class LeetCode121 {

    /**
     * 给定一个数组 prices ，它的第 i 个元素 prices[i] 表示一支给定股票第 i 天的价格。
     * 你只能选择 某一天 买入这只股票，并选择在 未来的某一个不同的日子 卖出该股票。设计一个算法来计算你所能获取的最大利润。
     * 返回你可以从这笔交易中获取的最大利润。如果你不能获取任何利润，返回 0 。
     *
     * 示例 1：
     * 输入：[7,1,5,3,6,4]
     * 输出：5
     * 解释：在第 2 天（股票价格 = 1）的时候买入，在第 5 天（股票价格 = 6）的时候卖出，最大利润 = 6-1 = 5 。
     *      注意利润不能是 7-1 = 6, 因为卖出价格需要大于买入价格；同时，你不能在买入前卖出股票。
     *
     * 示例 2：
     * 输入：prices = [7,6,4,3,1]
     * 输出：0
     * 解释：在这种情况下, 没有交易完成, 所以最大利润为 0。
     *  
     * 提示：
     * 1 <= prices.length <= 105
     * 0 <= prices[i] <= 104
     */

    /**
     * self
     * 时间复杂度：O(n)，只需要遍历一次。
     * 空间复杂度：O(1)，只使用了常数个变量。
     * @author liuzhen
     * @date 2021/8/19 23:36
     * @param prices
     * @return int
     */
    public static int maxProfit(int[] prices) {
        int maxProfit = 0;
        int minBuyPrice = prices[0];

        for (int i = 1; i < prices.length; i++) {
            int curBuyPrice = prices[i];
            if (minBuyPrice > curBuyPrice) {
                minBuyPrice = curBuyPrice;
                continue;
            }

            maxProfit = Math.max(maxProfit, (curBuyPrice - minBuyPrice));

        }

        return maxProfit;
    }

    /**
     * DP方程
     * 时间复杂度：O(N)，遍历股价数组可以得到最优解；
     * 空间复杂度：O(N)，状态数组的长度为 NN。
     * @author liuzhen
     * @date 2022/3/29 16:05
     * @param prices
     * @return int
     */
    public static int maxProfitByDP(int[] prices) {
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
            dp[i][1] = Math.max(dp[i - 1][1], -prices[i]);
        }

        return dp[len - 1][0];
    }

    public static void main(String[] args) {
        int[] prices = {7, 1, 5, 3, 6, 4};
        // int[] prices = {7, 6, 4, 3, 1};
        int maxProfit = maxProfit(prices);
        System.out.println(maxProfit);

        int maxProfitByDP = maxProfitByDP(prices);
        System.out.println(maxProfitByDP);
    }

}
