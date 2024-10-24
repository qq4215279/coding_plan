package com.mumu.geek.c20_string.basic;

/**
 * LeetCode557
 * 反转字符串中的单词3
 * @author liuzhen
 * @version 1.0.0 2021/8/30 22:51
 */
public class LeetCode557 {

    /**
     * 给定一个字符串，你需要反转字符串中每个单词的字符顺序，同时仍保留空格和单词的初始顺序。
     *  
     * 示例：
     * 输入："Let's take LeetCode contest"
     * 输出："s'teL ekat edoCteeL tsetnoc"
     *
     * 提示：
     * 在字符串中，每个单词由单个空格分隔，并且字符串中不会有任何额外的空格。
     */

    /**
     *
     * @author liuzhen
     * @date 2021/9/2 17:22
     * @param s
     * @return java.lang.String
     */
    public static String reverseWords(String s) {
        StringBuffer sb = new StringBuffer();
        int length = s.length();
        int index = 0;
        while (index < length) {
            int start = index;
            while (index < length && s.charAt(index) != ' ') {
                index++;
            }

            for (int p = start; p < index; p++) {
                sb.append(s.charAt(start + index - 1 - p));
            }
            while (index < length && s.charAt(index) == ' ') {
                index++;
                sb.append(' ');
            }
        }
        return sb.toString();
    }

}
