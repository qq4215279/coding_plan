package com.demo.geek.c12_dynamic_programming;

/**
 * LeetCode063
 * 不同路径2
 * @author liuzhen
 * @version 1.0.0 2021/8/18 20:53
 */
public class LeetCode063 {

    /**
     * 一个机器人位于一个 m x n 网格的左上角 （起始点在下图中标记为“Start” ）。
     * 机器人每次只能向下或者向右移动一步。机器人试图达到网格的右下角（在下图中标记为“Finish”）。
     * 现在考虑网格中有障碍物。那么从左上角到右下角将会有多少条不同的路径？
     *
     * 网格中的障碍物和空位置分别用 1 和 0 来表示。
     *
     * 示例 1：
     * 输入：obstacleGrid = [[0,0,0],[0,1,0],[0,0,0]]
     * 输出：2
     * 解释：
     * 3x3 网格的正中间有一个障碍物。
     * 从左上角到右下角一共有 2 条不同的路径：
     * 1. 向右 -> 向右 -> 向下 -> 向下
     * 2. 向下 -> 向下 -> 向右 -> 向右
     *
     * 示例 2：
     * 输入：obstacleGrid = [[0,1],[0,0]]
     * 输出：1
     *  
     * 提示：
     * m == obstacleGrid.length
     * n == obstacleGrid[i].length
     * 1 <= m, n <= 100
     * obstacleGrid[i][j] 为 0 或 1
     *
     */


    /**
     * 递归
     * @author liuzhen
     * @date 2021/8/21 22:17
     * @param obstacleGrid
     * @return int
     */
    public static int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int[][] memo = new int[obstacleGrid.length][obstacleGrid[0].length];
        for (int i = 0; i < memo.length; i++) {
            for (int j = 0; j < memo[0].length; j++) {
                memo[i][j] = -1;
            }
        }

        return recursion(obstacleGrid, 0, 0, memo);
    }

    private static int recursion(int[][] obstacleGrid, int row, int col, int[][] memo) {
        int r = obstacleGrid.length;
        int c = obstacleGrid[0].length;
        // 终止条件
        if (row >= r || col >= c) {
            return 0;
        }
        // 有缓存，直接从缓存取
//        if (memo[row][col] != -1) {
//            return memo[row][col];
//        }

        // 遇到障碍物
        if (obstacleGrid[row][col] == 1) {
            return 0;
        }

        // 结果
        if (row == r - 1 && col == c - 1) {
            if (obstacleGrid[row][col] == 0) {
                return 1;
            } else {
                return 0;
            }
        }

        if (memo[row][col] == -1) {
            memo[row][col] = recursion(obstacleGrid, row + 1, col, memo) + recursion(obstacleGrid, row, col + 1, memo);
        }

        return memo[row][col];
    }

    /**
     * DP方程
     * @author liuzhen
     * @date 2021/8/21 17:11
     * @param obstacleGrid
     * @return int
     */
    public int uniquePathsWithObstacles2(int[][] obstacleGrid) {
        if (obstacleGrid == null || obstacleGrid.length == 0) {
            return 0;
        }

        // 定义 dp 数组并初始化第 1 行和第 1 列。
        int row = obstacleGrid.length;
        int col = obstacleGrid[0].length;
        int[][] dp = new int[row][col];
        // 初始化第一列结果
        for (int i = 0; i < row && obstacleGrid[i][0] == 0; i++) {
            dp[i][0] = 1;
        }
        // 初始化第一行结果
        for (int j = 0; j < col && obstacleGrid[0][j] == 0; j++) {
            dp[0][j] = 1;
        }

        // 根据状态转移方程 dp[i][j] = dp[i - 1][j] + dp[i][j - 1] 进行递推。
        for (int i = 1; i < row; i++) {
            for (int j = 1; j < col; j++) {
                if (obstacleGrid[i][j] == 0) {
                    dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
                }
            }
        }
        return dp[row - 1][col - 1];
    }

    public static void main(String[] args) {
        int[][] obstacleGrid = {{0, 0, 0}, {0, 1, 0}, {0, 0, 0}};
        System.out.println(uniquePathsWithObstacles(obstacleGrid));
    }

}
