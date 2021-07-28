/*
 * Copyright 2018-2020, 上海哈里奥科技有限公司
 * All Right Reserved.
 */

package com.demo.Geek.c4_stack_queue;

import java.util.Deque;
import java.util.LinkedList;

public class LeetCode239 {// LeetCode：T239

    /**
     * 滑动窗口最大值
     * <p>
     * 给定一个数组 nums，有一个大小为 k 的滑动窗口从数组的最左侧移动到数组的最右侧。你只可以看到在滑动窗口内的 k 个数字。滑动窗口每次只向右移动一位。
     * 返回滑动窗口中的最大值。
     * <p>
     * 进阶：
     * 你能在线性时间复杂度内解决此题吗？
     * <p>
     * 示例:
     * 输入: nums = [1,3,-1,-3,5,3,6,7], 和 k = 3
     * 输出: [3,3,5,5,6,7]
     * 解释:
     * <p>
     * 滑动窗口的位置                最大值
     * ---------------               -----
     * [1  3  -1] -3  5  3  6  7       3
     * 1 [3  -1  -3] 5  3  6  7       3
     * 1  3 [-1  -3  5] 3  6  7       5
     * 1  3  -1 [-3  5  3] 6  7       5
     * 1  3  -1  -3 [5  3  6] 7       6
     * 1  3  -1  -3  5 [3  6  7]      7
     *  
     * <p>
     * 提示：
     * 1 <= nums.length <= 10^5
     * -10^4 <= nums[i] <= 10^4
     * 1 <= k <= nums.length
     */

    public static int[] maxSlidingWindow01(int[] nums, int k) {  // 双端队列处理    O（n）
        //        if (nums == null || nums.length == 0 || k <= 0 || nums.length < k) {
        //            return new int[nums.length -k + 1];
        //        }

        Deque<Integer> deque = new LinkedList<>();
        int[] res = new int[nums.length - k + 1];
        int j = 0;
        for (int i = 0; i < nums.length; i++) {
            // 1.每循环一个i，保证从队尾进去的值 > 队尾的最后一个值                    -----> 保证队首的值最大
            while (!deque.isEmpty() && nums[i] > nums[deque.peekLast()]) { // eg: 3   4 6 7
                // 不然从队尾出来
                deque.pollLast();
            }
            // 2.每个值都得从队尾进去
            deque.addLast(i);
            // 3.判断队首是否大于窗口大小
            if (deque.peekFirst() == i - k) {
                deque.pollFirst();
            }
            // 4.给新数组存值
            if (i >= k - 1) {
                res[j++] = nums[deque.peekFirst()];
            }
        }
        return res;
    }

    public static int[] maxSlidingWindow02(int[] nums, int k) {  // 暴力    O（n*k）
        int[] res = new int[nums.length - k + 1];
        for (int i = 0; i < nums.length - k + 1; i++) {
            int max = nums[i];
            for (int j = i; j < i + k - 1; j++) {
                max = max > nums[j] ? max : nums[j];
            }
            res[i] = max;
        }
        return res;
    }

    public static void main(String[] args) {
        int[] nums = {1, 3, -1, -3, 5, 3, 6, 7};

        int[] ints01 = maxSlidingWindow01(nums, 3);
        for (int i = 0; i < ints01.length; i++) {
            System.out.println(ints01[i]);
        }

        System.out.println("------------------------>");

        int[] ints02 = maxSlidingWindow02(nums, 3);
        for (int i = 0; i < ints02.length; i++) {
            System.out.println(ints02[i]);
        }
    }

}
