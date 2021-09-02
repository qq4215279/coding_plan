package com.demo.geek.c20_string.basic;

import java.util.Arrays;
import java.util.Collections;

/**
 * LeetttCode151
 * 反转字符串里的单词
 * @author liuzhen
 * @version 1.0.0 2021/8/30 22:49
 */
public class LeetCode151 {

    /**
     * 给你一个字符串 s ，逐个翻转字符串中的所有 单词 。
     * 单词 是由非空格字符组成的字符串。s 中使用至少一个空格将字符串中的 单词 分隔开。
     * 请你返回一个翻转 s 中单词顺序并用单个空格相连的字符串。
     *
     * 说明：
     * 输入字符串 s 可以在前面、后面或者单词间包含多余的空格。
     * 翻转后单词间应当仅用一个空格分隔。
     * 翻转后的字符串中不应包含额外的空格。
     *  
     * 示例 1：
     * 输入：s = "the sky is blue"
     * 输出："blue is sky the"
     *
     * 示例 2：
     * 输入：s = "  hello world  "
     * 输出："world hello"
     * 解释：输入字符串可以在前面或者后面包含多余的空格，但是翻转后的字符不能包括。
     *
     * 示例 3：
     * 输入：s = "a good   example"
     * 输出："example good a"
     * 解释：如果两个单词间有多余的空格，将翻转后单词间的空格减少到只含一个。
     *
     * 示例 4：
     * 输入：s = "  Bob    Loves  Alice   "
     * 输出："Alice Loves Bob"
     *
     * 示例 5：
     * 输入：s = "Alice does not even like bob"
     * 输出："bob like even not does Alice"
     *
     * 提示：
     * 1 <= s.length <= 104
     * s 包含英文大小写字母、数字和空格 ' '
     * s 中 至少存在一个 单词
     *  
     * 进阶：
     * 请尝试使用 O(1) 额外空间复杂度的原地解法。
     *
     * 思路：
     * 2.
     * eg:
     * eulb si yks eht
     * blue is sky the
     *
     */

    /**
     * 思路：split，reverse，join
     * @author liuzhen
     * @date 2021/9/2 16:56
     * @param s
     * @return java.lang.String
     */
    public static String reverseWords(String s) {
        String[] words = s.trim().split(" +");
        Collections.reverse(Arrays.asList(words));
        return String.join(" ", words);
    }

    /**
     * 思路：reverse整个string，然后再单独reverse每个但崔单词   TODO 未通过？？
     * @author liuzhen
     * @date 2021/9/2 17:11
     * @param s
     * @return java.lang.String
     */
    public static String reverseWords2(String s) {
        s = new StringBuilder(s).reverse().toString();
        String[] words = s.trim().split(" ");

        for (int i = 0; i < words.length; i++) {
            words[i] = reverseSingleWord(words[i]);
        }

        return String.join(" ", words);
    }

    private static String reverseSingleWord(String word) {
        int left = 0;
        int right = word.length() - 1;
        char[] chars = word.trim().toCharArray();
        while (left <= right) {
            char temp = chars[left];
            chars[left] = chars[right];
            chars[right] = temp;

            left++;
            right--;
        }

        return String.valueOf(chars);
    }

    public static void main(String[] args) {
        String s = "a good   example";
//        System.out.println(reverseWords(s));
        System.out.println(reverseWords2(s));
    }

}
