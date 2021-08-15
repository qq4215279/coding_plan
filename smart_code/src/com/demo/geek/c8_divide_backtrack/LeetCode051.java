package com.demo.geek.c8_divide_backtrack;

import java.util.*;

/**
 * LeetCode051
 * N皇后
 * @author liuzhen
 * @version 1.0.0 2021/8/15 17:16
 */
public class LeetCode051 {

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
    public List<List<String>> solveNQueens(int n) {
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
     * @param res
     * @param queens
     * @param n
     * @param row
     * @param columnSet
     * @param naSet
     * @param pieSet
     */
    public static void backtrack(List<List<String>> res, int[] queens, int n, int row, Set<Integer> columnSet, Set<Integer> naSet,
                          Set<Integer> pieSet) {
        if (row == n) {
            List<String> board = generateBoard(queens, n);
            res.add(board);
            return;
        }

        for (int i = 0; i < n; i++) {
            if (columnSet.contains(i)) {
                continue;
            }
            int na = row - i;
            if (naSet.contains(na)) {
                continue;
            }
            int pie = row + i;
            if (pieSet.contains(pie)) {
                continue;
            }

            queens[row] = i;
            columnSet.add(i);
            naSet.add(na);
            pieSet.add(pie);
            //
            backtrack(res, queens, n, row + 1, columnSet, naSet, pieSet);
            //
            queens[row] = -1;
            columnSet.remove(i);
            naSet.remove(na);
            pieSet.remove(pie);
        }
    }

    public static List<String> generateBoard(int[] queens, int n) {
        List<String> board = new ArrayList<String>();
        for (int i = 0; i < n; i++) {
            char[] row = new char[n];
            Arrays.fill(row, '.');
            row[queens[i]] = 'Q';
            board.add(new String(row));
        }
        return board;
    }

}
