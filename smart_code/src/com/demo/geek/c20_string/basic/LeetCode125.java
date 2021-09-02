package com.demo.geek.c20_string.basic;

/**
 * LeetCode125
 * 验证回文串
 * @author liuzhen
 * @version 1.0.0 2021/8/30 22:57
 */
public class LeetCode125 {

    /**
     * 给定一个字符串，验证它是否是回文串，只考虑字母和数字字符，可以忽略字母的大小写。
     * 说明：本题中，我们将空字符串定义为有效的回文串。
     *
     * 示例 1:
     * 输入: "A man, a plan, a canal: Panama"
     * 输出: true
     * 解释："amanaplanacanalpanama" 是回文串
     *
     * 示例 2:
     * 输入: "race a car"
     * 输出: false
     * 解释："raceacar" 不是回文串
     *
     * 提示：
     * 1 <= s.length <= 2 * 105
     * 字符串 s 由 ASCII 字符组成
     */

    // TODO
    public static boolean isPalindrome(String s) {
        char[] chars = s.trim().toLowerCase().toCharArray();
        int left = 0;
        int right = chars.length - 1;

        while (left <= right) {
            if (left < chars.length && (!Character.isLetterOrDigit(chars[left]) || chars[left] == 32)) {
                left++;
            }
            if (right > left && (!Character.isLetterOrDigit(chars[right]) || chars[left] == 32)) {
                right--;
            }

            if (chars[left] != chars[right]) {
                return false;
            }

            left++;
            right--;
        }

        return true;
    }

    public static void main(String[] args) {
        String s = "A man, a plan, a canal: Panama";
        System.out.println(isPalindrome(s));
    }

}
