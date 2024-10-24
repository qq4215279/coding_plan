package com.mumu.geek.c20_string.basic;

import java.util.*;

/**
 * LeetCode049
 * 字母异位词分组
 * @author liuzhen
 * @version 1.0.0 2021/8/30 22:55
 */
public class LeetCode049 { // you ?

    /**
     * 给你一个字符串数组，请你将字母异位词组合在一起。可以按任意顺序返回结果列表。
     * 字母异位词是由重新排列源单词的字母得到的一个新单词，所有源单词中的字母都恰好只用一次。
     *  
     * 示例 1:
     * 输入: strs = ["eat", "tea", "tan", "ate", "nat", "bat"]
     * 输出: [["bat"],["nat","tan"],["ate","eat","tea"]]
     *
     * 示例 2:
     * 输入: strs = [""]
     * 输出: [[""]]
     *
     * 示例 3:
     * 输入: strs = ["a"]
     * 输出: [["a"]]
     *
     * 提示：
     * 1 <= strs.length <= 104
     * 0 <= strs[i].length <= 100
     * strs[i] 仅包含小写字母
     */

    /**
     * 排序  时间复杂度：O(nklogk)   空间复杂度：O(nk)
     * @author liuzhen
     * @date 2021/9/2 17:56
     * @param strs
     * @return java.util.List<java.util.List<java.lang.String>>
     */
    public List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> map = new HashMap<>();
        for (String str : strs) {
            char[] array = str.toCharArray();
            Arrays.sort(array);
            String key = new String(array);
            List<String> list = map.getOrDefault(key, new ArrayList<>());
            list.add(str);
            map.put(key, list);
        }
        return new ArrayList<>(map.values());
    }

    /**
     * 计数：
     * @author liuzhen
     * @date 2021/9/2 18:05
     * @param strs
     * @return java.util.List<java.util.List<java.lang.String>>
     */
    public static List<List<String>> groupAnagrams2(String[] strs) {
        Map<String, List<String>> map = new HashMap<>();
        for (String str : strs) {
            int[] counts = new int[26];
            int length = str.length();
            for (int i = 0; i < length; i++) {
                counts[str.charAt(i) - 'a']++;
            }

            // 将每个出现次数大于 0 的字母和出现次数按顺序拼接成字符串，作为哈希表的键
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < 26; i++) {
                if (counts[i] != 0) {
                    sb.append((char) ('a' + i));
                    sb.append(counts[i]);
                }
            }
            String key = sb.toString();
            List<String> list = map.getOrDefault(key, new ArrayList<String>());
            list.add(str);
            map.put(key, list);
        }
        return new ArrayList<>(map.values());
    }

}
