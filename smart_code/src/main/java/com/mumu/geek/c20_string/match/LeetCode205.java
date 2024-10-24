package com.mumu.geek.c20_string.match;

import java.util.HashMap;
import java.util.Map;

/**
 * LeetCode205
 * 同构字符串
 * @author liuzhen
 * @version 1.0.0 2021/9/1 19:21
 */
public class LeetCode205 {

    /**
     * 给定两个字符串 s 和 t，判断它们是否是同构的。
     * 如果 s 中的字符可以按某种映射关系替换得到 t ，那么这两个字符串是同构的。
     * 每个出现的字符都应当映射到另一个字符，同时不改变字符的顺序。不同字符不能映射到同一个字符上，相同字符只能映射到同一个字符上，字符可以映射到自己本身。
     *
     * 示例 1:
     * 输入：s = "egg", t = "add"
     * 输出：true
     *
     * 示例 2：
     * 输入：s = "foo", t = "bar"
     * 输出：false
     *
     * 示例 3：
     * 输入：s = "paper", t = "title"
     * 输出：true
     *
     * 提示：
     * 可以假设 s 和 t 长度相同。
     */

    /**
     * hash表法（官方）：需要我们判断 ss 和 tt 每个位置上的字符是否都一一对应，即 ss 的任意一个字符被 tt 中唯一的字符对应，同时 tt 的任意一个字符被 ss 中唯一的字符对应。这也被称为「双射」的关系。
     * 因此，我们维护两张哈希表，第一张哈希表 s2t 以 ss 中字符为键，映射至 tt 的字符为值，第二张哈希表 t2s 以 tt 中字符为键，映射至 ss 的字符为值。从左至右遍历两个字符串的字符，不断更新两张哈希表，
     * 如果出现冲突（即当前下标 index 对应的字符 s[index] 已经存在映射且不为 t[index] 或当前下标 index 对应的字符 t[index] 已经存在映射且不为s[index]）时说明两个字符串无法构成同构，返回 false。
     * 如果遍历结束没有出现冲突，则表明两个字符串是同构的，返回 true 即可。
     * @author liuzhen
     * @date 2021/9/3 22:56
     * @param s
     * @param t
     * @return boolean
     */
    public static boolean isIsomorphic(String s, String t) {
        Map<Character, Character> s2t = new HashMap<>();
        Map<Character, Character> t2s = new HashMap<>();
        int len = s.length();
        for (int i = 0; i < len; ++i) {
            char sChar = s.charAt(i);
            char tChar = t.charAt(i);
            if ((s2t.containsKey(sChar) && s2t.get(sChar) != tChar) || (t2s.containsKey(tChar) && t2s.get(tChar) != sChar)) {
                return false;
            }
            s2t.put(sChar, tChar);
            t2s.put(tChar, sChar);
        }
        return true;
    }

    /**
     * 大神解法 恐怖如斯！
     * @author liuzhen
     * @date 2021/9/3 23:10
     * @param s
     * @param t
     * @return boolean
     */
    public static boolean isIsomorphic2(String s, String t) {
        for(int i = 0; i < s.length(); i++){
            // s.indexOf(s.charAt(i): 当前i索引位置的字符，这个字符对应的所在位置
            if(s.indexOf(s.charAt(i)) != t.indexOf(t.charAt(i))){
                return false;
            }
        }
        return true;
    }

}
