package com.mumu.geek.c13_trie_disjoint_set;

/**
 * LeetCode208
 * 实现前缀树
 * @author liuzhen
 * @version 1.0.0 2021/8/22 15:50
 */
public class LeetCode208 {

    /**
     * Trie（发音类似 "try"）或者说 前缀树 是一种树形数据结构，用于高效地存储和检索字符串数据集中的键。这一数据结构有相当多的应用情景，例如自动补完和拼写检查。
     *
     * 请你实现 Trie 类：
     * Trie() 初始化前缀树对象。
     * void insert(String word) 向前缀树中插入字符串 word 。
     * boolean search(String word) 如果字符串 word 在前缀树中，返回 true（即，在检索之前已经插入）；否则，返回 false 。
     * boolean startsWith(String prefix) 如果之前已经插入的字符串 word 的前缀之一为 prefix ，返回 true ；否则，返回 false 。
     *  
     * 示例：
     * 输入
     * ["Trie", "insert", "search", "search", "startsWith", "insert", "search"]
     * [[], ["apple"], ["apple"], ["app"], ["app"], ["app"], ["app"]]
     * 输出
     * [null, null, true, false, true, null, true]
     *
     * 解释
     * Trie trie = new Trie();
     * trie.insert("apple");
     * trie.search("apple");   // 返回 True
     * trie.search("app");     // 返回 False
     * trie.startsWith("app"); // 返回 True
     * trie.insert("app");
     * trie.search("app");     // 返回 True
     *  
     *
     * 提示：
     * 1 <= word.length, prefix.length <= 2000
     * word 和 prefix 仅由小写英文字母组成
     * insert、search 和 startsWith 调用次数 总计 不超过 3 * 104 次
     */

    /**
     * Your Trie object will be instantiated and called as such:
     * Trie obj = new Trie();
     * obj.insert(word);
     * boolean param_2 = obj.search(word);
     * boolean param_3 = obj.startsWith(prefix);
     */
    public class Trie { // 每个节点最多有26个不同的小写字母
        // 用于判断是否是一个单词
        private boolean isEnd;
        private Trie[] children;

        public Trie() {
            isEnd = false;
            children = new Trie[26];
        }

        /**
         * 插入单词
         * @param word
         */
        public void insert(String word) {
            Trie node = this;
            for (int i = 0; i < word.length(); i++) {
                char ch = word.charAt(i);
                int index = ch - 'a';
                if (node.children[index] == null) {
                    node.children[index] = new Trie();
                }
                node = node.children[index];
            }
            // 加上一个标记，表示为一个单词
            node.isEnd = true;
        }

        /**
         * word 是否在字段树里.
         * @param word
         * @return
         */
        public boolean search(String word) {
            Trie node = searchPrefix(word);
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
        private Trie searchPrefix(String prefix) {
            Trie node = this;
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

}



