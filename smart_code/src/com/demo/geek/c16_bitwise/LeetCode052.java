package com.demo.geek.c16_bitwise;

/**
 * LeetCode052
 * N皇后2
 * @author liuzhen
 * @version 1.0.0 2021/8/26 21:31
 */
public class LeetCode052 {

    /**
     * n 皇后问题 研究的是如何将 n 个皇后放置在 n×n 的棋盘上，并且使皇后彼此之间不能相互攻击。
     * 给你一个整数 n ，返回 n 皇后问题 不同的解决方案的数量。
     *
     * 示例 1：
     * 输入：n = 4
     * 输出：2
     * 解释：如上图所示，4 皇后问题存在两个不同的解法。
     *
     * 示例 2：
     * 输入：n = 1
     * 输出：1
     *
     * 提示：
     * 1 <= n <= 9
     * 皇后彼此不能相互攻击，也就是说：任何两个皇后都不能处于同一条横行、纵行或斜线上。
     */

    private int size;
    private int count;
    public int totalNQueens(int n) {
        count = 0;
        size = (1 << n) - 1;
        sovle(0, 0, 0);

        return count;
    }

    private void sovle(int row, int ld, int rd) {
        if (row == size) {
            count++;
            return;
        }

        int pos = size & (~(row | ld | rd));
        while (pos != 0) {
            int p = pos & (-pos);
            pos -= p; // pos &= pos -1;
            sovle(row | p, (ld | p) << 1, (rd | p) >> 1);
        }
    }

}
