package com.demo.geek.c13_trie_disjoint_set;

import com.demo.common.entity.UnionFind;

/**
 * FriendCircles
 * 朋友圈数量
 * @author liuzhen
 * @version 1.0.0 2021/8/22 16:01
 */
public class FriendCircles {

    /**
     * 班上有N名学生，其中有些人是朋友，有些则不是。他们的友谊具有是传递性。如果已知A是B的朋友，B是C的朋友，那么我们也可以认为A也是C的朋友。所谓的朋友圈，是指所有朋友的集合。
     * 给定一个N * N 的矩阵M，表示班级中学生之间的朋友关系。如果M[i][j] = 1，表示已知第i个和j个学生互为朋友关系，否则为不知道。你必须输出所有学生中的已知的朋友圈总数。
     *
     * 示例1：
     * 输入：
     *  [[1,1,0],
     *   [1,1,0],
     *   [0,0,1]
     *  ]
     * 输出：2
     * 说明：已知学生0和学生1互为朋友，他们在一个朋友圈。第2个学生自己在一个朋友圈。所以返回2。
     *
     * 示例2：
     * 输入：
     *  [[1,1,0],
     *   [1,1,1],
     *   [0,1,1]
     *  ]
     * 输出：1
     * 说明：已知学生0和学生1互为朋友，学生1和学生2互为朋友，所以学生0和学生2也是朋友，所以他们3个在一个朋友圈，返回1。
     *
     * 思路：（思维转换一下）该题相当于可以转换为岛屿数目问题（LeetCode200） 主对角线，自己和自己一定是朋友
     * 1. DFS
     * 2. BFS
     * 3. 并查集 disjoint set
     *   a. N --> 各自独立集合
     *   b. 遍历好友关系矩阵M: M[i][j] --> 合并
     *   c. 看有多少孤立的集合
     *
     *   思路：每个学生都是独立的集合，遍历矩阵M，如果M(i,j) = M(j,i)，说明他们是朋友，合并成同一个集合；
     *        当遍历完整个矩阵时，有多少个独立的集合，就相当于多少个朋友圈。   =>如何合并？找到一个集合的头元素，将这个集合的头元素合并到另外一个集合的头元素下
     */

    /**
     * 并查集解法
     * @author liuzhen
     * @date 2021/8/22 22:19
     * @param M
     * @return int
     */
    public static int findCircleNum(int[][] M) {
        int n = M.length;
        UnionFind uf = new UnionFind(n);

        for (int i = 0; i < n - 1; i++) {
            for (int j = i + 1; j < n; j++) {
                if (M[i][j] == 1) {
                    uf.union(i, j);
                }
            }
        }
        return uf.count;
    }

    /**
     * DFS
     * @author liuzhen
     * @date 2021/8/22 22:19
     * @param M
     * @return int
     */
    public static int findCircleNum2(int[][] M) {
        int[] visited = new int[M.length];
        int count = 0;
        for (int i = 0; i < M.length; i++) {
            if (visited[i] == 0) {
                dfs(M, visited, i);
                count++;
            }
        }
        return count;
    }

    private static void dfs(int[][] M, int[] visited, int i) {
        for (int j = 0; j < M.length; j++) {
            if (M[i][j] == 1 && visited[j] == 0) {
                visited[j] = 1;
                dfs(M, visited, j);
            }
        }
    }

    public static void main(String[] args) {
        int[][] M = {{1,1,0},{1,1,0},{0,0,1}};

        System.out.println(findCircleNum(M));

        System.out.println("----------->");

        System.out.println(findCircleNum2(M));

    }


}
