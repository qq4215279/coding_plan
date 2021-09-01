package com.demo.geek.c20_string.basic;

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
     * DP
     * 1. 嵌套循环，枚举 i，j（起点终点），判断该子串是否回文
     * 2. 中间向两边扩张法
     * 3. DP[i][j]
     * @author liuzhen
     * @date 2021/9/1 20:54
     * @param s
     * @return java.lang.String
     */
    public String longestPalindrome(String s) {
        int n = s.length();
        String res = "";
        boolean[][] dp = new boolean[n][n];
        for (int i = n - 1; i >= 0; i--) {
            for (int j = i; j < n; j++) {
                dp[i][j] = s.charAt(i) == s.charAt(j) && (j - i < 2 || dp[i + 1][j - 1]);
                if (dp[i][j] && j - i + 1 > res.length()) {
                    res = s.substring(i, j + 1);
                }
            }
        }

        return res;
    }

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

    private void extendPalindrom(String s, int j, int k) {
        while (j >= 0 && k < s.length() && s.charAt(j) == s.charAt(k)) {
            j--;
            k++;
        }

        if (maxLen < k - j - 1) {
            lo = j + 1;
            maxLen = k - j - 1;
        }
    }


}
