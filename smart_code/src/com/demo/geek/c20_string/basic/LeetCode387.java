package com.demo.geek.c20_string.basic;

import java.util.HashMap;
import java.util.Map;

/**
 * LeetCode387
 * 字符串中的第一个唯一字符
 * @author liuzhen
 * @version 1.0.0 2021/8/30 22:39
 */
public class LeetCode387 {

    /**
     * 给定一个字符串，找到它的第一个不重复的字符，并返回它的索引。如果不存在，则返回 -1。
     *
     * 示例：
     * s = "leetcode"
     * 返回 0
     *
     * s = "loveleetcode"
     * 返回 2
     *
     * 提示：你可以假定该字符串只包含小写字母
     *
     * 思路：
     * 1. brute-force：i 枚举所有字符；j 枚举 找重复  O(n^2)
     * 2. map (hashmap O(111), treemap O(logN))     两种map都行
     * 3. hash table
     *
     */

    /**
     * 暴力法
     * @author liuzhen
     * @date 2021/9/2 14:22
     * @param s
     * @return int
     */
    public static int firstUniqCharByForce(String s) {

        return 1;
    }

    /**
     * hash表存储频数   时间复杂度：O(n)
     * @author liuzhen
     * @date 2021/9/2 14:17
     * @param s
     * @return int
     */
    public static int firstUniqChar(String s) {
        Map<Character, Integer> hm = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            hm.put(s.charAt(i), hm.getOrDefault(s.charAt(i), 0) + 1);
        }

        for (int i = 0; i < s.length(); i++) {
            if (hm.get(s.charAt(i)) == 1) {
                return i;
            }
        }

        return -1;
    }

    /**
     * 使用hash存储索引
     * @author liuzhen
     * @date 2021/9/2 14:22
     * @param s
     * @return int
     */
    public static int firstUniqChar2(String s) {
        Map<Character, Integer> position = new HashMap<>();
        int n = s.length();
        for (int i = 0; i < n; ++i) {
            char ch = s.charAt(i);
            if (position.containsKey(ch)) {
                position.put(ch, -1);
            } else {
                position.put(ch, i);
            }
        }
        int first = n;
        for (Map.Entry<Character, Integer> entry : position.entrySet()) {
            int pos = entry.getValue();
            if (pos != -1 && pos < first) {
                first = pos;
            }
        }
        if (first == n) {
            first = -1;
        }
        return first;
    }

    public static void main(String[] args) {
        String s = "loveleetcode";

        System.out.println(firstUniqChar(s));
    }

}
