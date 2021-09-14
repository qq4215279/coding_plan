package com.demo.geek.c13_trie_disjoint_set;

import com.demo.common.entity.UnionFind;
import com.demo.common.utils.ArrayUtil;

/**
 * LeetCode130
 * 被包围的区域
 * @author liuzhen
 * @version 1.0.0 2021/8/22 15:56
 */
public class LeetCode130 {

    /**
     * 给你一个 m x n 的矩阵 board ，由若干字符 'X' 和 'O' ，找到所有被 'X' 围绕的区域，并将这些区域里所有的 'O' 用 'X' 填充。
     *  
     * 示例 1：
     *  x   x   x   x       x   x   x   x
     *  x   o   o   x  =>   x   x   x   x
     *  x   x   o   x       x   x   x   x
     *  x   o   x   x       x   o   x   x
     *
     * 输入：board = [["X","X","X","X"],["X","O","O","X"],["X","X","O","X"],["X","O","X","X"]]
     * 输出：[["X","X","X","X"],["X","X","X","X"],["X","X","X","X"],["X","O","X","X"]]
     * 解释：被围绕的区间不会存在于边界上，换句话说，任何边界上的 'O' 都不会被填充为 'X'。 任何不在边界上，或不与边界上的 'O' 相连的 'O' 最终都会被填充为 'X'。如果两个元素在水平或垂直方向相邻，则称它们是“相连”的。
     *
     * 示例 2：
     * 输入：board = [["X"]]
     * 输出：[["X"]]
     *  
     *
     * 提示：
     * m == board.length
     * n == board[i].length
     * 1 <= m, n <= 200
     * board[i][j] 为 'X' 或 'O'
     */

    /**
     * DFS
     * @author liuzhen
     * @date 2021/8/22 22:56
     * @param board
     * @return void
     */
    public static void solveByDFS(char[][] board) {
        if (board == null || board.length == 0) return;
        int m = board.length;
        int n = board[0].length;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                // 从边缘o开始搜索
                boolean isEdge = i == 0 || j == 0 || i == m - 1 || j == n - 1;
                if (isEdge && board[i][j] == 'O') {
                    dfs(board, i, j);
                }
            }
        }

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (board[i][j] == 'O') {
                    board[i][j] = 'X';
                }
                if (board[i][j] == '#') {
                    board[i][j] = 'O';
                }
            }
        }
    }

    private static void dfs(char[][] board, int i, int j) {
        if (i < 0 || j < 0 || i >= board.length  || j >= board[0].length || board[i][j] == 'X' || board[i][j] == '#') {
            // board[i][j] == '#' 说明已经搜索过了.
            return;
        }
        board[i][j] = '#';
        dfs(board, i - 1, j); // 上
        dfs(board, i + 1, j); // 下
        dfs(board, i, j - 1); // 左
        dfs(board, i, j + 1); // 右
    }

    /**
     * 并查集
     * https://leetcode-cn.com/problems/surrounded-regions/solution/bfsdi-gui-dfsfei-di-gui-dfsbing-cha-ji-by-ac_pipe/
     * @author liuzhen
     * @date 2021/8/22 23:01
     * @param board
     * @return void
     */
    public static void solveByDisjointSet(char[][] board) {
        if (board == null || board.length == 0)
            return;

        int rows = board.length;
        int cols = board[0].length;

        // 用一个虚拟节点, 边界上的 O 的父节点都是这个虚拟节点
        UnionFind uf = new UnionFind(rows * cols + 1);
        int dummyNode = rows * cols;

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                // 遇到O进行并查集操作合并
                if (board[i][j] == 'O') {
                    // 将第一行 || 最后一行 || 第一列 || 最后一列的 O 进行合并
                    if (i == 0 || i == rows - 1 || j == 0 || j == cols - 1) {
                        // 边界上的O,把它和 dummyNode 合并成一个连通区域.
                        uf.union(ArrayUtil.getReferencePoint(i, j, cols), dummyNode);
                    } else { // 将不是边界的O进行合并   注：下面的合并操作不需要也是可以的！
                        // 和上下左右合并成一个连通区域.
                       /* if (i > 0 && board[i - 1][j] == 'O')
                            uf.union(ArrayUtil.getReferencePoint(i, j, cols), ArrayUtil.getReferencePoint(i - 1, j, cols));
                        if (i < rows - 1 && board[i + 1][j] == 'O')
                            uf.union(ArrayUtil.getReferencePoint(i, j, cols), ArrayUtil.getReferencePoint(i + 1, j, cols));
                        if (j > 0 && board[i][j - 1] == 'O')
                            uf.union(ArrayUtil.getReferencePoint(i, j, cols), ArrayUtil.getReferencePoint(i, j - 1, cols));
                        if (j < cols - 1 && board[i][j + 1] == 'O')
                            uf.union(ArrayUtil.getReferencePoint(i, j, cols), ArrayUtil.getReferencePoint(i, j + 1, cols));*/
                    }
                }
            }
        }

        // update O -> X
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                // 不和 dummyNode 在一个连通区域的，那么就是X；
                if (!uf.isConnected(ArrayUtil.getReferencePoint(i, j, cols), dummyNode)) {
                    board[i][j] = 'X';
                } else { // 因为本来就是O，所以下面这部分代码也可省去
//                    board[i][j] = 'O';
                }
            }
        }
    }

    public static void main(String[] args) {
        char[][] board = {{'X','X','X','X'}, {'X','O','O','X'}, {'X','X','O','X',}, {'X','O','X','X',}};

//        solveByDFS(board);
        // 并查集
        solveByDisjointSet(board);

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                System.out.print(board[i][j]);
            }
            System.out.println();
        }
    }

}
