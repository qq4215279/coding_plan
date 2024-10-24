package com.mumu.geek.c20_string.high_lv;

/**
 * LeetCode072
 * 编辑距离
 * @author liuzhen
 * @version 1.0.0 2021/9/1 19:13
 */
public class LeetCode072 { // c12_dynamic_programming.high_lv_dp/LeetCode072

    /**
     * 给你两个单词 word1 和 word2，请你计算出将 word1 转换成 word2 所使用的最少操作数 。
     * 你可以对一个单词进行如下三种操作：
     * 插入一个字符
     * 删除一个字符
     * 替换一个字符
     *
     * 示例 1：
     * 输入：word1 = "horse", word2 = "ros"
     * 输出：3
     * 解释：
     * horse -> rorse (将 'h' 替换为 'r')
     * rorse -> rose (删除 'r')
     * rose -> ros (删除 'e')
     *
     * 示例 2：
     * 输入：word1 = "intention", word2 = "execution"
     * 输出：5
     * 解释：
     * intention -> inention (删除 't')
     * inention -> enention (将 'i' 替换为 'e')
     * enention -> exention (将 'n' 替换为 'x')
     * exention -> exection (将 'n' 替换为 'c')
     * exection -> execution (插入 'u')
     *  
     * 提示：
     * 0 <= word1.length, word2.length <= 500
     * word1 和 word2 由小写英文字母组成
     *
     * // 思路：
     * edit_dist(i, j) = edit_dist(i-1, j-1)   if w1[i] == w2[j] // 分治
     * else // w1[i] != w2[j]
     *
     */

    public int minDistance(String word1, String word2) {

        return 1;
    }

}
