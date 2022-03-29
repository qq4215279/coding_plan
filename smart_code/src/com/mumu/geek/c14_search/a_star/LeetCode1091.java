/*
 * Copyright 2020-2021, 木木996.
 * All Right Reserved.
 */

package com.mumu.geek.c14_search.a_star;

import java.util.*;

/**
 * LeetCode1091
 * 二进制矩阵中的最短路径（A * 寻路）
 * @author liuzhen
 * @version 1.0.0 2021/8/24 8:56
 */
public class LeetCode1091 {

    /**
     * 给你一个 n x n 的二进制矩阵 grid 中，返回矩阵中最短 畅通路径 的长度。如果不存在这样的路径，返回 -1 。
     * 二进制矩阵中的 畅通路径 是一条从 左上角 单元格（即，(0, 0)）到 右下角 单元格（即，(n - 1, n - 1)）的路径，该路径同时满足下述要求：
     *
     * 路径途经的所有单元格都的值都是 0 。
     * 路径中所有相邻的单元格应当在 8 个方向之一 上连通（即，相邻两单元之间彼此不同且共享一条边或者一个角）。
     * 畅通路径的长度 是该路径途经的单元格总数。
     *
     * 估价函数：就是这个点距离终点的坐标差的绝对值之和，也就是曼哈顿距离。
     *
     * 示例 1：
     * 输入：grid = [[0,1],[1,0]]
     * 输出：2
     *
     * 示例 2：
     * 输入：grid = [[0,0,0],[1,1,0],[1,1,0]]
     * 输出：4
     *
     * 示例 3：
     * 输入：grid = [[1,0,0],[1,1,0],[1,1,0]]
     * 输出：-1
     *  
     * 提示：
     * n == grid.length
     * n == grid[i].length
     * 1 <= n <= 100
     * grid[i][j] 为 0 或 1
     *
     * 解法：https://leetcode-cn.com/problems/shortest-path-in-binary-matrix/solution/bfsa-qi-fa-sou-suo-duo-chong-fang-fa-you-jrqp/
     */


    int[] dx = {0, 0, -1, 1, -1, 1, -1, 1};
    int[] dy = {-1, 1, 0, 0, -1, -1, 1, 1};

    /** 
     * 方法1：BFS
     * Node表示Location(x,y)。数组dx、dy表示：上下左右， 左上右上左下右下 八个方向坐标变化。
     * BFS即宽度优先搜索，这样的搜索树，是一层层地。从出发点开始，第一次遍历到终点时过的那条路径就是最短的路径。
     * 因为这条路径没有多绕一个不相关节点，所以它是最短的,也符合题目最短畅通路径的长度。BFS这类也是盲目搜索，下面介绍启发式搜索算法。
     *
     * @author liuzhen
     * @date 2022/3/29 23:13 
     * @param grid 
     * @return int
     */
    public int shortestPathBinaryMatrixByBFS(int[][] grid) {
        Node node = new Node(0, 0, 2);

        Deque<Node> queue = new ArrayDeque<>();
        queue.addLast(node);

        int n = grid.length;
        if (grid[0][0] == 1 || grid[n - 1][n - 1] == 1) {
            return -1;
        } else if (n <= 2) {
            return n;
        }

        while (!queue.isEmpty()) {
            Node cur = queue.removeFirst();
            int x = cur.x;
            int y = cur.y;
            int step = cur.step;
            for (int i = 0; i < 8; i++) {
                int newX = x + dx[i];
                int newY = y + dy[i];
                if (0 <= newX && newX < n && 0 <= newY && newY < n && grid[newX][newY] == 0) {
                    // 找到终点
                    if (newX == n - 1 && newY == n - 1) {
                        return step;
                    }
                    queue.addLast(new Node(newX, newY, step + 1));
                    // 标记已遍历过，避免重复
                    grid[newX][newY] = 1;
                }
            }
        }
        return -1;
    }

    private static class Node {
        public int x;
        public int y;
        public int step;

        public Node(int start, int end, int step) {
            this.x = start;
            this.y = end;
            this.step = step;
        }
    }

    
    // 代码来自LeetCode国际评论区。
    final int[][] dirs = new int[][] {{0, 1}, {1, 0}, {1, 1}, {0, -1}, {-1, 0}, {-1, -1}, {-1, 1}, {1, -1}};
    /**
     * 方法4：代码来自LeetCode国际评论区。
     * 该题解启发式函数选用曼哈顿距离（Manhattan Distance）
     * @author liuzhen
     * @date 2022/3/29 23:33
     * @param grid
     * @return int
     */
    public int shortestPathBinaryMatrix(int[][] grid) {
        int n = grid.length;

        // Heuristic based priority Queue- Choose closest to the target
        Queue<int[]> queue = new PriorityQueue<>((a, b) -> {
            if (a[2] != b[2]) {
                return a[2] - b[2];
            } else {
                int manhattenDistanceA = (n - 1 - a[0]) + (n - 1 - a[1]);
                int manhattenDistanceB = (n - 1 - b[0]) + (n - 1 - b[1]);
                return manhattenDistanceA - manhattenDistanceB;
            }

        });

        // The above queue stores a,b,c where c is the least distance from source

        if (grid[0][0] != 0 || grid[n - 1][n - 1] != 0) {
            return -1;
        }

        if (n == 1) {
            return 1;
        }

        queue.offer(new int[] {0, 0, 1});
        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            if (grid[current[0]][current[1]] != 0) {
                continue;
            }

            // Mark the least distant node from source as visited
            grid[current[0]][current[1]] = -1;

            for (int[] dir : dirs) {
                int newX = current[0] + dir[0];
                int newY = current[1] + dir[1];
                if (newX >= 0 && newX < n && newY >= 0 && newY < n && grid[newX][newY] == 0) {
                    queue.offer(new int[] {newX, newY, current[2] + 1});
                }
                // Special check to get the end
                if (newX == n - 1 && newY == n - 1) {
                    return current[2] + 1;
                }
            }
        }
        
