package com.mumu.geek.c12_dynamic_programming;

/**
 * LeetCode198
 * 打劫宿舍
 * @author liuzhen
 * @version 1.0.0 2021/8/18 21:05
 */
public class LeetCode198 {
    /**
     * 你是一个专业的小偷，计划偷窃沿街的房屋。每间房内都藏有一定的现金，影响你偷窃的唯一制约因素就是相邻的房屋装有相互连通的防盗系统，如果两间相邻的房屋在同一晚上被小偷闯入，系统会自动报警。
     * 给定一个代表每个房屋存放金额的非负整数数组，计算你 不触动警报装置的情况下 ，一夜之内能够偷窃到的最高金额。
     *
     * 示例 1：
     * 输入：[1,2,3,1]
     * 输出：4
     * 解释：偷窃 1 号房屋 (金额 = 1) ，然后偷窃 3 号房屋 (金额 = 3)。
     *      偷窃到的最高金额 = 1 + 3 = 4 。
     *
     * 示例 2：
     * 输入：[2,7,9,3,1]
     * 输出：12
     * 解释：偷窃 1 号房屋 (金额 = 2), 偷窃 3 号房屋 (金额 = 9)，接着偷窃 5 号房屋 (金额 = 1)。
     *      偷窃到的最高金额 = 2 + 9 + 1 = 12 。
     *
     * 思路：
     * 1. 定义二位数组，a[i][0,1] 第一个：代表第几号屋  第二个：表示状态，0：不偷；1：偷
     * DP方程: a[i][0] = Max(a[i-1][0], a[i-1][1])  => 第i号屋不偷时，最大值 = 第i-1号不偷 或 第i-1偷的较大值
     *        a[i][1] = a[i-1][0] + nums[i]   => 第i号屋偷时，最大值 = 第i-1号不能偷 + 第i号屋偷的
     *
     * 2. 定义二位数组，a[i][] 第一个：代表第几号屋  第二个：表示能偷到的 max value： max(a)
     *    a[i]: 0..i填，切num[i]必偷的最大值
     *    a[i] = Max(a[i-1], a[i-2] + num[i])
     */

    /**
     * DP解法1
     * @author liuzhen
     * @date 2021/8/19 23:25
     * @param nums
     * @return int
     */
    public static int rob(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        int n = nums.length;
        int[][] a = new int[n][2];

        a[0][0] = 0;
        a[0][1] = nums[0];

        for (int i = 1; i < n; i++) {
            a[i][0] = Math.max(a[i - 1][0], a[i - 1][1]);
            a[i][1] = a[i - 1][0] + nums[i];
        }

        return Math.max(a[n - 1][0], a[n - 1][1]);
    }

    /**
     * DP解法2
     * @author liuzhen
     * @date 2021/8/19 23:31
     * @param nums
     * @return int
     */
    public static int rob2(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        int n = nums.length;
        int[] a = new int[n];

        a[0] = nums[0];
        a[1] = Math.max(nums[0], nums[1]);
        int res = Math.max(a[0], a[1]);

        for (int i = 2; i < n; i++) {
            a[i] = Math.max(a[i - 1], a[i - 2] + nums[i]);
            res = Math.max(res, a[i]);
        }

        return res;
    }

    public static int rob3(int[] nums) {
        int prevMax = 0;
        int currMax = 0;

        for (int x : nums) {
            int temp = currMax;
            currMax = Math.max(prevMax + x, currMax);
            prevMax = temp;
        }
        return currMax;
    }

    public static void main(String[] args) {
        int[] nums = {1, 2, 3, 1};
        System.out.println(rob(nums));
        System.out.println(rob2(nums));
        System.out.println(rob3(nums));
    }

}
