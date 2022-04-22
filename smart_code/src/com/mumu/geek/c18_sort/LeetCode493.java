/*
 * Copyright 2020-2021, 木木996.
 * All Right Reserved.
 */

package com.mumu.geek.c18_sort;

import java.util.Arrays;

/**
 * LeetCode493
 * 翻转对
 * @author liuzhen
 * @version 1.0.0 2021/8/28 23:22
 */
public class LeetCode493 {

    /**
     * 给定一个数组 nums ，如果 i < j 且 nums[i] > 2*nums[j] 我们就将 (i, j) 称作一个重要翻转对。
     * 你需要返回给定数组中的重要翻转对的数量。
     *
     * 示例 1:
     * 输入: [1,3,2,3,1]
     * 输出: 2
     *
     * 示例 2:
     * 输入: [2,4,3,5,1]
     * 输出: 3
     *
     * 注意:
     * 给定数组的长度不会超过50000。
     * 输入数组中的所有数字都在32位整数的表示范围内。
     *
     * 思路：
     * 1. 暴力法：两个嵌套循环：O(n^2)
     * 2. merge-sort
     * 3. 树状数组
     *
     */

    public static int reversePairs(int[] nums) {
        return mergeSort(nums, 0, nums.length - 1);
    }

    private static int mergeSort(int[] nums, int start, int end) {
        if (start >= end) {
            return 0;
        }

        int mid = start + (end - start) / 2;
        int cnt = mergeSort(nums, start, mid) + mergeSort(nums, mid + 1, end);
        for (int i = start, j = mid + 1; i <= mid; i++) {
            while (j <= end && nums[i] / 2.0 > nums[j]) {
                j++;
            }
            cnt += j - (mid + 1);
        }

        Arrays.sort(nums, start, end + 1);

        return cnt;
    }

    public static int ret;

    public static int reversePairs2(int[] nums) {
        ret = 0;
        mergeSort2(nums, 0, nums.length - 1);

        return ret;
    }

    private static void mergeSort2(int[] nums, int left, int right) {
        if (right <= left) {
            return;
        }
        int middle = left + (right - left) / 2;
        mergeSort2(nums, left, middle);
        mergeSort2(nums, middle + 1, right);

        // count elements
        int count = 0;
        for (int l = left, r = middle + 1; l <= middle;) {
            if (r > right || (long)nums[l] <= 2 * (long)nums[r]) {
                l++;
                ret += count;
            } else {
                r++;
                count++;
            }
        }

        // sort
        Arrays.sort(nums, left, right + 1);
    }

    public static int reversePairs3(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        return mergeSort3(nums, 0, nums.length - 1);
    }

    private static int mergeSort3(int[] nums, int l, int r) {
        if (l >= r) {
            return 0;
        }

        int mid = l + (r - l) / 2;
        int count = mergeSort3(nums, l, mid) + mergeSort3(nums, mid + 1, r);
        int[] cache = new int[r - l + 1];
        int i = l;
        int t = l;
        int c = 0;

        for (int j = mid + 1; j <= r; j++, c++) {
            while (i <= mid && nums[i] <= 2 * (long)nums[j]) {
                i++;
            }
            while (t <= mid && nums[t] < nums[j]) {
                cache[c++] = nums[t++];
            }
            cache[c] = nums[j];
            count += mid - i + 1;
        }

        while (t <= mid) {
            cache[c++] = nums[t++];
        }
        System.arraycopy(cache, 0, nums, l, r - l + 1);
        return count;
    }

    public static void main(String[] args) {
        int[] nums = {1, 3, 2, 3, 1};

        System.out.println(reversePairs(nums));
        System.out.println(reversePairs2(nums));
        System.out.println(reversePairs3(nums));

    }

}
