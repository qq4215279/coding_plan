package com.mumu.geek.c9_dfs_bfs;

import java.util.List;

/**
 * LeetCode127
 * 单词接龙
 * @author liuzhen
 * @version 1.0.0 2021/8/16 14:06
 */
public class LeetCode127 {

    /**
     * 字典 wordList 中从单词 beginWord 和 endWord 的转换序列是一个按下述规格形成的序列：
     * 序列中第一个单词是 beginWord 。序列转换过程中的中间单词必须是字典 wordList 中的单词。
     * 给你两个单词 beginWord 和 endWord 和一个字典 wordList ，找到从 beginWord 到 endWord 的最短转换序列中的单词数目。
     * 如果不存在这样的转换序列，返回 0。
     *
     * 示例 1：
     * 输入：beginWord = "hit", endWord = "cog", wordList = ["hot","dot","dog","lot","log","cog"]
     * 输出：5
     * 解释：一个最短转换序列是 "hit" -> "hot" -> "dot" -> "dog" -> "cog", 返回它的长度 5。
     *
     * 示例 2：
     * 输入：beginWord = "hit", endWord = "cog", wordList = ["hot","dot","dog","lot","log"]
     * 输出：0
     * 解释：endWord "cog" 不在字典中，所以无法进行转换。
     *  
     * 提示：
     * 1 <= beginWord.length <= 10
     * endWord.length == beginWord.length
     * 1 <= wordList.length <= 5000
     * wordList[i].length == beginWord.length
     * beginWord、endWord 和 wordList[i] 由小写英文字母组成
     * beginWord != endWord
     * wordList 中的所有字符串互不相同
     */

    /**
     * TODO
     * @author liuzhen
     * @date 2021/10/8 17:08
     * @param beginWord
     * @param endWord
     * @param wordList
     * @return int
     */
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {

        return 0;
    }

}