        // No path found
        return -1;
    }

}

/** 
 * 方法2：A* Search 启发式搜索
 * @author liuzhen
 * @date 2022/3/29 23:27 
 * @return 
 */
class SolutionByAStar {
    
    int[] dx = {0, 0, -1, 1, -1, 1, -1, 1};
    int[] dy = {-1, 1, 0, 0, -1, -1, 1, 1};

    /**
     * 方法2：A* Search 启发式搜索
     * 在方法一基础上，稍作修改。A* Search不再使用简单的队列，这里使用优先队列，将最有前途的点，优先弹出查找。相比队列这种按顺序依次弹出要智能很多。
     * 这个算法启发式函数很重要，如果没有选好速度反而会变慢。这里顺便加了个路径记录，有些面试题输出路径可以参考（本题去掉速度会变快）。
     * 路径思路是：顺着终点方向，找到它的父亲，再找到父亲的父亲...，如此依次回溯，就能找到一条最佳路径了。
     *
     * @author liuzhen
     * @date 2022/3/29 23:28
     * @param grid
     * @return int
     */
    public int shortestPathBinaryMatrixByAStar(int[][] grid) {
        int n = grid.length;

        if (grid[0][0] == 1 || grid[n - 1][n - 1] == 1) {
            return -1;
        }

        if (n == 1) {
            return 1;
        }

        PriorityQueue<Node> queue = new PriorityQueue<>(10, (i, j) -> Double.compare(i.cost, j.cost));
        queue.add(new Node(0, 0, 0));

        while (!queue.isEmpty()) {

            Node cur = queue.poll();
            int x = cur.x;
            int y = cur.y;

            if (x == n - 1 && y == n - 1) {
                // return step;
                return pathLength(cur);
            }

            grid[y][x] = 1;

            for (int i = 0; i < 8; i++) {

                int newX = x + dx[i];
                int newY = y + +dy[i];

                if (inBounds(n, newX, newY) && grid[newY][newX] == 0) {

                    double cost = cur.cost + 1 + distance(newX, newY, x, y);
                    Node next = new Node(newX, newY, cost);
                    if (queue.contains(next)) {
                        continue;
                    }

                    grid[newY][newX] = 1;
                    // 保存路径，方便后面打印路径。
                    next.setParent(cur);

                    queue.add(next);
                }
            }
        }
        return -1;
    }

    /**
     *
     * @author liuzhen
     * @date 2022/3/29 23:23
     * @param n
     * @param newX
     * @param newY
     * @return boolean
     */
    private boolean inBounds(int n, int newX, int newY) {
        return newX >= 0 && newX < n && newY >= 0 && newY < n;
    }

    /**
     * 启发式函数，使用两点距离坐标公式
     * @author liuzhen
     * @date 2022/3/29 23:23 
     * @param x
     * @param y
     * @param targetX
     * @param targetY
     * @return double
     */
    private double distance(int x, int y, int targetX, int targetY) {
        return Math.sqrt(Math.pow(targetX - x, 2) + Math.pow(targetY - y, 2));
    }

    private int pathLength(Node node) {
        if (node == null) {
            return 0;
        }
        
        int pathLength = 1;
        while (node.getParent() != null) {
            node = node.getParent();
            pathLength++;
        }
        return pathLength;
    }


    private class Node {
        public int x, y;
        public double cost;
        public Node parent = null;

        public Node(int x, int y) {
            this(x, y, 0);
        }

        public Node(int x, int y, double cost) {
            this.x = x;
            this.y = y;
            this.cost = cost;
        }

        public Node getParent() {
            return parent;
        }

        public void setParent(Node parent) {
            this.parent = parent;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }

            Node node = (Node)o;
            return x == node.x && y == node.y;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }
    }
}

/** 
 * 方法3：A* Search优化(速度提升)
 * @author liuzhen
 * @date 2022/3/29 23:29 
 * @return 
 */
class SolutionByAStar2 {
    int n = 0;
    int[] dx = {0, 0, -1, 1, -1, 1, -1, 1};
    int[] dy = {-1, 1, 0, 0, -1, -1, 1, 1};

