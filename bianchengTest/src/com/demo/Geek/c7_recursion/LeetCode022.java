package com.demo.Geek.c7_recursion;

import java.util.ArrayList;
import java.util.List;

public class LeetCode022 { // LeetCode 22

    /**
     * 括号生成
     * <p>
     * 数字 n 代表生成括号的对数，请你设计一个函数，用于能够生成所有可能的并且 有效的 括号组合。
     * <p>
     * 示例：
     * 输入：n = 3
     * 输出：[
     * "((()))",
     * "(()())",
     * "(())()",
     * "()(())",
     * "()()()"
     * ]
     */

    private ArrayList<String> result;

    public List<String> generateParenthesis(int n) {
        result = new ArrayList<>();
        _generate(0, 0, n, "");
        return result;
    }

    public void _generate(int left, int right, int n, String s) {
        if (left == n && right == n) {
            result.add(s);
            return;
        }

        if (left < n)
            _generate(left + 1, right, n, s + "(");
        if (right < left)
            _generate(left, right + 1, n, s + ")");

    }

    public static void main(String[] args) {
        List<String> list = new LeetCode022().generateParenthesis(3);
        list.forEach(s -> {
            System.out.println(s);
        });
        System.out.println("size:" + list.size());
    }

}
