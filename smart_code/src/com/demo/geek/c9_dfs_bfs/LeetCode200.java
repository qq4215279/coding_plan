package com.demo.geek.c9_dfs_bfs;

/**
 * LeetCode200
 * 岛屿数量
 * @author liuzhen
 * @version 1.0.0 2021/8/16 14:08
 */
public class LeetCode200 {

    /**
     * 给你一个由 '1'（陆地）和 '0'（水）组成的的二维网格，请你计算网格中岛屿的数量。
     * 岛屿总是被水包围，并且每座岛屿只能由水平方向和/或竖直方向上相邻的陆地连接形成。
     * 此外，你可以假设该网格的四条边均被水包围。
     * 既：目标是找到矩阵中 “岛屿的数量” ，上下左右相连的 1 都被认为是连续岛屿。
     *
     * 示例 1：
     * 输入：grid = [
     *   ["1","1","1","1","0"],
     *   ["1","1","0","1","0"],
     *   ["1","1","0","0","0"],
     *   ["0","0","0","0","0"]
     * ]
     * 输出：1
     *
     * 示例 2：
     * 输入：grid = [
     *   ["1","1","0","0","0"],
     *   ["1","1","0","0","0"],
     *   ["0","0","1","0","0"],
     *   ["0","0","0","1","1"]
     * ]
     * 输出：3
     *  
     * 提示：
     * m == grid.length
     * n == grid[i].length
     * 1 <= m, n <= 300
     * grid[i][j] 的值为 '0' 或 '1'
     */

    /**
     * 解法1（深度优先搜索）：碰到岛就将1变为0，并将周围连着的1全部变为0，记录为一个岛，直到元素全部变为0，得到岛数量
     * 解法2（广度优先遍历）：
     */

    /** 
     * 深度优先遍历(DFS)     时间复杂度：O(MN)O(MN)，其中 MM 和 NN 分别为行数和列数。空间复杂度：O(MN)O(MN)，在最坏情况下，整个网格均为陆地，深度优先搜索的深度达到 M NMN。
     * 思路：
     *      设目前指针指向一个岛屿中的某一点 (i, j)，寻找包括此点的岛屿边界。从 (i, j) 向此点的上下左右 (i+1,j),(i-1,j),(i,j+1),(i,j-1) 做深度搜索。
     *      终止条件： (i, j) 越过矩阵边界; grid[i][j] == 0，代表此分支已越过岛屿边界。搜索岛屿的同时，执行 grid[i][j] = '0'，即将岛屿所有节点删除，以免之后重复搜索相同岛屿。
     *      主循环：遍历整个矩阵，当遇到 grid[i][j] == '1' 时，从此点开始做深度优先搜索 dfs，岛屿数 count + 1 且在深度优先搜索中删除此岛屿。最终返回岛屿数 count 即可。
     * @author liuzhen
     * @date 2021/8/16 21:43 
     * @param grid
     * @return int
     */
    public static int countIslands(char[][] grid) {
        if (grid == null || grid[0] == null) {
            return 0;
        }

        int res = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == '1') {
                    res++;
                    infect(grid, i, j);
                }
            }
        }
        return res;
    }

    /**
     * 感染函数: 负责将岛周围的岛感染成0
     * @param grid
     * @param i
     * @param j
     */
    private static void infect(char[][] grid, int i, int j) {
        // 元素越界 || 不是岛
        if (i < 0 || i >= grid.length || j < 0 || j >= grid[0].length || grid[i][j] != '1') {
            return;
        }

        // 将岛感染成0
        grid[i][j] = 0;

        // 对这个岛的上下左右进行判断是否进行感染
        infect(grid, i + 1, j);
        infect(grid, i - 1, j);
        infect(grid, i, j + 1);
        infect(grid, i, j - 1);
    }

    /**
     * 广度优先遍历(BFS)   时间复杂度：O(MN)O(MN)，其中 MM 和 NN 分别为行数和列数。空间复杂度：O(\min(M, N))O(min(M,N))，在最坏情况下，整个网格均为陆地，队列的大小可以达到 \min(M, N)min(M,N)
     * 思路：主循环和思路一类似，不同点是在于搜索某岛屿边界的方法不同。
     * @author liuzhen
     * @date 2021/8/16 21:27
     * @param grid
     * @return int
     */
    public static int numIslands(char[][] grid) {
        int dx[] = {-1, 1, 0, 0};
        int dy[] = {0, 0, -1, 1};
        int isLands = 0;

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j] == '0') {
                    continue;
                }
                isLands += sink(grid, i, j, dx, dy);
            }
        }

        return isLands;
    }

    private static int sink(char[][] grid, int i, int j, int dx[], int dy[]) {
        // terminator
        if (grid[i][j] == '0') {
            return 0;
        }

        // i,j == '1' 即将岛变为0
        grid[i][j] = '0';

        // 遍历当前岛周围的岛（左 右 下 上）
        for (int k = 0; k < dx.length; k++) {
            int x = i + dx[k];
            int y = j + dy[k];
            if (x >= 0 && x < grid.length && y >= 0 && y < grid[i].length) {
                if (grid[x][y] == '0') {
                    continue;
                }
                // 判断周围的岛有没有连着的岛
                sink(grid, x, y, dx, dy);
            }
        }

        return 1;
    }

    public static void main(String[] args) {
        char[][] grid1 = {{'1', '1', '1', '1', '0' }, {'1', '1', '0', '1', '0' }, {'1', '1', '0', '0', '0' }, {'0', '0', '0', '0', '0' }};
        System.out.println("深度优先遍历：" + countIslands(grid1));

        System.out.println("---------------------------->");

        char[][] grid2 = {{'1', '1', '1', '1', '0' }, {'1', '1', '0', '1', '0' }, {'1', '1', '0', '0', '0' }, {'0', '0', '0', '0', '0' }};
        System.out.println("广度优先遍历：" + numIslands(grid2));

    }

}
