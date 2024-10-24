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
     * 方法1：基于集合的回溯
     * 时间复杂度：O(N!)，其中 NN 是皇后数量。
     * 空间复杂度：O(N)，其中 NN 是皇后数量。
     * @author liuzhen
     * @date 2021/8/15 17:46
     * @param n
     * @return java.util.List<java.util.List<java.lang.String>>
     */
    public static List<List<String>> solveNQueens(int n) {
        List<List<String>> res = new ArrayList<>();
        // 定义每行皇后所在的index的数组
        int[] queens = new int[n];
        // 初始化皇后位置为-1
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

    
    /**
     * 方法2：基于位运算的回溯
     * 时间复杂度：O(N!)，其中 NN 是皇后数量。
     * 空间复杂度：O(N)，其中 NN 是皇后数量。
     * 官方题解 - 位运算: https://leetcode-cn.com/problems/n-queens/solution/nhuang-hou-by-leetcode-solution/
     * @author liuzhen
     * @date 2022/4/23 16:40
     * @param n
     * @return java.util.List<java.util.List<java.lang.String>>
     */
    public static List<List<String>> solveNQueensByBitOp(int n) {
        List<List<String>> res = new ArrayList<>();
        // 这个数组用于记录每行中皇后所在的位置
        int[] queens = new int[n];
        // 官方在这里用-1填充queens数组，但是和下面的状态重置一样，没有必要
        // Arrays.fill(queens,-1);
        Arrays.fill(queens, -1);

        backtrackByBitOp(res, queens, n, 0, 0, 0, 0);
        return res;
    }

    /**
     *
     * 三个整数(columns nas pies)的计算方法：
     * 1. 初始时，三个整数的值都等于 0，表示没有放置任何皇后；
     * 2. 在当前行放置皇后，如果皇后放置在第 i 列，则将三个整数的第 i 个二进制位（指从低到高的第 i 个二进制位）的值设为 1；
     * 3. 进入下一行时，columns 的值保持不变，nas 左移一位，pies右移一位，由于棋盘的最左列对应每个整数的最低二进制位，即每个整数的最右二进制位，
     *   因此对整数的移位操作方向和对棋盘的移位操作方向相反（对棋盘的移位操作方向是 nas 右移一位，pies左移一位）。
     *
     * 每次放置皇后时，三个整数的按位或运算的结果即为不能放置皇后的位置，其余位置即为可以放置皇后的位置。可以通过 (2^n-1) & (~(columns | nas | pies))
     * 得到可以放置皇后的位置（该结果的值为 1 的位置表示可以放置皇后的位置），然后遍历这些位置，尝试放置皇后并得到可能的解。
     *
     * 遍历可以放置皇后的位置时，可以利用以下两个按位与运算的性质：
     * 1. x & (−x) 可以获得 x 的二进制表示中的最低位的 1 的位置；
     * 2. x & (x−1) 可以将 x 的二进制表示中的最低位的 1 置成 0。
     * 具体做法是，每次获得可以放置皇后的位置中的最低位，并将该位的值置成 0，尝试在该位置放置皇后。这样即可遍历每个可以放置皇后的位置。
     *
     * @author liuzhen
     * @date 2022/4/23 16:51
     * @param solutions
     * @param queens
     * @param n 总行数
     * @param row 当前行数
     * @param columns 不可选的列
     * @param nas 不可选的na
     * @param pies 不可选的pie
     * @return void
     */
    private static void backtrackByBitOp(List<List<String>> solutions, int[] queens, int n, int row, int columns, int nas, int pies) {
        // 如果能到达这一步，说明搜索已经到底了，我们已经记录下了一个可行的方案
        if (row == n) {
            // 直接生成一个结果，并放入结果集中
            List<String> board = generateBoard(queens, n);
            solutions.add(board);
            return;
        }

        // 1 << n-1 是为了转化一个长度为n的，每位上都是1的二进制数，用于定位可以放置皇后的位置
        // 这里用于定位所有可选的位置，这里有一步取反，千万不要忽视了！
        // 上面我们用 1 表示不可选的位置，但是这里我们取反后，用1表示可选的位置
        int availablePositions = ((1 << n) - 1) & (~(columns | nas | pies));

        // 左斜边因为下降了一行需要左移一位
        nas <<= 1;
        // 右斜边因为下降了一行需要右移一位
        pies >>= 1;

        // 开始检查每个可选的位置
        while (availablePositions != 0) {
            // 定位最后一个1的位置，这个操作可以自己手写验证一下（不要忘了把负数转成补码）
            // 这个定位的意思是，生成的这个二进制数只有最后一个1还为1，其他位都变成了0
            int position = availablePositions & (-availablePositions);

            // 将这一位从可选取的位中移除
            // 减1把最后一个1拆成后面的多个1，再经过一次与操作把这些多出来的1全部清除
            availablePositions = availablePositions & (availablePositions - 1);

            // 这个方法是统计一个二进制数中所有的“1”的个数
            int columnNum = Integer.bitCount(position - 1);
            // 将这个位置添加到记录数组中
            queens[row] = columnNum;

            // 沿着这个位置向下搜索，可选行和可选列的直接在参数上变化即可，这样就不需要手动重置状态了
            backtrackByBitOp(solutions, queens, n, row + 1, columns | position, nas | position << 1, pies | position >> 1);
            // backtrackByBitOp(solutions, queens, n, row + 1, columns | position, (nas | position) << 1, (pies | position) >> 1);

            // 官方在这里曾经重置过数组queens的状态，但实际上没这个必要，每次循环上一次的结果都会被覆盖
            // queens[row] = -1;
        }
    }

    /**
     * 生成字符串
     * @param queens
     * @param n
     * @return
     */
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
