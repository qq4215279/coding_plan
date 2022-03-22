package com.mumu.geek.c20_string.basic;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * LeetCode438 TODO practice again
 * 找到字符串中所有字母异位词
 * @author liuzhen
 * @version 1.0.0 2021/8/30 22:56
 */
public class LeetCode438 {

    /**
     * 给定两个字符串 s 和 p，找到 s 中所有 p 的 异位词的子串，返回这些子串的起始索引。不考虑答案输出的顺序。
     * 异位词 指字母相同，但排列不同的字符串。
     *
     * 示例 1:
     * 输入: s = "cbaebabacd", p = "abc"
     * 输出: [0,6]
     * 解释:
     * 起始索引等于 0 的子串是 "cba", 它是 "abc" 的异位词。
     * 起始索引等于 6 的子串是 "bac", 它是 "abc" 的异位词。
     *
     * 示例 2:
     * 输入: s = "abab", p = "ab"
     * 输出: [0,1,2]
     * 解释:
     * 起始索引等于 0 的子串是 "ab", 它是 "ab" 的异位词。
     * 起始索引等于 1 的子串是 "ba", 它是 "ab" 的异位词。
     * 起始索引等于 2 的子串是 "ab", 它是 "ab" 的异位词。
     *
     * 提示:
     * 1 <= s.length, p.length <= 3 * 104
     * s 和 p 仅包含小写字母
     */

    /**
     * 滑动窗口
     * @author liuzhen
     * @date 2021/9/2 18:09
     * @param s
     * @param p
     * @return java.util.List<java.lang.Integer>
     */
    public List<Integer> findAnagrams(String s, String p) {
        List<Integer> resultList = new ArrayList<>();

        // 计算字符串p中各元素的出现次数
        int[] pFreq = new int[26];
        for (int i = 0; i < p.length(); i++) {
            pFreq[p.charAt(i) - 'a']++;
        }

        // 窗口区间为[start,end]
        int start = 0, end = -1;
        while (start < s.length()) {
            // 还有剩余元素未考察，且窗口内字符串长度小于字符串p的长度
            // 则扩大窗口右侧边界
            if (end + 1 < s.length() && end - start + 1 < p.length()) {
                end++;
            } else {
                // 右侧边界不能继续扩大或窗口内字符串长度等于字符串p的长度
                // 则缩小左侧边界
                start++;
            }

            // 当窗口内字符串长度等于字符串p的长度时，则判断其是不是字符串p的字母异位词子串
            if (end - start + 1 == p.length() && isAnagrams(s.substring(start, end + 1), pFreq)) {
                resultList.add(start);
            }
        }

        return resultList;
    }

    /**
     * 判断当前子串是不是字符串p的字母异位词
     */
    private boolean isAnagrams(String window, int[] pFreq) {
        // 计算窗口内字符串各元素的出现次数
        int[] windowFreq = new int[26];
        for (int i = 0; i < window.length(); i++) {
            windowFreq[window.charAt(i) - 'a']++;
        }

        // 比较窗口内各元素的出现次数和字符串p中各元素的出现次数是否一样
        // 如果都一样，则说明窗口内的字符串是字符串p的字母异位词子串
        // 如果不一样，则说明不是其子串
        for (int j = 0; j < 26; j++) {
            if (windowFreq[j] != pFreq[j]) {
                return false;
            }
        }
        return true;
    }

    /**
     * 滑动指针框架试
     * @author liuzhen
     * @date 2021/9/2 18:12
     * @param s
     * @param p
     * @return java.util.List<java.lang.Integer>
     */
    public static List<Integer> findAnagrams2(String s, String p) {
        int start = 0, left = 0, right = 0;
        int match = 0;

        List<Integer> res = new ArrayList<>();

        HashMap<Character, Integer> window = new HashMap<>();
        HashMap<Character, Integer> needs = new HashMap<>();

        for (char c : p.toCharArray())
            needs.put(c, needs.getOrDefault(c, 0) + 1);

        while (right < s.length()) {
            //---------rp to move-------------------
            char c1 = s.charAt(right);
            if (needs.containsKey(c1)) {
                window.put(c1, window.getOrDefault(c1, 0) + 1);
                if (window.get(c1).equals(needs.get(c1)))
                    match++;
            } else {
                left = right + 1;
                window = new HashMap<>();
                match = 0;
            }
            right++;
            //---------rp to move-------------------

            while (match == needs.size()) {
                start = left;
                if (window.equals(needs))
                    res.add(start);

                char c2 = s.charAt(left);
                if (window.containsKey(c2)) {
                    window.put(c2, window.get(c2) - 1);
                    if (window.get(c2) < (needs.get(c2)))
                        match--;
                }

                left++;
            }
        }
        return res;
    }

}
