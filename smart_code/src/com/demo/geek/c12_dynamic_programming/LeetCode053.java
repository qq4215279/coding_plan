package com.demo.geek.c12_dynamic_programming;

/**
 * LeetCode053
 * 最大序列和
 * @author liuzhen
 * @version 1.0.0 2021/8/18 21:01
 */
public class LeetCode053 {

    /**
     * 给定一个整数数组 nums ，找到一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。
     *
     * 示例 1：
     * 输入：nums = [-2,1,-3,4,-1,2,1,-5,4]
     * 输出：6
     * 解释：连续子数组 [4,-1,2,1] 的和最大，为 6 。
     *
     * 示例 2：
     * 输入：nums = [1]
     * 输出：1
     *
     * 示例 3：
     * 输入：nums = [0]
     * 输出：0
     *
     * 示例 4：
     * 输入：nums = [-1]
     * 输出：-1
     *
     * 示例 5：
     * 输入：nums = [-100000]
     * 输出：-100000
     *  
     * 提示：
     * 1 <= nums.length <= 3 * 104
     * -105 <= nums[i] <= 105
     *  
     * 进阶：如果你已经实现复杂度为 O(n) 的解法，尝试使用更为精妙的 分治法 求解。
     *
     * 思路：
     * 1. 暴力：枚举出所有的数组起点，与终点的每个数组和，O(n^2)
     * 2. DP:
     *    a: 分治（子问题）: max_sum(i) = Max(max_sum(i-1), 0) + a[i]
     *    b: 状态数组定义: f[i]
     *    c: 化简后的最终DP方程: f[i] = Max(f[i-1], 0) + a[i]
     *
     *    ->DP方程推导过程：最大子序和 = 当前元素自身最大，或者 包含之前后最大
     *       db[i] = max(nums[i], nums[i] + dp[i-1])
     *  <=>  db[i] = max(nums[i] + 0, nums[i] + dp[i-1])
     *  <=>  db[i] = nums[i] + max(0, dp[i-1])
     */

    /**
     * DP
     * @author liuzhen
     * @date 2021/8/19 22:41
     * @param nums
     * @return int
     */
    public static int maxSubArray(int[] nums) {
        int[] dp = new int[nums.length];
        dp[0] = nums[0];
        int max = nums[0];
        for (int i = 1; i < nums.length; i++) {
            dp[i] = Math.max(dp[i- 1] + nums[i], nums[i]);
            if (max < dp[i]) {
                max = dp[i];
            }
        }
        return max;
    }

    /**
     * 根据上解，也可以直接不要定义dp数组，直接用num[] 取代dp数组也可以
     * @author liuzhen
     * @date 2021/8/19 22:47
     * @param nums
     * @return int
     */
    public static int maxSubArray2(int[] nums) {
//        int[] dp = new int[nums.length];
//        dp[0] = nums[0];
        int max = nums[0];
        for (int i = 1; i < nums.length; i++) {
            nums[i] = Math.max(nums[i- 1] + nums[i], nums[i]);
            if (max < nums[i]) {
                max = nums[i];
            }
        }
        return max;
    }

    public static void main(String[] args) {
        int[] nums = {-2, 1, -3, 4, -1, 2, 1, -5, 4};
        System.out.println(maxSubArray(nums));

        System.out.println(maxSubArray2(nums));

    }
}
