package com.demo.common.entity;

/**
 * TrieNode
 * 字典树（前缀树）定义：每个节点最多有26个不同的小写字母
 *  时间复杂度：初始化为 O(1)O(1)，其余操作为 O(|S|)O(∣S∣)，其中 |S|∣S∣ 是每次插入或查询的字符串的长度。
 *  空间复杂度：O(|T|\cdot\Sigma)O(∣T∣⋅Σ)，其中 |T|∣T∣ 为所有插入字符串的长度之和，\SigmaΣ 为字符集的大小，本题 \Sigma=26Σ=26
 *  可参考: Code_01_TrieTree
 * @author liuzhen
 * @version 1.0.0 2021/8/22 17:59
 */
public class TrieNode {
    public String word;
    // 有多少字符串到达过，既生成树过程中的字母出现（经过）的次数
    public int path;
    // 用于判断是否是一个单词
    public boolean isEnd;
    // 有多少字符串在这个节点结尾 （注：有了end的存在，则不需要上面的isEnd字段了）
    public int end;
    public TrieNode[] children;

    public TrieNode() {
        path = 0;
        isEnd = false;
        end = 0;
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

            // 先跳到当前字符的节点，在做下面需要的操作
            node = node.children[index];

            node.path++;
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
        node.end++;
    }

    /**
     * 删除单词
     * @param word
     */
    public void delete(String word) {
        // 先判断word是否存在
        if (search(word)) {
            char[] chs = word.toCharArray();
            TrieNode node = this;
            int index = 0;
            for (int i = 0; i < chs.length; i++) {
                index = chs[i] - 'a';
                // 判断当前node所对应的path个数出现了几次，如果为0，则该node后面的node就不用再往下跑了，后面的node直接设为赋值为空
                if (--node.children[index].path == 0) {
                    node.children[index] = null;
                    // 直接返回
                    return;
                }
                // 如果大于0，则node继续往下跑（因为--node的存在）
                node = node.children[index];
            }

            // 跑到最下面一个node，
            node.end--;
        }
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
     * word 在字典树里出现的次数.
     * @param word
     * @return
     */
    public int searchCount(String word) {
        TrieNode node = searchPrefix(word);
        return node == null ? 0 : node.end;
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
     * 返回生成树过程中的字母出现（经过）的次数
     * @param prefix
     * @return
     */
    public int startsWithCount(String prefix) {
        TrieNode node = searchPrefix(prefix);
        return node == null ? 0 : node.path;
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
