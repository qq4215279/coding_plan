/*
 * Copyright 2020-2021, 木木996.
 * All Right Reserved.
 */

package com.mumu.geek.c3_1_array;

import java.util.Arrays;

public class LeetCode283 { // 有点类似 LeetCode026
    /**
     * 给定一个数组 nums，编写一个函数将所有 0 移动到数组的末尾，同时保持非零元素的相对顺序。
     *
     * 输入: [0,1,0,3,12]
     * 输出: [1,3,12,0,0]
     *
     * 说明:
     * 必须在原数组上操作，不能拷贝额外的数组。
     * 尽量减少操作次数。
     * *
     **/

    public static void moveZeroes(int[] nums) { // 使用了双指针
        int j = 0;  // j记录0的位置
        for (int i = 0; i < nums.length ; i++) {
            if (nums[i] != 0) {
                nums[j] = nums[i];
                if (j < i) {
                    nums[i] = 0;
                }
                j++;
            }
        }
    }


    public static void main(String[] args) {
        int[] nums = {0, 1, 0, 3, 12};
        moveZeroes(nums);

        System.out.println(Arrays.toString(nums));
    }


}
