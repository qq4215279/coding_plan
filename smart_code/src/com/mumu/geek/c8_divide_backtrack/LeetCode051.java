/*
 * Copyright 2020-2021, 木木996.
 * All Right Reserved.
 */

package com.mumu.geek.c8_divide_backtrack;

import java.util.*;

/**
 * LeetCode051
 * N皇后
 * @author liuzhen
 * @version 1.0.0 2021/8/15 17:16
 */
public class LeetCode051 { //

    /**
     * n 皇后问题 研究的是如何将 n 个皇后放置在 n×n 的棋盘上，并且使皇后彼此之间不能相互攻击。
     * 给你一个整数 n ，返回所有不同的 n 皇后问题 的解决方案。
     * 每一种解法包含一个不同的 n 皇后问题 的棋子放置方案，该方案中 'Q' 和 '.' 分别代表了皇后和空位。
     *
     * 示例 1：
     * 输入：n = 4
     * 输出：[
     *          [" .  Q  .  . ",
     *           " .  .  .  Q ",
     *           " Q  .  .  . ",
     *           " .  .  Q  . "],
     *          [" .  .  Q  . ",
     *           " Q  .  .  . ",
     *           " .  .  .  Q ",
     *           " .  Q  .  . "]
     *      ]
     * 解释：如上图所示，4 皇后问题存在两个不同的解法。
     *
     * 示例 2：
     * 输入：n = 1
     * 输出：[["Q"]]
     */

    /**
     * 基于集合的回溯
     * @author liuzhen
     * @date 2021/8/15 17:46
     * @param n
     * @return java.util.List<java.util.List<java.lang.String>>
     */
    public static List<List<String>> solveNQueens(int n) {
        List<List<String>> res = new ArrayList<>();
        // 定义每行皇后所在的index的数组
        int[] queens = new int[n];
        // 初始化皇后位置为--1
        Arrays.fill(queens, -1);
        // 列
        Set<Integer> columnSet = new HashSet<>();
        // na
        Set<Integer> naSet = new HashSet<>();
        // pie
        Set<Integer> pieSet = new HashSet<>();
        backtrack(res, queens, n, 0, columnSet, naSet, pieSet);
        return res;
    }

    /**
     * 回溯
     * 重点：
     * 1. 判断最近左上斜对角元素：int na = row - i;
     *      . Q .   Q: 0 - 1 = -1
     *      . . i   i: 1 - 2 = -1
     * 2. 判断最近右上斜对角元素：int pie = row + i;
     *      . Q     Q: 0 + 1 = 1
     *      i .     i: 1 + 0 = 1
     * @param res
     * @param queens
     * @param n
     * @param row
     * @param columnSet
     * @param naSet
     * @param pieSet
     */
    private static void backtrack(List<List<String>> res, int[] queens, int n, int row, Set<Integer> columnSet, Set<Integer> naSet, Set<Integer> pieSet) {
        if (row == n) {
            List<String> board = generateBoard(queens, n);
            res.add(board);
            return;
        }

        // 每次递归后都是一行一行横着遍历
        for (int column = 0; column < n; column++) {
            // 判断这一列有没有皇后
            if (columnSet.contains(column)) {
                continue;
            }
            /*
            * 每加一行row，则列column也要加1，才能叫做在同一“na”或在同一“pie”上
            * 判断最近左上斜对角元素：int na = row - column;
            * . Q .   Q: 0 - 1 = -1
            * . . i   i: 1 - 2 = -1
            */
            int na = row - column;
            if (naSet.contains(na)) {
                continue;
            }
            /*
             * 判断最近右上斜对角元素：int pie = row + column;
             * . Q    Q: 0 + 1 = 1
             * i .    i: 1 + 0 = 1
             */
            int pie = row + column;
            if (pieSet.contains(pie)) {
                continue;
            }

            // 记录一个皇后
            queens[row] = column;

            // 暂存lie、na、pie的Q位置
            columnSet.add(column);
            naSet.add(na);
            pieSet.add(pie);

            // 递归下一行，找下一行的Q位置
            backtrack(res, queens, n, row + 1, columnSet, naSet, pieSet);

            // 将每次递归中间过程产生的数清除
            queens[row] = -1;
            columnSet.remove(column);
            naSet.remove(na);
            pieSet.remove(pie);
        }
    }

    private static List<String> generateBoard(int[] queens, int n) {
        List<String> board = new ArrayList<String>();
        for (int i = 0; i < n; i++) {
            char[] row = new char[n];
            Arrays.fill(row, '.');
            row[queens[i]] = 'Q';
            board.add(new String(row));
        }
        return board;
    }

    public static void main(String[] args) {
        List<List<String>> lists = solveNQueens(4);
        System.out.println(lists); // [[.Q.., ...Q, Q..., ..Q.], [..Q., Q..., ...Q, .Q..]]
    }

}
