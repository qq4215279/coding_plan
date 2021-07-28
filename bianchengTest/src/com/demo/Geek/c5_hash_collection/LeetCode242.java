/*
 * Copyright 2018-2020, 上海哈里奥科技有限公司
 * All Right Reserved.
 */

package com.demo.Geek.c5_hash_collection;

import java.util.Arrays;

public class LeetCode242 { // LeetCode  242

    /**
     * 有效的字母异位词
     * 异位词：字母出现的次数一样，只是顺序不一样
     *
     * 给定两个字符串 s 和 t ，编写一个函数来判断 t 是否是 s 的字母异位词。
     *
     * 示例 1:
     * 输入: s = "anagram", t = "nagaram"
     * 输出: true
     *
     * 示例 2:
     * 输入: s = "rat", t = "car"
     * 输出: false
     * 说明:
     * 你可以假设字符串只包含小写字母。
     *
     * 进阶:
     * 如果输入字符串包含 unicode 字符怎么办？你能否调整你的解法来应对这种情况？
     */


    public boolean isAnagram00(String s, String t) { // 1.暴力：排序！ O(nlogn)：因为排序时间复杂度为O(nlogn)，比较两个字符串的成本 O(n)O(n)
        if (s.length() != t.length())
            return false;

        char[] chars1 = s.toCharArray();    //不是排序的问题需要用到排序则直接调用排序方法即可！！！
        char[] chars2 = t.toCharArray();
        Arrays.sort(chars1);
        Arrays.sort(chars2);

        return Arrays.equals(chars1, chars2);
    }

    public boolean isAnagram01(String s, String t) { // 2.通过hash函数，对两个字符串中每个字母的出现次数并进行比较。 O(n)
        if (s.length() != t.length())
            return false;

        int[] count = new int[26];
        for (int i = 0; i < s.length(); i++) {
            count[s.charAt(i) - 'a']++;
            count[t.charAt(i) - 'a']--;
        }

        for (int i = 0; i < count.length; i++) {
            if (count[i] != 0)
                return false;
        }

        return true;
    }

    public boolean isAnagram02(String s, String t) { // 3.hash改进版本  O(n)
        // 3.先用计数器表计算 ss，然后用 tt 减少计数器表中的每个字母的计数器。如果在任何时候计数器低于零，
        // 我们知道 tt 包含一个不在 ss 中的额外字母，并立即返回 FALSE。

        if (s.length() != t.length())
            return false;

        int[] count = new int[26];
        for (int i = 0; i < s.length(); i++) {
            count[s.charAt(i) - 'a']++;
        }

        for (int i = 0; i < t.length(); i++) {
            count[t.charAt(i) - 'a']--;
            if (count[t.charAt(i ) - 'a'] < 0)
                return false;
        }
        return true;
    }


    public static void main(String[] args) {
        System.out.println('c' - 'a');
    }
}
