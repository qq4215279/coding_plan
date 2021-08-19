package com.demo.geek.c12_dynamic_programming;

import java.util.ArrayList;
import java.util.List;

/**
 * LeetCode120
 * 三角形最小路径和
 * @author liuzhen
 * @version 1.0.0 2021/8/18 20:59
 */
public class LeetCode120 {

    /**
     * 给定一个三角形 triangle ，找出自顶向下的最小路径和。
     * 每一步只能移动到下一行中相邻的结点上。相邻的结点 在这里指的是 下标 与 上一层结点下标 相同或者等于 上一层结点下标 + 1 的两个结点。也就是说，如果正位于当前行的下标 i ，那么下一步可以移动到下一行的下标 i 或 i + 1 。
     *
     * 示例 1：
     * 输入：triangle = [[2],[3,4],[6,5,7],[4,1,8,3]]
     * 输出：11
     * 解释：如下面简图所示：
     *    2
     *   3 4
     *  6 5 7
     * 4 1 8 3
     * 自顶向下的最小路径和为 11（即，2 + 3 + 5 + 1 = 11）。
     *
     * 示例 2：
     * 输入：triangle = [[-10]]
     * 输出：-10
     *  
     * 提示：
     * 1 <= triangle.length <= 200
     * triangle[0].length == 1
     * triangle[i].length == triangle[i - 1].length + 1
     * -104 <= triangle[i][j] <= 104
     *  
     * 进阶： 你可以只使用 O(n) 的额外空间（n 为三角形的总行数）来解决这个问题吗？
     *
     * 思路：
     * 1. brute-force,递归，n层：left or right：2^n
     * 2. DP:
     *  a. 重复性（分治）:  problem(i,j) = min(sub(i+1, j), sub(i+1, j+1)) + a[i,j]
     *  b. 定义状态分组: f[i,j]
     *  c. DP方程：f[i,j] = min(f[i+1, j], f[i+1, j+1]) + a[i,j]
     */

    /**
     *
     * @author liuzhen
     * @date 2021/8/19 22:15
     * @param triangle
     * @return int
     */
    public static int minimumTotal(List<List<Integer>> triangle) {
        int[] A = new int[triangle.size() + 1];
        for (int i = triangle.size() - 1; i >=  0; i--) {
            for (int j = 0; j < triangle.get(i).size(); j++) {
                A[j] = Math.min(A[j], A[j + 1]) + triangle.get(i).get(j);
            }
        }

        return A[0];
    }

    /**
     * DP方程解法
     * @author liuzhen
     * @date 2021/8/19 22:26
     * @param triangle
     * @return int
     */
    public static int minimumTotal2(List<List<Integer>> triangle) {
        int n = triangle.size();
        int[] dp = new int[n + 1];
        for (int i = n - 1; i >= 0; i--) {
            for (int j = 0; j <= i; j++) {
                dp[j] = Math.min(dp[j], dp[j + 1]) + triangle.get(i).get(j);
            }
        }
        return dp[0];
    }

    public static void main(String[] args) { // triangle = [[2],[3,4],[6,5,7],[4,1,8,3]]
        List<List<Integer>> triangle = new ArrayList<>();
        List<Integer> list1 = new ArrayList<>();
        list1.add(2);
        List<Integer> list2 = new ArrayList<>();
        list2.add(3);
        list2.add(4);
        List<Integer> list3= new ArrayList<>();
        list3.add(6);
        list3.add(5);
        list3.add(7);
        List<Integer> list4 = new ArrayList<>();
        list4.add(4);
        list4.add(1);
        list4.add(8);
        list4.add(3);
        triangle.add(list1);
        triangle.add(list2);
        triangle.add(list3);
        triangle.add(list4);

        System.out.println(minimumTotal(triangle));

        System.out.println("===========================>");

        System.out.println(minimumTotal2(triangle));
    }
}
