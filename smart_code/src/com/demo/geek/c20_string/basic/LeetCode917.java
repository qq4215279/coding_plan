package com.demo.geek.c20_string.basic;

import java.util.Stack;

/**
 * LeetCode917
 * 仅仅反转字母
 * @author liuzhen
 * @version 1.0.0 2021/8/30 22:52
 */
public class LeetCode917 {

    /**
     * 给定一个字符串 S，返回 “反转后的” 字符串，其中不是字母的字符都保留在原地，而所有字母的位置发生反转。
     *
     * 示例 1：
     * 输入："ab-cd"
     * 输出："dc-ba"
     *
     * 示例 2：
     * 输入："a-bC-dEf-ghIj"
     * 输出："j-Ih-gfE-dCba"
     *
     * 示例 3：
     * 输入："Test1ng-Leet=code-Q!"
     * 输出："Qedo1ct-eeLg=ntse-T!"
     *
     * 提示：
     * S.length <= 100
     * 33 <= S[i].ASCIIcode <= 122 
     * S 中不包含 \ or "
     */

    /**
     * 进栈
     * @author liuzhen
     * @date 2021/9/2 17:25
     * @param s
     * @return java.lang.String
     */
    public static String reverseOnlyLetters(String s) {
        Stack<Character> stack = new Stack();
        for (char c : s.toCharArray()) {
            // 字母 进栈
            if (Character.isLetter(c)) {
                stack.push(c);
            }
        }

        StringBuilder ans = new StringBuilder();
        for (char c : s.toCharArray()) {
            if (Character.isLetter(c)) {
                ans.append(stack.pop());
            } else {
                ans.append(c);
            }
        }

        return ans.toString();
    }

    /**
     * 双指针
     * @author liuzhen
     * @date 2021/9/2 17:29
     * @param s
     * @return java.lang.String
     */
    public static String reverseOnlyLetters2(String s) {
        char[] chars = s.toCharArray();
        int length = chars.length;
        int left = 0;
        int right = s.length() - 1;

        while (left <= right) {
            while (left < length && !Character.isLetter(chars[left])) {
                left++;
            }
            while (right >= left && !Character.isLetter(chars[right])) {
                right--;
            }

            if (left < right) {
                char temp = chars[left];
                chars[left] = chars[right];
                chars[right] = temp;

            }
            left++;
            right--;
        }

        return String.valueOf(chars);
    }

    public static void main(String[] args) {
        String str = "Test1ng-Leet=code-Q!";
        System.out.println(reverseOnlyLetters(str));
        System.out.println(reverseOnlyLetters2(str));
    }

}
