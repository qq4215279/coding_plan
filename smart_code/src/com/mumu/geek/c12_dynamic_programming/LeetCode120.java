package com.mumu.geek.c12_dynamic_programming;

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
     * 每一步只能移动到下一行中相邻的结点上。相邻的结点在这里指的是下标与上一层结点下标相同或者等于上一层结点下标 + 1 的两个结点。
     * 也就是说，如果正位于当前行的下标 i ，那么下一步可以移动到下一行的下标 i 或 i + 1 。
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
     * 思路：类比于LeetCode062/063的比较工整的二维数组，转化为子问题就是：求2的最小路径，变为求3或4的最小路径，再加上第一层的2，dp方程如下：
     * 1. brute-force,递归，n层：每一次走left or right：2^n
     * 2. DP:
     *  a. 重复性（分治）:  problem(i,j) = min(sub(i+1, j), sub(i+1, j+1)) + a[i,j]     注：i表示行，j表示列
     *  b. 定义状态分组: f[i,j]
     *  c. DP方程：f[i,j] = min(f[i+1, j], f[i+1, j+1]) + a[i,j]
     *
     *     2
     *    3 4
     *   6 5 7
     *  4 1 8 3
     *             tr[0,0]             //
     *         dp[j]     dp[j + 1]    // 从第二行开始，两个两个比较，找出每一行中的最小值，通过第一个for循环，上探到上一行。。。 eg：dp[j] = 6 + Math.min(4,1) = 7
     *      6         5             7
     *   4     1              8        3
     */


    /**
     * DP方程解法：自底向上
     * @author liuzhen
     * @date 2021/8/19 22:26
     * @param triangle
     * @return int
     */
    public static int minimumTotal(List<List<Integer>> triangle) {
        // 行数
        int rowSize = triangle.size();
        // 因为根据题意，每一列的长度等于上一列的长度+1，而且第一行只有1列，所以dp数组最大长度为行size+1
        int[] dp = new int[rowSize + 1];
        // 从最后一行开始
        for (int i = rowSize - 1; i >= 0; i--) {
            int colSize = triangle.get(i).size();
            // 遍历当前行的每一列，将当前行的每一个元素 + 下一行的与当前元素相邻的的两个元素的较小值
            for (int j = 0; j < colSize; j++) {
                // eg：dp[j] = 6 + Math.min(4,1) = 7
                dp[j] = triangle.get(i).get(j) + Math.min(dp[j], dp[j + 1]);
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


        System.out.println("===========================>");

        System.out.println(minimumTotal(triangle));
    }
}
