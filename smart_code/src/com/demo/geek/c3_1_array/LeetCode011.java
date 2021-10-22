/*
 * Copyright 2018-2020, 上海哈里奥科技有限公司
 * All Right Reserved.
 */

package com.demo.geek.c3_1_array;

public class LeetCode011 {

    /**
     * 盛最多水的容器
     * 给你 n 个非负整数 a1，a2，...，an，每个数代表坐标中的一个点 (i, ai) 。在坐标内画 n 条垂直线，垂直线 i 的两个端点分别为 (i, ai) 和 (i, 0)。
     * 找出其中的两条线，使得它们与 x 轴共同构成的容器可以容纳最多的水。
     * 说明：你不能倾斜容器，且 n 的值至少为 2。
     * <p>
     * 输入：[1,8,6,2,5,4,8,3,7]
     * 输出：49
     * *
     **/

    public int maxArea(int[] height) {
        int left = 0;
        int right = height.length - 1;
        int maxArea = 0;
        int h = 0;
        while (left < right) {

            // 方式一：
//            h = height[left] < height[right] ? height[left] : height[right];
//            maxArea = (h * (right - left)) > maxArea ? (h * (right - left)) : maxArea;

            // 方式二：
            maxArea = Math.max(Math.min(height[left], height[right]) * (right - left), maxArea);

            if (height[left] < height[right]) {
                left++;
            } else {
                right--;
            }

        }
        return maxArea;
    }


}
