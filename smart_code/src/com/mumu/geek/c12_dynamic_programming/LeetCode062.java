package com.mumu.geek.c12_dynamic_programming;

/**
 * LeetCode062
 * 不同路径
 * @author liuzhen
 * @version 1.0.0 2021/8/18 20:51
 */
public class LeetCode062 {
    /**
     * 一个机器人位于一个 m x n 网格的左上角 （起始点在下图中标记为 “Start” ）。
     * 机器人每次只能向下或者向右移动一步。机器人试图达到网格的右下角（在下图中标记为 “Finish” ）。
     * 问总共有多少条不同的路径？
     *
     * 示例 1：
     * 输入：m = 3, n = 7
     * 输出：28
     *
     * 示例 2：
     * 输入：m = 3, n = 2
     * 输出：3
     * 解释：
     * 从左上角开始，总共有 3 条路径可以到达右下角。
     * 1. 向右 -> 向下 -> 向下
     * 2. 向下 -> 向下 -> 向右
     * 3. 向下 -> 向右 -> 向下
     *
     * 示例 3：
     * 输入：m = 7, n = 3
     * 输出：28
     *
     * 示例 4：
     * 输入：m = 3, n = 3
     * 输出：6
     *
     * 提示：
     *
     * 1 <= m, n <= 100
     * 题目数据保证答案小于等于 2 * 109
     *
     * 思路：
     * 动态规划关键点：
     * 1. 最优子结构 opt[n] = best_of(opt[n-1], opt[n-2], ...)
     * 2. 储存中间状态：opt[i]
     * 3. 递推公式（美其名曰：状态转移方程或者DP方程）
     *    - Fib: opt[i] = opt[n-1] + opt[n-2]
     *    - 二维路径：opt[i,j] = opt[i+1][j] + opt[i][j+1] (且判断a[i,j]是否为空地)
     *
     * DP方程：opt[i,j] = opt[i+1][j] + opt[i][j+1]
     * 完整逻辑：
     *      if a[i,j] = '空地'：
     *          opt[i,j] = opt[i+1,j] + opt[i,j+1]
     *      else:
     *          opt[i,j] = 0
     */

    /**
     * DP方程(自底向上)：从终点（右下角）开始出发，往上找。每一个的结果都是：当前格的右边一格结果 + 当前格的下边一格结果的和。
     * 转变思路后，所以从左上角到右下角同样使用如上的DP公式 时间复杂度：O(m*n) 空间复杂度：O(m∗n)
     * @author liuzhen
     * @date 2021/8/20 21:02
     * @param row
     * @param col
     * @return int
     */
    public static int uniquePaths(int row, int col) {
        int[][] dp = new int[row][col];
        // 定义第1列的每一格都只能为1
        for (int i = 0; i < row; i++) {
            dp[i][0] = 1;
        }
        // 定义第1行的每一格都只能为1
        for (int i = 0; i < col; i++) {
            dp[0][i] = 1;
        }

        for (int i = 1; i < row; i++) {
            for (int j = 1; j < col; j++) {
                dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
            }
        }

        return dp[row - 1][col - 1];
    }

    /**
     * 递归解法
     * @author liuzhen
     * @date 2021/8/20 23:31
     * @param row
     * @param col
     * @return int
     */
    public static int uniquePaths2(int row, int col) {
        if (row <= 1 || col <= 1) {
            return 1;
        }
        return uniquePaths2(row - 1, col) + uniquePaths2(row, col - 1);
    }

    public static void main(String[] args) {
        System.out.println(uniquePaths(3, 7));
        System.out.println(uniquePaths2(3, 7));
    }
}
