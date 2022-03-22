package com.mumu.geek.c14_search.double_bfs;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * LeetCode127
 * 单词接龙
 * @author liuzhen
 * @version 1.0.0 2021/8/24 8:54
 */
public class LeetCode127 { // c9_dfs_bfs

    /**
     * 思路：
     * 1. BFS
     * 2. DFS
     * 3. Two-ended BFS
     */


    /**
     * TODO LeetCode127 未通过？？？
     * @author liuzhen
     * @date 2021/8/24 20:30
     * @param beginWord
     * @param endWord
     * @param wordList__
     * @return int
     */
    public static int ladderLength(String beginWord, String endWord, List<String> wordList__) {
        Set<String> wordList = new HashSet<>(wordList__);
        Set<String> beginSet = new HashSet<>();
        Set<String> endSet = new HashSet<>();

        int len = 1;
        int strLen = beginWord.length();
        Set<String> visited = new HashSet<>();

        beginSet.add(beginWord);
        endSet.add(endWord);

        // start BFS
        while (!beginSet.isEmpty() && !endSet.isEmpty()) {
            // 交换
            if (beginSet.size() > endSet.size()) {
                Set<String> set = beginSet;
                beginSet = endSet;
                endSet = set;
            }

            Set<String> temp = new HashSet<>();
            for (String word : beginSet) {
                char[] chs = word.toCharArray();

                for (int i = 0; i < chs.length; i++) {
                    for (char c = 'a'; c <= 'z'; c++) {
                        char old = chs[i];
                        chs[i] = c;
                        String target = String.valueOf(chs);

                        if (endSet.contains(target)) {
                            return len + 1;
                        }

                        if (!visited.contains(target) && wordList.contains(target)) {
                            temp.add(target);
                            visited.add(target);
                        }
                        chs[i] = old;
                    }
                }
            }

            beginSet = temp;
            len++;
        }

        return 0;
    }

    public static void main(String[] args) {
        String beginWord = "hit";
        String endWord = "cog";
        List<String> wordList__ = new ArrayList<>();
        wordList__.add("hot");
        wordList__.add("dot");
        wordList__.add("dog");
        wordList__.add("lot");
        wordList__.add("log");
        wordList__.add("cog");

        System.out.println(ladderLength(beginWord, endWord, wordList__));

    }

}
