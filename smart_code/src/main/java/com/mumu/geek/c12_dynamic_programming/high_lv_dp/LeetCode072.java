package com.mumu.geek.c12_dynamic_programming.high_lv_dp;

/**
 * LeetCode072
 * 编辑距离
 * @author liuzhen
 * @version 1.0.0 2021/8/18 21:27
 */
public class LeetCode072 { // 重点！！！

    /**
     * 给你两个单词 word1 和 word2，请你计算出将 word1 转换成 word2 所使用的最少操作数 。
     * 你可以对一个单词进行如下三种操作：
     * 插入一个字符
     * 删除一个字符
     * 替换一个字符
     *
     * 示例 1：
     * 输入：word1 = "horse", word2 = "ros"
     * 输出：3
     * 解释：
     * horse -> rorse (将 'h' 替换为 'r')
     * rorse -> rose (删除 'r')
     * rose -> ros (删除 'e')
     *
     * 示例 2：
     * 输入：word1 = "intention", word2 = "execution"
     * 输出：5
     * 解释：
     * intention -> inention (删除 't')
     * inention -> enention (将 'i' 替换为 'e')
     * enention -> exention (将 'n' 替换为 'x')
     * exention -> exection (将 'n' 替换为 'c')
     * exection -> execution (插入 'u')
     *  
     * 提示：
     * 0 <= word1.length, word2.length <= 500
     * word1 和 word2 由小写英文字母组成
     */

    /**
     * DP方程：自底向上  时间复杂度：O(mn)，其中 mm 为 word1 的长度，nn 为 word2 的长度。空间复杂度：O(mn)，我们需要大小为 O(mn) 的 DD 数组来记录状态值。
     * 思路：
     * dp[i][j] 代表 word1 到 i 位置转换成 word2 到 j 位置需要最少步数
     * 所以：
     *      当 word1[i] == word2[j]，dp[i][j] = dp[i-1][j-1]；
     *      当 word1[i] != word2[j]，dp[i][j] = min(dp[i-1][j-1], dp[i-1][j], dp[i][j-1]) + 1
     *      其中，dp[i-1][j-1] 表示替换操作，dp[i-1][j] 表示删除操作，dp[i][j-1] 表示插入操作。
     *  注意，针对第一行，第一列要单独考虑，我们引入 '' 下图所示：
     *      ''  r   o   s
     *  ''  0   1   2   3
     *  h   1
     *  o   2
     *  r   3
     *  s   4
     *  e   5
     *  第一行，是 word1 为空变成 word2 最少步数，就是插入操作
     *  第一列，是 word2 为空，需要的最少步数，就是删除操作
     * @author liuzhen
     * @date 2021/9/3 22:46
     * @param word1
     * @param word2
     * @return int
     */
    public static int minDistance(String word1, String word2) {
        int n1 = word1.length();
        int n2 = word2.length();
        int[][] dp = new int[n1 + 1][n2 + 1];
        // 第一行
        for (int j = 1; j <= n2; j++) {
            dp[0][j] = dp[0][j - 1] + 1;
        }
        // 第一列
        for (int i = 1; i <= n1; i++) {
            dp[i][0] = dp[i - 1][0] + 1;
        }

        for (int i = 1; i <= n1; i++) {
            for (int j = 1; j <= n2; j++) {
                // 因为dp数组有效位从1开始
                // 所以当前遍历到的字符串的位置为i-1 | j-1
                if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    dp[i][j] = Math.min(Math.min(dp[i - 1][j - 1], dp[i][j - 1]), dp[i - 1][j]) + 1;
                }
            }
        }

        return dp[n1][n2];
    }

}
