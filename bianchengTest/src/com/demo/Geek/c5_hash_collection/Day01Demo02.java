/*
 * Copyright 2018-2020, 上海哈里奥科技有限公司
 * All Right Reserved.
 */

package com.demo.Geek.c5_hash_collection;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class Day01Demo02 { //LeetCode  49

    /**
     * 字符异位词分组
     * 异位词：字母出现的次数一样，只是顺序不一样
     *
     * 给定一个字符串数组，将字母异位词组合在一起。字母异位词指字母相同，但排列不同的字符串。
     *
     * 示例:
     *
     * 输入: ["eat", "tea", "tan", "ate", "nat", "bat"]
     * 输出:
     * [
     *   ["ate","eat","tea"],
     *   ["nat","tan"],
     *   ["bat"]
     * ]
     *
     * 说明：
     * 所有输入均为小写字母。
     * 不考虑答案输出的顺序。
     */

    public List<List<String>> groupAnagrams00(String[] strs) {// 1.排序数组分类
        // 时间复杂度：O(NK \log K)O(NKlogK)，其中 NN 是 strs 的长度，而 KK 是 strs 中字符串的最大长度。
        if (strs.length == 0)
            return new ArrayList<>();

        HashMap<String, ArrayList<String>> map = new HashMap<>();
        for (String s : strs) {
            char[] chars = s.toCharArray();
            Arrays.sort(chars);
            String key = String.valueOf(chars); //字符数组也可以通过String静态类转字符串！！！！！
            if (!map.containsKey(key)){
                map.put(key, new ArrayList<>());
            }
            map.get(key).add(s);
        }

        return new ArrayList<>(map.values());// 转换技巧！！！  重点！！
    }

//    public List<List<String>> groupAnagrams(String[] strs) {
//
//    }
//






}
