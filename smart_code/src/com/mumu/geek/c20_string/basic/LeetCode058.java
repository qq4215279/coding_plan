package com.mumu.geek.c20_string.basic;

/**
 * LeetCode058
 * 最后一个单词长度
 * @author liuzhen
 * @version 1.0.0 2021/8/30 22:36
 */
public class LeetCode058 {

    /**
     * 给你一个字符串 s，由若干单词组成，单词前后用一些空格字符隔开。返回字符串中最后一个单词的长度。
     * 单词 是指仅由字母组成、不包含任何空格字符的最大子字符串。
     *
     * 示例 1：
     * 输入：s = "Hello World"
     * 输出：5
     *
     * 示例 2：
     * 输入：s = "   fly me   to   the moon  "
     * 输出：4
     *
     * 示例 3：
     * 输入：s = "luffy is still joyboy"
     * 输出：6
     *  
     * 提示：
     * 1 <= s.length <= 104
     * s 仅有英文字母和空格 ' ' 组成
     * s 中至少存在一个单词
     */


    /**
     * 工具
     * @author liuzhen
     * @date 2021/9/2 14:06
     * @param s
     * @return int
     */
    public static int lengthOfLastWord(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }

        s = s.trim();
        String[] split = s.split(" ");
        return split[split.length - 1].length();
    }

    /**
     * 思路：
     * 从字符串末尾开始向前遍历，其中主要有两种情况
     * 第一种情况，以字符串"Hello World"为例，从后向前遍历直到遍历到头或者遇到空格为止，即为最后一个单词"World"的长度5
     * 第二种情况，以字符串"Hello World "为例，需要先将末尾的空格过滤掉，再进行第一种情况的操作，即认为最后一个单词为"World"，长度为5
     * 所以完整过程为先从后过滤掉空格找到单词尾部，再从尾部向前遍历，找到单词头部，最后两者相减，即为单词的长度
     * 时间复杂度：O(n)，n为结尾空格和结尾单词总体长度
     * @author liuzhen
     * @date 2021/9/2 13:59
     * @param s
     * @return int
     */
    public static int lengthOfLastWord2(String s) { // "   fly me   to   the moon  "
        int end = s.length() - 1;
        // 从字符串末尾开始找，找到 moon 的 n 字符处的索引位置
        while (end >= 0 && s.charAt(end) == ' ') {
            end--;
        }

        if (end < 0) {
            return 0;
        }

        int start = end;
        // 找出 moon 的开始字符处，moon -> m 的索引位置
        while (start >= 0 && s.charAt(start) != ' ') {
            start--;
        }
        return end - start;
    }


    public static void main(String[] args) {
        String s = "   fly me   to   the moon  ";
        System.out.println(lengthOfLastWord(s));
        System.out.println(lengthOfLastWord2(s));
    }
}
