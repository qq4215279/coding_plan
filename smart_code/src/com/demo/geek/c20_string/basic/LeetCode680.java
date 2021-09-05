package com.demo.geek.c20_string.basic;

/**
 * LeetCode680
 * 验证回文字符串2
 * @author liuzhen
 * @version 1.0.0 2021/8/30 22:58
 */
public class LeetCode680 {

    /**
     * 给定一个非空字符串 s，最多删除一个字符。判断是否能成为回文字符串。
     *
     * 示例 1:
     * 输入: s = "aba"
     * 输出: true
     *
     * 示例 2:
     * 输入: s = "abca"
     * 输出: true
     * 解释: 你可以删除c字符。
     *
     * 示例 3:
     * 输入: s = "abc"
     * 输出: false
     *  
     * 提示:
     * 1 <= s.length <= 105
     * s 由小写英文字母组成
     */

    /**
     * 时间复杂度：O(n)   空间复杂度：O(1)
     * @author liuzhen
     * @date 2021/9/3 11:13
     * @param s
     * @return boolean
     */
    public static boolean validPalindrome(String s) {
        int front = 0;
        int end = s.length() - 1;
        // < 符号，中间一个字符不影响回文与否
        while (front < end) {
            if (s.charAt(front) != s.charAt(end)) {
                return validPalindromeHelper(s, front + 1, end) || validPalindromeHelper(s, front, end - 1);
            }
            front++;
            end--;
        }
        return true;
    }

    private static boolean validPalindromeHelper(String s, int front, int end) {
        while (front < end) {
            if (s.charAt(front) != s.charAt(end)) {
                return false;
            }
            front++;
            end--;
        }
        return true;
    }

}
