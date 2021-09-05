package com.demo.geek.c20_string.match;

import java.util.Stack;

/**
 * LeetCode032
 * 最长有效括号
 * @author liuzhen
 * @version 1.0.0 2021/9/1 19:24
 */
public class LeetCode032 {

    /**
     * 给你一个只包含 '(' 和 ')' 的字符串，找出最长有效（格式正确且连续）括号子串的长度。
     *
     * 示例 1：
     * 输入：s = "(()"
     * 输出：2
     * 解释：最长有效括号子串是 "()"
     *
     * 示例 2：
     *
     * 输入：s = ")()())"
     * 输出：4
     * 解释：最长有效括号子串是 "()()"
     *
     * 示例 3：
     * 输入：s = ""
     * 输出：0
     *
     * 提示：
     * 0 <= s.length <= 3 * 104
     * s[i] 为 '(' 或 ')'
     */

    /**
     * 通过栈（官方写法） 时间复杂度为 O(n)
     * @author liuzhen
     * @date 2021/9/5 11:13
     * @param s
     * @return int
     */
    public static int longestValidParentheses(String s) {
        int maxans = 0;
        Stack<Integer> stack = new Stack<>();
        stack.add(-1);
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                stack.add(i);
            } else {
                /*这里pop()函数的巧妙之处：
                    1. 如果此时弹出的是左括号：更新有效括号开始索引start
                    2. 如果此时弹出的是右括号：再将其进栈，1是为了避免空指针，2是达到更新开始索引start的位置向右移动
                */
                stack.pop();
                // 将当前右括号进栈，达到有效括号的开始索引start
                if (stack.isEmpty()) {
                    stack.add(i);
                } else {
                    maxans = Math.max(maxans, i - stack.peek());
                }
            }
        }
        return maxans;
    }

    /**
     * 通过栈  时间复杂度为 O(n)
     * @author liuzhen
     * @date 2021/9/4 18:43
     * @param s
     * @return int
     */
    public static int longestValidParentheses2(String s) {
        Stack<Integer> stack = new Stack<>();
        int ans = 0;
        for (int i = 0, start = 0; i < s.length(); i++) {
            // 1. 遇到左括号 进栈
            if (s.charAt(i) == '(') {
                stack.add(i);
            } else { // 2. 遇到右括号
                // 此时栈为空 更新左端点为栈顶下一个元素，即   start+1
                if (stack.isEmpty()) {
                    start = i + 1;
                } else { // 此时栈不为空， ..
                    // 弹出栈顶元素
                    stack.pop();
                    // 得先判断栈是否为空！！
                    if (stack.isEmpty()) {
                        ans = Math.max(ans, i - start + 1);
                    } else {
                        ans = Math.max(ans, i - stack.peek());
                    }
                }
            }
        }

        return ans;
    }

    /**
     * dp方程： todo again
     * @author liuzhen
     * @date 2021/9/5 11:48
     * @param s
     * @return int
     */
    public int longestValidParentheses3(String s) {
        int maxans = 0;
        int[] dp = new int[s.length()];
        for (int i = 1; i < s.length(); i++) {
            if (s.charAt(i) == ')') {
                if (s.charAt(i - 1) == '(') {
                    dp[i] = (i >= 2 ? dp[i - 2] : 0) + 2;
                } else if (i - dp[i - 1] > 0 && s.charAt(i - dp[i - 1] - 1) == '(') {
                    dp[i] = dp[i - 1] + ((i - dp[i - 1]) >= 2 ? dp[i - dp[i - 1] - 2] : 0) + 2;
                }
                maxans = Math.max(maxans, dp[i]);
            }
        }
        return maxans;
    }

    public static void main(String[] args) {
//        String s = ")()())";
//        String s = "(((()))))";
        String s = ")()())";
        System.out.println(longestValidParentheses2(s));
    }

}
