package com.mumu.geek.c20_string.basic;

/**
 * LeetCode005
 * 最长文子串
 * @author liuzhen
 * @version 1.0.0 2021/8/30 22:59
 */
public class LeetCode005 {

    /**
     * 给你一个字符串 s，找到 s 中最长的回文子串。
     *
     * 示例 1：
     * 输入：s = "babad"
     * 输出："bab"
     * 解释："aba" 同样是符合题意的答案。
     *
     * 示例 2：
     * 输入：s = "cbbd"
     * 输出："bb"
     *
     * 示例 3：
     * 输入：s = "a"
     * 输出："a"
     *
     * 示例 4：
     * 输入：s = "ac"
     * 输出："a"
     *  
     * 提示：
     * 1 <= s.length <= 1000
     * s 仅由数字和英文字母（大写和/或小写）组成
     *
     * 思路：
     * 1. 暴力：O(n^3)
     * 2. 中间想两边扩张法 O(n^2)
     * 3. 动态规划
     * 首先定义 DP(i, j):
     * DP(i, j) =  true   s[i,j] 是回文子串
     *             false  s[i,j] 不是回文子串
     *  接下来： DP(i,j) = (DP(i+1, j-1) && s[i] == s[j])
     *
     *
     */


    /**
     * 暴力解法：嵌套循环，枚举 i，j（起点终点），判断该子串是否回文
     * @author liuzhen
     * @date 2021/9/1 20:54
     * @param s
     * @return java.lang.String
     */
    public String longestPalindrome(String s) {

        return "";
    }

    /**
     * 中间向两边扩张法：即枚回文子串的中心，同时向两边扩张
     * @author liuzhen
     * @date 2021/9/3 11:24
     * @param s
     * @return java.lang.String
     */
    private int lo, maxLen;
    public String longestPalindrome2(String s) {
        int len = s.length();
        if (len < 2) {
            return s;
        }

        for (int i = 0; i < len - 1; i++) {
            extendPalindrom(s, i, i); // odd length
            extendPalindrom(s, i, i + 1); // even length
        }
        return s.substring(lo, lo + maxLen);
    }

    private void extendPalindrom(String s, int left, int right) {
        while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
            left--;
            right++;
        }

        if (maxLen < right - left - 1) {
            lo = left + 1;
            maxLen = right - left - 1;
        }
    }

    /**
     * db方程解法：
     * @author liuzhen
     * @date 2021/9/1 20:54
     * @param s
     * @return java.lang.String
     */
    public String longestPalindrome3(String s) {
        int n = s.length();
        String res = "";
        // row指字符串起点位置索引，col指字符串终点位置索引
        boolean[][] dp = new boolean[n][n];

        // 起点中末尾开始，往前扩
        for (int i = n - 1; i >= 0; i--) {
            // 终点从i开始，往后扩
            for (int j = i; j < n; j++) {
                // dp[i][j] 表示i到j范围的字符串是否是回文串。 DP(i,j) = (s[i] == s[j]) && DP(i+1, j-1)
                // j - i < 2 : 表示字符串长度小于2的也是回文子串
                dp[i][j] = s.charAt(i) == s.charAt(j) && (j - i < 2 || dp[i + 1][j - 1]);

                // 是回文串，且比之前找出来的回文子串更长
                if (dp[i][j] && j - i + 1 > res.length()) {
                    res = s.substring(i, j + 1);
                }
            }
        }

        return res;
    }


}
