/*
 * Copyright 2020-2021, 木木996.
 * All Right Reserved.
 */

package com.mumu.geek.c9_dfs_bfs;

import java.util.HashSet;
import java.util.Set;

/**
 * LeetCode433 最小基因变化
 * 
 * @author liuzhen
 * @version 1.0.0 2021/8/16 14:02
 */
public class LeetCode433 {

    /**
     * 一条基因序列由一个带有8个字符的字符串表示，其中每个字符都属于 "A", "C", "G", "T"中的任意一个。 假设我们要调查一个基因序列的变化。一次基因变化意味着这个基因序列中的一个字符发生了变化。
     * 例如，基因序列由"AACCGGTT" 变化至 "AACCGGTA" 即发生了一次基因变化。 与此同时，每一次基因变化的结果，都需要是一个合法的基因串，即该结果属于一个基因库。 现在给定3个参数 — start, end,
     * bank，分别代表起始基因序列，目标基因序列及基因库， 请找出能够使起始基因序列变化为目标基因序列所需的最少变化次数。如果无法实现目标变化，请返回 -1。
     *
     * 注意： 起始基因序列默认是合法的，但是它并不一定会出现在基因库中。 如果一个起始基因序列需要多次变化，那么它每一次变化之后的基因序列都必须是合法的。 假定起始基因序列与目标基因序列是不一样的。   示例 1： start:
     * "AACCGGTT" end: "AACCGGTA" bank: ["AACCGGTA"] 返回值: 1
     *
     * 示例 2： start: "AACCGGTT" end: "AAACGGTA" bank: ["AACCGGTA", "AACCGCTA", "AAACGGTA"] 返回值: 2
     *
     * 示例 3： start: "AAAAACCC" end: "AACCCCCC" bank: ["AAAACCCC", "AAACCCCC", "AACCCCCC"] 返回值: 3
     */

    /**
     * TODO 无记忆化版本（存在问题）
     * https://leetcode-cn.com/problems/minimum-genetic-mutation/solution/java-shuang-xiang-kuan-sou-xiang-xi-zhu-h302z/
     * 
     * @author liuzhen
     * @date 2021/10/8 17:23
     * @param start
     * @param end
     * @param bank
     * @return int
     */
    public static int minMutation(String start, String end, String[] bank) {
        // 定义三个集合，分别是合法基因集合，起始基因集合，目标基因集合
        Set<String> dict = new HashSet<>();
        Set<String> st = new HashSet<>();
        Set<String> ed = new HashSet<>();
        for (String s : bank)
            dict.add(s);
        // 基因库中不包含目标，则无法转换
        if (!dict.contains(end)) {
            return -1;
        }

        st.add(start);
        ed.add(end);
        // 宽搜
        return bfs(st, ed, dict, 0);
    }

    // 宽搜
    private static int bfs(Set<String> st, Set<String> ed, Set<String> dict, int len) {
        // 起始集合为空，那么就无法到达目标
        if (st.size() == 0) {
            return -1;
        }

        // 优先从数量少的一端开始搜索，减少搜索量
        if (st.size() > ed.size()) {
            return bfs(ed, st, dict, len);
        }

        Set<String> next = new HashSet<>();
        char[] mode = {'A', 'C', 'G', 'T'};
        // 枚举起始集合可以一步转换的所有基因序列
        for (String s : st) {
            StringBuilder temp = new StringBuilder(s);
            for (int i = 0; i < 8; i++) {
                for (int j = 0; j < 4; j++) {
                    // 不包含相同的字符
                    if (s.charAt(i) == mode[j]) {
                        continue;
                    }
                    temp.setCharAt(i, mode[j]);
                    String cur = temp.toString();
                    // 终点集合中包含了当前字符，那么直接返回步数
                    if (ed.contains(cur)) {
                        return len + 1;
                    }
                    // 如果是合法序列，则加入下一个搜索集合中
                    if (dict.contains(cur)) {
                        next.add(cur);
                    }
                    temp.setCharAt(i, s.charAt(i));
                }
            }
        }

        // 搜索下一层
        return bfs(next, ed, dict, len + 1);
    }

    /**
     * TODO 记忆化版本
     * 
     * @author liuzhen
     * @date 2021/10/8 17:24
     * @param start
     * @param end
     * @param bank
     * @return int
     */
    public static int minMutation2(String start, String end, String[] bank) {
        // 定义三个集合，分别是合法基因集合，
        Set<String> dict = new HashSet<>();
        // 起始基因集合
        Set<String> st = new HashSet<>();
        // 目标基因集合
        Set<String> ed = new HashSet<>();
        // 起始基因记忆集
        Set<String> menSt = new HashSet<>();
        // 目标基因记忆集
        Set<String> menEd = new HashSet<>();

        for (String s : bank) {
            dict.add(s);
        }

        // 基因库中不包含目标，则无法转换
        if (!dict.contains(end)) {
            return -1;
        }

        st.add(start);
        ed.add(end);
        // 宽搜
        return bfs2(st, ed, menSt, menEd, dict, 0);
    }

    // 宽搜
    private static int bfs2(Set<String> st, Set<String> ed, Set<String> menSt, Set<String> menEd, Set<String> dict,
        int len) {
        // 起始集合为空，那么就无法到达目标
        if (st.size() == 0) {
            return -1;
        }

        // 优先从数量少的一端开始搜索，减少搜索量
        if (st.size() > ed.size()) {
            return bfs2(ed, st, menEd, menSt, dict, len);
        }

        Set<String> next = new HashSet<>();
        char[] mode = {'A', 'C', 'G', 'T'};
        // 枚举起始集合可以一步转换的所有基因序列
        for (String s : st) {
            StringBuilder temp = new StringBuilder(s);
            for (int i = 0; i < 8; i++) {
                for (int j = 0; j < 4; j++) {
                    temp.setCharAt(i, mode[j]);
                    String cur = temp.toString();
                    // 终点集合中包含了当前字符，那么直接返回步数
                    if (ed.contains(cur)) {
                        return len + 1;
                    }
                    // 如果搜过了该种情况，就不能重复遍历
                    if (menSt.contains(cur)) {
                        continue;
                    }

                    // 如果是合法序列，则加入下一个搜索集合中
                    if (dict.contains(cur)) {
                        next.add(cur);
                        menSt.add(cur);
                    }

                    temp.setCharAt(i, s.charAt(i));
                }
            }
        }

        // 搜索下一层
        return bfs2(next, ed, menSt, menEd, dict, len + 1);
    }

}
