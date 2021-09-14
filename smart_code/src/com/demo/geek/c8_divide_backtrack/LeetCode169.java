package com.demo.geek.c8_divide_backtrack;

import java.util.HashMap;
import java.util.Map;

/**
 * LeetCode169
 * 多数元素
 * @author liuzhen
 * @version 1.0.0 2021/8/15 18:01
 */
public class LeetCode169 {

    /**
     * 给定一个大小为 n 的数组，找到其中的多数元素。多数元素是指在数组中出现次数大于 ⌊ n/2 ⌋ 的元素。
     * 你可以假设数组是非空的，并且给定的数组总是存在多数元素。
     *
     * 示例 1：
     * 输入：[3,2,3]
     * 输出：3
     *
     * 示例 2：
     * 输入：[2,2,1,1,1,2,2]
     * 输出：2
     *  
     * 进阶：尝试设计时间复杂度为 O(n)、空间复杂度为 O(1) 的算法解决此问题。
     */

    /**
     * 暴力解法(self)  O(n)
     * @author liuzhen
     * @date 2021/8/15 18:05
     * @param nums
     * @return int
     */
    public static int majorityElement(int[] nums) {
        int key = 0;
        int maxCount = 0;
        Map<Integer, Integer> map = new HashMap<>();

        for (int n : nums) {
            map.put(n, map.getOrDefault(n, 0) + 1);
            if (map.get(n) > maxCount) {
                key = n;
                maxCount = map.get(n);
            }
        }

        return key;
    }

    /**
     * 分治回溯：  时间复杂度：O(nlogn)  空间复杂度：O(logn)
     * 思路：
     *  1. 将nums数组平局分为左右两部分，分别找到左右数组中分别出现次数最多的数
     *  2. 分别将上找出的两个数，在左右两个边界上，找出这两个数分别出现的次数
     *  3. 比较次数大小后返回该数。
     * @author liuzhen
     * @date 2021/8/15 18:58
     * @param nums
     * @return int
     */
    public static int majorityElementByRecur(int[] nums) {
        return doMajorityElementRecur(nums, 0, nums.length - 1);
    }

    private static int doMajorityElementRecur(int[] nums, int leftPos, int rightPos) {
        if (leftPos == rightPos) {
            return nums[leftPos];
        }
        // 小机灵 等价于 mid = l + (r - l) / 2
        int midPos = leftPos + ((rightPos - leftPos) >> 1);
        // 左边出现次数最大的数
        int left = doMajorityElementRecur(nums, leftPos, midPos);
        // 右边出现次数最大的数
        int right = doMajorityElementRecur(nums, midPos + 1, rightPos);

        // 出现次数
        int leftCount = findCount(nums, left, leftPos, rightPos);
        int rightCount = findCount(nums, right, leftPos, rightPos);

        // 比较大的数后返回出现次数较多的数
        return leftCount > rightCount ? left : right;
    }

    private static int findCount(int[] nums, int num, int leftPos, int rightPos) {
        int count = 0;
        for (int i = leftPos; i <= rightPos; i++) {
            if (nums[i] == num) {
                count++;
            }
        }
        return count;
    }

    public static void main(String[] args) {
        int[] nums = {3, 4, 3};
        int i = majorityElement(nums);
        System.out.println(i);

        System.out.println("----------->");

        int j = majorityElementByRecur(nums);
        System.out.println("j: " + j);
    }

}
