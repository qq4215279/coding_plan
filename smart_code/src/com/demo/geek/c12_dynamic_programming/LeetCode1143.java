package com.demo.geek.c12_dynamic_programming;

import java.util.*;

/**
 * LeetCode1143
 * 最长公共子序列（LCS: longest-common-subsequence）
 * @author liuzhen
 * @version 1.0.0 2021/8/20 21:01
 */
public class LeetCode1143 {

    /**
     * 给定两个字符串 text1 和 text2，返回这两个字符串的最长公共子序列的长度。如果不存在公共子序列 ，返回 0 。
     * 一个字符串的子序列 是指这样一个新的字符串：它是由原字符串在不改变字符的相对顺序的情况下删除某些字符（也可以不删除任何字符）后组成的新字符串。
     *
     * 例如，"ace" 是 "abcde" 的子序列，但 "aec" 不是 "abcde" 的子序列。
     * 两个字符串的公共子序列 是这两个字符串所共同拥有的子序列。
     *
     * 示例 1：
     * 输入：text1 = "abcde", text2 = "ace"
     * 输出：3
     * 解释：最长公共子序列是 "ace" ，它的长度为 3 。
     *
     * 示例 2：
     * 输入：text1 = "abc", text2 = "abc"
     * 输出：3
     * 解释：最长公共子序列是 "abc" ，它的长度为 3 。
     *
     * 示例 3：
     * 输入：text1 = "abc", text2 = "def"
     * 输出：0
     * 解释：两个字符串没有公共子序列，返回 0 。
     *
     * 提示：
     * 1 <= text1.length, text2.length <= 1000
     * text1 和 text2 仅由小写英文字符组成。
     *
     * 思路:
     * 一 暴力法：先在一个字符序列中，找出它所以有的子序列（可取可不取，跟生成括号解法一样），再根据生成所有序列，去字符序列2中看有没有一样的,，时间复杂度：O(n^2)
     * 二
     *  1. s1 = ""
     *     s2 = 任意字符串
     *  2. s1 = "a"
     *     s2 = 任意
     *  3. s1 = "......a"
     *     s2 =   "....a"
     *
     *        →  →   →
     *        a  b   a   z   d   c
     * ↓  b   0  1   1   1   1   1
     * ↓  a   1  1   2   2   2   2
     * ↓  c   1  1   2   2   2   2
     * ↓  b   1  2   2   2   2   3
     * ↓  a   1  2   3   3   3   3
     * ↓  d   1  2   3   3   4   4
     *  相当于找最右下角的那个值，有点类似leetCode062从左上角到右下角
     *
     *  DP方程：(longest-common-subsequence)
     *  - 最后一个字符不相同：if(s1[n-1] != s2[m-1]) { LCS[s1, s2] = Math.Max(LCS[s1-1, s2], LCS[s1, s2-1]) }
     *  - 最后一个字符相同：  if(s1[n-1]] == s2[m-1]) { LCS[s1, s2] = LCS[s1-1, s2-1] + 1 }
     *
     */

    /**
     * DP方程
     * @author liuzhen
     * @date 2021/8/20 21:14
     * @param text1
     * @param text2
     * @return int
     */
    public static int longestCommonSubsequence(String text1, String text2) {
        int m = text1.length();
        int n = text2.length();

        int[][] dp = new int[m + 1][n + 1];

        for (int i = 1; i < m + 1; i++) {
            for (int j = 1; j < n + 1; j++) {
                // 最后一个字符相同
                if (text1.charAt(i - 1) == text2.charAt(j - 1)) {
                    // dp[i][j]个数为dp[i - 1][j - 1] + 1,因为当text1[i] === text2[j]时，dp[i][j]就为text1[i - 1], text2[j - 1]的最长子序列加1。
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else { // 不相同
                    // text1[i - 1], text2[j] 或 text1[i], text2[j - 1] 两个里的最大值即可
                    dp[i][j] = Math.max(dp[i][j - 1], dp[i - 1][j]);
                }
            }
        }
        return dp[m][n];
    }

    /**
     * 递归 + 记忆化搜索
     * @author liuzhen
     * @date 2021/8/21 21:40
     * @param text1
     * @param text2
     * @return int
     */
    public static int longestCommonSubsequence2(String text1, String text2) {
        char[] t1Chars = text1.toCharArray();
        char[] t2Chars = text2.toCharArray();
        HashMap<String, Integer> cache = new HashMap<>();
        return process(t1Chars, t2Chars, t1Chars.length, t2Chars.length, cache);
    }

    private static int process(char[] t1Chars, char[] t2Chars, int x, int y, HashMap<String, Integer> cache) {
        String key = x + "_" + y;
        if (cache.containsKey(key)) {
            return cache.get(key);
        }
        if (x == 0 || y == 0) {
            cache.put(key, 0);
            return 0;
        }

        int result = 0;
        // 最后一个字符都相同
        if (t1Chars[x - 1] == t2Chars[y - 1]) {
            // 最长子串 就比较前一个字符 + 1
            result = process(t1Chars, t2Chars, x - 1, y - 1, cache) + 1;
        } else {
            result = Math.max(process(t1Chars, t2Chars, x - 1, y, cache), process(t1Chars, t2Chars, x, y - 1, cache));
        }
        cache.put(key, result);

        return result;
    }

    /**
     * 递归
     * @author liuzhen
     * @date 2021/8/21 18:03
     * @param text1
     * @param text2
     * @return int
     */
    public static int longestCommonSubsequence3(String text1, String text2) {
        List<String> list = generateAllSequence(text1);

        int result = 0;
        for (String str : list) {
            int index = 0;
            char[] chars1 = str.toCharArray();
            char[] chars2 = text2.toCharArray();
            for (int i = 0; i < chars2.length; i++) {
                if (chars1.length > 0 && chars1[index] == chars2[i]) {
                    index++;
                    if (index == str.length()) {
                        break;
                    }
                }
            }

            if (index == str.length()) {
                result = result > index ? result : index;
            }
        }

        return result;
    }

    /**
     * TODO 递归得优化！memo的使用？？测试用不能通过，超出内存限制，因为重复递归太多了！！
     * @author liuzhen
     * @date 2021/8/21 21:51
     * @param str
     * @return java.util.List<java.lang.String>
     */
    public static List<String> generateAllSequence(String str) {
        if (str == null || str.length() == 0) {
            return Collections.emptyList();
        }

        List<String> list = new ArrayList<>();
        recursion(str.toCharArray(), list, "",0, new String[str.length()]);

        return list;
    }

    private static String recursion(char[] chars, List<String> list, String s, int level, String[] memo) {
        if (level == chars.length) {
            list.add(s);
            return s;
        }

        // TODO memo的使用？？？

        memo[level] = recursion(chars, list, s, level + 1, memo);
        memo[level] = recursion(chars, list, s + chars[level], level + 1, memo);

        return memo[level];
    }

    public static void main(String[] args) {
        String text1 = "abcba"; // a ab ac aa b bc
        String text2 = "abcbcba"; //

        System.out.println(longestCommonSubsequence(text1, text2));

        System.out.println(longestCommonSubsequence2(text1, text2));

        System.out.println("-------------->");

        // 递归
        System.out.println("递归：" + generateAllSequence(text1));
        // 暴力解
        System.out.println(longestCommonSubsequence3(text1, text2));

//        String text3 = "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa";
//        String text4 = "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa";
//        System.out.println(longestCommonSubsequence3(text3, text4));
    }

}
