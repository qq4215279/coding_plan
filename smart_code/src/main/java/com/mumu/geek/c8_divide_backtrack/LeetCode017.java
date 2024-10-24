/*
 * Copyright 2020-2021, 木木996.
 * All Right Reserved.
 */

package com.mumu.geek.c8_divide_backtrack;

import java.util.*;

/**
 * LeetCode017
 * 电话号码的字母组合
 * @author liuzhen
 * @version 1.0.0 2021/8/15 17:07
 */
public class LeetCode017 {

    /**
     * 给定一个仅包含数字 2-9 的字符串，返回所有它能表示的字母组合。答案可以按任意顺序返回。
     * 给出数字到字母的映射如下（与电话按键相同）。注意 1 不对应任何字母。
     *
     * 示例 1：
     * 输入：digits = "23"
     * 输出：["ad","ae","af","bd","be","bf","cd","ce","cf"]
     *
     * 示例 2：
     * 输入：digits = ""
     * 输出：[]
     *
     * 示例 3：
     * 输入：digits = "2"
     * 输出：["a","b","c"]
     *  
     * 提示：
     * 0 <= digits.length <= 4
     * digits[i] 是范围 ['2', '9'] 的一个数字。
     */

    /**
     * 分治回溯
     * 思路：跟有效的括号思路一样，分层级，一层一层遍历
     * @author liuzhen
     * @date 2021/8/15 17:08
     * @param digits
     * @return java.util.List<java.lang.String>
     */
    public static List<String> letterCombinations(String digits) {
        if (digits == null || digits.length() == 0) {
            return new ArrayList<>();
        }

        Map<Character, String> map = new HashMap<>();
        map.put('2', "abc");
        map.put('3', "def");
        map.put('4', "ghi");
        map.put('5', "jkl");
        map.put('6', "mno");
        map.put('7', "pqrs");
        map.put('8', "tuv");
        map.put('9', "wxyz");

        List<String> res = new LinkedList<>();

        search("", digits, 0, res, map);

        return res;
    }

    private static void search(String s, String digits, int index, List<String> res, Map<Character, String> map) {
        // terminator
        if (index == digits.length()) {
            res.add(s);
            return;
        }

        // process
        String letters = map.get(digits.charAt(index));
        for (int j = 0; j < letters.length(); j++) {
            // drill down
            search(s + letters.charAt(j), digits, index + 1, res, map);
        }

    }

}
