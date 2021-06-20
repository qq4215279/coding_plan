/*
 * Copyright 2018-2020, 上海哈里奥科技有限公司
 * All Right Reserved.
 */

package com.demo.Geek.c3_array_linkedList;

import java.util.HashMap;

public class Day02Demo04 { //T1

    /**
     * 两数之和
     * <p>
     * 给定一个整数数组 nums 和一个目标值 target，请你在该数组中找出和为目标值的那 两个 整数，并返回他们的数组下标。
     * 你可以假设每种输入只会对应一个答案。但是，数组中同一个元素不能使用两遍。
     * <p>
     * 给定 nums = [2, 7, 11, 15], target = 9
     * 因为 nums[0] + nums[1] = 2 + 7 = 9
     * 所以返回 [0, 1]
     */

    public static int[] twoSum01(int[] nums, int target) { //方法1：使用集合
        HashMap<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < nums.length; i++) {
            map.put(nums[i], i);
        }
        for (int i = 0; i < nums.length; i++) { // O(n)
            int d = target - nums[i];
            if (map.containsKey(d) && i != map.get(d)) {
                int left = Math.min(i, map.get(d));
                int right = Math.max(i, map.get(d));
                return new int[]{left, right};
            }
        }
        return null;
    }

    public static int[] twoSum02(int[] nums, int target) { //方法1：暴力解：使用两层循环。 O(n^2)

        for (int i = 0; i < nums.length ; i++) {
            for (int j = i+1; j < nums.length ; j++) {
                if (target == (nums[i] + nums[j])){
                    return new int[]{i,j};
                }
            }
        }
        return null;
    }

    public static void main(String[] args) {
        int[] nums = {2, 7, 11, 15};
        int[] ints = twoSum02(nums, 9);
        for (int i = 0; i <ints.length ; i++) {
            System.out.println(ints[i]);
        }

    }

}
