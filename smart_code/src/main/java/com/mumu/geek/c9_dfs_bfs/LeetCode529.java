/*
 * Copyright 2020-2021, 木木996.
 * All Right Reserved.
 */

package com.mumu.geek.c9_dfs_bfs;

import java.util.LinkedList;
import java.util.Queue;

/**
 * LeetCode529
 * 扫雷游戏
 * @author liuzhen
 * @version 1.0.0 2021/8/16 14:10
 */
public class LeetCode529 {

    /**
     * 让我们一起来玩扫雷游戏！
     * 给定一个代表游戏板的二维字符矩阵。 'M' 代表一个未挖出的地雷，'E' 代表一个未挖出的空方块，'B' 代表没有相邻（上，下，左，右，和所有4个对角线）地雷的已挖出的空白方块，
     * 数字（'1' 到 '8'）表示有多少地雷与这块已挖出的方块相邻，'X' 则表示一个已挖出的地雷。
     * 现在给出在所有未挖出的方块中（'M'或者'E'）的下一个点击位置（行和列索引），根据以下规则，返回相应位置被点击后对应的面板：
     * 1. 如果一个地雷（'M'）被挖出，游戏就结束了- 把它改为 'X'。
     * 2. 如果一个没有相邻地雷的空方块（'E'）被挖出，修改它为（'B'），并且所有和其相邻的未挖出方块都应该被递归地揭露。
     * 3. 如果一个至少与一个地雷相邻的空方块（'E'）被挖出，修改它为数字（'1'到'8'），表示相邻地雷的数量。
     * 4. 如果在此次点击中，若无更多方块可被揭露，则返回面板。
     *  
     * 示例 1：
     * 输入:
     * [['E', 'E', 'E', 'E', 'E'],
     *  ['E', 'E', 'M', 'E', 'E'],
     *  ['E', 'E', 'E', 'E', 'E'],
     *  ['E', 'E', 'E', 'E', 'E']]
     * Click : [3,0]
     * 输出:
     * [['B', '1', 'E', '1', 'B'],
     *  ['B', '1', 'M', '1', 'B'],
     *  ['B', '1', '1', '1', 'B'],
     *  ['B', 'B', 'B', 'B', 'B']]
     *
     * 解释: ./pic/扫雷游戏_解释1
     *
     * 示例 2：
     * 输入:
     * [['B', '1', 'E', '1', 'B'],
     *  ['B', '1', 'M', '1', 'B'],
     *  ['B', '1', '1', '1', 'B'],
     *  ['B', 'B', 'B', 'B', 'B']]
     * Click : [1,2]
     * 输出:
     * [['B', '1', 'E', '1', 'B'],
     *  ['B', '1', 'X', '1', 'B'],
     *  ['B', '1', '1', '1', 'B'],
     *  ['B', 'B', 'B', 'B', 'B']]
     *
     * 解释: ./pic/扫雷游戏_解释2
     *
     * 注意：
     * 输入矩阵的宽和高的范围为 [1,50]。
     * 点击的位置只能是未被挖出的方块 ('M' 或者 'E')，这也意味着面板至少包含一个可点击的方块。
     * 输入面板不会是游戏结束的状态（即有地雷已被挖出）。
     * 简单起见，未提及的规则在这个问题中可被忽略。例如，当游戏结束时你不需要挖出所有地雷，考虑所有你可能赢得游戏或标记方块的情况。
     */

    public static int[] dirX = {0, 1, 0, -1, 1, 1, -1, -1};
    public static int[] dirY = {1, 0, -1, 0, 1, -1, 1, -1};

    /**
     * 深度优先搜索 + 模拟  时间复杂度：O(nm) 空间复杂度：O(nm)
     * https://leetcode-cn.com/problems/minesweeper/solution/sao-lei-you-xi-by-leetcode-solution/
     * @author liuzhen
     * @date 2021/10/8 18:03
     * @param board
     * @param click
     * @return char[][]
     */
    public static char[][] updateBoard(char[][] board, int[] click) {
        int x = click[0];
        int y = click[1];
        if (board[x][y] == 'M') {
            // 规则 1
            board[x][y] = 'X';
        } else{
            dfs(board, x, y);
        }
        return board;
    }

    public static void dfs(char[][] board, int x, int y) {
        int cnt = 0;
        // 找出周围有几个雷
        for (int i = 0; i < 8; i++) {
            int tx = x + dirX[i];
            int ty = y + dirY[i];
            // 越界
            if (tx < 0 || tx >= board.length || ty < 0 || ty >= board[0].length) {
                continue;
            }
            // 不用判断 M，因为如果有 M 的话游戏已经结束了
            if (board[tx][ty] == 'M') {
                cnt++;
            }
        }

        // 有雷
        if (cnt > 0) {
            // 规则 3
            board[x][y] = (char) (cnt + '0');
        } else {
            // 规则 2
            board[x][y] = 'B';
            for (int i = 0; i < 8; i++) {
                int tx = x + dirX[i];
                int ty = y + dirY[i];
                // 这里不需要在存在 B 的时候继续扩展，因为 B 之前被点击的时候已经被扩展过了
                if (tx < 0 || tx >= board.length || ty < 0 || ty >= board[0].length || board[tx][ty] != 'E') {
                    continue;
                }

                dfs(board, tx, ty);
            }
        }
    }


    /**
     * 广度优先搜索 + 模拟  时间复杂度：O(nm) 空间复杂度：O(nm)
     * @author liuzhen
     * @date 2021/10/8 18:05
     * @param board
     * @param click
     * @return char[][]
     */
    public char[][] updateBoard2(char[][] board, int[] click) {
        int x = click[0];
        int y = click[1];
        if (board[x][y] == 'M') {
            // 规则 1
            board[x][y] = 'X';
        } else{
            bfs(board, x, y);
        }
        return board;
    }

    public static void bfs(char[][] board, int sx, int sy) {
        boolean[][] visited = new boolean[board.length][board[0].length];
        visited[sx][sy] = true;
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{sx, sy});

        while (!queue.isEmpty()) {
            int[] pos = queue.poll();
            int cnt = 0;
            int x = pos[0];
            int y = pos[1];
            // 判断周围有几个雷
            for (int i = 0; i < 8; i++) {
                int tx = x + dirX[i];
                int ty = y + dirY[i];
                if (tx < 0 || tx >= board.length || ty < 0 || ty >= board[0].length) {
                    continue;
                }
                // 不用判断 M，因为如果有 M 的话游戏已经结束了
                if (board[tx][ty] == 'M') {
                    cnt++;
                }
            }
            // 有雷
            if (cnt > 0) {
                // 规则 3
                board[x][y] = (char) (cnt + '0');
            } else {
                // 规则 2
                board[x][y] = 'B';
                for (int i = 0; i < 8; ++i) {
                    int tx = x + dirX[i];
                    int ty = y + dirY[i];
                    // 这里不需要在存在 B 的时候继续扩展，因为 B 之前被点击的时候已经被扩展过了
                    if (tx < 0 || tx >= board.length || ty < 0 || ty >= board[0].length || board[tx][ty] != 'E' || visited[tx][ty]) {
                        continue;
                    }
                    queue.offer(new int[]{tx, ty});
                    visited[tx][ty] = true;
                }
            }
        }
    }


}
