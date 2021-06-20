/*
 * Copyright 2018-2020, 上海哈里奥科技有限公司
 * All Right Reserved.
 */

package com.demo.Geek.c4_stack_queue;

import java.util.HashMap;
import java.util.Stack;

public class Day01Demo01 {// LeetCode：T20

    /**
     * 有效的括号
     * 给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串，判断字符串是否有效。
     * <p>
     * 有效字符串需满足：
     * <p>
     * 左括号必须用相同类型的右括号闭合。
     * 左括号必须以正确的顺序闭合。
     * 注意空字符串可被认为是有效字符串。
     * <p>
     * 输入: "()"
     * 输出: true
     * <p>
     * 输入: "()[]{}"
     * 输出: true
     * <p>
     * 输入: "([)]"
     * 输出: false
     * <p>
     * 输入: "{[]}"       递归：recursion
     * 输出: true
     * <p>
     * 用栈来解决：如果一个问题，它具有最近相关性的话就用栈俩解决。
     * eg：最内层和最外层是一堆，类似于洋葱的结构，即对称性性。
     * (2)前来后到，即公平性。---用Queue。
     */

    public static boolean isValid01(String s) {// 暴力求解：不断用replace()匹配的括号替换成->""      O(n^2)
        char[] chars = s.toCharArray();
        if (chars == null) {
            return true;
        }

        HashMap<Character, Character> map = new HashMap<>();
        map.put(')', '(');
        map.put(']', '[');
        map.put('}', '{');

        for (int i = 1; i < chars.length; i++) {
            char c = chars[i];
            if (map.containsKey(c)) {
                int k = i - 1;
                while (k >= 0) {
                    char last = chars[k];
                    if (chars[k] == '#') {
                        k--;

                    } else if (map.get(c) == last) {
                        chars[k] = '#';
                        chars[i] = '#';
                        break;
                    } else {
                        return false;
                    }
                }
            }
        }

        for (int i = 0; i < chars.length; i++) {
            System.out.println(chars[i]);
            if (chars[i] != '#')
                return false;
        }
        return true;
    }

    public static boolean isValid02(String s) {// 通过栈：通过栈存左括号

        if (s == null) {
            return true;
        }

        Stack<Character> stack = new Stack<>();
        char[] chars = s.toCharArray();
        HashMap<Character, Character> map = new HashMap<>();
        map.put('(', ')');
        map.put('[', ']');
        map.put('{', '}');

        int i = 0;
        while (i < s.length()) {
            if (map.containsKey(chars[i])) {
                stack.push(chars[i]);
            } else {
                if (stack.isEmpty())
                    return false;
                if (map.get(stack.pop()) != chars[i])
                    return false;
            }
            i++;
        }

        return stack.isEmpty();
    }

    public static void main(String[] args) {
        String s = "{{[]}";
        System.out.println(isValid01(s));

        System.out.println("------------------------->");

        String s2 = "{(}";
        System.out.println(isValid02(s2));
    }

}



