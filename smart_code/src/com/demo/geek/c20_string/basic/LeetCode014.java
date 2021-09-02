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

    /**
     * 暴力：
     * 思路：纵向扫描  时间复杂度：O(mn)   空间复杂度：O(1)
     * 纵向扫描时，从前往后遍历所有字符串的每一列，比较相同列上的字符是否相同，如果相同则继续对下一列进行比较，
     * 如果不相同则当前列不再属于公共前缀，当前列之前的部分为最长公共前缀。
     * @author liuzhen
     * @date 2021/9/2 15:01
     * @param strs
     * @return java.lang.String
     */
    public String longestCommonPrefix(String[] strs) {
        if (strs == null || strs.length == 0) {
            return "";
        }

        // 遍历字符串数组的第一个字符串的每个字符
        for (int i = 0; i < strs[0].length(); i++) {
            char c = strs[0].charAt(i);

            // 遍历字符串数组剩下每个字符串，比较对应索引位置上的字符与字符 c 是否相同，不相同直接返回对应字符串
            for (int j = 1; j < strs.length; j++) {
                if (i == strs[j].length() || strs[j].charAt(i) != c) {
                    return strs[0].substring(0, i);
                }
            }
        }
        return strs[0];
    }

    /**
     * 横向扫描，依次遍历每个字符串，更新最长公共前缀。
     * 时间复杂度：O(mn) 空间复杂度：O(1)
     * @author liuzhen
     * @date 2021/9/2 15:05
     * @param strs
     * @return java.lang.String
     */
    public static String longestCommonPrefix2(String[] strs) {
        if (strs == null || strs.length == 0) {
            return "";
        }
        String prefix = strs[0];
        int count = strs.length;
        for (int i = 1; i < count; i++) {
            prefix = longestCommonPrefix(prefix, strs[i]);
            if (prefix.length() == 0) {
                break;
            }
        }
        return prefix;
    }

    private static String longestCommonPrefix(String str1, String str2) {
        int length = Math.min(str1.length(), str2.length());
        int index = 0;
        while (index < length && str1.charAt(index) == str2.charAt(index)) {
            index++;
        }
        return str1.substring(0, index);
    }

    /**
     * 分治
     * 时间复杂度：O(mn)  时间复杂度的递推式是 T(n)=2T(n/2) + O(m)，通过计算可得 T(n)=O(mn)。空间复杂度：O(mlogn)
     * @author liuzhen
     * @date 2021/9/2 15:18
     * @param strs
     * @return java.lang.String
     */
    public static String longestCommonPrefix3(String[] strs) {
        if (strs == null || strs.length == 0) {
            return "";
        }

        return longestCommonPrefix(strs, 0, strs.length - 1);
    }

    private static String longestCommonPrefix(String[] strs, int start, int end) {
        if (start == end) {
            return strs[start];
        }

        int mid = start + (end - start) / 2;
        String lcpLeft = longestCommonPrefix(strs, start, mid);
        String lcpRight = longestCommonPrefix(strs, mid + 1, end);
        return commonPrefix(lcpLeft, lcpRight);
    }

    /**
     * 找到最长公共前缀
     */
    private static String commonPrefix(String lcpLeft, String lcpRight) {
        int minLength = Math.min(lcpLeft.length(), lcpRight.length());
        for (int i = 0; i < minLength; i++) {
            if (lcpLeft.charAt(i) != lcpRight.charAt(i)) {
                return lcpLeft.substring(0, i);
            }
        }
        return lcpLeft.substring(0, minLength);
    }

    /**
     * 二分法（了解）
     * @author liuzhen
     * @date 2021/9/2 15:17
     * @param strs
     * @return java.lang.String
     */
    public static String longestCommonPrefix4(String[] strs) {
        if (strs == null || strs.length == 0) {
            return "";
        }
        int minLength = Integer.MAX_VALUE;
        for (String str : strs) {
            minLength = Math.min(minLength, str.length());
        }
        int low = 0, high = minLength;
        while (low < high) {
            int mid = (high - low + 1) / 2 + low;
            if (isCommonPrefix(strs, mid)) {
                low = mid;
            } else {
                high = mid - 1;
            }
        }
        return strs[0].substring(0, low);
    }

    private static boolean isCommonPrefix(String[] strs, int length) {
        String str0 = strs[0].substring(0, length);
        int count = strs.length;
        for (int i = 1; i < count; i++) {
            String str = strs[i];
            for (int j = 0; j < length; j++) {
                if (str0.charAt(j) != str.charAt(j)) {
                    return false;
                }
            }
        }
        return true;
    }

    public static void main(String[] args) {
        String[] strs = {"flower", "flow", "flight"};
        System.out.println(longestCommonPrefix2(strs));
    }

}
