package com.demo.geek.c20_string.match;

/**
 * LeetCode115
 * 不同的子序列
 * @author liuzhen
 * @version 1.0.0 2021/9/1 19:25
 */
public class LeetCode115 {

    /**
     * 给定一个字符串 s 和一个字符串 t ，计算在 s 的子序列中 t 出现的个数。
     * 字符串的一个 子序列 是指，通过删除一些（也可以不删除）字符且不干扰剩余字符相对位置所组成的新字符串。（例如，"ACE" 是 "ABCDE" 的一个子序列，而 "AEC" 不是）
     * 题目数据保证答案符合 32 位带符号整数范围。
     *
     * 示例 1：
     * 输入：s = "rabbbit", t = "rabbit"
     * 输出：3
     * 解释：
     * 如下图所示, 有 3 种可以从 s 中得到 "rabbit" 的方案。
     * rabbbit
     * rabbbit
     * rabbbit
     *
     * 示例 2：
     * 输入：s = "babgbag", t = "bag"
     * 输出：5
     * 解释：
     * 如下图所示, 有 5 种可以从 s 中得到 "bag" 的方案。
     * babgbag
     * babgbag
     * babgbag
     * babgbag
     * babgbag
     *
     * 提示：
     * 0 <= s.length, t.length <= 1000
     * s 和 t 由英文字母组成
     */

    // TODO 未通过 ？？？
    public int numDistinct(String s, String t) {
        int[][] dp = new int[t.length() + 1][s.length() + 1];
        for (int i = 0; i < s.length() + 1; i++) {
            dp[0][i] = 1;
        }

        for (int i = 1; i < t.length() + 1; i++) {
            for (int j = i; j < s.length(); j++) {
                if (t.charAt(i - 1) == s.charAt(j - 1)) {
                    dp[i][j] = dp[i][j - 1] + dp[i - 1][j - 1];
                } else {
                    dp[i][j] = dp[i][j - 1];
                }
            }
        }

        return dp[t.length()][s.length()];
    }

    /**
     * DP：（官方）
     * @author liuzhen
     * @date 2021/9/5 15:41
     * @param s
     * @param t
     * @return int
     */
    public static int numDistinct2(String s, String t) {
        int m = s.length(), n = t.length();
        if (m < n) {
            return 0;
        }
        int[][] dp = new int[m + 1][n + 1];
        for (int i = 0; i <= m; i++) {
            dp[i][n] = 1;
        }
        for (int i = m - 1; i >= 0; i--) {
            char sChar = s.charAt(i);
            for (int j = n - 1; j >= 0; j--) {
                char tChar = t.charAt(j);
                if (sChar == tChar) {
                    dp[i][j] = dp[i + 1][j + 1] + dp[i + 1][j];
                } else {
                    dp[i][j] = dp[i + 1][j];
                }
            }
        }
        return dp[0][0];
    }

    public static void main(String[] args) {
//        String s = "rabbbit", t = "rabbit";
        String s = "babgbag", t = "bag";
        System.out.println(numDistinct2(s, t));

    }

}