    /** 
     * 方法3：A* Search优化(速度提升)
     * 整体没有改变太多，基本还是方法二那种。启发式函数改为切比雪夫距离距离。
     * 距离计算方法有很多，常用启发式函数有这几种。选择合适的启发式函数有利于速度的提升。这题可以用好几种启发式函数，初学都可以试着都写一下。
     *
     * 曼哈顿距离（Manhattan Distance）
     * 一般只能在四个方向上移动时用（右、左、上、下）
     *
     * 对角线距离（Diagonal Distance）：
     * 当我们只允许向八个方向移动时用（国际象棋中的王移动方式那种）
     *
     * 欧几里得距离（Euclidean Distance）：
     * 不受限制，允许向任何方向移动时。
     *
     * 切比雪夫距离（Chebyshev Distance）：
     * 可参考LeetCode 1266，https://leetcode-cn.com/problems/minimum-time-visiting-all-points/solution/fang-wen-suo-you-dian-de-zui-xiao-shi-jian-by-le-2/
     *
     * @author liuzhen
     * @date 2022/3/29 23:30 
     * @param grid 
     * @return int
     */
    public int shortestPathBinaryMatrix(int[][] grid) {
        int n = grid.length;
        if (grid[0][0] != 0 || grid[n - 1][n - 1] != 0) {
            return -1;
        }
        if (n == 1) {
            return 1;
        }
        PriorityQueue<Node> pq = new PriorityQueue<>(Comparator.comparingInt(v -> v.f));
        grid[0][0] = 1;
        pq.offer(new Node(0, 0, 1));
        while (!pq.isEmpty()) {
            Node curr = pq.poll();
            int x = curr.x;
            int y = curr.y;
            if (x == n - 1 && y == n - 1) return grid[x][y];
            for (int j = 0; j < 8; j++) {
                int newX = x + dx[j];
                int newY = y + dy[j];

                if (newX < 0 || newX > n - 1 || newY < 0 || newY > n - 1) {
                    continue;
                }
                //注意判断 grid[newX][newY] > grid[x][y] + 1
                if (grid[newX][newY] == 0 || grid[newX][newY] > grid[x][y] + 1) {
                    grid[newX][newY] = grid[x][y] + 1;
                    pq.offer(new Node(newX, newY, grid[newX][newY]));
                }
            }
        }
        return -1;
    }

    public class Node {
        int x, y, f;

        public Node(int x, int y, int step) {
            this.x = x;
            this.y = y;
            this.f = distance(x, y, step);
        }

        public int distance(int x, int y, int step) {
            return step + Math.max(n - x - 1, n - y - 1);
        }
    }
}

/**
 * 方法5：Dijkstra算法
 * @author liuzhen
 * @date 2022/3/29 23:37
 * @return
 */
class SolutionByDijkstra {
    
    // 代码来自Google, StackoverFlow
    public int[][] DIRECTIONS = new int[][] {{1, 0}, {-1, 0}, {0, 1}, {0, -1}, {-1, -1}, {-1, 1}, {1, -1}, {1, 1}};

    /** 
     *
     * @author liuzhen
     * @date 2022/3/29 23:39
     * @param grid 
     * @return int
     */
    public int shortestPathBinaryMatrix(int[][] grid) {
        int side_length = grid.length;

        // if the s left top corner is 1 then, no path exist and return -1
        if (grid[0][0] == 1 || grid[side_length - 1][side_length - 1] == 1) {
            return -1;
        }

        if (side_length == 1) {
            return 1;
        }

        PriorityQueue<Node> queue = new PriorityQueue<>(10, (i, j) -> Double.compare(i.cost, j.cost));
        queue.add(new Node(0, 0, 0));

        while (!queue.isEmpty()) {
            Node curr_point = queue.poll();
            int x = curr_point.x;
            int y = curr_point.y;

            if (x == side_length - 1 && y == side_length - 1) {
                return pathLength(curr_point);
            }

            grid[y][x] = 1;
            for (int[] successor : DIRECTIONS) {

                int successor_x = x + successor[0];
                int successor_y = y + +successor[1];

                if (successor_x >= 0 && successor_x < side_length && successor_y >= 0 && successor_y < side_length
                    && grid[successor_y][successor_x] == 0) {

                    double cost = curr_point.cost + 1;
                    Node successor_point = new Node(successor_x, successor_y, cost);
                    if (queue.contains(successor_point)) {
                        continue;
                    }

                    // mark as explored
                    grid[successor_y][successor_x] = 1;
                    // mark as child of current node
                    successor_point.setParent(curr_point);
                    queue.add(successor_point);
                }
            }
        }
        return -1;
    }

    private int pathLength(Node node) {
        if(node == null) {
            return 0;
        }
        
        int pathLength = 1;
        while (node.getParent() !=null){
            node = node.getParent();
            pathLength++;
        }
        return pathLength;
    }
    
    
    private class Node {
        public int x, y;
        public double cost;
        public Node parent = null;

        public Node(int x, int y) {
            this(x, y, 0);
        }

        public Node(int x, int y, double cost) {
            this.x = x;
            this.y = y;
            this.cost = cost;
        }

        public Node getParent() {
            return parent;
        }

        public void setParent(Node parent) {
            this.parent = parent;
        }

        // todo implement equals and hashCode
    }

}