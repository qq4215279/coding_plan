package com.mumu.geek.c13_trie_disjoint_set;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * LeetCode212
 * 单词搜索
 * @author liuzhen
 * @version 1.0.0 2021/8/22 15:52
 */
public class LeetCode212 {

    /**
     * 给定一个 m x n 二维字符网格 board 和一个单词（字符串）列表 words，找出所有同时在二维网格和字典中出现的单词。
     * 单词必须按照字母顺序，通过相邻的单元格内的字母构成，其中“相邻”单元格是那些水平相邻或垂直相邻的单元格。同一个单元格内的字母在一个单词中不允许被重复使用。
     *
     * 示例 1：
     *  o   a   a   n
     *  e   t   a   e
     *  i   h   k   r
     *  i   f   i   v
     *
     * 输入：board = [["o","a","a","n"],["e","t","a","e"],["i","h","k","r"],["i","f","l","v"]], words = ["oath","pea","eat","rain"]
     * 输出：["eat","oath"]
     *
     * 示例 2：
     *  a   b
     *  c   d
     * 输入：board = [["a","b"],["c","d"]], words = ["abcb"]
     * 输出：[]
     *  
     * 提示：
     * m == board.length
     * n == board[i].length
     * 1 <= m, n <= 12
     * board[i][j] 是一个小写英文字母
     * 1 <= words.length <= 3 * 104
     * 1 <= words[i].length <= 10
     * words[i] 由小写英文字母组成
     * words 中的所有字符串互不相同
     *
     * 思路：
     * 1. words 遍历 --> board search    O(N*m*m*4^k)
     * 2. Trie
     *  a. all words -->  Trie 构建起 prefix
     *  b. board, DFS
     *  时间复杂度：？？？
     */

    /**
     * 前缀树解法: 将所有words生成字典树，遍历board二维数组，如果char匹配到字典树的字符，则会递归（char当前坐标的左、右、上、下）坐标，
     * 如果没有找到匹配的字符或者数组越界，则提前返回。注：返回的时候一定得visited数组当前层的状态清除
     * @author liuzhen
     * @date 2021/8/22 20:33
     * @param board
     * @param words
     * @return java.util.List<java.lang.String>
     */
    public static List<String> findWords(char[][] board, String[] words) {
        // 构建字典树
        TrieNode212 myTrie = new TrieNode212();
        for (String word : words) {
            myTrie.insert(word);
        }

        // 使用set防止重复
        Set<String> result = new HashSet<>();
        int rSize = board.length;
        int lSize = board[0].length;
        boolean[][] visited = new boolean[rSize][lSize];

        // 遍历整个二维数组
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                find(board, visited, i, j, rSize, lSize, result, myTrie);
            }
        }

        System.out.println(result);
        return new ArrayList<>(result);
    }

    /**
     *
     * @author liuzhen
     * @date 2021/8/22 21:14
     * @return void
     */
    private static void find(char[][] board, boolean[][] visited, int row, int col, int rSize, int lSize, Set<String> result, TrieNode212 myTrie) {
        // 边界以及是否已经访问判断
        if (row < 0 || row >= rSize || col < 0 || col >= lSize || visited[row][col]) {
            return;
        }

        myTrie = myTrie.children[board[row][col] - 'a'];
        visited[row][col] = true;
        if (myTrie == null) {
            // 如果单词不匹配，回退
            visited[row][col] = false;
            return;
        }

        // 找到单词加入
        if (myTrie.isEnd) {
            result.add(myTrie.word);
            // 注意：这里不能有return，因为还要继续递归，后面可能还有别的比当前word更长的word eg: oa时，返回后oaa就添加不到结果上去了！！！
//            return;
        }

        int[] dx = {-1, 1, 0, 0};
        int[] dy = {0, 0, -1, 1};
        for (int k = 0; k < 4; k++) {
            find(board, visited, row + dx[k], col + dy[k], rSize, lSize, result, myTrie);
        }

        // 等价与上面循环
//        find(board, visited, row + 1, j, m, n, result, cur);
//        find(board, visited, row, j + 1, m, n, result, cur);
//        find(board, visited, row, j - 1, m, n, result, cur);
//        find(board, visited, row - 1, j, m, n, result, cur);

        // 最后要回退，因为下一个起点可能会用到上一个起点的字符
        visited[row][col] = false;
    }

    public static void main(String[] args) {
        char[][] board = {{'o', 'a', 'a', 'n'}, {'e', 't', 'a', 'e'}, {'i', 'h', 'k', 'r'}, {'i', 'f', 'l', 'v'}};
        String[] words = {"oath", "pea", "eat", "rain"};
//        findWords(board, words);

        //        [["o","a","b","n"],["o","t","a","e"],["a","h","k","r"],["a","f","l","v"]]
        //["oa","oaa"]

        char[][] board2 = {{'o', 'a', 'b', 'n'}, {'o', 't', 'a', 'e'}, {'a', 'h', 'k', 'r'}, {'a', 'f', 'l', 'v'}};
        String[] words2 = {"oa", "oaa"};
        findWords(board2, words2);


    }

}

/**
 * 定义前缀树（字典树）
 */
class TrieNode212 {
    public String word;
    // 用于判断是否是一个单词
    public boolean isEnd;
    public TrieNode212[] children;

    public TrieNode212() {
        isEnd = false;
        children = new TrieNode212[26];
    }

    /**
     * 插入单词
     * @param word
     */
    public void insert(String word) {
        TrieNode212 node = this;
        for (int i = 0; i < word.length(); i++) {
            char ch = word.charAt(i);
            int index = ch - 'a';
            if (node.children[index] == null) {
                node.children[index] = new TrieNode212();
            }

            node = node.children[index];
            // 记录过程字符
            if (node.word == null) {
                node.word = word.substring(0, i + 1);
            }
        }
        // 加上一个标记，表示为一个单词
//        if (node.word == null) {
//            node.word = word;
//        }
        node.isEnd = true;
    }

    /**
     * word 是否在字段树里.
     * @param word
     * @return
     */
    public boolean search(String word) {
        TrieNode212 node = searchPrefix(word);
        return node != null && node.isEnd;
    }

    /**
     * 返回字典树中是否有以给定前缀开头的单词
     * @param prefix
     * @return
     */
    public boolean startsWith(String prefix) {
        return searchPrefix(prefix) != null;
    }

    /**
     * 查找前缀
     * @param prefix
     * @return
     */
    private TrieNode212 searchPrefix(String prefix) {
        TrieNode212 node = this;
        for (int i = 0; i < prefix.length(); i++) {
            char ch = prefix.charAt(i);
            int index = ch - 'a';
            if (node.children[index] == null) {
                return null;
            }
            node = node.children[index];
        }
        return node;
    }
}
