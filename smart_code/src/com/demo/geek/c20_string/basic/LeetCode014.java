package com.demo.geek.c20_string.basic;

/**
 * LeetCode014
 * 最长公共前缀
 * @author liuzhen
 * @version 1.0.0 2021/8/30 22:42
 */
public class LeetCode014 {

    /**
     * 编写一个函数来查找字符串数组中的最长公共前缀。
     * 如果不存在公共前缀，返回空字符串 ""。
     *
     * 示例 1：
     * 输入：strs = ["flower","flow","flight"]
     * 输出："fl"
     *
     * 示例 2：
     * 输入：strs = ["dog","racecar","car"]
     * 输出：""
     * 解释：输入不存在公共前缀。
     *
     * 提示：
     * 1 <= strs.length <= 200
     * 0 <= strs[i].length <= 200
     * strs[i] 仅由小写英文字母组成
     *
     * 思路
     * 1. 纯暴力
     * 2. folower  flow  flight
     * 3. Trie
     *
     */

    public String longestCommonPrefix(String[] strs) {
        if (strs == null || strs.length == 0) {
            return "";
        }
        for (int i = 0; i < strs[0].length(); i++) {
            char c = strs[0].charAt(i);
            for (int j = 1; j < strs.length; j++) {
                if (i == strs[j].length() || strs[j].charAt(i) != c) {
                    return strs[0].substring(0, i);
                }
            }
        }
        return strs[0];
    }

}
