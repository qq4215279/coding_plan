package com.demo.geek.c20_string.basic;

/**
 * LeetCode541
 * 翻转字符串2
 * @author liuzhen
 * @version 1.0.0 2021/8/30 22:48
 */
public class LeetCode541 {

    /**
     * 给定一个字符串 s 和一个整数 k，从字符串开头算起，每 2k 个字符反转前 k 个字符。
     * 如果剩余字符少于 k 个，则将剩余字符全部反转。
     * 如果剩余字符小于 2k 但大于或等于 k 个，则反转前 k 个字符，其余字符保持原样。
     *  
     * 示例 1：
     * 输入：s = "abcdefg", k = 2
     * 输出："bacdfeg"
     *
     * 示例 2：
     * 输入：s = "abcd", k = 2
     * 输出："bacd"
     *
     * 提示：
     * 1 <= s.length <= 104
     * s 仅由小写英文组成
     */

    /**
     *
     * @author liuzhen
     * @date 2021/9/2 16:11
     * @param s
     * @param k
     * @return java.lang.String
     */
    public static String reverseStr(String s, int k) {
        StringBuilder sb = new StringBuilder();

        int start = 0;
        int end = -1;
        boolean isReverse = true;

        int length = s.length();
        while (start < length) {
            end = Math.min(end + k, length - 1);

            // 反转
            if (isReverse) {
                for (int i = end; i >= start; i--) {
                    sb.append(s.charAt(i));
                }
            } else {
                for (int i = start; i <= end; i++) {
                    sb.append(s.charAt(i));
                }
            }

            isReverse = isReverse ? false : true;
            start = end + 1;
        }

        return sb.toString();
    }

    /**
     * 官方 时间复杂度：O(n)  空间复杂度：O(1)
     * @author liuzhen
     * @date 2021/9/2 16:06
     * @param s
     * @param k
     * @return java.lang.String
     */
    public static String reverseStr2(String s, int k) {
        int n = s.length();
        char[] arr = s.toCharArray();
        for (int i = 0; i < n; i += 2 * k) {
            reverse(arr, i, Math.min(i + k, n) - 1);
        }
        return new String(arr);
    }

    private static void reverse(char[] arr, int left, int right) {
        while (left < right) {
            char temp = arr[left];
            arr[left] = arr[right];
            arr[right] = temp;
            left++;
            right--;
        }
    }

    public static void main(String[] args) {
        String s = "abcdefg";
        System.out.println(reverseStr(s, 2));
    }

}
