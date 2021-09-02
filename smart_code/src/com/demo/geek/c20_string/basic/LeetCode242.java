package com.demo.geek.c20_string.basic;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * LeetCode242
 * 有效的字母异位词
 * @author liuzhen
 * @version 1.0.0 2021/8/30 22:53
 */
public class LeetCode242 {

    /**
     * 给定两个字符串 s 和 t ，编写一个函数来判断 t 是否是 s 的字母异位词。
     * 注意：若 s 和 t 中每个字符出现的次数都相同，则称 s 和 t 互为字母异位词。
     *
     * 示例 1:
     * 输入: s = "anagram", t = "nagaram"
     * 输出: true
     *
     * 示例 2:
     * 输入: s = "rat", t = "car"
     * 输出: false
     *
     * 提示:
     * 1 <= s.length, t.length <= 5 * 104
     * s 和 t 仅包含小写字母
     *  
     * 进阶: 如果输入字符串包含 unicode 字符怎么办？你能否调整你的解法来应对这种情况？
     */

    /**
     * 排序  时间复杂度：O(nlogn)，其中 nn 为 ss 的长度。排序的时间复杂度为 O(nlogn)，比较两个字符串是否相等时间复杂度为O(n)，
     *      因此总体时间复杂度为 O(nlogN+n) = O(nlogn)。  空间复杂度：O(logn)
     *
     * @author liuzhen
     * @date 2021/9/2 17:46
     * @param s
     * @param t
     * @return boolean
     */
    public static boolean isAnagram(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }
        char[] str1 = s.toCharArray();
        char[] str2 = t.toCharArray();
        Arrays.sort(str1);
        Arrays.sort(str2);
        return Arrays.equals(str1, str2);
    }

    public static boolean isAnagram2(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }

        int[] table = new int[26];
        for (int i = 0; i < s.length(); i++) {
            table[s.charAt(i) - 'a']++;
        }
        for (int i = 0; i < t.length(); i++) {
            table[t.charAt(i) - 'a']--;
            if (table[t.charAt(i) - 'a'] < 0) {
                return false;
            }
        }
        return true;
    }

    /**
     * 进阶解决
     * @author liuzhen
     * @date 2021/9/2 17:52
     * @param s
     * @param t
     * @return boolean
     */
    public static boolean isAnagram3(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }
        Map<Character, Integer> table = new HashMap<Character, Integer>();
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            table.put(ch, table.getOrDefault(ch, 0) + 1);
        }
        for (int i = 0; i < t.length(); i++) {
            char ch = t.charAt(i);
            table.put(ch, table.getOrDefault(ch, 0) - 1);
            if (table.get(ch) < 0) {
                return false;
            }
        }
        return true;
    }

}
