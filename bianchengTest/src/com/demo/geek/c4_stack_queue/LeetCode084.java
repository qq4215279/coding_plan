/*
 * Copyright 2018-2020, 上海哈里奥科技有限公司
 * All Right Reserved.
 */

package com.demo.geek.c4_stack_queue;

import java.util.Stack;

public class LeetCode084 { // LeetCode： T84

    /**
     * 柱状图中的最大矩形
     * <p>
     * 给定 n 个非负整数，用来表示柱状图中各个柱子的高度。每个柱子彼此相邻，且宽度为 1 。
     * 求在该柱状图中，能够勾勒出来的矩形的最大面积。
     * <p>
     * 以上是柱状图的示例，其中每个柱子的宽度为 1，给定的高度为 [2,1,5,6,2,3]。
     */

    public static int largestRectangleArea00(int[] heights) { // 1.暴力解法  O(n^3)
        int maxArea = 0;
        for (int i = 0; i < heights.length; i++) {
            for (int j = 0; j < heights.length; j++) {
                int minheight = Integer.MAX_VALUE;
                for (int k = i; k <= j; k++) {
                    minheight = Math.min(minheight, heights[k]);
                }
                maxArea = Math.max(maxArea, minheight * (j - i + 1));
            }

        }
        return maxArea;
    }

    public static int largestRectangleArea0(int[] heights) { // 2.优化暴力解法
        int maxArea = 0;
        for (int i = 0; i < heights.length; i++) {
            int minheight = Integer.MAX_VALUE;
            for (int j = i; j < heights.length; j++) {
                minheight = Math.min(minheight, heights[j]);
                maxArea = Math.max(maxArea, minheight * (j - i + 1));
            }

        }
        return maxArea;
    }

    public int largestRectangleArea(int[] heights) { // 3.单调栈
        Stack<Integer> stack = new Stack<>();
        stack.push(-1);
        int maxArea = 0;
        for (int i = 0; i < heights.length; ++i) {
            while (stack.peek() != -1 && heights[stack.peek()] >= heights[i]) {
                maxArea = Math.max(maxArea, heights[stack.pop() * (i - stack.peek() - 1)]);
            }
            stack.push(i);
        }

        while (stack.peek() != -1) {
            maxArea = Math.max(maxArea, heights[stack.peek()] * (stack.pop() - stack.peek() - 1));
        }

        return maxArea;
    }

    public static void main(String[] args) {
        int[] heights = {2, 1, 5, 6, 2, 3};
        System.out.println(largestRectangleArea0(heights));
    }


}
