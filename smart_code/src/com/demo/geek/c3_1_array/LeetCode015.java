/*
 * Copyright 2018-2020, 上海哈里奥科技有限公司
 * All Right Reserved.
 */

package com.demo.geek.c3_1_array;

import java.util.*;

public class LeetCode015 { // LeetCode T15

    /**
     * 三数之和
     * <p>
     * 给你一个包含 n 个整数的数组 nums，判断 nums 中是否存在三个元素 a，b，c ，使得 a + b + c = 0 ？
     * 请你找出所有满足条件且不重复的三元组。
     * <p>
     * 注意：答案中不可以包含重复的三元组。
     * <p>
     * 给定数组 nums = [-1, 0, 1, 2, -1, -4]，
     * <p>
     * 满足要求的三元组集合为：
     * [
     * [-1, 0, 1],
     * [-1, -1, 2]
     * ]
     */

    public List<List<Integer>> threeSum01(int[] nums) {//暴力解

        if (nums == null || nums.length <= 2) {
            return Collections.emptyList();
        }
        Arrays.sort(nums);
        Set<List<Integer>> result = new HashSet<>();

        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                for (int k = j + 1; k < nums.length; k++) {
                    if (nums[i] + nums[j] + nums[k] == 0) {
                        List<Integer> list = Arrays.asList(nums[i], nums[j], nums[k]);
                        // list.add(nums[i]);
                        // list.add(nums[j]);
                        // list.add(nums[k]);
                        // if (result.indexOf(list) < 0) {
                        //     result.add(list);
                        // }
                        result.add(list);
                    }
                }
            }
        }
        return new ArrayList<>(result);
    }

    public List<List<Integer>> threeSum02(int[] nums) { //使用hash链表

        Arrays.sort(nums);
        Set<List<Integer>> result = new HashSet<>();

        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            map.put(nums[i], i);
        }

        for (int i = 0; i < nums.length; i++) {
            int k = -nums[i];

            for (int j = i + 1; j < nums.length; j++) {
                int target = k - nums[j];
                if (map.containsKey(target) && map.get(target) != i && map.get(target) != j) {
                    List<Integer> list = Arrays.asList(-k, nums[j], target);
                    list.sort(Comparator.naturalOrder());
                    result.add(list);
                }
            }

        }
        return new ArrayList<>(result);
    }

    public List<List<Integer>> threeSum03(int[] nums) {//使用双指针

        Arrays.sort(nums);
        Set<List<Integer>> set = new HashSet<>();
        for (int i = 0; i < nums.length - 2; i++) {
            int target = -nums[i];
            int p = i + 1;
            int q = nums.length - 1;
            while (p < q) {
                int sum = nums[p] + nums[q];
                if (target == sum) {
                    List<Integer> list = Arrays.asList(-target, nums[p], nums[q]);
                    set.add(list);
                }

                if (target > sum) {
                    p++;
                } else {
                    q--;
                }
            }
        }
        return new ArrayList<>(set);
    }


}
