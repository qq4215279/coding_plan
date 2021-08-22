package com.demo.common.entity;

/**
 * TrieNode
 * 字典树（前缀树）定义：每个节点最多有26个不同的小写字母
 *  时间复杂度：初始化为 O(1)O(1)，其余操作为 O(|S|)O(∣S∣)，其中 |S|∣S∣ 是每次插入或查询的字符串的长度。
 *  空间复杂度：O(|T|\cdot\Sigma)O(∣T∣⋅Σ)，其中 |T|∣T∣ 为所有插入字符串的长度之和，\SigmaΣ 为字符集的大小，本题 \Sigma=26Σ=26
 * @author liuzhen
 * @version 1.0.0 2021/8/22 17:59
 */
public class TrieNode { // Code_01_TrieTree  Code_04_TrieTree
    public String word;
    // 有多少字符串到达过，既生成树过程中的字母出现（经过）的次数
    public int path;
    // 用于判断是否是一个单词
    public boolean isEnd;
    public TrieNode[] children;

    public TrieNode() {
        path = 0;
        isEnd = false;
        children = new TrieNode[26];
    }

    /**
     * 插入单词
     * @param word
     */
    public void insert(String word) {
        TrieNode node = this;
        for (int i = 0; i < word.length(); i++) {
            char ch = word.charAt(i);
            int index = ch - 'a';
            if (node.children[index] == null) {
                node.children[index] = new TrieNode();
            }

            node.path++;
            node = node.children[index];
            // 每一个节点都有一个过程word
            if (node.word == null) {
                node.word = word.substring(0, i + 1);
            }
        }

        // 只有单词结尾才有word
//        if (node.word == null) {
//            node.word = word;
//        }
        // 加上一个标记，表示为一个单词
        node.isEnd = true;
    }

    /**
     * word 是否在字段树里.
     * @param word
     * @return
     */
    public boolean search(String word) {
        TrieNode node = searchPrefix(word);
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
    private TrieNode searchPrefix(String prefix) {
        TrieNode node = this;
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
